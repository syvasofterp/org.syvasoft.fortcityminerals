package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MTyreStatusChange extends X_TF_TyreStatusChange {

	public MTyreStatusChange(Properties ctx, int TF_TyreStatusChange_ID,
			String trxName) {
		super(ctx, TF_TyreStatusChange_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MTyreStatusChange(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8261510390583803977L;

}
