package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MPriceList;
import org.compiere.model.MSysConfig;
import org.compiere.process.DocAction;
import org.compiere.util.Env;

public class MCrusherKatingEntry extends X_TF_CrusherKatingEntry {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6641225223046443500L;

	public MCrusherKatingEntry(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCrusherKatingEntry(Properties ctx, int TF_CrusherKatingEntry_ID, String trxName) {
		super(ctx, TF_CrusherKatingEntry_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public void processIt(String docAction) {
		if(DocAction.ACTION_Prepare.equals(docAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(DocAction.ACTION_Complete.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			
			//Transporter Invoice
			MRentedVehicle vehicle = new MRentedVehicle(getCtx(), getTF_RentedVehicle_ID(), get_TrxName());
			MBPartner bp = new MBPartner(getCtx(), vehicle.getC_BPartner_ID(), get_TrxName());			
			//Invoice Header
			TF_MInvoice invoice = new TF_MInvoice(getCtx(), 0, get_TrxName());
			invoice.setClientOrg(getAD_Client_ID(), getAD_Org_ID());			
			invoice.setC_DocTypeTarget_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getTransporterInvoiceDocType_ID());
			
			invoice.setDateInvoiced(getDateAcct());
			invoice.setDateAcct(getDateAcct());
			//
			invoice.setSalesRep_ID(Env.getAD_User_ID(getCtx()));
			//
			invoice.setBPartner(bp);
			invoice.setIsSOTrx(false);		
			invoice.setDescription(getDocumentNo());
			if(getDescription() != null && getDescription().length() > 0)
				invoice.addDescription(getDescription());
			
			//Price List
			int m_M_PriceList_ID = Env.getContextAsInt(getCtx(), "#M_PriceList_ID");
			if(bp.getPO_PriceList_ID() > 0)
				m_M_PriceList_ID = bp.getPO_PriceList_ID();
			
			if(m_M_PriceList_ID == 0) {
				m_M_PriceList_ID = MPriceList.getDefault(getCtx(), false).getM_PriceList_ID();
			}
				
			
			invoice.setM_PriceList_ID(m_M_PriceList_ID);
			invoice.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
			
			//Financial Dimension - Profit Center
			invoice.setC_Project_ID(getC_Project_ID());
			
			invoice.saveEx();
			//End Invoice Header
			
			//Invoice Line - Vehicle Rental Charge
			MInvoiceLine invLine = new MInvoiceLine(invoice);
			invLine.setM_Product_ID(vehicle.getM_Product_ID(), true);
			invLine.setDescription("Transportation Charge for Kating");
			
			int load_uom_id = MSysConfig.getIntValue("LOAD_UOM", 1000072, getAD_Client_ID());
			int tonnage_uom_Id = MSysConfig.getIntValue("TONNAGE_UOM", 1000069, getAD_Client_ID());
			
			int uom_id = tonnage_uom_Id;
			BigDecimal qty = getTonnage(); 
			if(KATINGENTRYTYPE_Load.equals(getKatingEntryType())) {
				uom_id = load_uom_id;
				qty = getTotalLoad();
			}
			
			invLine.setC_UOM_ID(uom_id);
			invLine.setQty(qty);			
			invLine.setPriceActual(getTransport_Price());
			invLine.setPriceList(getTransport_Price());
			invLine.setPriceLimit(getTransport_Price());
			invLine.setPriceEntered(getTransport_Price());			
			invLine.saveEx();
			
						
			//DocAction
			if (!invoice.processIt(docAction))
				throw new AdempiereException("Failed when processing document - " + invoice.getProcessMsg());
			invoice.saveEx();
			//End DocAction
			
			setTransporterInvoice_ID(invoice.getC_Invoice_ID());
			
			
			//--------------------------------------
			
			// Loader invoice
			if(getLoaderVehicle_ID() > 0) {
				vehicle = new MRentedVehicle(getCtx(), getLoaderVehicle_ID(), get_TrxName());
				bp = new MBPartner(getCtx(), vehicle.getC_BPartner_ID(), get_TrxName());			
				//Invoice Header
				invoice = new TF_MInvoice(getCtx(), 0, get_TrxName());
				invoice.setClientOrg(getAD_Client_ID(), getAD_Org_ID());			
				invoice.setC_DocTypeTarget_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getTransporterInvoiceDocType_ID());
				
				invoice.setDateInvoiced(getDateAcct());
				invoice.setDateAcct(getDateAcct());
				//
				invoice.setSalesRep_ID(Env.getAD_User_ID(getCtx()));
				//
				invoice.setBPartner(bp);
				invoice.setIsSOTrx(false);		
				invoice.setDescription(getDocumentNo());
				if(getDescription() != null && getDescription().length() > 0)
					invoice.addDescription(getDescription());
				
				//Price List
				 m_M_PriceList_ID = Env.getContextAsInt(getCtx(), "#M_PriceList_ID");
				if(bp.getPO_PriceList_ID() > 0)
					m_M_PriceList_ID = bp.getPO_PriceList_ID();
				
				if(m_M_PriceList_ID == 0) {
					m_M_PriceList_ID = MPriceList.getDefault(getCtx(), false).getM_PriceList_ID();
				}
				
				invoice.setM_PriceList_ID(m_M_PriceList_ID);
				invoice.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
				
				//Financial Dimension - Profit Center
				invoice.setC_Project_ID(getC_Project_ID());
				
				invoice.saveEx();
				//End Invoice Header
				
				//Invoice Line - Vehicle Rental Charge
				invLine = new MInvoiceLine(invoice);
				invLine.setM_Product_ID(vehicle.getM_Product_ID(), true);
				invLine.setDescription("Loading Charge for Kating");
				invLine.setC_UOM_ID(uom_id);
				invLine.setQty(qty);			
				invLine.setPriceActual(getLoading_Price());
				invLine.setPriceList(getLoading_Price());
				invLine.setPriceLimit(getLoading_Price());
				invLine.setPriceEntered(getLoading_Price());			
				invLine.saveEx();
				
							
				//DocAction
				if (!invoice.processIt(docAction))
					throw new AdempiereException("Failed when processing document - " + invoice.getProcessMsg());
				invoice.saveEx();
				//End DocAction
				
				setLoaderInvoice_ID(invoice.getC_Invoice_ID());
			}
			MWeighmentEntry wEntry = (MWeighmentEntry) getTF_WeighmentEntry();
			if(wEntry != null) {				
				wEntry.close();
				wEntry.saveEx();
			}
		}
	}
	
	public void reverseIt() {
		if(getTransporterInvoice_ID() > 0 ) {
			TF_MInvoice inv = new TF_MInvoice(getCtx(), getTransporterInvoice_ID(), get_TrxName());
			if(inv.getDocStatus().equals(DocAction.ACTION_Complete))
				inv.reverseCorrectIt();
			inv.saveEx();
			setTransporterInvoice_ID(0);
		}
		if(getLoaderInvoice_ID() > 0 ) {
			TF_MInvoice inv = new TF_MInvoice(getCtx(), getLoaderInvoice_ID(), get_TrxName());
			if(inv.getDocStatus().equals(DocAction.ACTION_Complete))
				inv.reverseCorrectIt();
			inv.saveEx();
			setLoaderInvoice_ID(0);
		}
		setDocStatus(DOCSTATUS_InProgress);
		setProcessed(false);
		MWeighmentEntry wEntry = (MWeighmentEntry) getTF_WeighmentEntry();
		if(wEntry != null) {				
			wEntry.reverse();
			wEntry.saveEx();
		}
	}

}
