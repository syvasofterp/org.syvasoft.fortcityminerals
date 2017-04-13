package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MJournal;
import org.compiere.model.MPeriod;
import org.compiere.process.DocAction;
import org.compiere.util.Env;

public class MLabourWageAdvance extends X_TF_Labour_Wage_Advance {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3369073879660333733L;
	public MLabourWageAdvance(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	public MLabourWageAdvance(Properties ctx, int TF_Labour_Wage_Advance_ID,
			String trxName) {
		super(ctx, TF_Labour_Wage_Advance_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public void processIt(String docAction) {
		if(DocAction.ACTION_Prepare.equals(docAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(DocAction.ACTION_Complete.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			
			
			MGLPostingConfig glConfig = MGLPostingConfig.getMGLPostingConfig(getCtx());
			
			//Create Wages Advance Charge if it is not there already.
			//It should be in atomic transaction to get account settings of Charge for the current docaction transaction.
			TF_MCharge charge = TF_MCharge.createChargeFromAccount(getCtx(), glConfig.getWageAdvanceAcct_ID(), null);
			
			
			//Posting Payment Document for Labour Wage Advance
			TF_MPayment payment = new TF_MPayment(getCtx(), 0, get_TrxName());
			payment.setDateAcct(getDateAcct());
			payment.setDateTrx(getDateAcct());
			payment.setDescription("Generated from Labour Wage Advance - " + getDocumentNo());
			payment.setCashType(TF_MPayment.CASHTYPE_EmployeePayment);
			payment.setC_DocType_ID(false);
			payment.setC_BPartner_ID(getC_BPartner_ID());
			payment.setC_Charge_ID(charge.getC_Charge_ID());
			payment.setUser1_ID(getC_ElementValue_ID()); // Profit Center
			payment.setC_ElementValue_ID(glConfig.getWageAdvanceAcct_ID());
			payment.setC_BankAccount_ID(getC_BankAccount_ID());
			payment.setPayAmt(getAdvance_Amt());
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
