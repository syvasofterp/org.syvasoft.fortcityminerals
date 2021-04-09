package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

public class MDispensePlanLine extends X_TF_DispensePlanLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8086375269293054622L;

	public MDispensePlanLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDispensePlanLine(Properties ctx, int TF_Destination_ID, String trxName) {
		super(ctx, TF_Destination_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(newRecord) {
			if(getC_OrderLine_ID() > 0) {
				setType(TYPE_Order);
			}
			else {
				setType(TYPE_Instant);
			}
		}
		
		return super.beforeSave(newRecord);
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if(newRecord) {
			
		}
		return super.afterSave(newRecord, success);
	}	
}
