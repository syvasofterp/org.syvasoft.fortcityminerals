package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MMeterLog extends X_PM_Meter_Log {

	/**
	 * 
	 */
	private static final long serialVersionUID = -65533629607944959L;

	public MMeterLog(Properties ctx, int PM_Meter_Log_ID, String trxName) {
		super(ctx, PM_Meter_Log_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMeterLog(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
