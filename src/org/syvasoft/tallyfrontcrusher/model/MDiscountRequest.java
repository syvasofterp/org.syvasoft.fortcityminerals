package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDiscountRequest extends X_TF_DiscountRequest {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6077541940782954617L;

	public MDiscountRequest(Properties ctx, int TF_DiscountRequest_ID, String trxName) {
		super(ctx, TF_DiscountRequest_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDiscountRequest(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	
}
