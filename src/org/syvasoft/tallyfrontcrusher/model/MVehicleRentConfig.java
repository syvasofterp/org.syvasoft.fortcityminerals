package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MVehicleRentConfig extends X_TF_Vehicle_Rent_Config {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2707477724866876240L;

	public MVehicleRentConfig(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MVehicleRentConfig(Properties ctx, int TF_Vehicle_Rent_Config_ID,
			String trxName) {
		super(ctx, TF_Vehicle_Rent_Config_ID, trxName);
		// TODO Auto-generated constructor stub
	}

}
