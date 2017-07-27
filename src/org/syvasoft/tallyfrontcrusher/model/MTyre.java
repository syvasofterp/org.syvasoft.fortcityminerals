package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
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
		
		
		return true;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {		
		if(newRecord) {
			createTyreLifeRecords();
		}
		return super.afterSave(newRecord, success);
	}

	private void createTyreLifeRecords() {
		List<MTyreType> types = new Query(getCtx(), MTyreType.Table_Name, "", null)
			.setClient_ID() .setOnlyActiveRecords(true).setOrderBy("SeqNo").list();
		int seq = 10;
		String trxName = Trx.createTrxName();
		Trx trans = Trx.get(trxName, true);
		try {
			for(MTyreType type : types) {
				MTyreLife tlife = new MTyreLife(getCtx(), 0, trxName);
				tlife.setAD_Org_ID(getAD_Org_ID());			
				tlife.setTF_Tyre_ID(getTF_Tyre_ID());
				tlife.setSeqNo(seq);
				tlife.setTF_TyreType_ID(type.getTF_TyreType_ID());
				tlife.setEstRunning_Meter(type.getEstRunning_Meter());
				tlife.setActRunning_Meter(BigDecimal.ZERO);
				tlife.saveEx();
				seq = seq + 10;
			}
		}
		catch(Exception ex) {
			trans.rollback();
			throw new AdempiereException(ex.getMessage());
		}
		finally {
			trans.commit();
		}
	}
	
}
