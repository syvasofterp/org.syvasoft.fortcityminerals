package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MSysConfig;
import org.compiere.util.DB;

public class TF_MOrderLine extends MOrderLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5835318473934629869L;

	public TF_MOrderLine(MOrder order) {
		super(order);
		// TODO Auto-generated constructor stub
	}

	public TF_MOrderLine(Properties ctx, int C_OrderLine_ID, String trxName) {
		super(ctx, C_OrderLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public TF_MOrderLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/** Column name IsUpdatePrice */
    public static final String COLUMNNAME_IsUpdatePrice = "IsUpdatePrice";
    /** Set Update Price.
	@param IsUpdatePrice 
	Update Price into Price List
	  */
	public void setIsUpdatePrice (boolean IsUpdatePrice)
	{
		set_Value (COLUMNNAME_IsUpdatePrice, Boolean.valueOf(IsUpdatePrice));
	}
	
	/** Get Update Price.
		@return Update Price into Price List
	  */
	public boolean isUpdatePrice () 
	{
		Object oo = get_Value(COLUMNNAME_IsUpdatePrice);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {		
		Boolean ok = super.afterSave(newRecord, success);
		updateOrderTonnage();
		if(isUpdatePrice()) {
			TF_MOrder.updateProductPricing(getM_Product_ID(), getC_Order().getM_PriceList_ID(), getC_BPartner_ID(), 
					getQtyEntered(), getPriceEntered(), getC_Order().getDateOrdered(), getC_Order().isSOTrx());
		}
		return ok;
	}	
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		setQtyReserved(BigDecimal.ZERO);
		TF_MOrder.addProductPricingIfNot(getM_Product_ID(), getC_Order().getM_PriceList_ID(), getC_BPartner_ID(), 
				getQtyEntered(), getPriceEntered(), getC_Order().getDateOrdered(), getC_Order().isSOTrx());
		return super.beforeSave(newRecord);
	}

	@Override
	protected boolean beforeDelete() {
		setQtyReserved(BigDecimal.ZERO);
		return super.beforeDelete();
	}

	@Override
	protected boolean afterDelete(boolean success) {		
		boolean ok =  super.afterDelete(success);
		updateOrderTonnage();
		return ok;
	}

	private void updateOrderTonnage() {
		int tonnage_uom_id = MSysConfig.getIntValue("TONNAGE_UOM", 1000069, getAD_Client_ID());
		String sql = " SELECT SUM(QtyEntered) FROM C_OrderLine WHERE C_Order_ID=? AND C_UOM_ID=?";
		BigDecimal tonnage = DB.getSQLValueBD(get_TrxName(), sql, getC_Order_ID(), tonnage_uom_id);
		if(tonnage == null)
			tonnage = BigDecimal.ZERO;
		
		sql = "UPDATE C_Order SET Tonnage = " + tonnage.doubleValue() + " WHERE C_Order_ID = " + getC_Order_ID();
		DB.executeUpdate(sql, get_TrxName());
				
		sql = "UPDATE C_Order SET Rent_Amt = Distance * Rate * Tonnage WHERE C_Order_ID = " + getC_Order_ID()
			+ " AND TF_Destination_ID IS NOT NULL AND TF_RentedVehicle_ID IS NOT NULL";
		DB.executeUpdate(sql, get_TrxName());
		
	}

	
}
