package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MVehicleRent extends X_TF_Vehicle_Rent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4964964952091340862L;

	public MVehicleRent(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MVehicleRent(Properties ctx, int TF_Vehicle_Rent_ID, String trxName) {
		super(ctx, TF_Vehicle_Rent_ID, trxName);
		// TODO Auto-generated constructor stub
	}

}
