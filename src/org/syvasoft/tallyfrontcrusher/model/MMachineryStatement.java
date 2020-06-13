package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MMachineryStatement extends X_PM_MachineStmt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8837243841677553600L;

	public MMachineryStatement(Properties ctx, int PM_MachineStmt_ID, String trxName) {
		super(ctx, PM_MachineStmt_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMachineryStatement(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
