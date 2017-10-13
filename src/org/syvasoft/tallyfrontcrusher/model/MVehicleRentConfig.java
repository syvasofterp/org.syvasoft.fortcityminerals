package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MVehicleRentConfig extends X_TF_VehicleRent_TajConfig {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8401430132407131437L;
	public MVehicleRentConfig(Properties ctx, int TF_VehicleRent_TajConfig_ID, String trxName) {
		super(ctx, TF_VehicleRent_TajConfig_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MVehicleRentConfig(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}	

}
