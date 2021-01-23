package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;

public class MBoulderMovement extends X_TF_Boulder_Movement {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4027717865768847637L;

	public MBoulderMovement(Properties ctx, int TF_Boulder_Movement_ID, String trxName) {
		super(ctx, TF_Boulder_Movement_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MBoulderMovement(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static void createBoulderReceipt(String trxName, Timestamp dateMovement,int AD_Org_ID,  
			int M_Product_ID, BigDecimal QtyReceipt, int TF_WeighmentEntry_ID, int M_Warehouse_ID) {	
		
		MBoulderMovement bm = new MBoulderMovement(Env.getCtx(), 0, trxName);
		bm.setAD_Org_ID(AD_Org_ID);
		bm.setMovementDate(dateMovement);		
		bm.setM_Product_ID(M_Product_ID);
		bm.setQty_Receipt(QtyReceipt);
		bm.setTF_WeighmentEntry_ID(TF_WeighmentEntry_ID);		
		bm.setM_Warehouse_ID(M_Warehouse_ID);
		bm.saveEx();
	}
	
	public static void createBoulderIssue(String trxName, Timestamp dateMovement,int AD_Org_ID,  
			int M_Product_ID, BigDecimal QtyPayment, int TF_WeighmentEntry_ID, int M_Warehouse_ID) {	
		
		MBoulderMovement bm = new MBoulderMovement(Env.getCtx(), 0, trxName);
		bm.setAD_Org_ID(AD_Org_ID);
		bm.setMovementDate(dateMovement);		
		bm.setM_Product_ID(M_Product_ID);
		bm.setQty_Payment(QtyPayment);
		bm.setTF_WeighmentEntry_ID(TF_WeighmentEntry_ID);
		bm.setM_Warehouse_ID(M_Warehouse_ID);
		bm.saveEx();
		
	}
	
	public static void deleteBoulderMovement(int TF_WeighmentEntry_ID, String trxName) {
		String sql = "DELETE FROM TF_Boulder_Movement WHERE TF_WeighmentEntry_ID = " + TF_WeighmentEntry_ID;
		DB.executeUpdate(sql, trxName);
	}
	
	
}
