package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MOrg;

public class MInterOrgCashTransfer extends X_TF_OrgCashTransfer_Config {

	/**
	 * 
	 */
	private static final long serialVersionUID = 689272763134192483L;

	public MInterOrgCashTransfer(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MInterOrgCashTransfer(Properties ctx, int TF_OrgCashTransfer_Config_ID, String trxName) {
		super(ctx, TF_OrgCashTransfer_Config_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		StringBuilder sb = new StringBuilder();
		MOrg srcOrg = new MOrg(getCtx(), getSrc_Org_ID(), get_TrxName());
		MOrg destOrg = new MOrg(getCtx(), getDest_Org_ID(), get_TrxName());
		TF_MBankAccount srcCash = new TF_MBankAccount(getCtx(), getSrc_BankAccount_ID(), get_TrxName());
		TF_MBankAccount destCash = new TF_MBankAccount(getCtx(), getDest_BankAccount_ID(),get_TrxName());
		TF_MElementValue destAcct = new TF_MElementValue(getCtx(), getDest_Acct_ID(), get_TrxName());
		TF_MElementValue srcAcct = new TF_MElementValue(getCtx(), getSrc_Acct_ID(), get_TrxName());
		
		sb.append("For " + srcOrg.getName() + " (Source Organization),\n")
			.append("    Cash Transfers from " + srcCash.getName() +" A/c to " + destAcct.getName() + " A/c .\n" )
			.append("\n\n")
			.append("Counter Transaction for " + destOrg.getName() + " (Destination Organization), \n")
			.append("    Cash Transfers from " + srcAcct.getName() +" A/c to " + destCash.getName() + " A/c ." );		
		
		setDescription(sb.toString());
		
		return super.beforeSave(newRecord);
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		TF_MCharge.createChargeFromAccount(getCtx(), getSrc_Acct_ID(), null);
		TF_MCharge.createChargeFromAccount(getCtx(), getDest_Acct_ID(), null);
		return super.afterSave(newRecord, success);
	}
	
		
	
}
