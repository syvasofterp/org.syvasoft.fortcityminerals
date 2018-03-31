package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.util.DB;

public class MWeighmentEntry extends X_TF_WeighmentEntry {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2613943323993702690L;
	public MWeighmentEntry(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	public MWeighmentEntry(Properties ctx, int TF_WeighmentEntry_ID, String trxName) {
		super(ctx, TF_WeighmentEntry_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected boolean beforeSave(boolean newRecord) {
		boolean ok = super.beforeSave(newRecord);
		
		if(getTF_RentedVehicle_ID() > 0 && (getVehicleNo() == null || getVehicleNo().length() == 0))
				setVehicleNo(getTF_RentedVehicle().getVehicleNo());
		
		if(getC_BPartner_ID() == 0 && getPaymentRule().equals(PAYMENTRULE_Cash)) {
			TF_MBPartner bp = new Query(getCtx(), TF_MBPartner.Table_Name, "IsPOSCashBP='Y'", get_TrxName())
					.setClient_ID().first();
			if(bp != null)
				setC_BPartner_ID(bp.getC_BPartner_ID());
		}
		
		//if(getGrossWeight().doubleValue() > 0 && getTareWeight().doubleValue() <=0)
		//	throw new AdempiereException("Tare Weight should be greater than ZERO!");
		
		//if(getNetWeight().doubleValue() < 0)
		//	throw new AdempiereException("Gross Weight should be greater Tare Weight!");
		
		//if(getTareWeight().doubleValue() > 0 
		//		&& is_ValueChanged(COLUMNNAME_TareWeight)) {
			//Timestamp tareWeightTime = new Timestamp(System.currentTimeMillis());
			//setTareWeightTime(tareWeightTime);
		//	setStatus(STATUS_InProgress);
			
		//	if (getGrossWeight().doubleValue() > 0)
		//		setStatus(STATUS_Unbilled);
		//}
		//else if(getTareWeight().doubleValue() == 0) {
		//	setTareWeightTime(null);
		//	setStatus(STATUS_InProgress);
		//}
		
		//if(getGrossWeight().doubleValue() > 0 
		//		&& is_ValueChanged(COLUMNNAME_GrossWeight) ) {
			//Timestamp grossWeightTime = new Timestamp(System.currentTimeMillis());
			//setGrossWeightTime(grossWeightTime);		
		//	setStatus(STATUS_Unbilled);
		//}
		//else if(getGrossWeight().doubleValue() == 0) {
			//setGrossWeightTime(null);
		//	setStatus(STATUS_InProgress);
		//}
		if(getTF_RentedVehicle_ID() > 0 && !getTF_RentedVehicle().getTareWeight().equals(getTareWeight())) {
			MRentedVehicle v = new MRentedVehicle(getCtx(), getTF_RentedVehicle_ID(), get_TrxName());
			v.setTareWeight(getTareWeight());
			v.saveEx();
			int expiryDays = MSysConfig.getIntValue("TAREWEIGHT_EXPIRY_DAYS", 10);
			String sql = "UPDATE TF_RentedVehicle SET DateTareweightExpiry = now() + " + expiryDays
					+ " WHERE TF_RentedVehicle_ID = " + getTF_RentedVehicle_ID();
			DB.executeUpdate(sql, get_TrxName());
			
		}
		return ok;
	}
	
	public void close() {
		if(isProcessed())
			throw new AdempiereException("Weighment Entry is already processed!");
		setStatus(STATUS_Billed);
		setProcessed(true);		
	}
	public void reverse() {
		setStatus(STATUS_Unbilled);		
		setProcessed(false);
	}
	
}
