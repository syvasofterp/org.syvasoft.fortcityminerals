package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MTyreMovement extends X_TF_TyreMovement {

	public MTyreMovement(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

	public MTyreMovement(Properties ctx, int TF_TyreMovement_ID, String trxName) {
		super(ctx, TF_TyreMovement_ID, trxName);
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 609598185867877264L;

	

}
