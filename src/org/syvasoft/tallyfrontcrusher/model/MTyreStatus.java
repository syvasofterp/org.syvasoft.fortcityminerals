package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;

public class MTyreStatus extends X_TF_TyreStatus {

	public MTyreStatus(Properties ctx, int TF_TyreStatus_ID, String trxName) {
		super(ctx, TF_TyreStatus_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MTyreStatus(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5985475381519747291L;
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(newRecord) {
			//	Get SeqNo
			if (getSeqNo() == 0)
			{
				String sql = "SELECT COALESCE(MAX(SeqNo),0)+10 FROM TF_TyreStatus";
				int ii = DB.getSQLValue (get_TrxName(), sql);
				setSeqNo(ii);
			}
		}
		return super.beforeSave(newRecord);
	}
}
