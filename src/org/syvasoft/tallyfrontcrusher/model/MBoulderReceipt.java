package org.syvasoft.tallyfrontcrusher.model;



import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

public class MBoulderReceipt extends X_TF_Boulder_Receipt {

		
	/**
	 * 
	 */
	private static final long serialVersionUID = -6229740359935434019L;

	public MBoulderReceipt(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MBoulderReceipt(Properties ctx, int TF_Boulder_Receipt_ID,
			String trxName) {
		super(ctx, TF_Boulder_Receipt_ID, trxName);
		// TODO Auto-generated constructor stub
	}

}
