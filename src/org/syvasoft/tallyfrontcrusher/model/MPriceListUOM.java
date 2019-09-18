package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.Query;

public class MPriceListUOM extends X_TF_PriceListUOM {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1724960450410275886L;

	public MPriceListUOM(Properties ctx, int TF_PriceListUOM_ID, String trxName) {
		super(ctx, TF_PriceListUOM_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MPriceListUOM(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static BigDecimal getPrice(Properties ctx, int M_Product_ID, int C_UOM_ID, 
			int C_BPartner_ID, boolean isSOTrx) {
		String whereClause = "M_Product_ID = ? AND C_UOM_ID = ? AND IsSOTrx=? "
				+ " AND C_BPartner_ID "
				+ (C_BPartner_ID == 0 ? " IS NULL " : " = " + C_BPartner_ID);
		MPriceListUOM priceUOM = new Query(ctx, Table_Name, whereClause, null)
				.setClient_ID()
				.setParameters(M_Product_ID, C_UOM_ID, isSOTrx ? "Y" : "N")
				.first();
		if(priceUOM != null) {
			return priceUOM.getPrice();
		}
		else {
			whereClause = "M_Product_ID = ? AND C_UOM_ID = ? AND IsSOTrx=? "
					+ " AND C_BPartner_ID IS NULL";
			priceUOM = new Query(ctx, Table_Name, whereClause, null)
					.setClient_ID()
					.setParameters(M_Product_ID, C_UOM_ID, isSOTrx ? "Y" : "N")
					.first();
			if(priceUOM != null) {
				return priceUOM.getPrice();
			}
			else {
				return BigDecimal.ZERO;
			}
		}
			
	}
}
