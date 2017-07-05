package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalLine;
import org.compiere.model.MPeriod;
import org.compiere.util.Env;

public class MEmployeeSalary extends X_TF_Employee_Salary {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1657054354220279252L;

	public MEmployeeSalary(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MEmployeeSalary(Properties ctx, int TF_Employee_Salary_ID,
			String trxName) {
		super(ctx, TF_Employee_Salary_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(isCalculated() && getStd_Days().doubleValue()!=0) {			
			setSalary_Amt(getStd_Wage().multiply(getPresent_Days().divide(getStd_Days())));
		}
		if(getSalary_Amt().doubleValue() == 0 )			
			throw new AdempiereException("Invalid Earned Salary");
		
		//If the Employee is created from Quick Entry
		if(!getC_BPartner().isEmployee()) {
			MBPartner bp = new MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
			bp.setIsEmployee(true);
			bp.setIsCustomer(false);
			bp.setIsVendor(false);
			bp.saveEx();
		}
				
		return super.beforeSave(newRecord);
	}
	
	public void processIt(String DocAction) {
		if(MBoulderReceipt.DOCACTION_Prepare.equals(DocAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(MBoulderReceipt.DOCACTION_Complete.equals(DocAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			
			//Posting GL journal for Employee Salary 
			MJournal j = new MJournal(getCtx(), 0, get_TrxName());
			j.setDescription("Generated from Employee Salary Entry - " + getDocumentNo());
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
			
			//Salaries Expense Dr
			MJournalLine jl = new MJournalLine(j);
			jl.setLine(10);			
			jl.setAccount_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getSalariesExpenseAcct());
			jl.setC_BPartner_ID(getC_BPartner_ID());
			jl.setUser1_ID(getC_ElementValue_ID()); // Quarry Profit Center
			jl.setAmtSourceDr(getSalary_Amt());
			jl.setAmtAcctDr(getSalary_Amt());
			jl.setIsGenerated(true);
			jl.saveEx();
			
			//Salary Payable Cr.
			jl = new MJournalLine(j);
			jl.setLine(20);			
			jl.setAccount_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getSalaryPayable_Acct());
			jl.setC_BPartner_ID(getC_BPartner_ID());
			jl.setUser1_ID(getC_ElementValue_ID()); // Quarry Profit Center
			jl.setAmtSourceCr(getSalary_Amt());
			jl.setAmtAcctCr(getSalary_Amt());
			jl.setIsGenerated(true);
			jl.saveEx();
			
			j.processIt(MJournal.ACTION_Complete);
			j.saveEx();
			
			setGL_Journal_ID(j.getGL_Journal_ID());
		}
	}
	public void reverseIt() {
		if(getGL_Journal_ID()>0) {
			MJournal j = new MJournal(getCtx(), getGL_Journal_ID(), get_TrxName());
			j.reverseCorrectIt();
			j.saveEx();
			
			setGL_Journal_ID(0);
			setProcessed(false);
			setDocStatus(DOCSTATUS_Drafted);
		}
	}
}
