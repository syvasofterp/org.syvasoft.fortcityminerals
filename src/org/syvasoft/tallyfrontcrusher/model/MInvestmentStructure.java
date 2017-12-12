package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

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
		if(newRecord || is_ValueChanged(COLUMNNAME_Payable_Amount))
			MShareholder.updateInvestmentReceivable(getAD_Org_ID(), get_TrxName());
		return super.afterSave(newRecord, success);
	}
	
	public void updateInvestmentPaid(int AD_Org_ID, String trxName) {
		
		
		MShareholder.updateInvestmentReceived(AD_Org_ID, trxName);
	}

}
