package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;

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
		
		if(getGrossWeight().doubleValue() > 0 && getTareWeight().doubleValue() <=0)
			throw new AdempiereException("Tare Weight should be greater than ZERO!");
		
		if(getNetWeight().doubleValue() < 0)
			throw new AdempiereException("Gross Weight should be greater Tare Weight!");
		
		if(getTareWeight().doubleValue() > 0 
				&& is_ValueChanged(COLUMNNAME_TareWeight)) {
			Timestamp tareWeightTime = new Timestamp(System.currentTimeMillis());
			setTareWeightTime(tareWeightTime);
			setStatus(STATUS_InProgress);
			
			if (getGrossWeight().doubleValue() > 0)
				setStatus(STATUS_Completed);
		}
		else if(getTareWeight().doubleValue() == 0) {
			setTareWeightTime(null);
			setStatus(STATUS_InProgress);
		}
		
		if(getGrossWeight().doubleValue() > 0 
				&& is_ValueChanged(COLUMNNAME_GrossWeight) ) {
			Timestamp grossWeightTime = new Timestamp(System.currentTimeMillis());
			setGrossWeightTime(grossWeightTime);		
			setStatus(STATUS_Completed);
		}
		else if(getGrossWeight().doubleValue() == 0) {
			setGrossWeightTime(null);
			setStatus(STATUS_InProgress);
		}
	
		
		
		return ok;
	}
	
	public void close() {
		setStatus(STATUS_Closed);
		setProcessed(true);		
	}
	public void reverse() {
		setStatus(STATUS_Completed);		
		setProcessed(false);
	}
	
}
