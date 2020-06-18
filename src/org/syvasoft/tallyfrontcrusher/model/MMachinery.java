package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;

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

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		// TODO Auto-generated method stub
		boolean ok = super.afterSave(newRecord, success);
		int M_Product_ID = getM_Product_ID();		
		if(M_Product_ID==0) {
			MProduct prod = new MProduct(getCtx(), M_Product_ID, get_TrxName());
			MMachineryType mType=new MMachineryType(getCtx(), getPM_MachineryType_ID(), get_TrxName());
			
			TF_MOrg org = new TF_MOrg(getCtx(), getAD_Org_ID(), get_TrxName());
			prod.setAD_Org_ID(getAD_Org_ID());		
			prod.setValue(org.getValue() + "_" + getMachineryNo());
			prod.setName(getMachineryNo());
			prod.setC_UOM_ID(mType.getC_UOM_ID());
			prod.setM_Product_Category_ID(mType.getM_Product_Category_ID());
			prod.setProductType(MProduct.PRODUCTTYPE_Resource);
			prod.setIsPurchased(false);
			prod.setIsSold(false);
			prod.set_ValueOfColumn("IsRented", false);
			prod.setIsActive(isActive());
			prod.setC_TaxCategory_ID(Env.getContextAsInt(getCtx(), "#C_TaxCategory_ID"));
			prod.saveEx();
			
			if(getM_Product_ID() == 0) {
				DB.executeUpdate("UPDATE PM_Machinery SET M_Product_ID = " + prod.getM_Product_ID() +
						" WHERE PM_Machinery_ID = " + getPM_Machinery_ID(), get_TrxName());			
			}

		}
		return ok;
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
	public static int getPM_Product_ID(Properties ctx, int PM_Machinery_ID, String trxName) {
		String whereClause = "PM_Machinery_ID = ?";
		MMachinery m = new Query(ctx, Table_Name, whereClause, trxName)
				.setClient_ID()
				.setParameters(PM_Machinery_ID)
				.first();
		if(m != null) {
			return m.getM_Product_ID();
		}
		else {
			return 0;
		}
	}

}
