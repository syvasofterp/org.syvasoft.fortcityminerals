package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;

public class MTyreLife extends X_TF_TyreLife {

	public MTyreLife(Properties ctx, int TF_TyreLife_ID, String trxName) {
		super(ctx, TF_TyreLife_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MTyreLife(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1918825772813492028L;

	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(newRecord) {
			//	Get SeqNo
			if (getSeqNo() == 0)
			{
				String sql = "SELECT COALESCE(MAX(SeqNo),0)+10 FROM TF_TyreLife";
				int ii = DB.getSQLValue (get_TrxName(), sql);
				setSeqNo(ii);
			}
		}
		return super.beforeSave(newRecord);
	}	
}
