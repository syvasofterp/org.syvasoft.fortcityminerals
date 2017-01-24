package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

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
	
	public static MVehicleRentConfig getVehicleRentConfig(Properties ctx, int TF_VehicleType_ID, Timestamp dateAcct) {
		String where = " TF_VehicleType_ID = ? AND ValidFrom <= ? ";
		List<MVehicleRentConfig> rentConfig = new Query(ctx, Table_Name, where, null)
		.setClient_ID().setParameters(TF_VehicleType_ID, dateAcct).setOnlyActiveRecords(true)
		.setOrderBy("ValidFrom DESC").list();
		if(rentConfig.size() > 0)
			return rentConfig.get(0);
		else
			return null;
	}
	
	
}
