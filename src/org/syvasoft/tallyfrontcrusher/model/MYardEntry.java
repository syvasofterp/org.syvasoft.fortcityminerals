package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MYardEntry extends X_TF_YardEntry {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2551140800670796232L;

	public MYardEntry(Properties ctx, int TF_YardEntry_ID, String trxName) {
		super(ctx, TF_YardEntry_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MYardEntry(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
