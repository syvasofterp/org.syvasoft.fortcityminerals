package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MGLPostingConfig;
import org.syvasoft.tallyfrontcrusher.model.TF_MPayment;

public class CalloutPayment_ElementValue implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		boolean isSalaryPayment = false;
		boolean isSalaryAdvance = false;
		boolean isReceipt = mTab.getValueAsBoolean(TF_MPayment.COLUMNNAME_IsReceipt);
		MGLPostingConfig glConfig = MGLPostingConfig.getMGLPostingConfig(ctx);
		String description = null;
		if(mTab.getValue(TF_MPayment.COLUMNNAME_C_ElementValue_ID) != null) {
			mTab.setValue(TF_MPayment.COLUMNNAME_C_Invoice_ID, 0);
			int acct_id = (int) mTab.getValue(TF_MPayment.COLUMNNAME_C_ElementValue_ID);
			isSalaryPayment = glConfig.getSalaryPayable_Acct() == acct_id && !isReceipt;
			isSalaryAdvance = glConfig.getSalariesAdvanceAcct_ID() == acct_id && !isReceipt;
		}
				
		mTab.setValue(TF_MPayment.COLUMNNAME_IsSalaryPayment, isSalaryPayment);
		
		int salaryPayable_acctID = glConfig.getSalaryPayable_Acct();
		int salaryAdvance_acctID = glConfig.getSalariesAdvanceAcct_ID();
		BigDecimal salaryAmt = BigDecimal.ZERO;
		BigDecimal advancePaid = BigDecimal.ZERO;
		if(isSalaryPayment) {
			description = "Salary Paid";
			
			int bPartnerID = (int) mTab.getValue(TF_MPayment.COLUMNNAME_TF_BPartner_ID);
			int adorgID = (int) mTab.getValue(TF_MPayment.COLUMNNAME_AD_Org_ID);
			Timestamp dateAcct = (Timestamp) mTab.getValue(TF_MPayment.COLUMNNAME_DateAcct);
			
			//Salary Payable
			String sql = "SELECT 	SUM(AmtAcctCr - AmtAcctDr) Earned_Wage FROM Fact_Acct_Balance " +
					" WHERE AD_Org_ID = ? AND Account_ID = ? AND C_BPartner_ID = ? AND postingtype='A' AND DateAcct <= ?";
			salaryAmt = DB.getSQLValueBD(null, sql, adorgID, salaryPayable_acctID, bPartnerID, dateAcct);
			if(salaryAmt == null)
				salaryAmt = BigDecimal.ZERO;				
			
			//Advance Paid
			sql = "SELECT 	SUM(AmtAcctDr - AmtAcctCr) Advance_Paid FROM Fact_Acct_Balance " +
					" WHERE AD_Org_ID = ? AND Account_ID = ? AND C_BPartner_ID = ? AND postingtype='A' AND DateAcct <= ? ";
			advancePaid = DB.getSQLValueBD(null, sql, adorgID, salaryAdvance_acctID, bPartnerID, dateAcct);
			if(advancePaid == null)
				advancePaid = BigDecimal.ZERO;
			
		}
		else if(isSalaryAdvance) {
			description = "Advance Paid";
		}
		else if(mTab.getValue(TF_MPayment.COLUMNNAME_FromTo_BankAccount_ID) != null) {
			//do not change description.
			description = (String) mTab.getValue(TF_MPayment.COLUMNNAME_Description); 
		}
		else {
			description = null;
		}
		
		mTab.setValue(TF_MPayment.COLUMNNAME_Description, description);		
		mTab.setValue(TF_MPayment.COLUMNNAME_Salary_Amt, salaryAmt);
		mTab.setValue(TF_MPayment.COLUMNNAME_Advance_Paid, advancePaid);
		
		return null;
	}

}
