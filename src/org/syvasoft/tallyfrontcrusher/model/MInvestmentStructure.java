package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;

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
	
	public static void updateInvestmentPaid(int AD_Org_ID, String trxName) {
		String sql = " UPDATE TF_InvestmentStructure s SET Paid_Amount = COALESCE((SELECT SUM(PayAmt) FROM TF_InvestmentReceipt r " +
				" WHERE r.AD_Org_ID = s.AD_Org_ID AND r.C_ElementValue_ID = s.C_ElementValue_ID AND  r.DocStatus = 'CO' AND Processed ='Y'),0) " +
				" WHERE s.AD_Org_ID = " + AD_Org_ID;
		DB.executeUpdate(sql, trxName);		
		MShareholder.updateInvestmentReceived(AD_Org_ID, trxName);
	}

}
