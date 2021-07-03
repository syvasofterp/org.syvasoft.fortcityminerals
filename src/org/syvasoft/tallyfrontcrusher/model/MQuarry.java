package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MQuarry extends X_TF_Quarry {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6167828883070286025L;

	public MQuarry(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MQuarry(Properties ctx, int TF_Quarry_ID, String trxName) {
		super(ctx, TF_Quarry_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public void setCurrentConsumedQty() {
		String sql = "SELECT SUM(PermitQty) FROM TF_PermitPurchase WHERE TF_Quarry_ID = ? AND"
				+ " DocStatus = 'CO' ";
		BigDecimal consumedQty = DB.getSQLValueBD(get_TrxName(), sql, getTF_Quarry_ID());
		if(consumedQty == null)
			consumedQty = BigDecimal.ZERO;		
		setQtyConsumed(consumedQty);				
	}
	
	public static MQuarry getQuarry(String MLNo, String WeighmentType, int C_BPartner_ID, int M_Product_ID, int TF_Destination_ID, int C_UOM_ID, int TF_Quarry_ID) {
		String where = "Value = '" + MLNo + "' AND WeighmentEntryType = '" + WeighmentType + "'";
		
		if(C_BPartner_ID > 0) {
			where  = where + " AND C_BPartner_ID = " + C_BPartner_ID;
		}
		else {
			where = where + " AND C_BPartner_ID IS NULL";
		}
		
		if(M_Product_ID > 0) {
			where = where + " AND M_Product_ID = " + M_Product_ID;
		}
		else {
			where = where + " AND M_Product_ID IS NULL";
		}
		
		if(TF_Destination_ID > 0) {
			where = where + " AND TF_Destination_ID = " + TF_Destination_ID;
		}
		else {
			where = where + " AND TF_Destination_ID IS NULL";
		}
		
		if(C_UOM_ID > 0) {
			where = where + " AND C_UOM_ID = " + C_UOM_ID;
		}
		else {
			where = where + " AND C_UOM_ID IS NULL";
		}
		
		if(TF_Quarry_ID > 0) {
			where = where + "AND TF_Quarry_ID <> " + TF_Quarry_ID;
		}
		MQuarry quarrylsit = new Query(Env.getCtx(), MQuarry.Table_Name, where, null).first();
		return quarrylsit;
	}
	@Override
	protected boolean beforeSave(boolean newRecord) {
		
		MQuarry quarry = getQuarry(getValue(), getWeighmentEntryType(), getC_BPartner_ID(), getM_Product_ID(), getTF_Destination_ID(), getC_UOM_ID(), getTF_Quarry_ID());
		
		if(quarry != null) {
			throw new AdempiereException("Quarry already exists in selected combination");
		}
		
		boolean ok = super.beforeSave(newRecord);
		return ok;
	}
}
