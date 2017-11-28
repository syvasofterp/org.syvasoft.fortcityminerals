package org.syvasoft.tallyfrontcrusher.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MGLPostingConfig;
import org.syvasoft.tallyfrontcrusher.model.TF_MPayment;

public class CalloutPayment_ElementValue implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		boolean isSalaryPayment = false;
		boolean isSalaryAdvance = false;
		MGLPostingConfig glConfig = MGLPostingConfig.getMGLPostingConfig(ctx);
		String description = null;
		if(mTab.getValue(TF_MPayment.COLUMNNAME_C_ElementValue_ID) != null) {
			mTab.setValue(TF_MPayment.COLUMNNAME_C_Invoice_ID, 0);
			int acct_id = (int) mTab.getValue(TF_MPayment.COLUMNNAME_C_ElementValue_ID);
			isSalaryPayment = glConfig.getSalaryPayable_Acct() == acct_id;
			isSalaryAdvance = glConfig.getSalariesAdvanceAcct_ID() == acct_id;
		}
		
		Env.setContext(ctx, WindowNo, "IsSalaryPayment", isSalaryPayment);
		
		if(isSalaryPayment) {
			description = "Salary Paid";
		}
		else if(isSalaryAdvance) {
			description = "Advance Paid";
		}
		else {
			description = null;
		}
		mTab.setValue(TF_MPayment.COLUMNNAME_Description, description);
		
		return null;
	}

}
