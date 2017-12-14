package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MJournalLine;
import org.compiere.model.MOrg;
import org.compiere.model.MPeriod;
import org.compiere.model.MUser;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MInvestmentStructure extends X_TF_InvestmentStructure {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2233954973943104380L;

	public MInvestmentStructure(Properties ctx, int TF_InvestmentStructure_ID, String trxName) {
		super(ctx, TF_InvestmentStructure_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MInvestmentStructure(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		
		MOrg org = new MOrg(getCtx(), getAD_Org_ID(), get_TrxName());
		boolean isOffsetCapitalAcct = org.get_ValueAsBoolean("IsOffsetCapitalAcct");
				
		if(newRecord || is_ValueChanged(COLUMNNAME_Payable_Amount)) {
			MShareholder.updateInvestmentReceivable(getAD_Org_ID(), get_TrxName());
			if(isOffsetCapitalAcct)
				postInitialExpenseAllocationJournalEntry();
		}
		
		return super.afterSave(newRecord, success);
	}
	
	public static void updateInvestmentPaid(int AD_Org_ID, String trxName) {
		String sql = " UPDATE TF_InvestmentStructure s SET Paid_Amount = COALESCE((SELECT SUM(PayAmt) FROM TF_InvestmentReceipt r " +
				" WHERE r.AD_Org_ID = s.AD_Org_ID AND r.C_ElementValue_ID = s.C_ElementValue_ID AND  r.DocStatus = 'CO' AND Processed ='Y'"
				+ " AND r.InvestmentReceiptType IN ('B','P')),0) " +
				" WHERE s.AD_Org_ID = " + AD_Org_ID;
		DB.executeUpdate(sql, trxName);		
		MShareholder.updateInvestmentReceived(AD_Org_ID, trxName);
		MShareholder.updateUnallocatedAmt(AD_Org_ID, trxName);
	}
	
	private void postInitialExpenseAllocationJournalEntry() {
		reverseInitialExpenseAllocation();
		if(getPayable_Amount().doubleValue() == 0) 
			return;
		
		int m_C_DocTypeTarget_ID = 1000000;		
		TF_MJournal j = new TF_MJournal(getCtx(), 0, get_TrxName());
		j.setDescription("Initial Expense");
		j.setAD_Org_ID(getAD_Org_ID());
		j.setC_AcctSchema_ID(Env.getContextAsInt(getCtx(), "$C_AcctSchema_ID"));
		j.setC_Currency_ID(Env.getContextAsInt(getCtx(), "$C_Currency_ID"));
		j.setPostingType(TF_MJournal.POSTINGTYPE_Actual);
		j.setC_DocType_ID(m_C_DocTypeTarget_ID);
		j.setDateDoc(getDateAcct());
		j.setDateAcct(getDateAcct());
		j.setDocStatus(TF_MJournal.DOCSTATUS_Drafted);
		MPeriod period = MPeriod.get(getCtx(), getDateAcct());
		j.setC_Period_ID(period.getC_Period_ID());
		j.setGL_Category_ID(1000000);
		j.setC_ConversionType_ID(114);				
		j.saveEx();
		
		//Initial Expense Dr.
		MJournalLine jl;			
		jl = new MJournalLine(j);
		jl.setLine(10);			
		jl.setAccount_ID(getC_ElementValue_ID());
		jl.setDescription("Initial Expense Payable for " + getC_ElementValue().getName());
		jl.setAmtSourceCr(getPayable_Amount());
		jl.setAmtAcctCr(getPayable_Amount());
		jl.setIsGenerated(true);
		jl.saveEx();
		
		List<MShareholder> partners = new Query(getCtx(), MShareholder.Table_Name, "AD_Org_ID=?" , get_TrxName())
				.setParameters(getAD_Org_ID()).list();
		int line = 10;
		for(MShareholder partner : partners) {			
			jl = new MJournalLine(j);
			line = line + 10;
			jl.setLine(line);			
			jl.setAccount_ID(partner.getCapitalAcct_ID());
			jl.setDescription(" Initial Expense Receivable for " + getC_ElementValue().getName());
			
			double perecent = partner.getInvestmentShare().doubleValue() / 100;
			BigDecimal drAmt = getPayable_Amount().multiply(new BigDecimal(perecent));					
					
			jl.setAmtSourceDr(drAmt);
			jl.setAmtAcctDr(drAmt);
			jl.setIsGenerated(true);
			jl.saveEx();
		}
		//
				
		
		//DocAction
		if (!j.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + j.getProcessMsg());
		j.saveEx();
		
		DB.executeUpdate("UPDATE TF_InvestmentStructure SET GL_Journal_ID=" + j.getGL_Journal_ID() + " WHERE TF_InvestmentStructure_ID ="
				+ getTF_InvestmentStructure_ID() , get_TrxName());
				
	}

	private void reverseInitialExpenseAllocation() {
		if(getGL_Journal_ID() > 0) {
			TF_MJournal j = new TF_MJournal(getCtx(), getGL_Journal_ID(), get_TrxName());
			if(j.getDocStatus().equals(TF_MInvoice.DOCSTATUS_Completed)) {
				if (!j.processIt(DocAction.ACTION_Reverse_Correct))
					throw new AdempiereException("Failed when processing document - " + j.getProcessMsg());
				j.saveEx();
			}
			DB.executeUpdate("UPDATE TF_InvestmentStructure SET GL_Journal_ID=NULL  WHERE TF_InvestmentStructure_ID ="
					+ getTF_InvestmentStructure_ID() , get_TrxName());
		}
	}
}
