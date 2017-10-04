package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

public class MTyre extends X_TF_Tyre {

	public MTyre(Properties ctx, int TF_Tyre_ID, String trxName) {
		super(ctx, TF_Tyre_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public MTyre(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected boolean beforeSave(boolean newRecord) {
		boolean ok = super.beforeSave(newRecord);
		if(getCurrent_TyreType_ID()==0)
			setCurrent_TyreType_ID(getPurchased_TyreType_ID());
		//if(newRecord) {
		//	createTyreLifeRecords();
		//}		
		return ok;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		boolean ok = super.afterSave(newRecord, success);
		
		//if(newRecord) {
		//	createTyreLifeRecords();
		//}
		//calcRunningMeter();
		return ok;
	}

	public static void createTyreLifeRecords(MTyre tyre) {
		List<MTyreType> types = new Query(tyre.getCtx(), MTyreType.Table_Name, "", null)
			.setClient_ID() .setOnlyActiveRecords(true).setOrderBy("SeqNo").list();
		int seq = 10;
		String trxName = tyre.get_TrxName(); // Trx.createTrxName();
		//Trx trans = Trx.get(trxName, true);
		//trxName = get_TrxName();
		try {
			for(MTyreType type : types) {
				MTyreLife tlife = new MTyreLife(tyre.getCtx(), 0, trxName);
				tlife.setAD_Org_ID(tyre.getAD_Org_ID());			
				tlife.setTF_Tyre_ID(tyre.getTF_Tyre_ID());
				tlife.setSeqNo(seq);
				tlife.setTF_TyreType_ID(type.getTF_TyreType_ID());
				tlife.setTyreCost(BigDecimal.ZERO);
				tlife.setEstRunning_Meter(type.getEstRunning_Meter());
				tlife.setActRunning_Meter(BigDecimal.ZERO);
				tlife.saveEx();
				seq = seq + 10;
			}
		}
		catch(Exception ex) {
			//trans.rollback();
			throw new AdempiereException(ex.getMessage());
		}
		finally {
			//trans.commit();
		}
	}
	
	public void calcRunningMeter() {
		String sql = "SELECT SUM(ActRunning_Meter) FROM TF_TyreLife WHERE TF_Tyre_ID=?";
		BigDecimal totRunningMeter = DB.getSQLValueBD(get_TrxName(), sql, getTF_Tyre_ID());
		if(totRunningMeter==null) {
			totRunningMeter = BigDecimal.ZERO;
		}
		setRunning_Meter(totRunningMeter);
	}
	
}
