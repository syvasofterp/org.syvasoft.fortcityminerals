package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDestination extends X_TF_Destination {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8086375269293054622L;

	public MDestination(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDestination(Properties ctx, int TF_Destination_ID, String trxName) {
		super(ctx, TF_Destination_ID, trxName);
		// TODO Auto-generated constructor stub
	}

}
