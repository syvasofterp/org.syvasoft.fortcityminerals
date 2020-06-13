package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MMeter extends X_PM_Meter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5430807137929545510L;

	public MMeter(Properties ctx, int PM_Meter_ID, String trxName) {
		super(ctx, PM_Meter_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMeter(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
