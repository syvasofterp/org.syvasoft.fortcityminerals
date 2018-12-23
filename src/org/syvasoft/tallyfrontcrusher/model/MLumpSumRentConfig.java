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
	public static BigDecimal getRateKm(Properties ctx,int AD_Org_ID,int TF_Destination_ID, int TF_VehicleType_ID,BigDecimal Distinace, String trxName) {
		BigDecimal RateKM=BigDecimal.ZERO;
		String Where;
		
		Where=" AD_Org_ID=? AND TF_VehicleType_ID=? AND (COALESCE(TF_Destination_ID,0) = ? OR ? between minkm AND maxkm)";
		MLumpSumRentConfig lumpDistConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,TF_VehicleType_ID,TF_Destination_ID, Distinace)
				.setOrderBy("COALESCE(TF_Destination_ID,0) DESC")
				.first();
		if(lumpDistConfig!=null) {
			RateKM=lumpDistConfig.getratekm();
		}
		return RateKM;
	}

	public static BigDecimal getLumpSumRent(Properties ctx,int AD_Org_ID,int TF_Destination_ID, int TF_VehicleType_ID,BigDecimal Distance, String trxName) {
		
		BigDecimal Rent=BigDecimal.ZERO;
		BigDecimal Distance1=BigDecimal.ZERO;
		String Where;
		Where=" AD_Org_ID=? AND TF_Destination_ID=?";
		
		MDestination destination = new Query(ctx, MDestination.Table_Name,Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,TF_Destination_ID)
				.first();
		
		if(destination != null) {
			
			Distance1=destination.getDistance();
			
			Where=" AD_Org_ID=? AND TF_VehicleType_ID=? AND TF_Destination_ID=?";
			MLumpSumRentConfig lumpDestConfig=new Query(ctx, Table_Name, Where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(AD_Org_ID,TF_VehicleType_ID,TF_Destination_ID)
					.first();
			
			if(lumpDestConfig!=null) {
				if(lumpDestConfig.getRent_Amt().equals(BigDecimal.ZERO))
				{
					Rent=Distance1.multiply(lumpDestConfig.getratekm());
				}
				else
				{
					Rent=lumpDestConfig.getRent_Amt();
				}
			}
			else {
				Where=" AD_Org_ID=? AND TF_VehicleType_ID=? AND ? between minkm AND maxkm";
				MLumpSumRentConfig lumpConfig=new Query(ctx, Table_Name, Where, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(AD_Org_ID,TF_VehicleType_ID,Distance1)
					.first();

				if(lumpConfig!=null) {
					if(lumpConfig.getRent_Amt().equals(BigDecimal.ZERO))
					{
						Rent=Distance1.multiply(lumpConfig.getratekm());
					}
					else
					{
						Rent=lumpConfig.getRent_Amt();
					}
				}
				
			}
		}
		else
		{
			Where=" AD_Org_ID=? AND TF_VehicleType_ID=? AND ? between minkm AND maxkm";
			MLumpSumRentConfig lumpConfig=new Query(ctx, Table_Name, Where, trxName)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(AD_Org_ID,TF_VehicleType_ID,Distance)
				.first();

			if(lumpConfig!=null) {
				if(lumpConfig.getRent_Amt().equals(BigDecimal.ZERO))
				{
					Rent=Distance1.multiply(lumpConfig.getratekm());
				}
				else
				{
					Rent=lumpConfig.getRent_Amt();
				}
			}
		}
		return Rent;
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		// TODO Auto-generated method stub
		if(getTF_Destination_ID()>0 && (getMaxKM()>0 || getMinKM()>0)) {
			throw new AdempiereException("Please enter either Destination or Kilometer Range!");
		}
		
		BigDecimal lumpsumRent=getRent_Amt();
		BigDecimal RateKm=getratekm();
		int result1 = lumpsumRent.compareTo(BigDecimal.ZERO);
		int result2 = RateKm.compareTo(BigDecimal.ZERO);
		
		if(result1==1 && result2==1) {
			throw new AdempiereException("Please enter either Lumpsum Rent or Rate per KM!");
		}
			
		return super.beforeSave(newRecord);
	}
}
