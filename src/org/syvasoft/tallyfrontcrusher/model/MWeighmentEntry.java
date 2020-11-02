package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MOrgInfo;
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
		
		
		if(getTF_RentedVehicle_ID() > 0 && (getVehicleNo() == null || getVehicleNo().length() == 0))
				setVehicleNo(getTF_RentedVehicle().getVehicleNo());
		
		if(getC_BPartner_ID() == 0 && getPaymentRule().equals(PAYMENTRULE_Cash)) {
			TF_MBPartner bp = new Query(getCtx(), TF_MBPartner.Table_Name, "IsPOSCashBP='Y'", get_TrxName())
					.setClient_ID().first();
			if(bp != null)
				setC_BPartner_ID(bp.getC_BPartner_ID());
		}
		
		if(getTF_RentedVehicle_ID()>0) {
			String rvwhere="COALESCE(Tareweight,0)!=? AND IsTransporter='N' AND TF_RentedVehicle_ID=?";
			MRentedVehicle rv= new Query(getCtx(), MRentedVehicle.Table_Name, rvwhere, get_TrxName())
					.setClient_ID()
					.setParameters(getTareWeight(),getTF_RentedVehicle_ID())
					.first();
			
			if(rv!=null) {
				if(rv.getTareWeight()!=null) {
					rv.setOldTareweight(rv.getTareWeight());
				}
				rv.setTareWeight(getTareWeight());
				rv.saveEx();
			}
					
		}
		/*
		//csv import support
		if(newRecord) {
			
			//Set weighment entry type by product
			if(getWeighmentEntryType() == null) {
				if(!getM_Product().isSold()) {
					setWeighmentEntryType(WEIGHMENTENTRYTYPE_OwnProductionReceipt);
					setTF_Quarry_ID(1000006);
					setTF_Send_To(TF_SEND_TO_Stock);
				}
				else if(getM_Product().isSold()) {
					setWeighmentEntryType(WEIGHMENTENTRYTYPE_Sales);
				}
			}
			//set Rented Vehicle ID
			String whereClause = "AD_Org_ID IN (0,?) AND REPLACE(UPPER(VehicleNo),' ','') = ?";
			MRentedVehicle rv = new Query(getCtx(), MRentedVehicle.Table_Name, whereClause, get_TrxName())
					.setClient_ID()
					.setParameters(getAD_Org_ID(), getVehicleNo().toUpperCase().replace(" ", ""))
					.first();
			if(rv != null)
				setTF_RentedVehicle_ID(rv.getTF_RentedVehicle_ID());
			
			//set Payment Rule by BP
			if(getC_BPartner_ID() > 0) {
				TF_MBPartner bp = new TF_MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
				if(bp.getIsPOSCashBP()) 
					setPaymentRule(PAYMENTRULE_Cash);
				else
					setPaymentRule(PAYMENTRULE_OnCredit);
			}
			//set deafult warehouse
			MOrgInfo oInfo = MOrgInfo.get(getCtx(), getAD_Org_ID(), get_TrxName());
			setM_Warehouse_ID(oInfo.getM_Warehouse_ID());
			
			if(getNetWeightUnit() == null || getNetWeightUnit().doubleValue() == 0)
				setNetWeightUnit(getNetWeight());
		}
		*/
		//	if(getTF_RentedVehicle_ID()>0	&& is_ValueChanged(COLUMNNAME_TareWeight)) {
			//Timestamp tareWeightTime = new Timestamp(System.currentTimeMillis());
			//setTareWeightTime(tareWeightTime);
		//	setStatus(STATUS_InProgress);
		
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
		
		/*
		if(getTF_RentedVehicle_ID() > 0 && !getTF_RentedVehicle().getTareWeight().equals(getTareWeight())) {
			MRentedVehicle v = new MRentedVehicle(getCtx(), getTF_RentedVehicle_ID(), get_TrxName());
			v.setTareWeight(getTareWeight());
			v.saveEx();
			int expiryDays = MSysConfig.getIntValue("TAREWEIGHT_EXPIRY_DAYS", 10);
			String sql = "UPDATE TF_RentedVehicle SET DateTareweightExpiry = now() + " + expiryDays
					+ " WHERE TF_RentedVehicle_ID = " + getTF_RentedVehicle_ID();
			DB.executeUpdate(sql, get_TrxName());
			
		}
		*/
		boolean ok = super.beforeSave(newRecord);
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
	
	public BigDecimal getCFTMultiplyRate() {
		if(getNetWeightUnit() == null)
			return null;
		if(getNetWeight().doubleValue() == getNetWeightUnit().doubleValue())
			return BigDecimal.ONE;
		return getNetWeightUnit().divide(getNetWeight()
					.divide(new BigDecimal(1000), 2, RoundingMode.HALF_EVEN)
					, 2, RoundingMode.HALF_EVEN);
	}
	
	public boolean isGST() {
		BigDecimal royaltyPassQty = getPassQtyIssued();
		if(royaltyPassQty == null)
			royaltyPassQty = BigDecimal.ZERO;
		return royaltyPassQty.doubleValue() > 0;
	}
	
	public int getC_DocType_ID() {
		if(isGST())
			return TF_MOrder.GSTOrderDocType_ID(getCtx());
		else
			return TF_MOrder.NonGSTOrderDocType_ID(getCtx());
	}
	
	public int getRoyaltyPassProduct_ID() {
		return MSysConfig.getIntValue("ROYALTY_PASS_PRODUCT_ID", 1000329, getAD_Client_ID(), getAD_Org_ID());
	}
}
