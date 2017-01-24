package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MQuarryRent extends X_TF_Quarry_Rent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7230636520181550403L;

	public MQuarryRent(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MQuarryRent(Properties ctx, int TF_Quarry_Rent_ID, String trxName) {
		super(ctx, TF_Quarry_Rent_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public void processIt(String DocAction) {
		if(MBoulderReceipt.DOCACTION_Prepare.equals(DocAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(MBoulderReceipt.DOCACTION_Complete.equals(DocAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
		}
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(isCalculated()) {
			setRent_Amt(getStd_Rent().multiply(getNoOfLoad()));
		}
		setC_ElementValue_ID(getTF_Quarry().getC_ElementValue_ID());
		return super.beforeSave(newRecord);
	}
}
