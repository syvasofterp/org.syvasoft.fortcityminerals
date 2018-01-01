package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MPermitLedgerLine extends X_TF_PermitLedgerLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = -819528765567044826L;

	public MPermitLedgerLine(Properties ctx, int TF_PermitLedgerLine_ID, String trxName) {
		super(ctx, TF_PermitLedgerLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public MPermitLedgerLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}	

}
