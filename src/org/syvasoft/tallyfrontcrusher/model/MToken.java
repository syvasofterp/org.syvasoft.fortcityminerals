package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;

public class MToken extends X_TF_Token {
	
	private static final long serialVersionUID = -8086398266293054622L;

	public MToken(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MToken(Properties ctx, int TF_Destination_ID, String trxName) {
		super(ctx, TF_Destination_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public void close() {
		if(isProcessed())
			throw new AdempiereException("Token No is already closed!");
		setStatus(STATUS_Closed);
		setProcessed(true);		
	}
	public void reverse() {
		setStatus(STATUS_Open);
		setProcessed(false);
	}
	
}
