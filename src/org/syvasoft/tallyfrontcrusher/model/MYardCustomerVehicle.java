package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;

public class MYardCustomerVehicle extends X_TF_YardCustomerVehicle {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1063308998436513746L;
	public MYardCustomerVehicle(Properties ctx, int TF_YardCustomerVehicle_ID, String trxName) {
		super(ctx, TF_YardCustomerVehicle_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MYardCustomerVehicle(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public static MYardCustomerVehicle getYardCustomerVehicle(Properties ctx, int AD_Org_ID, String VehicleNo, String trxName) {
		String whereClause = "AD_Org_ID = ? AND UPPER(REPLACE(VehicleNo,' ','')) LIKE '%' || REPLACE('" + 
					VehicleNo.toUpperCase() + "',' ','') || '%'";
		List<MYardCustomerVehicle> vehicles = new Query(ctx, Table_Name, whereClause, trxName)
				.setClient_ID().setParameters(AD_Org_ID).list();
		if(vehicles.size() > 1) 
			throw new AdempiereException("Please Specify Full Vehicle No!");	
		else if(vehicles.size() == 0)
			return null;
		return vehicles.get(0);
	}
	
	public static MYardCustomerVehicle addCustomerVehicle(Properties ctx,int AD_Org_ID, int TF_VehicleType_ID,  String VehicleNo, int C_BPartner_ID, String trxName) {
		
		MYardCustomerVehicle v = getYardCustomerVehicle(ctx, AD_Org_ID, VehicleNo, trxName);
		if(v != null)
			return v;
		
		if(VehicleNo.length() <=4 )
			throw new AdempiereException("Please Specify Full Vehicle No!");
		
		if(TF_VehicleType_ID == 0)
			throw new AdempiereException("Please Specify Vehicle Type!");
		
		v = new MYardCustomerVehicle(ctx, 0, trxName);
		v.setAD_Org_ID(AD_Org_ID);
		v.setTF_VehicleType_ID(TF_VehicleType_ID);
		v.setVehicleNo(VehicleNo);
		v.setC_BPartner_ID(C_BPartner_ID);
		v.setPermitPrice(BigDecimal.ZERO);
		v.saveEx();
		
		return v;
	}

}
