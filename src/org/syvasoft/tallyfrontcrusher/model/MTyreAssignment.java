package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MTyreAssignment extends X_TF_TyreAssignment {

	
	public MTyreAssignment(Properties ctx, int TF_TyreAssignment_ID,
			String trxName) {
		super(ctx, TF_TyreAssignment_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MTyreAssignment(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
