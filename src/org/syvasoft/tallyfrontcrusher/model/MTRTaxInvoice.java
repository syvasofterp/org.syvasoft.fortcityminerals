package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MPriceList;
import org.compiere.model.MSequence;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.Env;


public class MTRTaxInvoice extends X_TF_TRTaxInvoice {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3112068874449361280L;

	public MTRTaxInvoice(Properties ctx, int TF_TRTaxInvoice_ID, String trxName) {
		super(ctx, TF_TRTaxInvoice_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MTRTaxInvoice(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public void reverseIt() {
		setProcessed(false);
		setDocStatus(DOCSTATUS_Drafted);
		
		//MOAInventoryTransaction.deleteSalesTaxInvoiceTransactions(getCtx(), getTF_TRTaxInvoice_ID(), get_TrxName());
		
		if(getC_Invoice_ID() > 0) {
			TF_MInvoice inv = new TF_MInvoice(getCtx(), getC_Invoice_ID(), get_TrxName());
			if(inv.getDocStatus().equals(TF_MInvoice.DOCSTATUS_Completed)) {
				if (!inv.processIt(DocAction.ACTION_Reverse_Correct))
					throw new AdempiereException("Failed when processing document - " + inv.getProcessMsg());
				inv.saveEx();
			}
			setC_Invoice_ID(0);
		}
	}

	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(newRecord) {
			MDocType[] dts = MDocType.getOfDocBaseType(getCtx(), "TIN");
			for(MDocType dt : dts) {
				if(dt.isSOTrx() && isSOTrx()) 
					setC_DocType_ID(dt.getC_DocType_ID());
				else if(!dt.isSOTrx() && !isSOTrx())
					setC_DocType_ID(dt.getC_DocType_ID());
			}
		}
		return super.beforeSave(newRecord);
	}
	
	public void processIt(String docAction) {
		if(DocAction.ACTION_Prepare.equals(docAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(DocAction.ACTION_Complete.equals(docAction)) {
		
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			
			List<MTRTaxInvoiceLine> lines = new Query(getCtx(), MTRTaxInvoiceLine.Table_Name,
					"TF_TRTaxInvoice_ID = ?", get_TrxName())
					.setClient_ID()
					.setParameters(getTF_TRTaxInvoice_ID())
					.list();
			/* Disabling On Accounting Inventory Transaction
			for(MTRTaxInvoiceLine line : lines) {
				MOAInventoryTransaction trans = new MOAInventoryTransaction(getCtx(), 0, get_TrxName());
				trans.setAD_Org_ID(getAD_Org_ID());
				trans.setMovementDate(getDateAcct());
				trans.setTF_SubOrg_ID(getTF_SubOrg_ID());
				trans.setM_Warehouse_ID(getM_Warehouse_ID());
				trans.setM_Product_ID(line.getM_Product_ID());			
				trans.setQtyReceived(BigDecimal.ZERO);
				trans.setQtyIssued(line.getQty());
				trans.setTF_TRTaxInvoice_ID(getTF_TRTaxInvoice_ID());
				trans.setTF_TRTaxInvoiceLine_ID(line.getTF_TRTaxInvoiceLine_ID());
				trans.saveEx();
			}
			*/
			// Post GST to Supplier
			MGLPostingConfig config = MGLPostingConfig.getMGLPostingConfig(getCtx());
			//Invoice Header
			TF_MInvoice invoice = new TF_MInvoice(getCtx(), 0, get_TrxName());
			invoice.setClientOrg(getAD_Client_ID(), getAD_Org_ID());
			invoice.setC_DocTypeTarget_ID(config.getDefaultSalesInvoiceDocType_ID());
			invoice.setDateInvoiced(getDateAcct());
			invoice.setDateAcct(getDateAcct());
			//
			invoice.setSalesRep_ID(Env.getAD_User_ID(getCtx()));
			//
			TF_MBPartner bp = new TF_MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
			invoice.setBPartner(bp);
			invoice.setIsSOTrx(true);		
			
			
			//Price List
			int m_M_PriceList_ID = Env.getContextAsInt(getCtx(), "#M_PriceList_ID");
			if(bp.getM_PriceList_ID() > 0)
				m_M_PriceList_ID = bp.getM_PriceList_ID();			
			invoice.setM_PriceList_ID(m_M_PriceList_ID);
			invoice.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
			invoice.setDescription("Tax Invoice: " + getDocumentNo());
			invoice.saveEx();
			//End Invoice Header
			
			//Invoice Line
			MInvoiceLine invLine = null;
			if(isPostTaxToCustomer()) {
				for(MTRTaxInvoiceLine line : lines) {
					if(line.getCGST_Rate().doubleValue() > 0 || line.getSGST_Rate().doubleValue() > 0) {
						//CGST
						invLine = new MInvoiceLine(invoice);
						TF_MCharge chrg = TF_MCharge.createChargeFromAccount(getCtx(), config.getC_ElementValueCGST_ID(), null);
						invLine.setC_Charge_ID(chrg.getC_Charge_ID());
						invLine.setC_Tax_ID(1000000);
						invLine.setQty(BigDecimal.ONE);			
						invLine.setPrice(line.getCGST_Amt());
						invLine.setDescription("CGST " +  line.getCGST_Rate() + "% of Rs." + line.getTaxableAmount());
						invLine.saveEx();
						
						//SGST
						invLine = new MInvoiceLine(invoice);
						chrg = TF_MCharge.createChargeFromAccount(getCtx(), config.getC_ElementValueSGST_ID(), null);
						invLine.setC_Charge_ID(chrg.getC_Charge_ID());
						invLine.setC_Tax_ID(1000000);
						invLine.setQty(BigDecimal.ONE);			
						invLine.setPrice(line.getSGST_Amt());
						invLine.setDescription("SGST " +  line.getSGST_Rate() + "% of Rs." + line.getTaxableAmount());
						invLine.saveEx();				
					}
					else {
						//IGST
						invLine = new MInvoiceLine(invoice);
						TF_MCharge chrg = TF_MCharge.createChargeFromAccount(getCtx(), config.getC_ElementValueIGST_ID(), null);
						invLine.setC_Charge_ID(chrg.getC_Charge_ID());
						invLine.setC_Tax_ID(1000000);
						invLine.setQty(BigDecimal.ONE);			
						invLine.setPrice(line.getIGST_Amt());
						invLine.setDescription("IGST " +  line.getIGST_Rate() + "% of Rs." + line.getTaxableAmount());
						invLine.saveEx();
					}
				}
				
				//End Invoice Line
				//DocAction
				if (!invoice.processIt(DocAction.ACTION_Complete))
					throw new AdempiereException("Failed when processing document - " + invoice.getProcessMsg());
				invoice.saveEx();
				
				setC_Invoice_ID(invoice.getC_Invoice_ID());
								
			}
			
		}
	}


}
