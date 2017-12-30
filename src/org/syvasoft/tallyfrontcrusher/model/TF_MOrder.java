package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPriceList;
import org.compiere.model.MProductPrice;
import org.compiere.model.MProductPricing;
import org.compiere.model.MResource;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTable;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
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
	
	/** Column name Item1_UOM_ID */
    public static final String COLUMNNAME_Item1_UOM_ID = "Item1_UOM_ID";
    public org.compiere.model.I_C_UOM getItem1_UOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getItem1_UOM_ID(), get_TrxName());	}

	/** Set UOM.
		@param Item1_UOM_ID UOM	  */
	public void setItem1_UOM_ID (int Item1_UOM_ID)
	{
		if (Item1_UOM_ID < 1) 
			set_Value (COLUMNNAME_Item1_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_Item1_UOM_ID, Integer.valueOf(Item1_UOM_ID));
	}

	/** Get UOM.
		@return UOM	  */
	public int getItem1_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Item1_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name Item1_Tax_ID */
    public static final String COLUMNNAME_Item1_Tax_ID = "Item1_Tax_ID";
    
    public org.compiere.model.I_C_Tax getItem1_Tax() throws RuntimeException
    {
		return (org.compiere.model.I_C_Tax)MTable.get(getCtx(), org.compiere.model.I_C_Tax.Table_Name)
			.getPO(getItem1_Tax_ID(), get_TrxName());	}

	/** Set Tax.
		@param Item1_Tax_ID 
		Tax Identifier
	  */
	public void setItem1_Tax_ID (int Item1_Tax_ID)
	{
		if (Item1_Tax_ID < 1) 
			set_Value (COLUMNNAME_Item1_Tax_ID, null);
		else 
			set_Value (COLUMNNAME_Item1_Tax_ID, Integer.valueOf(Item1_Tax_ID));
	}

	/** Get Tax.
		@return Tax Identifier
	  */
	public int getItem1_Tax_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Item1_Tax_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name Item1_IsUpdatePrice */
    public static final String COLUMNNAME_Item1_IsUpdatePrice = "Item1_IsUpdatePrice";
    /** Set Update Price.
	@param Item1_IsUpdatePrice 
	Update Price into Price List
	  */
	public void setItem1_IsUpdatePrice (boolean Item1_IsUpdatePrice)
	{
		set_Value (COLUMNNAME_Item1_IsUpdatePrice, Boolean.valueOf(Item1_IsUpdatePrice));
	}
	
	/** Get Update Price.
		@return Update Price into Price List
	  */
	public boolean isItem1_IsUpdatePrice () 
	{
		Object oo = get_Value(COLUMNNAME_Item1_IsUpdatePrice);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

    /** Column name IsLumpSumRent */
    public static final String COLUMNNAME_IsLumpSumRent = "IsLumpSumRent";
    
	/** Set Lump Sum Rent.
	@param IsLumpSumRent Lump Sum Rent	  */
	public void setIsLumpSumRent (boolean IsLumpSumRent)
	{
		set_Value (COLUMNNAME_IsLumpSumRent, Boolean.valueOf(IsLumpSumRent));
	}
	
	/** Get Lump Sum Rent.
		@return Lump Sum Rent	  */
	public boolean isLumpSumRent () 
	{
		Object oo = get_Value(COLUMNNAME_IsLumpSumRent);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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
	
	/** Column name TF_WeighmentEntry_ID */
    public static final String COLUMNNAME_TF_WeighmentEntry_ID = "TF_WeighmentEntry_ID";
    /** Set Weighment Entry.
	@param TF_WeighmentEntry_ID Weighment Entry	  */
	public void setTF_WeighmentEntry_ID (int TF_WeighmentEntry_ID)
	{
		if (TF_WeighmentEntry_ID < 1) 
			set_Value (COLUMNNAME_TF_WeighmentEntry_ID, null);
		else 
			set_Value (COLUMNNAME_TF_WeighmentEntry_ID, Integer.valueOf(TF_WeighmentEntry_ID));
	}
	
	/** Get Weighment Entry.
		@return Weighment Entry	  */
	public int getTF_WeighmentEntry_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_WeighmentEntry_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Column name IssueToSubcontract_ID */
    public static final String COLUMNNAME_IssueToSubcontract_ID = "IssueToSubcontract_ID";
    public org.compiere.model.I_C_Project getIssueToSubcontract() throws RuntimeException
    {
		return (org.compiere.model.I_C_Project)MTable.get(getCtx(), org.compiere.model.I_C_Project.Table_Name)
			.getPO(getIssueToSubcontract_ID(), get_TrxName());	}

	/** Set Issue to Subcontract.
		@param IssueToSubcontract_ID Issue to Subcontract	  */
	public void setIssueToSubcontract_ID (int IssueToSubcontract_ID)
	{
		if (IssueToSubcontract_ID < 1) 
			set_Value (COLUMNNAME_IssueToSubcontract_ID, null);
		else 
			set_Value (COLUMNNAME_IssueToSubcontract_ID, Integer.valueOf(IssueToSubcontract_ID));
	}

	/** Get Issue to Subcontract.
		@return Issue to Subcontract	  */
	public int getIssueToSubcontract_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IssueToSubcontract_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name TF_Destination_ID */
    public static final String COLUMNNAME_TF_Destination_ID = "TF_Destination_ID";
    /** Set Destination.
	@param TF_Destination_ID Destination	  */
	public void setTF_Destination_ID (int TF_Destination_ID)
	{
		if (TF_Destination_ID < 1) 
			set_Value (COLUMNNAME_TF_Destination_ID, null);
		else 
			set_Value (COLUMNNAME_TF_Destination_ID, Integer.valueOf(TF_Destination_ID));
	}
	
	/** Get Destination.
		@return Destination	  */
	public int getTF_Destination_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_Destination_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Column name Distance */
    public static final String COLUMNNAME_Distance = "Distance";
    /** Set Distance (km).
	@param Distance Distance (km)	  */
	public void setDistance (BigDecimal Distance)
	{
		set_Value (COLUMNNAME_Distance, Distance);
	}
	
	/** Get Distance (km).
		@return Distance (km)	  */
	public BigDecimal getDistance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Distance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Column name Tonnage */
    public static final String COLUMNNAME_Tonnage = "Tonnage";
    /** Set Tonnage.
	@param Tonnage Tonnage	  */
	public void setTonnage (BigDecimal Tonnage)
	{
		set_Value (COLUMNNAME_Tonnage, Tonnage);
	}
	
	/** Get Tonnage.
		@return Tonnage	  */
	public BigDecimal getTonnage () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Tonnage);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Column name TF_RentedVehicle_ID */
    public static final String COLUMNNAME_TF_RentedVehicle_ID = "TF_RentedVehicle_ID";
    /** Set Rented Vehicle.
	@param TF_RentedVehicle_ID Rented Vehicle	  */
	public void setTF_RentedVehicle_ID (int TF_RentedVehicle_ID)
	{
		if (TF_RentedVehicle_ID < 1) 
			set_Value (COLUMNNAME_TF_RentedVehicle_ID, null);
		else 
			set_Value (COLUMNNAME_TF_RentedVehicle_ID, Integer.valueOf(TF_RentedVehicle_ID));
	}
	
	/** Get Rented Vehicle.
		@return Rented Vehicle	  */
	public int getTF_RentedVehicle_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_RentedVehicle_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Column name Rate */
    public static final String COLUMNNAME_Rate = "Rate";
    /** Set Rate / ton / km.
	@param Rate 
	Rate per tone and  km
	  */
	public void setRate (BigDecimal Rate)
	{
		set_Value (COLUMNNAME_Rate, Rate);
	}
	
	/** Get Rate / ton / km.
		@return Rate per tone and  km
	  */
	public BigDecimal getRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
    /** Column name TransporterInvoice_ID */
    public static final String COLUMNNAME_TransporterInvoice_ID = "TransporterInvoice_ID";
	public org.compiere.model.I_C_Invoice getTransporterInvoice() throws RuntimeException
    {
		return (org.compiere.model.I_C_Invoice)MTable.get(getCtx(), org.compiere.model.I_C_Invoice.Table_Name)
			.getPO(getTransporterInvoice_ID(), get_TrxName());	}

	/** Set Transporter Invoice.
		@param TransporterInvoice_ID Transporter Invoice	  */
	public void setTransporterInvoice_ID (int TransporterInvoice_ID)
	{
		if (TransporterInvoice_ID < 1) 
			set_Value (COLUMNNAME_TransporterInvoice_ID, null);
		else 
			set_Value (COLUMNNAME_TransporterInvoice_ID, Integer.valueOf(TransporterInvoice_ID));
	}

	/** Get Transporter Invoice.
		@return Transporter Invoice	  */
	public int getTransporterInvoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TransporterInvoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Column name Item1_UnitPrice */
    public static final String COLUMNNAME_Item1_UnitPrice = "Item1_UnitPrice";
    /** Set Unit Price.
	@param Item1_UnitPrice Unit Price	  */
	public void setItem1_UnitPrice (BigDecimal Item1_UnitPrice)
	{
		set_Value (COLUMNNAME_Item1_UnitPrice, Item1_UnitPrice);
	}
	
	/** Get Unit Price.
		@return Unit Price	  */
	public BigDecimal getItem1_UnitPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item1_UnitPrice);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	/** Column name Item1_UnitRent */
    public static final String COLUMNNAME_Item1_UnitRent = "Item1_UnitRent";
    /** Set Unit Rent.
	@param Item1_UnitRent Unit Rent	  */
	public void setItem1_UnitRent (BigDecimal Item1_UnitRent)
	{
		set_Value (COLUMNNAME_Item1_UnitRent, Item1_UnitRent);
	}
	
	/** Get Unit Rent.
		@return Unit Rent	  */
	public BigDecimal getItem1_UnitRent () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item1_UnitRent);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	/** Column name RentMargin */
    public static final String COLUMNNAME_RentMargin = "RentMargin";
    /** Set Rent Margin.
	@param RentMargin Rent Margin	  */
	public void setRentMargin (BigDecimal RentMargin)
	{
		set_Value (COLUMNNAME_RentMargin, RentMargin);
	}
	
	/** Get Rent Margin.
		@return Rent Margin	  */
	public BigDecimal getRentMargin () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_RentMargin);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	/** Column name RentPayable */
    public static final String COLUMNNAME_RentPayable = "RentPayable";
    /** Set Rent Payable.
	@param RentPayable 
	Rent Payable for Transporter
  */
	public void setRentPayable (BigDecimal RentPayable)
	{
		set_Value (COLUMNNAME_RentPayable, RentPayable);
	}
	
	/** Get Rent Payable.
		@return Rent Payable for Transporter
	  */
	public BigDecimal getRentPayable () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_RentPayable);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
    /** Column name IsRentBreakup */
    public static final String COLUMNNAME_IsRentBreakup = "IsRentBreakup";

    /** Set Show Rent Breakup.
	@param IsRentBreakup Show Rent Breakup	  */
	public void setIsRentBreakup (boolean IsRentBreakup)
	{
		set_Value (COLUMNNAME_IsRentBreakup, Boolean.valueOf(IsRentBreakup));
	}
	
	/** Get Show Rent Breakup.
		@return Show Rent Breakup	  */
	public boolean isRentBreakup () 
	{
		Object oo = get_Value(COLUMNNAME_IsRentBreakup);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Column name IsRentInclusive */
    public static final String COLUMNNAME_IsRentInclusive = "IsRentInclusive";
    /** Set Rent Inclusive.
	@param IsRentInclusive 
	Whether Unit Price includes rent?
  */
	public void setIsRentInclusive (boolean IsRentInclusive)
	{
		set_Value (COLUMNNAME_IsRentInclusive, Boolean.valueOf(IsRentInclusive));
	}
	
	/** Get Rent Inclusive.
		@return Whether Unit Price includes rent?
	  */
	public boolean isRentInclusive () 
	{
		Object oo = get_Value(COLUMNNAME_IsRentInclusive);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
	
	/** Column name Subcon_Invoice_ID */
    public static final String COLUMNNAME_Subcon_Invoice_ID = "Subcon_Invoice_ID";
    public org.compiere.model.I_C_Invoice getSubcon_Invoice() throws RuntimeException
    {
		return (org.compiere.model.I_C_Invoice)MTable.get(getCtx(), org.compiere.model.I_C_Invoice.Table_Name)
			.getPO(getSubcon_Invoice_ID(), get_TrxName());	}

	/** Set Subcontractor Invoice.
		@param Subcon_Invoice_ID Subcontractor Invoice	  */
	public void setSubcon_Invoice_ID (int Subcon_Invoice_ID)
	{
		if (Subcon_Invoice_ID < 1) 
			set_Value (COLUMNNAME_Subcon_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_Subcon_Invoice_ID, Integer.valueOf(Subcon_Invoice_ID));
	}

	/** Get Subcontractor Invoice.
		@return Subcontractor Invoice	  */
	public int getSubcon_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Subcon_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
    /** Column name Subcon_Receipt_ID */
    public static final String COLUMNNAME_Subcon_Receipt_ID = "Subcon_Receipt_ID";
    public org.compiere.model.I_M_InOut getSubcon_Receipt() throws RuntimeException
    {
		return (org.compiere.model.I_M_InOut)MTable.get(getCtx(), org.compiere.model.I_M_InOut.Table_Name)
			.getPO(getSubcon_Receipt_ID(), get_TrxName());	}

	/** Set Subcontractor Material Receipt.
		@param Subcon_Receipt_ID Subcontractor Material Receipt	  */
	public void setSubcon_Receipt_ID (int Subcon_Receipt_ID)
	{
		if (Subcon_Receipt_ID < 1) 
			set_Value (COLUMNNAME_Subcon_Receipt_ID, null);
		else 
			set_Value (COLUMNNAME_Subcon_Receipt_ID, Integer.valueOf(Subcon_Receipt_ID));
	}

	/** Get Subcontractor Material Receipt.
		@return Subcontractor Material Receipt	  */
	public int getSubcon_Receipt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Subcon_Receipt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
		
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {		
		success = super.afterSave(newRecord, success);
		updateQuickOrderLines();
		//updateVehicleRentLine();
		updateRentedVehicleRentLine();
		if(isItem1_IsUpdatePrice() && isSOTrx()) {
			TF_MOrder.updateProductPricing(getItem1_ID(), getM_PriceList_ID(), getC_BPartner_ID(), 
					getItem1_Qty(), getItem1_UnitPrice(), getDateOrdered(), isSOTrx());
		}
		
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
		TF_MOrderLine ordLine = null;
		//Delete empty item lines
		if(is_ValueChanged(COLUMNNAME_Item1_ID) || (getItem1_ID() == 0 && getItem1_C_OrderLine_ID() > 0)) {
			ordLine = new TF_MOrderLine(getCtx(), getItem1_C_OrderLine_ID(), get_TrxName());
			if(ordLine.get_ID() > 0) {
				DB.executeUpdate("UPDATE C_Order SET " + COLUMNNAME_Item1_C_OrderLine_ID + " = NULL " +
						" WHERE C_Order_ID =" + getC_Order_ID(), get_TrxName());
				ordLine.setQtyReserved(BigDecimal.ZERO);
				ordLine.delete(false);
				setItem1_C_OrderLine_ID(0);
			}
		}
		if(is_ValueChanged(COLUMNNAME_Item2_ID) || (getItem2_ID() == 0 && getItem2_C_OrderLine_ID() > 0)) {
			ordLine = new TF_MOrderLine(getCtx(), getItem2_C_OrderLine_ID(), get_TrxName());
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
				ordLine = new TF_MOrderLine(getCtx(), getItem1_C_OrderLine_ID(), get_TrxName());
			else
				ordLine = new TF_MOrderLine(this);
			TF_MOrder.addProductPricingIfNot(getItem1_ID(), getM_PriceList_ID(), getC_BPartner_ID(), getItem1_Qty(), getItem1_Price(), 
					getDateOrdered(), getC_DocType().isSOTrx());
			setOrderLine(ordLine, getItem1_ID(), getItem1_Qty(), getItem1_Price());
			
			ordLine.setC_Tax_ID(getItem1_Tax_ID());
			
			//For SO, From header itself, price is updated.
			if(!isSOTrx())
				ordLine.setIsUpdatePrice(isItem1_IsUpdatePrice());
			
			ordLine.setC_UOM_ID(getItem1_UOM_ID());
			
			ordLine.saveEx();			
			DB.executeUpdate("UPDATE C_Order SET " + COLUMNNAME_Item1_C_OrderLine_ID + " = "
				+ ordLine.getC_OrderLine_ID() + " WHERE C_Order_ID = " + getC_Order_ID(), get_TrxName());	
		}
		//Item 2
		if(getItem2_ID() > 0 && (is_ValueChanged(COLUMNNAME_Item2_ID) || is_ValueChanged(COLUMNNAME_Item2_Qty)
				|| is_ValueChanged(COLUMNNAME_Item2_Price) || getItem2_C_OrderLine_ID() == 0)) {
			
			if(getItem2_C_OrderLine_ID() > 0)
				ordLine = new TF_MOrderLine(getCtx(), getItem2_C_OrderLine_ID(), get_TrxName());
			else
				ordLine = new TF_MOrderLine(this);
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

	public void updateRentedVehicleRentLine() {
		MOrderLine ordLine = null;
		//Delete empty item lines
		if(!isRentBreakup() || is_ValueChanged(COLUMNNAME_TF_RentedVehicle_ID) || (getTF_RentedVehicle_ID() == 0 && getVehicle_C_OrderLine_ID() > 0)) {
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
		if(isRentBreakup())
		 if(getTF_RentedVehicle_ID() > 0 && (is_ValueChanged(COLUMNNAME_TF_RentedVehicle_ID) || is_ValueChanged(COLUMNNAME_Rent_Amt)
				|| getVehicle_C_OrderLine_ID() == 0)) {
			
			if(getVehicle_C_OrderLine_ID() > 0) 
				ordLine = new MOrderLine(getCtx(), getVehicle_C_OrderLine_ID(), get_TrxName());
			else
				ordLine = new MOrderLine(this);
			MRentedVehicle rentVehicle = new MRentedVehicle(getCtx(), getTF_RentedVehicle_ID(), get_TrxName());
			int productID = rentVehicle.getM_Product_ID();
			TF_MOrder.addProductPricingIfNot(productID, getM_PriceList_ID(), getC_BPartner_ID(), BigDecimal.ONE, getRent_Amt(), 
					getDateOrdered(), getC_DocType().isSOTrx());
			setOrderLine(ordLine, productID, BigDecimal.ONE, getRent_Amt());
			int load_uom_id = MSysConfig.getIntValue("LOAD_UOM", 1000072, getAD_Client_ID());
			ordLine.setC_UOM_ID(load_uom_id);
			
			//MResource res = MResource.get(getCtx(), getVehicle().getS_Resource_ID());
			//ordLine.setUser1_ID(res.get_ValueAsInt("C_ElementValue_ID"));
			ordLine.setDescription("Vehicle Rent");
			ordLine.saveEx();
			DB.executeUpdate("UPDATE C_Order SET " + COLUMNNAME_Vehicle_C_OrderLine_ID + " = "
				+ ordLine.getC_OrderLine_ID() + " WHERE C_Order_ID = " + getC_Order_ID(), get_TrxName());	
		}
	}
	
	@Override
	public String completeIt() {
		createSubcontractPurchaseEntry();
		String msg = super.completeIt();
		createTransporterInvoice();
		closeWeighmentEntry();		
		return msg;
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(getVehicle_ID()>0 && getRent_Amt().doubleValue()==0) {
			throw new AdempiereUserError("Invalid Rent Amount");
		}
		
		TF_MProject proj = TF_MProject.getCrusherProductionSubcontractByWarehouse(getM_Warehouse_ID());
		if(proj != null && getC_Project_ID() == 0)
			setC_Project_ID(proj.getC_Project_ID());
		
		return super.beforeSave(newRecord);
	}

	@Override
	public boolean voidIt() {
		//POS Order's MR and Invoice should be reversed.
		if(getC_DocType_ID() == 1000050 || getC_DocType_ID() == 1000041) {
			//MR/Shipment reverse Correct
			List<MInOut> inOutList = new Query(getCtx(), MInOut.Table_Name, "C_Order_ID=? AND DocStatus=?", get_TrxName())
				.setClient_ID().setParameters(getC_Order_ID(),DOCSTATUS_Completed).list();
			for(MInOut inout : inOutList) {
				if(!inout.reverseCorrectIt())
					return false;				
				inout.saveEx();
			}
			
			//Invoice reverse Correct
			List<TF_MInvoice> invList = new Query(getCtx(), TF_MInvoice.Table_Name, "C_Order_ID=? AND DocStatus=?", get_TrxName())
				.setClient_ID().setParameters(getC_Order_ID(), DOCSTATUS_Completed).list();
			for(TF_MInvoice inv : invList) {
				if(!inv.reverseCorrectIt())
					return false;
				inv.saveEx();
			}
			
			if(getTF_DriverTips_Pay_ID() > 0) {
				TF_MPayment payment = new TF_MPayment(getCtx(), getTF_DriverTips_Pay_ID(), get_TrxName());
				payment.reverseCorrectIt();
				payment.saveEx();
			}
			
		}
		MJobworkItemIssue.ReverseFromPO(this);
		reverseTransporterInvoice();
		reverseWeighmentEntry();
		reverseSubcontractPurchaseEntry();
		return super.voidIt();
	}
	
	@Override
	public boolean reActivateIt() {
		//Only for POS Purchase
		//For POS Sales, Core already has this functionality.
		if(getC_DocType_ID() == 1000050) {
			//MR/Shipment reverse Correct
			List<MInOut> inOutList = new Query(getCtx(), MInOut.Table_Name, "C_Order_ID=? AND DocStatus=?", get_TrxName())
				.setClient_ID().setParameters(getC_Order_ID(),DOCSTATUS_Completed).list();
			for(MInOut inout : inOutList) {
				if(!inout.reverseCorrectIt())
					return false;				
				inout.saveEx();
			}
			
			//Invoice reverse Correct
			List<TF_MInvoice> invList = new Query(getCtx(), TF_MInvoice.Table_Name, "C_Order_ID=? AND DocStatus=?", get_TrxName())
				.setClient_ID().setParameters(getC_Order_ID(), DOCSTATUS_Completed).list();
			for(TF_MInvoice inv : invList) {
				if(!inv.reverseCorrectIt())
					return false;
				inv.saveEx();
			}
			
		}
		
		if(getTF_DriverTips_Pay_ID() > 0) {
			TF_MPayment payment = new TF_MPayment(getCtx(), getTF_DriverTips_Pay_ID(), get_TrxName());
			payment.reverseCorrectIt();
			payment.saveEx();
		}
		MJobworkItemIssue.ReverseFromPO(this);
		reverseTransporterInvoice();
		reverseWeighmentEntry();
		reverseSubcontractPurchaseEntry();
		return super.reActivateIt();
	}

	public void createTransporterInvoice() {
		if(getTF_RentedVehicle_ID() == 0 || getRent_Amt().doubleValue() == 0)
			return;
		//if(getRent_Amt().doubleValue() > 0 && getTF_RentedVehicle_ID() == 0)
		//	throw new AdempiereException("Please Select Rented Vehicle or Reset Rent (Amount) to ZERO!");
		//if(getTF_RentedVehicle_ID() > 0 && getRent_Amt().doubleValue() ==0)
		//	throw new AdempiereException("Rent (Amount) should be greater ZERO!");
		
		MRentedVehicle vehicle = new MRentedVehicle(getCtx(), getTF_RentedVehicle_ID(), get_TrxName());
		MBPartner bp = new MBPartner(getCtx(), vehicle.getC_BPartner_ID(), get_TrxName());
		//Invoice Header
		TF_MInvoice invoice = new TF_MInvoice(getCtx(), 0, get_TrxName());
		invoice.setClientOrg(getAD_Client_ID(), getAD_Org_ID());
		invoice.setC_DocTypeTarget_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getTransporterInvoiceDocType_ID());			
		invoice.setDateInvoiced(getDateOrdered());
		invoice.setDateAcct(getDateOrdered());
		//
		invoice.setSalesRep_ID(Env.getAD_User_ID(getCtx()));
		//
		invoice.setBPartner(bp);
		invoice.setIsSOTrx(false);		
		
		//Price List
		int m_M_PriceList_ID = Env.getContextAsInt(getCtx(), "#M_PriceList_ID");
		if(bp.getPO_PriceList_ID() > 0)
			m_M_PriceList_ID = bp.getPO_PriceList_ID();			
		invoice.setM_PriceList_ID(m_M_PriceList_ID);
		invoice.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
		
		//Financial Dimension - Profit Center
		invoice.setUser1_ID(getUser1_ID());
		
		invoice.saveEx();
		//End Invoice Header
		
		//Invoice Line - Vehicle Rental Charge
		MInvoiceLine invLine = new MInvoiceLine(invoice);
		invLine.setM_Product_ID(vehicle.getM_Product_ID(), true);
		invLine.setDescription("Vehicle Rent");
		
		String hdrDescription = "";
			
		MDestination dest = new MDestination(getCtx(), getTF_Destination_ID(), get_TrxName());
		if(isSOTrx()) {
			hdrDescription = "Destination : " + dest.getName();
		}
		else {
			hdrDescription = "Source : " + dest.getName();
		}
		if(!isLumpSumRent()) {
			invLine.setQty(getDistance());
			BigDecimal price = getRate().multiply(getTonnage());
			BigDecimal rate = getRate();
			if(isSOTrx()) {
				price = getRentPayable().divide(getDistance(), 2, RoundingMode.HALF_UP);
				rate = price.divide(getTonnage(), 2, RoundingMode.HALF_UP);
			}
			invLine.setPriceActual(price);
			invLine.setPriceList(price);
			invLine.setPriceLimit(price);
			invLine.setPriceEntered(price);
			hdrDescription = hdrDescription + ", Tonnage : " + getTonnage().doubleValue()
					+ ", Rate/ton/km : " + rate.doubleValue();
		}
		else {
			int load_uom_id = MSysConfig.getIntValue("LOAD_UOM", 1000072, getAD_Client_ID());
			invLine.setC_UOM_ID(load_uom_id);
			invLine.setQty(BigDecimal.ONE);
			BigDecimal price = getRent_Amt();
			if(isSOTrx()) {
				price = getRentPayable();
			}
			invLine.setPriceActual(price);
			invLine.setPriceList(price);
			invLine.setPriceLimit(price);
			invLine.setPriceEntered(price);
			hdrDescription = hdrDescription + ", Tonnage : " + getTonnage().doubleValue();
		}		
		invLine.saveEx();
		
		invoice.setDescription(hdrDescription);
		invoice.saveEx();
		
		//DocAction
		if (!invoice.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + invoice.getProcessMsg());
		invoice.saveEx();
		//End DocAction
		
		setTransporterInvoice_ID(invoice.getC_Invoice_ID());
		
	}
	
	public void reverseTransporterInvoice() {
		if(getTransporterInvoice_ID() > 0 ) {
			TF_MInvoice inv = new TF_MInvoice(getCtx(), getTransporterInvoice_ID(), get_TrxName());
			inv.reverseCorrectIt();
			inv.saveEx();
		}
	}
	
	public void closeWeighmentEntry()
	{
		if(getTF_WeighmentEntry_ID() > 0) {
			MWeighmentEntry weighment = new MWeighmentEntry(getCtx(), getTF_WeighmentEntry_ID(), get_TrxName());
			weighment.close();
			weighment.saveEx();
		}
	}
	
	public void reverseWeighmentEntry() {
		if(getTF_WeighmentEntry_ID() > 0) {
			MWeighmentEntry weighment = new MWeighmentEntry(getCtx(), getTF_WeighmentEntry_ID(), get_TrxName());
			weighment.reverse();
			weighment.saveEx();
		}
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
	
	public static MProductPrice updateProductPricing(int M_Product_ID, int M_PriceList_ID, int C_BPartner_ID, 
			BigDecimal Qty, BigDecimal price, Timestamp priceDate, boolean isSOTrx) {
		//Get Unit Price from Latest Price List.
		String sql = "SELECT plv.M_PriceList_Version_ID "
				+ "FROM M_PriceList_Version plv "
				+ "WHERE plv.M_PriceList_ID=? "	
				+ " AND plv.ValidFrom <= ? "
				+ "ORDER BY plv.ValidFrom DESC";
		MProductPrice prodPrice = null;
		int M_PriceList_Version_ID = DB.getSQLValueEx(null, sql, M_PriceList_ID, priceDate);
		prodPrice = MProductPrice.get(Env.getCtx(), M_PriceList_Version_ID, M_Product_ID, null);
		if(prodPrice != null) {						
			prodPrice.setPrices(price, price, price);		
			prodPrice.saveEx();	
		}
		return prodPrice;
	}
	
	
	public void createSubcontractPurchaseEntry() {
		if(getC_Project_ID() == 0 || !isSOTrx())
			return;
				
		TF_MProject proj = new TF_MProject(getCtx(), getC_Project_ID(), get_TrxName());
		if(!TF_MProject.SUBCONTRACTTYPE_CrusherProduction.equals(proj.getSubcontractType()) &&
				proj.getM_Warehouse_ID() != getM_Warehouse_ID())
			return;
		
		MSubcontractType st = new MSubcontractType(getCtx(), proj.getTF_SubcontractType_ID(), get_TrxName());
		
		int priceItem_Id = 0;
		if(st.getInvoicePriceFrom().equals(MSubcontractType.INVOICEPRICEFROM_Jobwork)) 
			priceItem_Id = proj.getJobWork_Product_ID();
		else
			priceItem_Id = getItem1_ID();
		
		//Crusher Production Subcontract Purchase		
		BigDecimal purchasePrice = MJobworkProductPrice.getPrice(getCtx(), getC_Project_ID(), priceItem_Id, getDateAcct()) ;
		if(purchasePrice == null)
			throw new AdempiereException("Please setup Contract Price for " + getItem1().getName() + "!");
		
		TF_MBPartner bp = new TF_MBPartner(getCtx(), proj.getC_BPartner_ID(), get_TrxName());
		
		//Invoice Header
		TF_MInvoice invoice = new TF_MInvoice(getCtx(), 0, get_TrxName());
		invoice.setClientOrg(getAD_Client_ID(), getAD_Org_ID());
		invoice.setC_DocTypeTarget_ID(1000005);	// AP Invoice		
		invoice.setDateInvoiced(getDateOrdered());
		invoice.setDateAcct(getDateAcct());
		//
		invoice.setSalesRep_ID(Env.getAD_User_ID(getCtx()));		
		//
		
		invoice.setBPartner(bp);
		invoice.setIsSOTrx(false);		
		invoice.setVehicleNo(getVehicleNo());		
		invoice.setDescription("Created from Sales: " + getDocumentNo());
		if(getTF_WeighmentEntry_ID() > 0) {
			MWeighmentEntry entry = new MWeighmentEntry(getCtx(), getTF_WeighmentEntry_ID(), get_TrxName());
			invoice.addDescription("Ticket No: " + entry.getDocumentNo());
		}
		//Price List
		int m_M_PriceList_ID = Env.getContextAsInt(getCtx(), "#M_PriceList_ID");
		if(bp.getPO_PriceList_ID() > 0)
			m_M_PriceList_ID = bp.getPO_PriceList_ID();			
		invoice.setM_PriceList_ID(m_M_PriceList_ID);
		invoice.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
		
		//Financial Dimension - Profit Center
		invoice.setUser1_ID(getUser1_ID());
		
		invoice.saveEx();
		//End Invoice Header
		
		//Invoice Line - Vehicle Rental Charge
		MInvoiceLine invLine = new MInvoiceLine(invoice);
		invLine.setM_Product_ID(getItem1_ID() , true);
				
		
		invLine.setQty(getItem1_Qty());					
		
		invLine.setPriceActual(purchasePrice);
		invLine.setPriceList(purchasePrice);
		invLine.setPriceLimit(purchasePrice);
		invLine.setPriceEntered(purchasePrice);
		
		invLine.setC_Tax_ID(1000000);
				
		//MM Receipt
		MInOut inout = new MInOut(invoice, 1000014, getDateAcct(), getM_Warehouse_ID());
		inout.setDescription(invoice.getDescription());
		inout.setMovementType(MInOut.MOVEMENTTYPE_VendorReceipts);
		inout.saveEx(get_TrxName());
		
		//Material Receipt Line
		MInOutLine ioLine = new MInOutLine(inout);
		MWarehouse wh = (MWarehouse) getM_Warehouse();
		ioLine.setInvoiceLine(invLine, wh.getDefaultLocator().get_ID(), getItem1_Qty());
		ioLine.setQty(getItem1_Qty());
		ioLine.saveEx(get_TrxName());
		
		//Material Receipt DocAction
		if (!inout.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + invoice.getProcessMsg());
		inout.saveEx();
		//End DocAction
		
		invLine.setM_InOutLine_ID(ioLine.getM_InOutLine_ID());
		invLine.saveEx();
		
		//Invoice DocAction
		if (!invoice.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + invoice.getProcessMsg());
		invoice.saveEx();
		//End DocAction
		if(st.isTrackMaterialMovement())
			MSubcontractMaterialMovement.createMaterialMovement(get_TrxName(), getDateAcct(),getAD_Org_ID(), getC_Project_ID(),
					invoice.getC_Invoice_ID(), invoice.getC_BPartner_ID(), getItem1_ID(), getItem1_Qty());
		setSubcon_Invoice_ID(invoice.getC_Invoice_ID());
		setSubcon_Receipt_ID(inout.getM_InOut_ID());		
	}
	
	public void reverseSubcontractPurchaseEntry() {
		if(getSubcon_Invoice_ID() > 0) {			
			TF_MInvoice inv = new TF_MInvoice(getCtx(), getSubcon_Invoice_ID(), get_TrxName());			
			inv.reverseCorrectIt();
			inv.saveEx();
			MSubcontractMaterialMovement.deleteInvoiceMovement(inv.getC_Invoice_ID(), get_TrxName());
			setSubcon_Invoice_ID(0);
		}
		if(getSubcon_Receipt_ID() > 0) {
			MInOut io = new MInOut(getCtx(), getSubcon_Receipt_ID(), get_TrxName());
			io.reverseCorrectIt();
			io.saveEx();
			setSubcon_Receipt_ID(0);
		}
		
	}
	
}
