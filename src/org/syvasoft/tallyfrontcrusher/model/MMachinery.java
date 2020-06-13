package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.Query;

public class MMachinery extends X_PM_Machinery {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8111796787587809642L;

	public MMachinery(Properties ctx, int PM_Machinery_ID, String trxName) {
		super(ctx, PM_Machinery_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMachinery(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static int getPM_Machinery_ID(Properties ctx, int M_Product_ID, String trxName) {
		String whereClause = "M_Product_ID = ?";
		MMachinery m = new Query(ctx, Table_Name, whereClause, trxName)
				.setClient_ID()
				.setParameters(M_Product_ID)
				.first();
		if(m != null) {
			return m.get_ID();
		}
		else {
			return 0;
		}
	}
}
