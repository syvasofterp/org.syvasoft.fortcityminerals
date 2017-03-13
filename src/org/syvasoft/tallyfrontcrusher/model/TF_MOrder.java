package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MInOut;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProductPrice;
import org.compiere.model.MProductPricing;
import org.compiere.model.MResource;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class TF_MOrder extends MOrder {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1285576200459103590L;

	public TF_MOrder(Properties ctx, int C_Order_ID, String trxName) {
		super(ctx, C_Order_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public TF_MOrder(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

	 /** Column name Item1_Amt */
    public static final String COLUMNNAME_Item1_Amt = "Item1_Amt";
    
    /** Set Item1 Amount.
	@param Item1_Amt Item1 Amount	  */
	public void setItem1_Amt (BigDecimal Item1_Amt)
	{
		set_Value (COLUMNNAME_Item1_Amt, Item1_Amt);
	}
	
	/** Get Item1 Amount.
		@return Item1 Amount	  */
	public BigDecimal getItem1_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item1_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Column name Item1_C_OrderLine_ID */
    public static final String COLUMNNAME_Item1_C_OrderLine_ID = "Item1_C_OrderLine_ID";
    
	public org.compiere.model.I_C_OrderLine getItem1_C_OrderLine() throws RuntimeException
    {
		return (org.compiere.model.I_C_OrderLine)MTable.get(getCtx(), org.compiere.model.I_C_OrderLine.Table_Name)
			.getPO(getItem1_C_OrderLine_ID(), get_TrxName());	}

	/** Set Item1 OrderLine ID.
		@param Item1_C_OrderLine_ID Item1 OrderLine ID	  */
	public void setItem1_C_OrderLine_ID (int Item1_C_OrderLine_ID)
	{
		if (Item1_C_OrderLine_ID < 1) 
			set_Value (COLUMNNAME_Item1_C_OrderLine_ID, null);
		else 
			set_Value (COLUMNNAME_Item1_C_OrderLine_ID, Integer.valueOf(Item1_C_OrderLine_ID));
	}

	/** Get Item1 OrderLine ID.
		@return Item1 OrderLine ID	  */
	public int getItem1_C_OrderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Item1_C_OrderLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Column name Item1_ID */
    public static final String COLUMNNAME_Item1_ID = "Item1_ID";
    
    public org.compiere.model.I_M_Product getItem1() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getItem1_ID(), get_TrxName());	}

	/** Set Item1.
		@param Item1_ID Item1	  */
	public void setItem1_ID (int Item1_ID)
	{
		if (Item1_ID < 1) 
			set_Value (COLUMNNAME_Item1_ID, null);
		else 
			set_Value (COLUMNNAME_Item1_ID, Integer.valueOf(Item1_ID));
	}

	/** Get Item1.
		@return Item1	  */
	public int getItem1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Item1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name Item1_Price */
    public static final String COLUMNNAME_Item1_Price = "Item1_Price";
    
    /** Set Item1 Price.
	@param Item1_Price Item1 Price	  */
	public void setItem1_Price (BigDecimal Item1_Price)
	{
		set_Value (COLUMNNAME_Item1_Price, Item1_Price);
	}
	
	/** Get Item1 Price.
		@return Item1 Price	  */
	public BigDecimal getItem1_Price () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item1_Price);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	/** Column name Item1_Qty */
	public static final String COLUMNNAME_Item1_Qty = "Item1_Qty";
	
	/** Set Item1 Qty.
	@param Item1_Qty Item1 Qty	  */
	public void setItem1_Qty (BigDecimal Item1_Qty)
	{
	set_Value (COLUMNNAME_Item1_Qty, Item1_Qty);
	}
	
	/** Get Item1 Qty.
	@return Item1 Qty	  */
	public BigDecimal getItem1_Qty () 
	{
	BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item1_Qty);
	if (bd == null)
		 return Env.ZERO;
	return bd;
	}
	
	/** Column name Item2_Amt */
	public static final String COLUMNNAME_Item2_Amt = "Item2_Amt";
	
	/** Set Item2 Amount.
	@param Item2_Amt Item2 Amount	  */
	public void setItem2_Amt (BigDecimal Item2_Amt)
	{
	set_Value (COLUMNNAME_Item2_Amt, Item2_Amt);
	}
	
	/** Get Item2 Amount.
	@return Item2 Amount	  */
	public BigDecimal getItem2_Amt () 
	{
	BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item2_Amt);
	if (bd == null)
		 return Env.ZERO;
	return bd;
	}
	
	/** Column name Item2_C_OrderLine_ID */
	public static final String COLUMNNAME_Item2_C_OrderLine_ID = "Item2_C_OrderLine_ID";
	
	public org.compiere.model.I_C_OrderLine getItem2_C_OrderLine() throws RuntimeException
	{
		return (org.compiere.model.I_C_OrderLine)MTable.get(getCtx(), org.compiere.model.I_C_OrderLine.Table_Name)
			.getPO(getItem2_C_OrderLine_ID(), get_TrxName());	}
	
	/** Set Item2 OrderLine ID.
		@param Item2_C_OrderLine_ID Item2 OrderLine ID	  */
	public void setItem2_C_OrderLine_ID (int Item2_C_OrderLine_ID)
	{
		if (Item2_C_OrderLine_ID < 1) 
			set_Value (COLUMNNAME_Item2_C_OrderLine_ID, null);
		else 
			set_Value (COLUMNNAME_Item2_C_OrderLine_ID, Integer.valueOf(Item2_C_OrderLine_ID));
	}
	
	/** Get Item2 OrderLine ID.
		@return Item2 OrderLine ID	  */
	public int getItem2_C_OrderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Item2_C_OrderLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name Item2_ID */
	public static final String COLUMNNAME_Item2_ID = "Item2_ID";
	
	public org.compiere.model.I_M_Product getItem2() throws RuntimeException
	{
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getItem2_ID(), get_TrxName());	}
	
	/** Set Item2.
		@param Item2_ID Item2	  */
	public void setItem2_ID (int Item2_ID)
	{
		if (Item2_ID < 1) 
			set_Value (COLUMNNAME_Item2_ID, null);
		else 
			set_Value (COLUMNNAME_Item2_ID, Integer.valueOf(Item2_ID));
	}
	
	/** Get Item2.
		@return Item2	  */
	public int getItem2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Item2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name Item2_Price */
	public static final String COLUMNNAME_Item2_Price = "Item2_Price";
	
	/** Set Item2 Price.
	@param Item2_Price Item2 Price	  */
	public void setItem2_Price (BigDecimal Item2_Price)
	{
	set_Value (COLUMNNAME_Item2_Price, Item2_Price);
	}
	
	/** Get Item2 Price.
	@return Item2 Price	  */
	public BigDecimal getItem2_Price () 
	{
	BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item2_Price);
	if (bd == null)
		 return Env.ZERO;
	return bd;
	}
	
	
	/** Column name Item2_Qty */
	public static final String COLUMNNAME_Item2_Qty = "Item2_Qty";
	
	/** Set Item2 Qty.
	@param Item2_Qty Item2 Qty	  */
	public void setItem2_Qty (BigDecimal Item2_Qty)
	{
	set_Value (COLUMNNAME_Item2_Qty, Item2_Qty);
	}
	
	/** Get Item2 Qty.
	@return Item2 Qty	  */
	public BigDecimal getItem2_Qty () 
	{
	BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item2_Qty);
	if (bd == null)
		 return Env.ZERO;
	return bd;
	}

	/** Column name Vehicle_ID */
    public static final String COLUMNNAME_Vehicle_ID = "Vehicle_ID";
    
    public org.compiere.model.I_C_OrderLine getVehicle_C_OrderLine() throws RuntimeException
    {
		return (org.compiere.model.I_C_OrderLine)MTable.get(getCtx(), org.compiere.model.I_C_OrderLine.Table_Name)
			.getPO(getVehicle_C_OrderLine_ID(), get_TrxName());	}

    /** Column name Vehicle_C_OrderLine_ID */
    public static final String COLUMNNAME_Vehicle_C_OrderLine_ID = "Vehicle_C_OrderLine_ID";
	/** Set Vehicle OrderLine ID.
		@param Vehicle_C_OrderLine_ID Vehicle OrderLine ID	  */
	public void setVehicle_C_OrderLine_ID (int Vehicle_C_OrderLine_ID)
	{
		if (Vehicle_C_OrderLine_ID < 1) 
			set_Value (COLUMNNAME_Vehicle_C_OrderLine_ID, null);
		else 
			set_Value (COLUMNNAME_Vehicle_C_OrderLine_ID, Integer.valueOf(Vehicle_C_OrderLine_ID));
	}

	/** Get Vehicle OrderLine ID.
		@return Vehicle OrderLine ID	  */
	public int getVehicle_C_OrderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Vehicle_C_OrderLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getVehicle() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getVehicle_ID(), get_TrxName());	}

	/** Set Vehicle.
		@param Vehicle_ID Vehicle	  */
	public void setVehicle_ID (int Vehicle_ID)
	{
		if (Vehicle_ID < 1) 
			set_Value (COLUMNNAME_Vehicle_ID, null);
		else 
			set_Value (COLUMNNAME_Vehicle_ID, Integer.valueOf(Vehicle_ID));
	}

	/** Get Vehicle.
		@return Vehicle	  */
	public int getVehicle_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Vehicle_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	 /** Column name VehicleNo */
    public static final String COLUMNNAME_VehicleNo = "VehicleNo";

	/** Set Vehicle No.
		@param VehicleNo Vehicle No	  */
	public void setVehicleNo (String VehicleNo)
	{
		set_Value (COLUMNNAME_VehicleNo, VehicleNo);
	}

	/** Get Vehicle No.
		@return Vehicle No	  */
	public String getVehicleNo () 
	{
		return (String)get_Value(COLUMNNAME_VehicleNo);
	}
    
	 /** Column name Rent_Amt */
    public static final String COLUMNNAME_Rent_Amt = "Rent_Amt";
    
    /** Set Rent (Amount).
	@param Rent_Amt Rent (Amount)	  */
	public void setRent_Amt (BigDecimal Rent_Amt)
	{
		set_Value (COLUMNNAME_Rent_Amt, Rent_Amt);
	}
	
	/** Get Rent (Amount).
		@return Rent (Amount)	  */
	public BigDecimal getRent_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rent_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
    
	 /** Column name TF_DriverTips_Pay_ID */
    public static final String COLUMNNAME_TF_DriverTips_Pay_ID = "TF_DriverTips_Pay_ID";
    public org.compiere.model.I_C_Payment getTF_DriverTips_Pay() throws RuntimeException
    {
		return (org.compiere.model.I_C_Payment)MTable.get(getCtx(), org.compiere.model.I_C_Payment.Table_Name)
			.getPO(getTF_DriverTips_Pay_ID(), get_TrxName());	}

	/** Set Driver Tips Payment.
		@param TF_DriverTips_Pay_ID Driver Tips Payment	  */
	public void setTF_DriverTips_Pay_ID (int TF_DriverTips_Pay_ID)
	{
		if (TF_DriverTips_Pay_ID < 1) 
			set_Value (COLUMNNAME_TF_DriverTips_Pay_ID, null);
		else 
			set_Value (COLUMNNAME_TF_DriverTips_Pay_ID, Integer.valueOf(TF_DriverTips_Pay_ID));
	}

	/** Get Driver Tips Payment.
		@return Driver Tips Payment	  */
	public int getTF_DriverTips_Pay_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_DriverTips_Pay_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	 /** Column name DriverTips */
    public static final String COLUMNNAME_DriverTips = "DriverTips";
    /** Set Driver Tips.
	@param DriverTips Driver Tips	  */
	public void setDriverTips (BigDecimal DriverTips)
	{
		set_Value (COLUMNNAME_DriverTips, DriverTips);
	}
	
	/** Get Driver Tips.
		@return Driver Tips	  */
	public BigDecimal getDriverTips () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DriverTips);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {		
		success = super.afterSave(newRecord, success);
		updateQuickOrderLines();
		updateVehicleRentLine();
		return success;
	}
	
	private void setLinePrice(MOrderLine line, BigDecimal price) {
		line.setPriceActual(price);
		line.setPriceList(price);
		line.setPriceLimit(price);
		line.setPriceEntered(price);
	}
	
	private void setOrderLine(MOrderLine line, int product_ID, BigDecimal qty, BigDecimal price) {
		line.setM_Product_ID(product_ID, true);
		line.setQty(qty);
		setLinePrice(line, price);
	}
	
	public void updateQuickOrderLines() {
		MOrderLine ordLine = null;
		//Delete empty item lines
		if(is_ValueChanged(COLUMNNAME_Item1_ID) || (getItem1_ID() == 0 && getItem1_C_OrderLine_ID() > 0)) {
			ordLine = new MOrderLine(getCtx(), getItem1_C_OrderLine_ID(), get_TrxName());
			if(ordLine.get_ID() > 0) {
				DB.executeUpdate("UPDATE C_Order SET " + COLUMNNAME_Item1_C_OrderLine_ID + " = NULL " +
						" WHERE C_Order_ID =" + getC_Order_ID(), get_TrxName());
				ordLine.setQtyReserved(BigDecimal.ZERO);
				ordLine.delete(false);
				setItem1_C_OrderLine_ID(0);
			}
		}
		if(is_ValueChanged(COLUMNNAME_Item2_ID) || (getItem2_ID() == 0 && getItem2_C_OrderLine_ID() > 0)) {
			ordLine = new MOrderLine(getCtx(), getItem2_C_OrderLine_ID(), get_TrxName());
			if(ordLine.get_ID() > 0) {
				DB.executeUpdate("UPDATE C_Order SET " + COLUMNNAME_Item2_C_OrderLine_ID + " = NULL " +
						" WHERE C_Order_ID =" + getC_Order_ID(), get_TrxName());
				ordLine.setQtyReserved(BigDecimal.ZERO);				
				ordLine.delete(false);
				setItem2_C_OrderLine_ID(0);
			}
		} // End Delete
		
		//Update modified item lines.		
		//Item1
		if(getItem1_ID() > 0 && (is_ValueChanged(COLUMNNAME_Item1_ID) || is_ValueChanged(COLUMNNAME_Item1_Qty)
				|| is_ValueChanged(COLUMNNAME_Item1_Price) || getItem1_C_OrderLine_ID() == 0)) {
			
			if(getItem1_C_OrderLine_ID() > 0) 
				ordLine = new MOrderLine(getCtx(), getItem1_C_OrderLine_ID(), get_TrxName());
			else
				ordLine = new MOrderLine(this);
			TF_MOrder.addProductPricingIfNot(getItem1_ID(), getM_PriceList_ID(), getC_BPartner_ID(), getItem1_Qty(), getItem1_Price(), 
					getDateOrdered(), getC_DocType().isSOTrx());
			setOrderLine(ordLine, getItem1_ID(), getItem1_Qty(), getItem1_Price());									
			ordLine.saveEx();			
			DB.executeUpdate("UPDATE C_Order SET " + COLUMNNAME_Item1_C_OrderLine_ID + " = "
				+ ordLine.getC_OrderLine_ID() + " WHERE C_Order_ID = " + getC_Order_ID(), get_TrxName());	
		}
		//Item 2
		if(getItem2_ID() > 0 && (is_ValueChanged(COLUMNNAME_Item2_ID) || is_ValueChanged(COLUMNNAME_Item2_Qty)
				|| is_ValueChanged(COLUMNNAME_Item2_Price) || getItem2_C_OrderLine_ID() == 0)) {
			
			if(getItem2_C_OrderLine_ID() > 0)
				ordLine = new MOrderLine(getCtx(), getItem2_C_OrderLine_ID(), get_TrxName());
			else
				ordLine = new MOrderLine(this);
			TF_MOrder.addProductPricingIfNot(getItem2_ID(), getM_PriceList_ID(), getC_BPartner_ID(), getItem2_Qty(), getItem2_Price(), 
					getDateOrdered(), getC_DocType().isSOTrx());
			setOrderLine(ordLine, getItem2_ID(), getItem2_Qty(), getItem2_Price());									
			ordLine.saveEx();			
			DB.executeUpdate("UPDATE C_Order SET " + COLUMNNAME_Item2_C_OrderLine_ID + " = "
				+ ordLine.getC_OrderLine_ID() + " WHERE C_Order_ID = " + getC_Order_ID(), get_TrxName());	
		}
		
		
	}
	
	public void updateVehicleRentLine() {
		MOrderLine ordLine = null;
		//Delete empty item lines
		if(is_ValueChanged(COLUMNNAME_Vehicle_ID) || (getVehicle_ID() == 0 && getVehicle_C_OrderLine_ID() > 0)) {
			ordLine = new MOrderLine(getCtx(), getVehicle_C_OrderLine_ID(), get_TrxName());
			if(ordLine.get_ID() > 0) {
				ordLine.setQtyReserved(BigDecimal.ZERO);
				ordLine.delete(false);
				DB.executeUpdate("UPDATE C_Order SET " + COLUMNNAME_Vehicle_C_OrderLine_ID + " = NULL " +
						" WHERE C_Order_ID =" + getC_Order_ID(), get_TrxName());
				setVehicle_C_OrderLine_ID(0);
			}			
		}//End Delete
		
		//Update modified Vehicle Rent line.		
		//Vehicle Rent
		if(getVehicle_ID() > 0 && (is_ValueChanged(COLUMNNAME_Vehicle_ID) || is_ValueChanged(COLUMNNAME_Rent_Amt)
				|| getVehicle_C_OrderLine_ID() == 0)) {
			
			if(getVehicle_C_OrderLine_ID() > 0) 
				ordLine = new MOrderLine(getCtx(), getVehicle_C_OrderLine_ID(), get_TrxName());
			else
				ordLine = new MOrderLine(this);
			
			TF_MOrder.addProductPricingIfNot(getVehicle_ID(), getM_PriceList_ID(), getC_BPartner_ID(), BigDecimal.ONE, getRent_Amt(), 
					getDateOrdered(), getC_DocType().isSOTrx());
			setOrderLine(ordLine, getVehicle_ID(), BigDecimal.ONE, getRent_Amt());
			MResource res = MResource.get(getCtx(), getVehicle().getS_Resource_ID());
			ordLine.setUser1_ID(res.get_ValueAsInt("C_ElementValue_ID"));
			ordLine.setDescription("Vehicle Rent");
			ordLine.saveEx();
			DB.executeUpdate("UPDATE C_Order SET " + COLUMNNAME_Vehicle_C_OrderLine_ID + " = "
				+ ordLine.getC_OrderLine_ID() + " WHERE C_Order_ID = " + getC_Order_ID(), get_TrxName());	
		}
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(getVehicle_ID()>0 && getRent_Amt().doubleValue()==0) {
			throw new AdempiereUserError("Invalid Rent Amount");
		}
			
		return super.beforeSave(newRecord);
	}

	@Override
	public boolean voidIt() {
		//POS Order's MR and Invoice should be reversed.
		if(getC_DocType_ID() == 1000050 || getC_DocType_ID() == 1000041) {
			//MR/Shipment reverse Accrual
			List<MInOut> inOutList = new Query(getCtx(), MInOut.Table_Name, "C_Order_ID=? AND DocStatus=?", get_TrxName())
				.setClient_ID().setParameters(getC_Order_ID(),DOCSTATUS_Completed).list();
			for(MInOut inout : inOutList) {
				if(!inout.reverseAccrualIt())
					return false;				
				inout.saveEx();
			}
			
			//Invoice reverse Accrual
			List<TF_MInvoice> invList = new Query(getCtx(), TF_MInvoice.Table_Name, "C_Order_ID=? AND DocStatus=?", get_TrxName())
				.setClient_ID().setParameters(getC_Order_ID(), DOCSTATUS_Completed).list();
			for(TF_MInvoice inv : invList) {
				if(!inv.reverseAccrualIt())
					return false;
				inv.saveEx();
			}
		}
		
		return super.voidIt();
	}
	
	public static MProductPricing getProductPricing(int M_Product_ID, int M_PriceList_ID, int C_BPartner_ID, 
			BigDecimal Qty,	Timestamp priceDate, boolean isSOTrx) {
		//Get Unit Price from Latest Price List.
		String sql = "SELECT plv.M_PriceList_Version_ID "
				+ "FROM M_PriceList_Version plv "
				+ "WHERE plv.M_PriceList_ID=? "	
				+ " AND plv.ValidFrom <= ? "
				+ "ORDER BY plv.ValidFrom DESC";
		
		int M_PriceList_Version_ID = DB.getSQLValueEx(null, sql, M_PriceList_ID, priceDate);
		MProductPricing pp = new MProductPricing (M_Product_ID, C_BPartner_ID, Qty, isSOTrx);
		pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
		pp.setPriceDate(priceDate);		
		return pp;
	}
	
	public static MProductPrice addProductPricingIfNot(int M_Product_ID, int M_PriceList_ID, int C_BPartner_ID, 
			BigDecimal Qty, BigDecimal price, Timestamp priceDate, boolean isSOTrx) {
		//Get Unit Price from Latest Price List.
		String sql = "SELECT plv.M_PriceList_Version_ID "
				+ "FROM M_PriceList_Version plv "
				+ "WHERE plv.M_PriceList_ID=? "	
				+ " AND plv.ValidFrom <= ? "
				+ "ORDER BY plv.ValidFrom DESC";
		MProductPrice prodPrice = null;
		int M_PriceList_Version_ID = DB.getSQLValueEx(null, sql, M_PriceList_ID, priceDate);	
		sql = " SELECT Count(*) FROM M_ProductPrice WHERE M_PriceList_Version_ID =? AND M_Product_ID =? AND IsActive='Y'";
		BigDecimal count = DB.getSQLValueBD(null, sql, M_PriceList_Version_ID,M_Product_ID);
		if(count == null || count.doubleValue() == 0) {
			prodPrice = new MProductPrice(Env.getCtx(), M_PriceList_Version_ID, M_Product_ID, null);			
			prodPrice.setPrices(price, price, price);		
			prodPrice.saveEx();	
		}
		return prodPrice;
	}
	
}
