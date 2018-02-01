package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.DocAction;

public class MYardLoadEntry extends X_TF_YardLoadEntry {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3681259456827671007L;

	public MYardLoadEntry(Properties ctx, int TF_YardLoadEntry_ID, String trxName) {
		super(ctx, TF_YardLoadEntry_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MYardLoadEntry(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getTotalBucketQty() {
		BigDecimal qty = BigDecimal.ZERO;
		
		qty = qty.add(getBucketQty(getBucket1()));
		qty = qty.add(getBucketQty(getBucket2()));
		qty = qty.add(getBucketQty(getBucket3()));
		qty = qty.add(getBucketQty(getBucket4()));
		qty = qty.add(getBucketQty(getBucket5()));
		qty = qty.add(getBucketQty(getBucket6()));
		qty = qty.add(getBucketQty(getBucket7()));
		qty = qty.add(getBucketQty(getBucket8()));
		qty = qty.add(getBucketQty(getBucket9()));
		qty = qty.add(getBucketQty(getBucket10()));
		
		return qty;
	}
	
	public BigDecimal getBucketQty(String bucketSize) {
		if(bucketSize == null)
			return BigDecimal.ZERO;
		
		if(bucketSize.equals(BUCKET1_Full))
			return BigDecimal.ONE;
		else if(bucketSize.equals(BUCKET1_Half))
			return new BigDecimal(0.5);
		
		return BigDecimal.ZERO;
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {		
		boolean ok = super.beforeSave(newRecord);
		
		setTotal_Bucket(getTotalBucketQty());
					
		
		MYardCustomerVehicle v = MYardCustomerVehicle.addCustomerVehicle(getCtx(), getAD_Org_ID(), getTF_VehicleType_ID(), getVehicleNo(), 
				getC_BPartner_ID(), get_TrxName());
		setTF_YardCustomerVehicle_ID(v.getTF_YardCustomerVehicle_ID());
		
		if(!newRecord && is_ValueChanged(COLUMNNAME_TF_VehicleType_ID)) {
			v.setTF_VehicleType_ID(getTF_VehicleType_ID());
			v.saveEx();
		}
		
		if(!newRecord && is_ValueChanged(COLUMNNAME_C_BPartner_ID)) {
			v.setC_BPartner_ID(getC_BPartner_ID());
			v.saveEx();
		}
		
		if(getC_BPartner_ID() == 0)
			setC_BPartner_ID(v.getC_BPartner_ID());
				
		setTF_VehicleType_ID(v.getTF_VehicleType_ID());
		
		//if(getBucketPerLoad().doubleValue() == 0) {
		MYardLoadConfig config = MYardLoadConfig.getMYardLoadConfig(getAD_Org_ID(), getTF_VehicleType_ID());
		if(config == null)
			throw new AdempiereException("Please Yard Load Configuration for the selected Vehicle Type!");
		setBucketPerLoad(config.getBucketPerLoad());		
		
		if(getTotal_Bucket().doubleValue() < getBucketPerLoad().doubleValue()) {
			throw new AdempiereException( getBucketPerLoad().subtract(getTotal_Bucket()) + 
					" Bucket Required to complete this load!");
		}
		
		return ok;
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if(newRecord && getDocStatus().equals(DOCSTATUS_Completed)) {
			processIt(DocAction.ACTION_Complete);
		}
		return super.afterSave(newRecord, success);
	}

	public void processIt(String docAction) {
		if(DocAction.ACTION_Prepare.equals(docAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(DocAction.ACTION_Complete.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
		}
	}
	
	public void reverseIt() {
		setProcessed(false);
		setDocStatus(DOCSTATUS_InProgress);	
	}
	
}
