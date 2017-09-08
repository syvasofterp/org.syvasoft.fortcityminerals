package org.syvasoft.tallyfrontcrusher.model;



import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCostDetail;
import org.compiere.model.MInvoice;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalLine;
import org.compiere.model.MPeriod;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProductPricing;
import org.compiere.model.MStorageOnHand;
import org.compiere.model.MTransaction;
import org.compiere.model.MWarehouse;
import org.compiere.model.MCost.QtyCost;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MBoulderReceipt extends X_TF_Boulder_Receipt {

		
	/**
	 * 
	 */
	private static final long serialVersionUID = -6229740359935434019L;

	public MBoulderReceipt(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MBoulderReceipt(Properties ctx, int TF_Boulder_Receipt_ID,
			String trxName) {
		super(ctx, TF_Boulder_Receipt_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(is_ValueChanged(COLUMNNAME_Subcontractor_ID)) {			
			int priceList_ID = getSubcontractor().getPO_PriceList_ID();
			if(priceList_ID == 0)
				log.saveError("Error", "Please configure default Purchase Price List for Subcontractor!");
				
			setPO_PriceList_ID(priceList_ID);
			
			//Get Unit Price from Latest Price List.
			String sql = "SELECT plv.M_PriceList_Version_ID "
					+ "FROM M_PriceList_Version plv "
					+ "WHERE plv.M_PriceList_ID=? "	
					+ " AND plv.ValidFrom <= ? "
					+ "ORDER BY plv.ValidFrom DESC";
			int M_PriceList_Version_ID = DB.getSQLValueEx(null, sql, getPO_PriceList_ID(), getDateReceipt());
			MProductPricing pp = new MProductPricing (getJobWork_Product_ID(), getSubcontractor_ID(), getQtyReceived(), false);
			pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
			pp.setPriceDate(getDateReceipt());
			
			BigDecimal priceStd = pp.getPriceStd();
			if(priceStd == null) {
				MPriceListVersion plv = new MPriceListVersion(getCtx(), M_PriceList_Version_ID, get_TrxName());
				log.saveError("Error", "Please configure price for " + getJobWork_Product().getName() + " in Price List Version:" + plv.getName());
			}
			
			setJobwork_StdPrice(priceStd);
			
		}
				
		return super.beforeSave(newRecord);
	}
	
	public String processIt(String DocAction) {
		String m_processMsg = null;
		if(MBoulderReceipt.DOCACTION_Prepare.equals(DocAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(MBoulderReceipt.DOCACTION_Complete.equals(DocAction)) {
			MWarehouse warehouse = MWarehouse.get(getCtx(), getM_Warehouse_ID());
			int defaultLocatorID = warehouse.getDefaultLocator().getM_Locator_ID();
			
			//Update Storage for the received Product from the Joborder.
			if (!MStorageOnHand.add(getCtx(), getM_Warehouse_ID(),
					defaultLocatorID,
					getM_Product_ID(),
					0,
					getQtyReceived(),getDateAcct(),
					get_TrxName()))
				{
					String lastError = CLogger.retrieveErrorString("");
					m_processMsg = "Cannot correct Inventory OnHand (MA) [" + getM_Product().getValue() + "] - " + lastError;				
					return m_processMsg;
				}
			//Update Transaction History
			MTransaction mtrx = new MTransaction (getCtx(), getAD_Org_ID(),
				"J+", defaultLocatorID,
				getM_Product_ID(), 0,
				getQtyReceived(), getDateAcct(), get_TrxName());
			mtrx.set_ValueOfColumn(MBoulderReceipt.COLUMNNAME_TF_Boulder_Receipt_ID, getTF_Boulder_Receipt_ID());
			if (!mtrx.save())
			{
				m_processMsg = "Could not create Material Transaction (MA) [" + getM_Product().getValue() + "]";			
				return m_processMsg;
			}
			
			//Update Costing Record...
			MAcctSchema as = (MAcctSchema) MGLPostingConfig.getMGLPostingConfig(getCtx()).getC_AcctSchema();			
			MCostDetail.createBoulderReceipt(as, getAD_Org_ID(), getM_Product_ID(), 0, getTF_Boulder_Receipt_ID()
					, 0, getJobwork_StdPrice().multiply(getQtyReceived()), getQtyReceived(), getDescription(), get_TrxName());
			
			//Posting GL journal for Jobwork expense 
			MJournal j = new MJournal(getCtx(), 0, get_TrxName());
			j.setDescription("Generated Jobwork Expense Journal Entry from Boulder Receipt - " + getDocumentNo());
			j.setC_AcctSchema_ID(Env.getContextAsInt(getCtx(), "$C_AcctSchema_ID"));
			j.setC_Currency_ID(Env.getContextAsInt(getCtx(), "$C_Currency_ID"));
			j.setPostingType(MJournal.POSTINGTYPE_Actual);
			j.setC_DocType_ID(1000000);
			j.setDateDoc(getDateAcct());
			j.setDateAcct(getDateAcct());
			j.setDocStatus(DOCSTATUS_Drafted);
			MPeriod period = MPeriod.get(getCtx(), getDateAcct());
			j.setC_Period_ID(period.getC_Period_ID());
			j.setGL_Category_ID(1000000);
			j.setC_ConversionType_ID(114);
			j.saveEx();
			
			BigDecimal amount = getQtyReceived().multiply(getJobwork_StdPrice());
			
			//Jobwork Expense
			MJournalLine jl = new MJournalLine(j);
			jl.setLine(10);			
			jl.setAccount_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getJobworkExpenseAcct_ID());
			jl.setC_BPartner_ID(getSubcontractor_ID());
			jl.setM_Product_ID(getJobWork_Product_ID());			
			jl.setUser1_ID(getTF_Quarry().getC_ElementValue_ID()); // Quarry Profit Center
			jl.setAmtSourceDr(amount);
			jl.setAmtAcctDr(amount);
			jl.setIsGenerated(true);
			jl.saveEx();
			
			//Jobwork Payable Clearing
			jl = new MJournalLine(j);
			jl.setLine(20);			
			jl.setAccount_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getJobworkPayableClearingAcct_ID());
			jl.setC_BPartner_ID(getSubcontractor_ID());
			jl.setM_Product_ID(getJobWork_Product_ID());			
			jl.setUser1_ID(getTF_Quarry().getC_ElementValue_ID()); // Quarry Profit Center
			jl.setAmtSourceCr(amount);
			jl.setAmtAcctCr(amount);
			jl.setIsGenerated(true);
			jl.saveEx();
			
			j.processIt(MJournal.ACTION_Complete);
			j.saveEx();
			
			setJobwork_Journal_ID(j.getGL_Journal_ID());			
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			setM_Transaction_ID(mtrx.getM_Transaction_ID());
			
		}
		
		if(getC_Project_ID() > 0) {
			TF_MProject jobWork = new TF_MProject(getCtx(), getC_Project_ID(), get_TrxName());
			jobWork.updateBoulderReceiptBasedFields(this);
			jobWork.saveEx();
		}
		
		if(TF_SEND_TO_Production.equals(getTF_Send_To())) {
			m_processMsg = postCrusherProduction();
		}
		return m_processMsg;
	}
	
	public void reverseIt() {
		
		if(getSubcon_Invoice_ID()>0) {			
			throw new AdempiereException("You cannot modify this entry before Reverse Correct Subcontractor Invoice!");
		}
				
		MWarehouse warehouse = MWarehouse.get(getCtx(), getM_Warehouse_ID());
		int defaultLocatorID = warehouse.getDefaultLocator().getM_Locator_ID();
		String m_processMsg; 
		//Update Storage for the received Product from the Joborder.
		if (!MStorageOnHand.add(getCtx(), getM_Warehouse_ID(),
				defaultLocatorID,
				getM_Product_ID(),
				0,
				getQtyReceived().negate(),getDateAcct(),
				get_TrxName()))
			{
				String lastError = CLogger.retrieveErrorString("");
				m_processMsg = "Cannot correct Inventory OnHand (MA) [" + getM_Product().getValue() + "] - " + lastError;				
				throw new AdempiereException(m_processMsg);
			}
		//Update Transaction History
		MTransaction mtrx = new MTransaction (getCtx(), getAD_Org_ID(),
			"J+", defaultLocatorID,
			getM_Product_ID(), 0,
			getQtyReceived().negate(), getDateAcct(), get_TrxName());
		mtrx.set_ValueOfColumn(MBoulderReceipt.COLUMNNAME_TF_Boulder_Receipt_ID, getTF_Boulder_Receipt_ID());
		if (!mtrx.save())
		{
			m_processMsg = "Could not create Material Transaction (MA) [" + getM_Product().getValue() + "]";			
			throw new AdempiereException(m_processMsg);
		}
				
		if(getJobwork_Journal_ID()>0) {
			MJournal j = new MJournal(getCtx(), getJobwork_Journal_ID(), get_TrxName());
			j.reverseCorrectIt();
			j.saveEx();
			setJobwork_Journal_ID(0);
		}
		if(getJobwork_VarJournal_ID()>0) {
			MJournal j = new MJournal(getCtx(), getJobwork_VarJournal_ID(), get_TrxName());
			j.reverseCorrectIt();
			j.saveEx();
			setJobwork_VarJournal_ID(0);
		}
		if(getTF_Crusher_Production_ID() > 0) {
			MCrusherProduction crProd = new MCrusherProduction(getCtx(), getTF_Crusher_Production_ID(), get_TrxName());
			crProd.reverseIt();
			crProd.saveEx();
			setTF_Crusher_Production_ID(0);
		}		
		if(getTF_Quarry_Rent_ID()>0) {
			MQuarryRent rent = new MQuarryRent(getCtx(), getTF_Quarry_Rent_ID(), get_TrxName());
			rent.reverseIt();
			rent.saveEx();
			int rentID = rent.getTF_Quarry_Rent_ID();
			String sql = " UPDATE TF_Boulder_Receipt SET TF_Quarry_Rent_ID = NULL WHERE TF_Quarry_Rent_ID = " + rentID;
			DB.executeUpdate(sql, get_TrxName());
			rent.deleteEx(true);
		}
		
		if(getC_Project_ID() > 0) {
			TF_MProject jobWork = new TF_MProject(getCtx(), getC_Project_ID(), get_TrxName());
			jobWork.reverseBoulderReceiptBasedFields(this);
			jobWork.saveEx();
		}
		
		setProcessed(false);
		setDocStatus(DOCSTATUS_Drafted);		
	}
	
	public String postCrusherProduction() {
		String m_processMsg = null;
		//Create Crusher Production
		MCrusherProduction cProd = new MCrusherProduction(getCtx(), 0, get_TrxName());
		cProd.setTF_BlueMetal_Type(getTF_BlueMetal_Type());
		cProd.setMovementDate(getDateReceipt());
		cProd.setC_UOM_ID(getC_UOM_ID());		
		cProd.setM_Warehouse_ID(getM_Warehouse_ID());
		MWarehouse wh = MWarehouse.get(getCtx(), getM_Warehouse_ID());
		cProd.setM_Locator_ID(wh.getDefaultLocator().get_ID());
		cProd.setRM_Product_ID(getM_Product_ID());
		cProd.setQtyUsed(getQtyReceived());
		cProd.setDescription("Created from Boulder Receipt : " + getDocumentNo());
		cProd.setDocStatus(DOCSTATUS_Drafted);
		cProd.setDocAction(DOCACTION_Prepare);
		cProd.saveEx();
		
		//Update Crusher Production Reference to Boulder Receipt
		setTF_Crusher_Production_ID(cProd.getTF_Crusher_Production_ID());
		
		cProd.createProduction(true);
		cProd.saveEx();		
		//End Create
		
		//Post Crusher Production
		m_processMsg = cProd.processIt(DOCACTION_Complete);
		if(m_processMsg == null)			
			cProd.saveEx();
		return m_processMsg;
	}
		
	
	/***
	 * This method will be called after DOCACTION_Complete of Subcontractor Invoice 
	 * @param ctx
	 * @param SubconInvoice
	 * @param invoicedAmt
	 * @param trxName
	 */
	public static void postJobworkExpenseVarianceJournal(Properties ctx, MInvoice SubconInvoice, BigDecimal invoicedAmt, String trxName) {
		//Update back Invoice ID to Boulder Receipts.
		String sql = " Update TF_Boulder_Receipt SET Jobwork_PriceActual = ? WHERE Subcon_Invoice_ID = ?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(invoicedAmt);
		params.add(SubconInvoice.getC_Invoice_ID());		
		DB.executeUpdateEx(sql, params.toArray(), trxName);
		//End Update
		
		sql = " SELECT SUM(QtyReceived * (Jobwork_PriceActual - Jobwork_StdPrice)) AS expVar FROM TF_Boulder_Receipt WHERE Subcon_Invoice_ID = ?";
		BigDecimal expVar = DB.getSQLValueBD(trxName, sql, SubconInvoice.getC_Invoice_ID());
		
		//Do not create Expense Variance Journal.
		if(expVar == null || expVar.doubleValue() == 0)
			return;
		
		
		//Posting GL journal for Jobwork expense variances
		MGLPostingConfig glConfig = MGLPostingConfig.getMGLPostingConfig(ctx);
		int DrAcct = glConfig.getJobworkExpenseVarianceAcct_ID();
		int CrAcct = glConfig.getJobworkPayableClearingAcct_ID();
		
		//If the expense variance is negative,
		if(expVar.doubleValue() < 0) {			
			DrAcct = glConfig.getJobworkPayableClearingAcct_ID();
			CrAcct = glConfig.getJobworkExpenseVarianceAcct_ID();
		}
		
		//GL journal for Jobwork expense variances
		MJournal j = new MJournal(ctx, 0, trxName);
		j.setDescription("Generated Jobwork Expense Variances Journal Entry from Subcontract Invoice - " + SubconInvoice.getDocumentNo());
		j.setC_AcctSchema_ID(Env.getContextAsInt(ctx, "$C_AcctSchema_ID"));
		j.setC_Currency_ID(Env.getContextAsInt(ctx, "$C_Currency_ID"));
		j.setPostingType(MJournal.POSTINGTYPE_Actual);
		j.setC_DocType_ID(1000000);
		j.setDateDoc(SubconInvoice.getDateAcct());
		j.setDateAcct(SubconInvoice.getDateAcct());
		j.setDocStatus(DOCSTATUS_Drafted);
		MPeriod period = MPeriod.get(ctx, SubconInvoice.getDateAcct());
		j.setC_Period_ID(period.getC_Period_ID());
		j.setGL_Category_ID(1000000);
		j.setC_ConversionType_ID(114);
		j.saveEx();
		
		//Debtor Account
		MJournalLine jl = new MJournalLine(j);
		jl.setLine(10);			
		jl.setAccount_ID(DrAcct);
		jl.setC_BPartner_ID(SubconInvoice.getC_BPartner_ID());
		jl.setM_Product_ID(glConfig.getJobWork_Product_ID());			
		jl.setUser1_ID(SubconInvoice.getUser1_ID()); // Quarry Profit Center
		jl.setAmtSourceDr(expVar.abs());
		jl.setAmtAcctDr(expVar.abs());
		jl.setIsGenerated(true);
		jl.saveEx();
		
		//Creditor Account
		jl = new MJournalLine(j);
		jl.setLine(20);			
		jl.setAccount_ID(CrAcct);
		jl.setC_BPartner_ID(SubconInvoice.getC_BPartner_ID());
		jl.setM_Product_ID(glConfig.getJobWork_Product_ID());			
		jl.setUser1_ID(SubconInvoice.getUser1_ID()); // Quarry Profit Center
		jl.setAmtSourceCr(expVar.abs());
		jl.setAmtAcctCr(expVar.abs());
		jl.setIsGenerated(true);
		jl.saveEx();
		
		j.processIt(MJournal.ACTION_Complete);
		j.saveEx();
		
		//Update back Jobwork Expense Variance Journal ID to Boulder Receipts.
		sql = " Update TF_Boulder_Receipt SET Jobwork_VarJournal_ID = ? WHERE Subcon_Invoice_ID = ?  "  + 
				" AND Jobwork_PriceActual - Jobwork_StdPrice != 0 " ;
		params = new ArrayList<Object>();
		params.add(j.getGL_Journal_ID());
		params.add(SubconInvoice.getC_Invoice_ID());					
		DB.executeUpdateEx(sql, params.toArray(), trxName);
		//End Update
		
	}
}
