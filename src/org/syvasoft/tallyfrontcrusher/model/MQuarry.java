package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MQuarry extends X_TF_Quarry {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6167828883070286025L;

	public MQuarry(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MQuarry(Properties ctx, int TF_Quarry_ID, String trxName) {
		super(ctx, TF_Quarry_ID, trxName);
		// TODO Auto-generated constructor stub
	}

}
