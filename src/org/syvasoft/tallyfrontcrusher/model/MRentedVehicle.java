package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MProduct;
import org.compiere.model.Query;
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
	protected boolean beforeSave(boolean newRecord) {
		// TODO Auto-generated method stub

		if(!newRecord && isTransporter() && is_ValueChanged(COLUMNNAME_TareWeight)) {
			if(get_ValueOld(COLUMNNAME_TareWeight)!=null) {
				BigDecimal TareWeight=(BigDecimal)get_ValueOld(COLUMNNAME_TareWeight);
				setOldTareweight(TareWeight);
			}
		}
		boolean ok = super.beforeSave(newRecord);
		return ok;
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
		prod.set_ValueOfColumn("IsRented", true);
		prod.saveEx();
		if(getM_Product_ID() == 0) {
			DB.executeUpdate("UPDATE TF_RentedVehicle SET M_Product_ID = " + prod.getM_Product_ID() +
					" WHERE TF_RentedVehicle_ID = " + getTF_RentedVehicle_ID(), get_TrxName());			
		}
		
		//Add all the destinations to the Rent Configuration with the default rate.
		if(newRecord && isRequireRentConfig()) {
			List<MDestination> destinations = new Query(getCtx(), MDestination.Table_Name, "AD_Org_ID IN (0,?)", get_TrxName())
					.setOnlyActiveRecords(true).setParameters(getAD_Org_ID()).setOrderBy("Name").list();
			for(MDestination dest : destinations) {
				MVehicleRentConfig rentConfig = new MVehicleRentConfig(getCtx(), 0, get_TrxName());
				rentConfig.setAD_Org_ID(getAD_Org_ID());
				rentConfig.setM_Product_ID(prod.getM_Product_ID());
				rentConfig.setTF_Destination_ID(dest.getTF_Destination_ID());
				rentConfig.setRate(dest.getRate());
				rentConfig.setIsActive(true);
				rentConfig.saveEx();
			}
			
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
