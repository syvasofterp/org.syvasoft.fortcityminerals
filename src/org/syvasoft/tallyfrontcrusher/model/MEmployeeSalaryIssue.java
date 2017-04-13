package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalLine;
import org.compiere.model.MPeriod;
import org.compiere.process.DocAction;
import org.compiere.util.Env;

public class MEmployeeSalaryIssue extends X_TF_Employee_Salary_Issue {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2047481242708130880L;
	public MEmployeeSalaryIssue(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	public MEmployeeSalaryIssue(Properties ctx,
			int TF_Employee_Salary_Issue_ID, String trxName) {
		super(ctx, TF_Employee_Salary_Issue_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public void processIt(String docAction) {
		if(getAdvance_Deduct().doubleValue() == 0 && getSalary_Paid().doubleValue() == 0)
			throw new AdempiereException("Invalid Salary Issue Entry due to both Advance Deduct and Salary Paid are ZERO");
		
		if(DocAction.ACTION_Prepare.equals(docAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(DocAction.ACTION_Complete.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
						
			MGLPostingConfig glConfig = MGLPostingConfig.getMGLPostingConfig(getCtx());
			
			//Post Advance Deduct Adjustment journal entry
			if(getAdvance_Deduct().doubleValue()>0 || getLoan_Deduct().doubleValue()>0) {
				MJournal j = new MJournal(getCtx(), 0, get_TrxName());
				j.setDescription("Generated from Employee Salary Issue Entry - " + getDocumentNo());
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
				
				//Salaries Payable Dr.
				MJournalLine jl;				
				jl = new MJournalLine(j);
				jl.setLine(10);			
				jl.setAccount_ID(glConfig.getSalaryPayable_Acct());
				jl.setC_BPartner_ID(getC_BPartner_ID());
				jl.setUser1_ID(getC_ElementValue_ID()); // Quarry Profit Center
				jl.setAmtSourceDr(getAdvance_Deduct().add(getLoan_Deduct()));
				jl.setAmtAcctDr(getAdvance_Deduct().add(getLoan_Deduct()));
				jl.setIsGenerated(true);
				jl.saveEx();
				
				//Salary Advance Cr.
				if(getAdvance_Deduct().doubleValue()>0) {
					jl = new MJournalLine(j);
					jl.setLine(10);			
					jl.setAccount_ID(glConfig.getSalariesAdvanceAcct_ID());
					jl.setC_BPartner_ID(getC_BPartner_ID());
					jl.setUser1_ID(getC_ElementValue_ID()); // Quarry Profit Center
					jl.setAmtSourceCr(getAdvance_Deduct());
					jl.setAmtAcctCr(getAdvance_Deduct());
					jl.setIsGenerated(true);
					jl.saveEx();
				}
				
				//Loan Advance Cr.
				if(getLoan_Deduct().doubleValue() > 0) {
					jl = new MJournalLine(j);
					jl.setLine(10);			
					jl.setAccount_ID(glConfig.getLoan_ID());
					jl.setC_BPartner_ID(getC_BPartner_ID());
					jl.setUser1_ID(getC_ElementValue_ID()); // Quarry Profit Center
					jl.setAmtSourceCr(getLoan_Deduct());
					jl.setAmtAcctCr(getLoan_Deduct());
					jl.setIsGenerated(true);
					jl.saveEx();
				}
				j.processIt(MJournal.ACTION_Complete);
				j.saveEx();
				
				setGL_Journal_ID(j.getGL_Journal_ID());
			}
			
			//Post Cash Book Entry for Salary Paid
			if(getSalary_Paid().doubleValue()>0) {
				//Create Salaries Payable Charge if it is not there already.
				//It should be in atomic transaction to get account settings of Charge for the current docaction transaction.
				TF_MCharge charge = TF_MCharge.createChargeFromAccount(getCtx(), glConfig.getSalaryPayable_Acct(), null);			
				//Posting Payment Document for Employee Salary Issue 
				TF_MPayment payment = new TF_MPayment(getCtx(), 0, get_TrxName());
				payment.setDateAcct(getDateAcct());
				payment.setDateTrx(getDateAcct());
				payment.setDescription("Generated from Employee Salary Issue Entry - " + getDocumentNo());
				payment.setCashType(TF_MPayment.CASHTYPE_EmployeePayment);
				payment.setC_DocType_ID(false);
				payment.setC_BPartner_ID(getC_BPartner_ID());
				payment.setC_Charge_ID(charge.getC_Charge_ID());
				payment.setUser1_ID(getC_ElementValue_ID()); // Profit Center
				payment.setC_ElementValue_ID(glConfig.getSalaryPayable_Acct());
				payment.setC_BankAccount_ID(getC_BankAccount_ID());
				payment.setPayAmt(getSalary_Paid());
				payment.setC_Currency_ID(Env.getContextAsInt(getCtx(), "$C_Currency_ID"));
				payment.setDocStatus(DOCSTATUS_InProgress);
				payment.setTenderType(TF_MPayment.TENDERTYPE_Cash);
				payment.saveEx();
				payment.processIt(DocAction.ACTION_Complete);
				payment.saveEx();
				
				setC_Payment_ID(payment.get_ID());
			}
		}
	}

}
