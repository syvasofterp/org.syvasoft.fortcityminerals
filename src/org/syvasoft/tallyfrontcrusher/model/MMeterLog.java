package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.Query;

public class MMeterLog extends X_PM_Meter_Log {

	/**
	 * 
	 */
	private static final long serialVersionUID = -65533629607944959L;

	public MMeterLog(Properties ctx, int PM_Meter_Log_ID, String trxName) {
		super(ctx, PM_Meter_Log_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMeterLog(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		setRunning_Meter(getClosing_Meter().subtract(getOpening_Meter()));
		return super.beforeSave(newRecord);
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		MMeter.updateCurrentMeter(getCtx(), getPM_Machinery_ID(), getC_UOM_ID(), get_TrxName());
		return super.afterSave(newRecord, success);
	}
	
	@Override
	protected boolean afterDelete(boolean success) {
		MMeter.updateCurrentMeter(getCtx(), getPM_Machinery_ID(), getC_UOM_ID(), get_TrxName());
		return super.afterDelete(success);
	}
	
	public static void deleteTripSheetMeterLog(Properties ctx, int TF_TripSheet_ID, String trxName) {
		String whereClause = "TF_TripSheet_ID = ?";
		MMeterLog ml = new Query(ctx, Table_Name, whereClause, trxName)
				.setClient_ID()
				.setParameters(TF_TripSheet_ID)
				.first();
		if(ml != null)
			ml.deleteEx(true, trxName);
	}
}
