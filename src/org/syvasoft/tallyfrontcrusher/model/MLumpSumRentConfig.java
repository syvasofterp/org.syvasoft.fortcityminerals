package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;

public class MLumpSumRentConfig extends X_TF_LumpSumRent_Config {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6002430357564063143L;

	public MLumpSumRentConfig(Properties ctx, int TF_LumpSumRent_Config_ID, String trxName) {
		super(ctx, TF_LumpSumRent_Config_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MLumpSumRentConfig(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static BigDecimal getLumpSumRent(Properties ctx,int AD_Org_ID,int TF_Destination_ID, int TF_VehicleType_ID,BigDecimal Distinace, String trxName) {
		
		BigDecimal Rent=BigDecimal.ZERO;
		String Where;
		Where=" AD_Org_ID=? AND TF_VehicleType_ID=? AND TF_Destination_ID=?";
		MLumpSumRentConfig lumpConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,TF_VehicleType_ID,TF_Destination_ID)
				.first();
		
		if(lumpConfig==null) {
			
			Where=" AD_Org_ID=? AND TF_VehicleType_ID=? AND TF_Destination_ID IS NULL AND ? between minkm AND maxkm";
			MLumpSumRentConfig lumpDistConfig=new Query(ctx, Table_Name, Where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(AD_Org_ID,TF_VehicleType_ID,Distinace)
					.first();
			if(lumpDistConfig!=null) {
				Rent=lumpDistConfig.getRent_Amt();
			}
		}
		else {
			Rent=lumpConfig.getRent_Amt();
		}
		
		return Rent;
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		// TODO Auto-generated method stub
		if(getTF_Destination() != null && (getMaxKM()>0 || getMinKM()>0)) {
			throw new AdempiereException("Please enter either Destination or Kilometer Range!");
		}
			
		return super.beforeSave(newRecord);
	}
}
