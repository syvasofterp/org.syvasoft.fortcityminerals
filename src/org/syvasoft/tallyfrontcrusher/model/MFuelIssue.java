package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MBPartner;
import org.compiere.model.MClient;
import org.compiere.model.MCost;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
import org.compiere.model.MResource;
import org.compiere.model.MSysConfig;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class MFuelIssue extends X_TF_Fuel_Issue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3927519272739123306L;
	public MFuelIssue(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public MFuelIssue(Properties ctx, int TF_Fuel_Issue_ID, String trxName) {
		super(ctx, TF_Fuel_Issue_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		
		MResource resource  = (MResource)getVehicle().getS_Resource();		
		setC_ElementValue_ID(resource.get_ValueAsInt(COLUMNNAME_C_ElementValue_ID));
		
		//Set Costing
		//MProduct product = MProduct.get(getCtx(), getM_Product_ID());
		//MClient client = MClient.get(getCtx());
		//MAcctSchema as = client.getAcctSchema();
		//String costingMethod = product.getCostingMethod(as);		
		//MCost cost = product.getCostingRecord(as, getAD_Org_ID(), 0, costingMethod);
		//if(cost == null)
		//	throw new AdempiereException("No Costing Info for : "  + product.getName());
		//setRate(cost.getCurrentCostPrice());
		
		//if(isCalculated()) {			
			setAmt(getQty().multiply(getRate()));
		//}
		
		//if(is_ValueChanged(COLUMNNAME_M_Product_ID) || is_ValueChanged(COLUMNNAME_Qty)) {
			MWarehouse wh = (MWarehouse) getM_Warehouse();
			
			String sql = " SELECT " +
								" bomQtyOnHand(" + getM_Product_ID() + "," + getM_Warehouse_ID() + " ,0) - " + 
								" bomQtyReserved(" + getM_Product_ID() + "," + getM_Warehouse_ID() + " ,0) - " +
								" prodQtyReserved(" + getM_Product_ID() + "," + getM_Warehouse_ID() + " ) " +							 
							" FROM " + 
								" M_Locator l " + 
							" WHERE l.M_Locator_ID=" + wh.getDefaultLocator().get_ID();
			BigDecimal qtyAvailable = DB.getSQLValueBD(null, sql);
			if(qtyAvailable.doubleValue() < getQty().doubleValue()) {
				log.saveError("NotEnoughStocked", Msg.getElement(getCtx(), COLUMNNAME_Qty));
				return false;
			}
		//}
		return super.beforeSave(newRecord);
	}
	
	public void processIt(String docAction) {
		if(DocAction.ACTION_Prepare.equals(docAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(DocAction.ACTION_Complete.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			
			//Post Inventory Use Inventory for Fuel Expense.
			MWarehouse wh = (MWarehouse) getM_Warehouse();
			//Inventory Use Header
			MInventory inv = new MInventory(wh, get_TrxName());
			inv.setC_DocType_ID(1000026);
			String desc = "Diesel Issued to " +  getVehicle().getName(); 
			inv.setDescription(desc);
			inv.setMovementDate(getDateAcct());
			inv.setUser1_ID(getC_ElementValue_ID());			
			inv.setDocStatus(DOCSTATUS_Drafted);
			inv.saveEx();
			
			//Inventory Use Line
			MInventoryLine line = new MInventoryLine(inv, wh.getDefaultLocator().get_ID(), getM_Product_ID(), 0, null, null, getQty());
			line.setC_Charge_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getFuelExpense_Charge_ID());
			line.setDescription(desc);
			//line.setCurrentCostPrice(getRate());
			line.saveEx();
			
			//Complete Inventory Use Document
			inv.processIt(docAction);
			inv.saveEx();
			
			//Update Inventory Use ID back to Fuel Issue Entry.
			setM_Inventory_ID(inv.getM_Inventory_ID());	
			
			createDebitNote();			
		}
	}
	
	private void createDebitNote() {
		MRentedVehicle rv = new Query(getCtx(), MRentedVehicle.Table_Name, "M_Product_ID=?", get_TrxName())
				.setParameters(getVehicle_ID()).first();
		if(rv != null) {
			//Invoice Header
			TF_MInvoice invoice = new TF_MInvoice(getCtx(), 0, get_TrxName());
			invoice.setClientOrg(getAD_Client_ID(), getAD_Org_ID());
			invoice.setC_DocTypeTarget_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getDebitNote_DocType_ID());			
			invoice.setDateInvoiced(getDateAcct());
			invoice.setDateAcct(getDateAcct());
			//
			invoice.setSalesRep_ID(Env.getAD_User_ID(getCtx()));
			//
			TF_MBPartner bp = new TF_MBPartner(getCtx(), rv.getC_BPartner_ID(), get_TrxName());
			invoice.setBPartner(bp);
			invoice.setIsSOTrx(false);		
			
			invoice.setDescription("Ref: Fuel Issue Entry : " + getDocumentNo());
			
			//Price List
			int m_M_PriceList_ID = Env.getContextAsInt(getCtx(), "#M_PriceList_ID");
			if(bp.getPO_PriceList_ID() > 0)
				m_M_PriceList_ID = bp.getPO_PriceList_ID();			
			invoice.setM_PriceList_ID(m_M_PriceList_ID);
			invoice.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
			
			//Financial Dimension - Profit Center
			invoice.setUser1_ID(getC_ElementValue_ID());
			
			invoice.saveEx();
			//End Invoice Header
			
			//Invoice Line - Vehicle Rental Charge
			MInvoiceLine invLine = new MInvoiceLine(invoice);
			invLine.setM_Product_ID(getM_Product_ID(), true);
			invLine.setDescription("Diesel Issued to " + rv.getVehicleNo());
			
			
			invLine.setQty(getQty());
			BigDecimal price = getRate();			
			
			invLine.setPriceActual(price);
			invLine.setPriceList(price);
			invLine.setPriceLimit(price);
			invLine.setPriceEntered(price);
				
			invLine.saveEx();
						
						
			//DocAction
			if (!invoice.processIt(DocAction.ACTION_Complete))
				throw new AdempiereException("Failed when processing document - " + invoice.getProcessMsg());
			invoice.saveEx();
			//End DocAction
			
			setDebitNote_Invoice_ID(invoice.getC_Invoice_ID());			
		}
	}
	
	public void reverseIt() {
		if(getDebitNote_Invoice_ID() > 0) {			
			TF_MInvoice inv = new TF_MInvoice(getCtx(), getDebitNote_Invoice_ID(), get_TrxName());			
			inv.reverseCorrectIt();
			inv.saveEx();
			setDebitNote_Invoice_ID(0);
		}
		if(getM_Inventory_ID() > 0) {
			MInventory inv = new MInventory(getCtx(), getM_Inventory_ID(), get_TrxName());
			if(!inv.getDocStatus().equals(DOCSTATUS_Reversed)) {
				inv.reverseCorrectIt();
				inv.saveEx();
			}
			setQty(BigDecimal.ZERO);
			setM_Inventory_ID(0);
			setProcessed(false);
			setDocStatus(DOCSTATUS_Drafted);
		}		
	}
}
