package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MSysConfig;
import org.compiere.util.DB;
import org.compiere.util.Env;

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
	
	/** Column name IsPermitSales */
    public static final String COLUMNNAME_IsPermitSales = "IsPermitSales";
    /** Set Permit Sales.
	@param IsPermitSales Permit Sales	  */
	public void setIsPermitSales (boolean IsPermitSales)
	{
		set_Value (COLUMNNAME_IsPermitSales, Boolean.valueOf(IsPermitSales));
	}
	
	/** Get Permit Sales.
		@return Permit Sales	  */
	public boolean isPermitSales () 
	{
		Object oo = get_Value(COLUMNNAME_IsPermitSales);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Column name BucketQty */
    public static final String COLUMNNAME_BucketQty = "BucketQty";
    /** Set Bucket Qty.
	@param BucketQty Bucket Qty	  */
	public void setBucketQty (BigDecimal BucketQty)
	{
		set_Value (COLUMNNAME_BucketQty, BucketQty);
	}
	
	/** Get Bucket Qty.
		@return Bucket Qty	  */
	public BigDecimal getBucketQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BucketQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	/** Column name TonePerBucket */
    public static final String COLUMNNAME_TonePerBucket = "TonePerBucket";
    /** Set Tone (per Bucket).
	@param TonePerBucket Tone (per Bucket)	  */
	public void setTonePerBucket (BigDecimal TonePerBucket)
	{
		set_Value (COLUMNNAME_TonePerBucket, TonePerBucket);
	}
	
	/** Get Tone (per Bucket).
		@return Tone (per Bucket)	  */
	public BigDecimal getTonePerBucket () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TonePerBucket);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
    /** Column name BucketRate */
    public static final String COLUMNNAME_BucketRate = "BucketRate";
    /** Set Bucket Rate.
	@param BucketRate Bucket Rate	  */
	public void setBucketRate (BigDecimal BucketRate)
	{
		set_Value (COLUMNNAME_BucketRate, BucketRate);
	}
	
	/** Column name SandType */
    public static final String COLUMNNAME_SandType = "SandType";
    /** Permit Sand = PM */
	public static final String SANDTYPE_PermitSand = "PM";
	/** Extra Bucket = EX */
	public static final String SANDTYPE_ExtraBucket = "EX";
	/** Without Permit = WP */
	public static final String SANDTYPE_WithoutPermit = "WP";
	/** Set Sand Type.
		@param SandType Sand Type	  */
	public void setSandType (String SandType)
	{

		set_Value (COLUMNNAME_SandType, SandType);
	}

	/** Get Sand Type.
		@return Sand Type	  */
	public String getSandType () 
	{
		return (String)get_Value(COLUMNNAME_SandType);
	}

	
	/** Get Bucket Rate.
		@return Bucket Rate	  */
	public BigDecimal getBucketRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BucketRate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Column name TotalLoad */
    public static final String COLUMNNAME_TotalLoad = "TotalLoad";
    /** Set Total Load.
	@param TotalLoad Total Load	  */
	public void setTotalLoad (BigDecimal TotalLoad)
	{
		set_Value (COLUMNNAME_TotalLoad, TotalLoad);
	}
	
	/** Column name TF_VehicleType_ID */
    public static final String COLUMNNAME_TF_VehicleType_ID = "TF_VehicleType_ID";
    /** Set Vehicle Type.
	@param TF_VehicleType_ID Vehicle Type	  */
	public void setTF_VehicleType_ID (int TF_VehicleType_ID)
	{
		if (TF_VehicleType_ID < 1) 
			set_Value (COLUMNNAME_TF_VehicleType_ID, null);
		else 
			set_Value (COLUMNNAME_TF_VehicleType_ID, Integer.valueOf(TF_VehicleType_ID));
	}
	
	/** Get Vehicle Type.
		@return Vehicle Type	  */
	public int getTF_VehicleType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_VehicleType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Get Total Load.
		@return Total Load	  */
	public BigDecimal getTotalLoad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalLoad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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
		int kg_uom_id = MSysConfig.getIntValue("KG_UOM", 1000070, getAD_Client_ID());
		String sql = " SELECT SUM(CASE WHEN C_UOM_ID = ? THEN QtyEntered/1000 ELSE QtyEntered END) FROM C_OrderLine WHERE C_Order_ID=? AND C_UOM_ID IN (?,?)";
		BigDecimal tonnage = DB.getSQLValueBD(get_TrxName(), sql, kg_uom_id, getC_Order_ID(), tonnage_uom_id,kg_uom_id);
		if(tonnage == null)
			tonnage = BigDecimal.ZERO;
		
		sql = "UPDATE C_Order SET Tonnage = " + tonnage.doubleValue() + " WHERE C_Order_ID = " + getC_Order_ID();
		DB.executeUpdate(sql, get_TrxName());
				
		sql = "UPDATE C_Order SET Rent_Amt = Distance * Rate WHERE C_Order_ID = " + getC_Order_ID()
			+ " AND TF_Destination_ID IS NOT NULL AND TF_RentedVehicle_ID IS NOT NULL AND IsLumpSumRent='N'";
		DB.executeUpdate(sql, get_TrxName());
	}

	
}
