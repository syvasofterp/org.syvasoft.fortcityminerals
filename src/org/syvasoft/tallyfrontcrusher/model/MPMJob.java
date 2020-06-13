package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MPMJob extends X_PM_Job {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7003447034439226741L;

	public MPMJob(Properties ctx, int PM_Job_ID, String trxName) {
		super(ctx, PM_Job_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MPMJob(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
