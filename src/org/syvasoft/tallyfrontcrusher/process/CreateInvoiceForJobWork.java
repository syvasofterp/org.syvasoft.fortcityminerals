package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MPriceList;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MJobworkCharges;
import org.syvasoft.tallyfrontcrusher.model.MJobworkExpense;
import org.syvasoft.tallyfrontcrusher.model.MJobworkIssuedItems;
import org.syvasoft.tallyfrontcrusher.model.MJobworkIssuedResource;
import org.syvasoft.tallyfrontcrusher.model.MJobworkProductPrice;
import org.syvasoft.tallyfrontcrusher.model.TF_MCharge;
import org.syvasoft.tallyfrontcrusher.model.TF_MInvoice;
import org.syvasoft.tallyfrontcrusher.model.TF_MProject;

public class CreateInvoiceForJobWork extends SvrProcess {

	private int recordID = 0;
	private int m_AD_Client_ID = 0;
	private int m_AD_Org_ID = 0;
	private Timestamp m_DateInvoiced = null;
	private String m_DocAction = "PR";
	private int m_C_DocTypeTarget_ID = 0;
	private BigDecimal jobWorkUnitPrice = BigDecimal.ZERO;
	
	@Override
	protected void prepare() {
		recordID = getRecord_ID();
		ProcessInfoParameter[] para = getParameter();
		m_AD_Client_ID = Env.getAD_Client_ID(getCtx());
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();			
			if (name.equals("AD_Org_ID"))				
				m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DateInvoiced")) 
				m_DateInvoiced = para[i].getParameterAsTimestamp();			
			else if (name.equals("Unit_Price")) 
				jobWorkUnitPrice = para[i].getParameterAsBigDecimal();
			else if (name.equals("DocAction"))
				m_DocAction = para[i].getParameterAsString();
			else if (name.equals("C_DocTypeTarget_ID")) 
				m_C_DocTypeTarget_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception {
		String msg = null;
		TF_MProject jobWork = new TF_MProject(getCtx(), recordID, get_TrxName());
		
		BigDecimal invQty = jobWork.getQtyProcessed().subtract(jobWork.getInvoicedQty());
		
		if(invQty.doubleValue() > 0) {
			MBPartner bp = new MBPartner(getCtx(), jobWork.getC_BPartner_ID(), get_TrxName());

			//Invoice Header
			TF_MInvoice invoice = new TF_MInvoice(getCtx(), 0, get_TrxName());
			invoice.setClientOrg(m_AD_Client_ID, m_AD_Org_ID);
			invoice.setC_DocTypeTarget_ID(m_C_DocTypeTarget_ID);			
			invoice.setDateInvoiced(m_DateInvoiced);
			invoice.setDateAcct(m_DateInvoiced);
			//
			invoice.setSalesRep_ID(Env.getAD_User_ID(getCtx()));
			//
			invoice.setBPartner(bp);
			invoice.setIsSOTrx(false);			
			invoice.setC_Project_ID(jobWork.getC_Project_ID());
			
			//Price List
			int m_M_PriceList_ID = Env.getContextAsInt(getCtx(), "#M_PriceList_ID");
			if(bp.getPO_PriceList_ID() > 0)
				m_M_PriceList_ID = bp.getPO_PriceList_ID();			
			invoice.setM_PriceList_ID(m_M_PriceList_ID);
			invoice.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
			
			//TODO:Finacial Dimension - Profit Center. It should be filled based on the Quarry
			
			invoice.saveEx();
			//End Invoice Header
			
			//Invoice Line - Jobwork
			MInvoiceLine invLine = new MInvoiceLine(invoice);
			invLine.setM_Product_ID(jobWork.getJobWork_Product_ID(), jobWork.getC_UOM_ID());
			invLine.setQty(invQty);
			invLine.setDescription("Jobwork");
			invLine.setPriceActual(jobWorkUnitPrice);
			invLine.setPriceList(jobWorkUnitPrice);
			invLine.setPriceLimit(jobWorkUnitPrice);
			invLine.setPriceEntered(jobWorkUnitPrice);
			invLine.saveEx();
			//Add Qty Invoiced to JobWork
			jobWork.setInvoicedQty(jobWork.getInvoicedQty().add(invQty));
			jobWork.setInvoicedAmt(jobWork.getInvoicedAmt().add(invLine.getLineNetAmt()));
			//End Invoice Line
			
			
			//Invoice Lines - Issued Items to be deducted
			for(MJobworkIssuedItems issuedItem : MJobworkIssuedItems.getIssuedItemsToDeduct(getCtx(), jobWork.getC_Project_ID())) {
				BigDecimal qtyDeduct = issuedItem.getQtyIssued().subtract(issuedItem.getQtyDeducted());
				BigDecimal price = MJobworkProductPrice.getPrice(getCtx(), jobWork.getC_Project_ID(), issuedItem.getM_Product_ID());
				
				if(price == null)
					throw new AdempiereUserError("Please specify Contract Price for " + issuedItem.getM_Product().getName());
				
				invLine = new MInvoiceLine(invoice);
				invLine.setM_Product_ID(issuedItem.getM_Product_ID(), issuedItem.getC_UOM_ID());
				invLine.setQty(qtyDeduct.negate());
				invLine.setDescription("Issued Item");
				invLine.setPriceActual(price);
				invLine.setPriceList(price);
				invLine.setPriceLimit(price);
				invLine.setPriceEntered(price);
				invLine.saveEx();
				
				//Add Deducted Qty to Jobwork Issued Item
				issuedItem.setQtyDeducted(issuedItem.getQtyDeducted().add(qtyDeduct));
				issuedItem.saveEx();
				
			}
			//End Invoice Line
			
			//Invoice Lines - Vehicle Rent to be deducted
			for(MJobworkIssuedResource res : MJobworkIssuedResource.getVehicleRentToDeduct(getCtx(), jobWork.getC_Project_ID())) {
				BigDecimal qtyDeduct = res.getQty().subtract(res.getQtyDeducted());
				BigDecimal price = res.getUnit_Price();
				
				invLine = new MInvoiceLine(invoice);
				invLine.setM_Product_ID(res.getM_Product_ID(), true);
				invLine.setQty(qtyDeduct.negate());
				invLine.setDescription("Issued Vehicle");
				invLine.setPriceActual(price);
				invLine.setPriceList(price);
				invLine.setPriceLimit(price);
				invLine.setPriceEntered(price);
				
				//Set UOM for ContractBase
				String sql = "SELECT C_UOM_ID FROM C_UOM WHERE ContractBase=? AND AD_Client_ID IN (0,?) AND IsActive='Y'";
				int uomID = DB.getSQLValue(null, sql, res.getContractBase() ,m_AD_Client_ID);
				invLine.setC_UOM_ID(uomID);
								
				invLine.saveEx();
				
				//Add Deducted Qty to Jobwork issued vehicle
				res.setQtyDeducted(res.getQtyDeducted().add(qtyDeduct));
				res.setDeductedAmt(res.getDeductedAmt().add(price.multiply(qtyDeduct)));
				
				//Deduct Operator Wage
				if(!res.isOperatorWageIncluded() && res.getOperatorTotalWage().doubleValue() > res.getOperatorDeductedWage().doubleValue()) {
					BigDecimal wageDeduct = res.getOperatorTotalWage().subtract(res.getOperatorDeductedWage());
					invLine = new MInvoiceLine(invoice);
					invLine.setC_Charge_ID(res.getWage_Charge_ID());
					invLine.setDescription("Operator Wage for " + res.getM_Product().getName());
					invLine.setQty(BigDecimal.ONE.negate());
					invLine.setPrice(wageDeduct);
					invLine.saveEx();
					
					//Add Deducted wage to Jobwork Issued Vehicle
					res.setOperatorDeductedWage(res.getOperatorDeductedWage().add(wageDeduct));					
				}
				res.saveEx();
			}
			// End Invoice Line
			
			//Invoice Lines - Additional Charges
			for(MJobworkCharges charge : MJobworkCharges.getChargesToDeduct(getCtx(), jobWork.getC_Project_ID())) {
				BigDecimal deductAmt = charge.getTotalAmt().subtract(charge.getDeductedAmt());
				invLine = new MInvoiceLine(invoice);
				invLine.setC_Charge_ID(charge.getC_Charge_ID());
				invLine.setDescription("Additional Charge / Advance" );
				invLine.setQty(BigDecimal.ONE.negate());
				invLine.setPrice(deductAmt);
				invLine.saveEx();
				
				//Add Deducted Amount to Jobwork Additonal Charge
				charge.setDeductedAmt(charge.getDeductedAmt().add(deductAmt));
				charge.saveEx();
			}
			//End Invoice Lines
			
			// Invoice Lines - Expenses
			for(MJobworkExpense exp : MJobworkExpense.getExpensesToDeduct(getCtx(), jobWork.getC_Project_ID())) {
				BigDecimal deductAmt = exp.getTotalAmt().subtract(exp.getDeductedAmt());
				invLine = new MInvoiceLine(invoice);
				TF_MCharge chrg = TF_MCharge.createChargeFromAccount(getCtx(), exp.getC_ElementValue_ID(), null);
				invLine.setC_Charge_ID(chrg.getC_Charge_ID());
				invLine.setDescription("Expense Deducted");
				invLine.setQty(BigDecimal.ONE.negate());
				invLine.setPrice(deductAmt);
				invLine.saveEx();
				
				//Add Deducted Amount to Jobwork Expense
				exp.setDeductedAmt(exp.getDeductedAmt().add(deductAmt));
				exp.saveEx();
			}
			
			
			//Update back Invoice ID to Boulder Receipts.
			String whereClause = " AD_Org_ID = ? AND Subcontractor_ID=? " + 
					" AND DocStatus='CO' AND Processed='Y' AND Subcon_Invoice_ID IS NULL " ;
			String sql = " Update TF_Boulder_Receipt SET Subcon_Invoice_ID = ? WHERE " + whereClause;
			ArrayList<Object> params = new ArrayList<Object>();
			params.add(invoice.getC_Invoice_ID());
			params.add(m_AD_Org_ID);
			params.add(jobWork.getC_BPartner_ID());			
			DB.executeUpdateEx(sql, params.toArray(), get_TrxName());
			//End Update
			
			//DocAction
			if (!invoice.processIt(m_DocAction))
				throw new AdempiereException("Failed when processing document - " + invoice.getProcessMsg());
			invoice.saveEx();
			//End DocAction
			
			jobWork.saveEx();
			
			addLog(0, null, null, "Invoice No : " + invoice.getDocumentNo() , MInvoice.Table_ID,invoice.getC_Invoice_ID());
			
		}
		else {
			msg = " This Subcontract is already invoiced completely!";
		}
		return msg;
	}

}
