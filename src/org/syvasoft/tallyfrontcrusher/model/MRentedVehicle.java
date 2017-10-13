package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MProduct;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MRentedVehicle extends X_TF_RentedVehicle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2641346720054190146L;

	public MRentedVehicle(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MRentedVehicle(Properties ctx, int TF_RentedVehicle_ID, String trxName) {
		super(ctx, TF_RentedVehicle_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {		
		boolean ok = super.afterSave(newRecord, success);
		int M_Product_ID = getM_Product_ID();		
		MProduct prod = new MProduct(getCtx(), M_Product_ID, get_TrxName());
		prod.setAD_Org_ID(getAD_Org_ID());
		prod.setValue(getVehicleNo());
		prod.setName(getVehicleNo());
		prod.setC_UOM_ID(getC_UOM_ID());
		prod.setM_Product_Category_ID(getM_Product_Category_ID());
		prod.setProductType(MProduct.PRODUCTTYPE_Resource);
		prod.setIsPurchased(true);
		prod.setIsSold(true);
		prod.setDescription("Rented from " + getC_BPartner().getName());
		prod.setIsActive(isActive());
		prod.setC_TaxCategory_ID(Env.getContextAsInt(getCtx(), "#C_TaxCategory_ID"));
		prod.saveEx();
		if(getM_Product_ID() == 0) {
			DB.executeUpdate("UPDATE TF_RentedVehicle SET M_Product_ID = " + prod.getM_Product_ID() +
					" WHERE TF_RentedVehicle_ID = " + getTF_RentedVehicle_ID(), get_TrxName());
		}
		return ok;
	}

	@Override
	protected boolean afterDelete(boolean success) {		
		boolean ok = super.afterDelete(success);
		MProduct prod = MProduct.get(getCtx(), getM_Product_ID());
		prod.deleteEx(true, get_TrxName());		
		return ok;
	}
	
	

}
