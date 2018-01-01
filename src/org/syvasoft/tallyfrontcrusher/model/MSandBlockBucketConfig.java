package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MSandBlockBucketConfig extends X_TF_SandBlockBucket_Config {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8701713393750088016L;

	public MSandBlockBucketConfig(Properties ctx, int TF_SandBlockBucket_Config_ID, String trxName) {
		super(ctx, TF_SandBlockBucket_Config_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MSandBlockBucketConfig(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(newRecord || is_ValueChanged(COLUMNNAME_SandType)) {
			String sql = "SELECT COUNT(*) FROM TF_SandBlockBucket_Config WHERE AD_Org_ID=? AND SandType=?";
			int count = DB.getSQLValue(get_TrxName(), sql, getAD_Org_ID(), getSandType());
			if(count > 0) {
				throw new AdempiereException("Required Unique Data for (Org, SandType)");
			}
		}
		return super.beforeSave(newRecord);
	}

	public static MSandBlockBucketConfig getBucketConfig(int AD_Org_ID, String sandType) {		
		MSandBlockBucketConfig config = new Query(Env.getCtx(), Table_Name, "AD_Org_ID = ? AND SandType=?", null)
				.setClient_ID().setParameters(AD_Org_ID, sandType).first();
		return config;		
	}
}