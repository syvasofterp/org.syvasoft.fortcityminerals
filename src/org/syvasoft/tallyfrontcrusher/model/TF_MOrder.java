package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.apps.form.PaySelect.BankInfo;
import org.compiere.model.MBPartner;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.model.MProductPricing;
import org.compiere.model.MResource;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTable;
import org.compiere.model.MTax;
import org.compiere.model.MUOM;
import org.compiere.model.MUOMConversion;
import org.compiere.model.MUser;
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
	
	/** Column name TF_Token_ID */
    public static final String COLUMNNAME_TF_Token_ID = "TF_Token_ID";
    /** Set Token No.
	@param TF_Token_ID Weighment Entry	  */
	public void setTF_Token_ID (int TF_Token_ID)
	{
		if (TF_Token_ID < 1) 
			set_Value (COLUMNNAME_TF_Token_ID, null);
		else 
			set_Value (COLUMNNAME_TF_Token_ID, Integer.valueOf(TF_Token_ID));
	}
	
	/** Get Token No.
		@return Token No	  */
	public int getTF_Token_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_Token_ID);
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
			 return Env.ONE;
		else if(bd.doubleValue() == 0)
			return Env.ONE;
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
	
	/** Column name IsTaxIncluded for client */
    public static final String COLUMNNAME_IsTaxIncluded1 = "IsTaxIncluded1";

	/** Set Price includes Tax.
	  * Tax is included in the price 
	  */
	public void setIsTaxIncluded1 (boolean IsTaxIncluded1)
	{
		set_Value (COLUMNNAME_IsTaxIncluded1, Boolean.valueOf(IsTaxIncluded1));
	}

	/** Get Price includes Tax.
	  * Tax is included in the price 
	  */
	public boolean isTaxIncluded1()
	{
		Object oo = get_Value(COLUMNNAME_IsTaxIncluded1);
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
	
	/** Column name Item2_UOM_ID */
    public static final String COLUMNNAME_Item2_UOM_ID = "Item2_UOM_ID";
	public org.compiere.model.I_C_UOM getItem2_UOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getItem2_UOM_ID(), get_TrxName());	}

	/** Set UOM.
		@param Item2_UOM_ID UOM	  */
	public void setItem2_UOM_ID (int Item2_UOM_ID)
	{
		if (Item2_UOM_ID < 1) 
			set_Value (COLUMNNAME_Item2_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_Item2_UOM_ID, Integer.valueOf(Item2_UOM_ID));
	}

	/** Get UOM.
		@return UOM	  */
	public int getItem2_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Item2_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name Item2_Tax_ID */
    public static final String COLUMNNAME_Item2_Tax_ID = "Item2_Tax_ID";
    public org.compiere.model.I_C_Tax getItem2_Tax() throws RuntimeException
    {
		return (org.compiere.model.I_C_Tax)MTable.get(getCtx(), org.compiere.model.I_C_Tax.Table_Name)
			.getPO(getItem2_Tax_ID(), get_TrxName());	}

	/** Set Tax.
		@param Item2_Tax_ID 
		Tax Identifier
	  */
	public void setItem2_Tax_ID (int Item2_Tax_ID)
	{
		if (Item2_Tax_ID < 1) 
			set_Value (COLUMNNAME_Item2_Tax_ID, null);
		else 
			set_Value (COLUMNNAME_Item2_Tax_ID, Integer.valueOf(Item2_Tax_ID));
	}

	/** Get Tax.
		@return Tax Identifier
	  */
	public int getItem2_Tax_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Item2_Tax_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name OrgType */
    public static final String COLUMNNAME_OrgType = "OrgType";    
    /** Crusher = C */
    /** Crusher = C */
	public static final String ORGTYPE_Crusher = "C";
	/** Sand Block Bucket = S */
	public static final String ORGTYPE_SandBlockBucket = "S";
	/** Trading = T */
	public static final String ORGTYPE_Trading = "T";
	/** Head Office = H */
	public static final String ORGTYPE_HeadOffice = "H";
	/** Sand Block Weighbridge = W */
	public static final String ORGTYPE_SandBlockWeighbridge = "W";
    /** Set Organization Type.
	@param OrgType Organization Type	  */
	public void setOrgType (String OrgType)
	{
	
		set_Value (COLUMNNAME_OrgType, OrgType);
	}
	
	/** Get Organization Type.
		@return Organization Type	  */
	public String getOrgType () 
	{
		return (String)get_Value(COLUMNNAME_OrgType);
	}
	
	/** Column name Item1_BucketQty */
    public static final String COLUMNNAME_Item1_BucketQty = "Item1_BucketQty";
    /** Set Bucket Qty.
	@param Item1_BucketQty Bucket Qty	  */
	public void setItem1_BucketQty (BigDecimal Item1_BucketQty)
	{
		set_Value (COLUMNNAME_Item1_BucketQty, Item1_BucketQty);
	}
	
	/** Get Bucket Qty.
		@return Bucket Qty	  */
	public BigDecimal getItem1_BucketQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item1_BucketQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Column name Item2_BucketQty */
    public static final String COLUMNNAME_Item2_BucketQty = "Item2_BucketQty";
    /** Set Bucket Qty.
	@param Item2_BucketQty Bucket Qty	  */
	public void setItem2_BucketQty (BigDecimal Item2_BucketQty)
	{
		set_Value (COLUMNNAME_Item2_BucketQty, Item2_BucketQty);
	}
	
	/** Get Bucket Qty.
		@return Bucket Qty	  */
	public BigDecimal getItem2_BucketQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item2_BucketQty);
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
    
	/** Column name Item1_IsPermitSales */
    public static final String COLUMNNAME_Item1_IsPermitSales = "Item1_IsPermitSales";
    /** Set Permit Sales.
	@param Item1_IsPermitSales Permit Sales	  */
	public void setItem1_IsPermitSales (boolean Item1_IsPermitSales)
	{
		set_Value (COLUMNNAME_Item1_IsPermitSales, Boolean.valueOf(Item1_IsPermitSales));
	}
	/** Column name Item2_IsPermitSales */
    public static final String COLUMNNAME_Item2_IsPermitSales = "Item2_IsPermitSales";
    /** Set Permit Sales.
	@param Item2_IsPermitSales Permit Sales	  */
	public void setItem2_IsPermitSales (boolean Item2_IsPermitSales)
	{
		set_Value (COLUMNNAME_Item2_IsPermitSales, Boolean.valueOf(Item2_IsPermitSales));
	}
	
	/** Get Permit Sales.
		@return Permit Sales	  */
	public boolean isItem2_IsPermitSales () 
	{
		Object oo = get_Value(COLUMNNAME_Item2_IsPermitSales);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
	
	/** Get Permit Sales.
	@return Permit Sales	  */
	public boolean isItem1_IsPermitSales () 
	{
		Object oo = get_Value(COLUMNNAME_Item1_IsPermitSales);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
	
    /** Column name Item1_BucketRate */
    public static final String COLUMNNAME_Item1_BucketRate = "Item1_BucketRate";
    
    /** Set Bucket Rate.
	@param Item1_BucketRate Bucket Rate	  */
	public void setItem1_BucketRate (BigDecimal Item1_BucketRate)
	{
		set_Value (COLUMNNAME_Item1_BucketRate, Item1_BucketRate);
	}
	
	/** Get Bucket Rate.
		@return Bucket Rate	  */
	public BigDecimal getItem1_BucketRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item1_BucketRate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Column name Item2_BucketRate */
    public static final String COLUMNNAME_Item2_BucketRate = "Item2_BucketRate";
    /** Set Bucket Rate.
	@param Item2_BucketRate Bucket Rate	  */
	public void setItem2_BucketRate (BigDecimal Item2_BucketRate)
	{
		set_Value (COLUMNNAME_Item2_BucketRate, Item2_BucketRate);
	}
	
	/** Get Bucket Rate.
		@return Bucket Rate	  */
	public BigDecimal getItem2_BucketRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item2_BucketRate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	/** Column name Item2_TonePerBucket */
    public static final String COLUMNNAME_Item2_TonePerBucket = "Item2_TonePerBucket";
    /** Set Tone (per Bucket).
	@param Item2_TonePerBucket Tone (per Bucket)	  */
	public void setItem2_TonePerBucket (BigDecimal Item2_TonePerBucket)
	{
		set_Value (COLUMNNAME_Item2_TonePerBucket, Item2_TonePerBucket);
	}
	
	/** Get Tone (per Bucket).
		@return Tone (per Bucket)	  */
	public BigDecimal getItem2_TonePerBucket () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item2_TonePerBucket);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Column name Item1_Desc */
    public static final String COLUMNNAME_Item1_Desc = "Item1_Desc";
    /** Set Description.
	@param Item1_Desc Description	  */
	public void setItem1_Desc (String Item1_Desc)
	{
		set_Value (COLUMNNAME_Item1_Desc, Item1_Desc);
	}
	
	/** Get Description.
		@return Description	  */
	public String getItem1_Desc () 
	{
		return (String)get_Value(COLUMNNAME_Item1_Desc);
	}
	
    /** Column name Item2_Desc */
    public static final String COLUMNNAME_Item2_Desc = "Item2_Desc";
    /** Set Description.
	@param Item2_Desc Description	  */
	public void setItem2_Desc (String Item2_Desc)
	{
		set_Value (COLUMNNAME_Item2_Desc, Item2_Desc);
	}
	
	/** Get Description.
		@return Description	  */
	public String getItem2_Desc () 
	{
		return (String)get_Value(COLUMNNAME_Item2_Desc);
	}

	/** Column name Item1_SandType */
    public static final String COLUMNNAME_Item1_SandType = "Item1_SandType";
    /** Permit Sand = PM */
	public static final String ITEM1_SANDTYPE_PermitSand = "PM";
	/** Extra Bucket = EX */
	public static final String ITEM1_SANDTYPE_ExtraBucket = "EX";
	/** Without Permit = WP */
	public static final String ITEM1_SANDTYPE_WithoutPermit = "WP";
	/** Set Sand Type.
		@param Item1_SandType Sand Type	  */
	public void setItem1_SandType (String Item1_SandType)
	{

		set_Value (COLUMNNAME_Item1_SandType, Item1_SandType);
	}

	/** Get Sand Type.
	@return Sand Type	  */
	public String getItem1_SandType () 
	{
		return (String)get_Value(COLUMNNAME_Item1_SandType);
	}

	/** Column name Item2_SandType */
    public static final String COLUMNNAME_Item2_SandType = "Item2_SandType";
    /** Permit Sand = PM */
	public static final String ITEM2_SANDTYPE_PermitSand = "PM";
	/** Extra Bucket = EX */
	public static final String ITEM2_SANDTYPE_ExtraBucket = "EX";
	/** Without Permit = WP */
	public static final String ITEM2_SANDTYPE_WithoutPermit = "WP";
	/** Set Sand Type.
		@param Item2_SandType Sand Type	  */
	public void setItem2_SandType (String Item2_SandType)
	{

		set_Value (COLUMNNAME_Item2_SandType, Item2_SandType);
	}

	/** Get Sand Type.
		@return Sand Type	  */
	public String getItem2_SandType () 
	{
		return (String)get_Value(COLUMNNAME_Item2_SandType);
	}

	/** Column name Item1_TotalLoad */
    public static final String COLUMNNAME_Item1_TotalLoad = "Item1_TotalLoad";
    /** Set Total Load.
	@param Item1_TotalLoad Total Load	  */
	public void setItem1_TotalLoad (BigDecimal Item1_TotalLoad)
	{
		set_Value (COLUMNNAME_Item1_TotalLoad, Item1_TotalLoad);
	}
	
	/** Get Total Load.
		@return Total Load	  */
	public BigDecimal getItem1_TotalLoad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item1_TotalLoad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Column name Item2_TotalLoad */
    public static final String COLUMNNAME_Item2_TotalLoad = "Item2_TotalLoad";
    /** Set Total Load.
	@param Item2_TotalLoad Total Load	  */
	public void setItem2_TotalLoad (BigDecimal Item2_TotalLoad)
	{
		set_Value (COLUMNNAME_Item2_TotalLoad, Item2_TotalLoad);
	}
	
	/** Get Total Load.
		@return Total Load	  */
	public BigDecimal getItem2_TotalLoad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item2_TotalLoad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
    
	/** Column name Item1_PermitIssued */
    public static final String COLUMNNAME_Item1_PermitIssued = "Item1_PermitIssued";

	/** Set Permit Issued.
		@param Item1_PermitIssued Permit Issued	  */
	public void setItem1_PermitIssued (BigDecimal Item1_PermitIssued)
	{
		set_Value (COLUMNNAME_Item1_PermitIssued, Item1_PermitIssued);
	}

	/** Get Permit Issued.
		@return Permit Issued	  */
	public BigDecimal getItem1_PermitIssued () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item1_PermitIssued);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Column name Item1_VehicleType_ID */
    public static final String COLUMNNAME_Item1_VehicleType_ID = "Item1_VehicleType_ID";

	/** Set Vehicle Type.
		@param Item1_VehicleType_ID Vehicle Type	  */
	public void setItem1_VehicleType_ID (int Item1_VehicleType_ID)
	{
		if (Item1_VehicleType_ID < 1) 
			set_Value (COLUMNNAME_Item1_VehicleType_ID, null);
		else 
			set_Value (COLUMNNAME_Item1_VehicleType_ID, Integer.valueOf(Item1_VehicleType_ID));
	}

	/** Get Vehicle Type.
		@return Vehicle Type	  */
	public int getItem1_VehicleType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Item1_VehicleType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Column name TF_YardEntry_ID */
    public static final String COLUMNNAME_TF_YardEntry_ID = "TF_YardEntry_ID";
    /** Set Yard Entry.
	@param TF_YardEntry_ID Yard Entry	  */
	public void setTF_YardEntry_ID (int TF_YardEntry_ID)
	{
		if (TF_YardEntry_ID < 1) 
			set_Value (COLUMNNAME_TF_YardEntry_ID, null);
		else 
			set_Value (COLUMNNAME_TF_YardEntry_ID, Integer.valueOf(TF_YardEntry_ID));
	}
	
	/** Get Yard Entry.
		@return Yard Entry	  */
	public int getTF_YardEntry_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_YardEntry_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	/** Column name TF_YardEntryApprove_ID */
    public static final String COLUMNNAME_TF_YardEntryApprove_ID = "TF_YardEntryApprove_ID";
    /** Set Approve Yard Entry.
	@param TF_YardEntryApprove_ID Approve Yard Entry	  */
	public void setTF_YardEntryApprove_ID (int TF_YardEntryApprove_ID)
	{
		if (TF_YardEntryApprove_ID < 1) 
			set_Value (COLUMNNAME_TF_YardEntryApprove_ID, null);
		else 
			set_Value (COLUMNNAME_TF_YardEntryApprove_ID, Integer.valueOf(TF_YardEntryApprove_ID));
	}
	
	/** Get Approve Yard Entry.
		@return Approve Yard Entry	  */
	public int getTF_YardEntryApprove_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_YardEntryApprove_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name OnAccount */
    public static final String COLUMNNAME_OnAccount = "OnAccount";
    /** Set On Account.
	@param OnAccount On Account	  */
	public void setOnAccount (boolean OnAccount)
	{
		set_Value (COLUMNNAME_OnAccount, Boolean.valueOf(OnAccount));
	}
	
	/** Get On Account.
		@return On Account	  */
	public boolean isOnAccount () 
	{
		Object oo = get_Value(COLUMNNAME_OnAccount);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
	
	/** Column name MDPNo */
    public static final String COLUMNNAME_MDPNo = "MDPNo";
    /** Set MDP No.
	@param MDPNo MDP No	  */
	public void setMDPNo (String MDPNo)
	{
		set_Value (COLUMNNAME_MDPNo, MDPNo);
	}
	
	/** Get MDP No.
		@return MDP No	  */
	public String getMDPNo () 
	{
		return (String)get_Value(COLUMNNAME_MDPNo);
	}
	
	/** Column name TF_TaxInvoice_ID */
    public static final String COLUMNNAME_TF_TaxInvoice_ID = "TF_TaxInvoice_ID";
    
    /** Column name SalesDiscountAmt */
    public static final String COLUMNNAME_SalesDiscountAmt = "SalesDiscountAmt";

    /** Column name C_PaymentSalesDiscount_ID */
    public static final String COLUMNNAME_C_PaymentSalesDiscount_ID = "C_PaymentSalesDiscount_ID";

    public static final String COLUMNNAME_TF_TRTaxInvoice_ID = "TF_TRTaxInvoice_ID";
    /** Set Tax Invoice.
	@param TF_TaxInvoice_ID Tax Invoice	  */
	public void setTF_TaxInvoice_ID (int TF_TaxInvoice_ID)
	{
		if (TF_TaxInvoice_ID < 1) 
			set_Value (COLUMNNAME_TF_TaxInvoice_ID, null);
		else 
			set_Value (COLUMNNAME_TF_TaxInvoice_ID, Integer.valueOf(TF_TaxInvoice_ID));
	}
		
	/** Get Tax Invoice.
		@return Tax Invoice	  */
	public int getTF_TaxInvoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_TaxInvoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Set Tax Invoice.
	@param TF_TRTaxInvoice_ID Tax Invoice	  */
	public void setTF_TRTaxInvoice_ID(int TF_TRTaxInvoice_ID)
	{
		if (TF_TRTaxInvoice_ID < 1) 
			set_Value (COLUMNNAME_TF_TRTaxInvoice_ID, null);
		else 
			set_Value (COLUMNNAME_TF_TRTaxInvoice_ID, Integer.valueOf(TF_TRTaxInvoice_ID));
	}
	
	/** Get Tax Invoice.
	@return Tax Invoice	  */
	public int getTF_TRTaxInvoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_TRTaxInvoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	/** Column name Rent_Tax_ID */
    public static final String COLUMNNAME_Rent_Tax_ID = "Rent_Tax_ID";
    public org.compiere.model.I_C_Tax getRent_Tax() throws RuntimeException
    {
		return (org.compiere.model.I_C_Tax)MTable.get(getCtx(), org.compiere.model.I_C_Tax.Table_Name)
			.getPO(getRent_Tax_ID(), get_TrxName());	}

	/** Set Tax for Delivery.
		@param Rent_Tax_ID Tax for Delivery	  */
	public void setRent_Tax_ID (int Rent_Tax_ID)
	{
		if (Rent_Tax_ID < 1) 
			set_Value (COLUMNNAME_Rent_Tax_ID, null);
		else 
			set_Value (COLUMNNAME_Rent_Tax_ID, Integer.valueOf(Rent_Tax_ID));
	}

	/** Get Tax for Delivery.
		@return Tax for Delivery	  */
	public int getRent_Tax_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Rent_Tax_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name PartyName */
    public static final String COLUMNNAME_PartyName = "PartyName";
	/** Get Party Name.
	@return Party Name	  */
	public String getPartyName () 
	{
		return (String)get_Value(COLUMNNAME_PartyName);
	}
		/** Set Party Name.
		@param PartyName Party Name	  */
	public void setPartyName (String PartyName)
	{
		set_Value (COLUMNNAME_PartyName, PartyName);
	}
	
	/** Column name Phone */
    public static final String COLUMNNAME_Phone = "Phone";
	/** Set Phone.
	@param Phone 
	Identifies a telephone number
  */
	public void setPhone (String Phone)
	{
		set_Value (COLUMNNAME_Phone, Phone);
	}
	
	/** Get Phone.
		@return Identifies a telephone number
	  */
	public String getPhone () 
	{
		return (String)get_Value(COLUMNNAME_Phone);
	}
	
	 /** Column name DiscntStatus */
    public static final String COLUMNNAME_DiscntStatus = "DiscntStatus";
    
	/** Requested = RQ */
	public static final String DISCNTSTATUS_Requested = "RQ";
	/** Approved = AP */
	public static final String DISCNTSTATUS_Approved = "AP";
	/** Closed = CL */
	public static final String DISCNTSTATUS_Closed = "CL";
	/** Voided = VO */
	public static final String DISCNTSTATUS_Voided = "VO";
	/** Set Discount Status.
		@param DiscntStatus Discount Status	  */
	public void setDiscntStatus (String DiscntStatus)
	{

		set_Value (COLUMNNAME_DiscntStatus, DiscntStatus);
	}

	/** Get Discount Status.
		@return Discount Status	  */
	public String getDiscntStatus () 
	{
		return (String)get_Value(COLUMNNAME_DiscntStatus);
	}


    /** Column name TF_DiscountRequest_ID */
    public static final String COLUMNNAME_TF_DiscountRequest_ID = "TF_DiscountRequest_ID";

	/** Set Discount Request.
	@param TF_DiscountRequest_ID Discount Request	  */
	public void setTF_DiscountRequest_ID (int TF_DiscountRequest_ID)
	{
		if (TF_DiscountRequest_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_DiscountRequest_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_DiscountRequest_ID, Integer.valueOf(TF_DiscountRequest_ID));
	}
	
	/** Get Discount Request.
		@return Discount Request	  */
	public int getTF_DiscountRequest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_DiscountRequest_ID);
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
	
	private void setOrderLine(MOrderLine line, int product_ID, int C_UOM_ID, BigDecimal qty, BigDecimal price) {
		line.setM_Product_ID(product_ID, true);
		line.setQtyEntered(qty);
		BigDecimal multiplyRate =  MUOMConversion.getProductRateTo(getCtx(), product_ID, C_UOM_ID);
		
		MWeighmentEntry wEntry = new MWeighmentEntry(getCtx(), getTF_WeighmentEntry_ID(), get_TrxName());
		if(getTF_WeighmentEntry_ID() > 0 && wEntry.getCFTMultiplyRate() != null)
			multiplyRate = wEntry.getCFTMultiplyRate();
		
		if(multiplyRate != null) {						
			line.setQtyOrdered (qty.divide(multiplyRate,2, RoundingMode.HALF_EVEN));
		}
		else {
			line.setQtyOrdered(qty); 
			multiplyRate = BigDecimal.ONE;
		}
		
		
		line.setPriceActual(price.multiply(multiplyRate)); // Price in Default UOM such as Tonnage
		line.setPriceList(price); // Price for the Sales UOM
		line.setPriceLimit(line.getPriceActual()); // 
		line.setPriceEntered(price); // Price for the Sales UOM
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
				|| is_ValueChanged(COLUMNNAME_Item1_Tax_ID)
				|| is_ValueChanged(COLUMNNAME_Item1_TotalLoad) || is_ValueChanged(COLUMNNAME_Item1_VehicleType_ID)
				|| is_ValueChanged(COLUMNNAME_Item1_SandType)
				|| is_ValueChanged(COLUMNNAME_Item1_Price) || getItem1_C_OrderLine_ID() == 0)) {
			
			if(getItem1_C_OrderLine_ID() > 0) 
				ordLine = new TF_MOrderLine(getCtx(), getItem1_C_OrderLine_ID(), get_TrxName());
			else
				ordLine = new TF_MOrderLine(this);
			TF_MOrder.addProductPricingIfNot(getItem1_ID(), getM_PriceList_ID(), getC_BPartner_ID(), getItem1_Qty(), getItem1_Price(), 
					getDateOrdered(), getC_DocType().isSOTrx());
			setOrderLine(ordLine, getItem1_ID(), getItem1_UOM_ID(), getItem1_Qty(), getItem1_Price());
			
			ordLine.setC_Tax_ID(getItem1_Tax_ID());
			
			//For SO, From header itself, price is updated.
			if(!isSOTrx())
				ordLine.setIsUpdatePrice(isItem1_IsUpdatePrice());
			
			ordLine.setC_UOM_ID(getItem1_UOM_ID());
			
			//Sand Block fields
			//these should be updated in invoice line at:
			// 1. Create Subcontract Invoice
			// 2. CrusherEventHandler.
			ordLine.setBucketQty(getItem1_BucketQty());
			ordLine.setSandType(getItem1_SandType());
			ordLine.setIsPermitSales(isItem1_IsPermitSales());
			ordLine.setTonePerBucket(getTonePerBucket());
			ordLine.setBucketRate(getItem1_BucketRate());
			ordLine.setDescription(getItem1_Desc());
			ordLine.setTotalLoad(getItem1_TotalLoad());
			ordLine.setTF_VehicleType_ID(getItem1_VehicleType_ID());			
			ordLine.saveEx();
			
			//FIX: Set Bucket Rate based Total amount			
			if(getOrgType().equals(ORGTYPE_SandBlockBucket)) {
			DB.executeUpdate("UPDATE C_OrderLine SET " + TF_MOrderLine.COLUMNNAME_LineNetAmt + " = "
					+ getItem1_Amt() + " WHERE C_OrderLine_ID = " + ordLine.getC_OrderLine_ID(), get_TrxName());
			}
			
			//ordLine.setLineNetAmt(getItem1_Amt());
			DB.executeUpdate("UPDATE C_Order SET " + COLUMNNAME_Item1_C_OrderLine_ID + " = "
				+ ordLine.getC_OrderLine_ID() + " WHERE C_Order_ID = " + getC_Order_ID(), get_TrxName());
			setItem1_C_OrderLine_ID(ordLine.getC_OrderLine_ID());
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
			setOrderLine(ordLine, getItem2_ID(), getItem2_UOM_ID(), getItem2_Qty(), getItem2_Price());
			
			ordLine.setC_Tax_ID(getItem2_Tax_ID());
			ordLine.setC_UOM_ID(getItem2_UOM_ID());
			
			//Sand Block fields
			//these should be updated in invoice line at:
			// 1. Create Subcontract Invoice
			// 2. CrusherEventHandler.
			ordLine.setBucketQty(getItem2_BucketQty());
			ordLine.setSandType(getItem2_SandType());
			ordLine.setIsPermitSales(isItem2_IsPermitSales());
			ordLine.setTonePerBucket(getItem2_TonePerBucket());
			ordLine.setBucketRate(getItem2_BucketRate());
			ordLine.setDescription(getItem2_Desc());
			ordLine.setTotalLoad(getItem2_TotalLoad());			
			//ordLine.setTF_VehicleType_ID(getItem1_VehicleType_ID()); //from first line.
			ordLine.saveEx();			
			
			//FIX: Set Bucket Rate based Total amount
			if(getOrgType().equals(ORGTYPE_SandBlockBucket)) {
				DB.executeUpdate("UPDATE C_OrderLine SET " + TF_MOrderLine.COLUMNNAME_LineNetAmt + " = "
						+ getItem2_Amt() + " WHERE C_OrderLine_ID = " + ordLine.getC_OrderLine_ID(), get_TrxName());
			}
			
			DB.executeUpdate("UPDATE C_Order SET " + COLUMNNAME_Item2_C_OrderLine_ID + " = "
				+ ordLine.getC_OrderLine_ID() + " WHERE C_Order_ID = " + getC_Order_ID(), get_TrxName());
			setItem2_C_OrderLine_ID(ordLine.getC_OrderLine_ID());
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
			int defaultTaxID = Env.getContextAsInt(getCtx(), "#C_Tax_ID");
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
				|| getVehicle_C_OrderLine_ID() == 0 || is_ValueChanged(COLUMNNAME_IsTaxIncluded1))) {
			
			if(getVehicle_C_OrderLine_ID() > 0) 
				ordLine = new MOrderLine(getCtx(), getVehicle_C_OrderLine_ID(), get_TrxName());
			else
				ordLine = new MOrderLine(this);
			MRentedVehicle rentVehicle = new MRentedVehicle(getCtx(), getTF_RentedVehicle_ID(), get_TrxName());
			int productID = rentVehicle.getM_Product_ID();
			int defaultTaxID = 0;
			BigDecimal rentAmount = new BigDecimal(0);
			
			if(getRent_Tax_ID() > 0)
			{
				defaultTaxID = getRent_Tax_ID();
				
				MTax tax = new MTax(getCtx(), defaultTaxID, get_TrxName());
				BigDecimal taxRate = (BigDecimal)tax.getRate();
				BigDecimal hundred = new BigDecimal("100");
				
				rentAmount = getRent_Amt().divide(BigDecimal.ONE.add(taxRate.divide(hundred,2,RoundingMode.HALF_UP)), 2, RoundingMode.HALF_UP);
			}
			else
			{
				defaultTaxID = Env.getContextAsInt(getCtx(), "#C_Tax_ID");
				rentAmount = getDistance().multiply(getRate());
			}
			ordLine.setC_Tax_ID(defaultTaxID);
			
			TF_MOrder.addProductPricingIfNot(productID, getM_PriceList_ID(), getC_BPartner_ID(), BigDecimal.ONE, rentAmount, 
					getDateOrdered(), getC_DocType().isSOTrx());
			setOrderLine(ordLine, productID, BigDecimal.ONE, rentAmount);
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
		if(getTF_RentedVehicle_ID() > 0 && getRent_Amt().doubleValue() <= 0)
			throw new AdempiereException("Please specify Rent Amount!");
		if(isSOTrx()) {
			MPriceListUOM priceUOM = MPriceListUOM.getPriceListUOM(getCtx(), getItem1_ID(), getItem1_UOM_ID(), getC_BPartner_ID(), true, getDateAcct());
			BigDecimal price=getItem1_UnitPrice();
			BigDecimal priceMin = BigDecimal.ZERO;
			if(priceUOM != null)
				priceMin = priceUOM.getPriceMin();
			else 
				throw new AdempiereException("Please define Price in the Sales Price List by UOM!");
			if(price.compareTo(priceMin)<0 ) {
				if(getTF_DiscountRequest_ID() == 0) {
					throw new AdempiereException("You cannot complete sales entry product price less than min price. Please create discount request");
				}
				else {
					MDiscountRequest dr = new MDiscountRequest(getCtx(), getTF_DiscountRequest_ID(), get_TrxName());
					if(dr.getDiscntStatus().equals(MDiscountRequest.DISCNTSTATUS_Approved)) {
						if(dr.getApprovedPrice().doubleValue() > getItem1_UnitPrice().doubleValue()) {
							throw new AdempiereException("Please enter Approved Discounted Price to complete the order!");
						}
						else {
							dr.closeIt();
							dr.saveEx();
						}
					}
					else {
						throw new AdempiereException("Your discount request is waiting for approval!");
					}
				}
			}
		}
		createSubcontractPurchaseEntry();
		String msg = super.completeIt();
		purchasePermit();
		issuePermit();
		createTransporterInvoice();
		closeWeighmentEntry();
		closeTokenNo();
		closeYardEntry();
		
		
		createTaxInvoice();
		
		createCashSalesDiscountPayment();
		return msg;
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		MRentedVehicle rv = new MRentedVehicle(getCtx(), getTF_RentedVehicle_ID(), get_TrxName());		
		if(getTF_RentedVehicle_ID()>0 && getRent_Amt().doubleValue()==0) {
			if((rv.isOwnVehicle() && isSOTrx()) || rv.isTransporter())
				throw new AdempiereUserError("Invalid Rent Amount");
		}
		
		if(getTF_RentedVehicle_ID()>0 || !isSOTrx()) {
			setDriverTips(BigDecimal.ZERO);
		}
		
		if(!getPaymentRule().equals("B")) {
			setSalesDiscountAmt(BigDecimal.ZERO);
		}
		
		TF_MProject proj = TF_MProject.getCrusherProductionSubcontractByWarehouse(getM_Warehouse_ID());
		if(proj != null && getC_Project_ID() == 0)
			setC_Project_ID(proj.getC_Project_ID());
		
		TF_MOrg org = new TF_MOrg(getCtx(), getAD_Org_ID(), get_TrxName());
		setOrgType(org.getOrgType());
		
		if(getOrgType().equals(ORGTYPE_SandBlockBucket) && isSOTrx()) {
			if(getItem1_BucketQty().doubleValue()<=0)
				throw new AdempiereException("Line 1: Bucket qty is Mandatory");
			if(isItem1_IsPermitSales() && getItem1_PermitIssued().doubleValue()<=0.5)
				throw new AdempiereException("Permit Issued Should be greater than or equal to 0.5");
			if(getItem1_ID() > 0 && getItem1_TotalLoad().doubleValue() <= 0)
				throw new AdempiereException("Total Load is mandatory");
			
			setItem1_Amt(getItem1_Amt().setScale(0, RoundingMode.HALF_EVEN));
			setItem2_Amt(getItem2_Amt().setScale(0, RoundingMode.HALF_EVEN));
			setItem1_PermitIssued(getItem1_PermitIssued().setScale(1, RoundingMode.HALF_EVEN));
		}
		
		if(newRecord) {
			setDateAcct(getDateOrdered());
		}
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
				
				
			}
			
			if(getTF_DriverTips_Pay_ID() > 0) {
				TF_MPayment payment = new TF_MPayment(getCtx(), getTF_DriverTips_Pay_ID(), get_TrxName());
				if(payment.getDocStatus().equals(DOCSTATUS_Completed)) {
					payment.reverseCorrectIt();
				}
				payment.saveEx();
			}

			if(getC_PaymentSalesDiscount_ID() > 0) {
				TF_MPayment payment = new TF_MPayment(getCtx(), getC_PaymentSalesDiscount_ID(), get_TrxName());
				if(payment.getDocStatus().equals(DOCSTATUS_Completed)) {
					payment.reverseCorrectIt();
				}
				payment.saveEx();
			}

			if(getTF_DiscountRequest_ID() > 0) {
				MDiscountRequest dr = new MDiscountRequest(getCtx(), getTF_DiscountRequest_ID(), get_TrxName());
				dr.voidIt();
				dr.saveEx();
			}
			
			MJobworkItemIssue.ReverseFromPO(this);
			reverseTransporterInvoice();
			reverseWeighmentEntry();
			reverseTokenNo();
			reverseYardEntry();
			reverseSubcontractPurchaseEntry();
			reverseIssuedPermit();
			reversePurchasedPermit();
			voidTaxInvoice();
			voidTR_TaxInvoice();
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
				if(payment.getDocStatus().equals(DOCSTATUS_Completed)){
					payment.reverseCorrectIt();
				}
				payment.saveEx();
			}

			if(getC_PaymentSalesDiscount_ID()> 0) {
				TF_MPayment payment = new TF_MPayment(getCtx(), getC_PaymentSalesDiscount_ID(), get_TrxName());
				if(payment.getDocStatus().equals(DOCSTATUS_Completed)){
					payment.reverseCorrectIt();
				}
				payment.saveEx();
			}
			
			
			MJobworkItemIssue.ReverseFromPO(this);
			reverseTransporterInvoice();
			reverseWeighmentEntry();
			reverseTokenNo();
			reverseYardEntry();
			reverseSubcontractPurchaseEntry();
			reverseIssuedPermit();
			reversePurchasedPermit();
			voidTaxInvoice();
			voidTR_TaxInvoice();
			return super.reActivateIt();
	}

	public void createTransporterInvoice() {
		if(getTF_RentedVehicle_ID() == 0 || getRent_Amt().doubleValue() == 0)
			return;
		MRentedVehicle rv = new MRentedVehicle(getCtx(), getTF_RentedVehicle_ID(), get_TrxName());
		if(rv.isOwnVehicle())
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
			hdrDescription = hdrDescription 
					+ ", Rate/km : " + getRate().doubleValue()
					+ ", Distance : " + getDistance() + " km";
		}
		
		int load_uom_id = MSysConfig.getIntValue("LOAD_UOM", 1000072, getAD_Client_ID());
		invLine.setC_UOM_ID(load_uom_id);
		invLine.setQty(BigDecimal.ONE);
		
		int defaultTaxID = 0;
		BigDecimal rentAmount = new BigDecimal(0);
		BigDecimal taxRate = new BigDecimal(0);
		BigDecimal hundred = new BigDecimal("100");
		
		if(getRent_Tax_ID() > 0){
			defaultTaxID = getRent_Tax_ID();
			
			MTax tax = new MTax(getCtx(), defaultTaxID, get_TrxName());
			taxRate = (BigDecimal)tax.getRate();
			rentAmount = getRent_Amt().divide(BigDecimal.ONE.add(taxRate.divide(hundred,2,RoundingMode.HALF_UP)), 2, RoundingMode.HALF_UP);
		}
		else{
			defaultTaxID = Env.getContextAsInt(getCtx(), "#C_Tax_ID");
			rentAmount = getDistance().multiply(getRate());
		}
		invLine.setC_Tax_ID(defaultTaxID);
		
		BigDecimal price = rentAmount;
		if(isSOTrx()) {
			if(defaultTaxID > 0){
				price = getRentPayable().divide(BigDecimal.ONE.add(taxRate.divide(hundred,2,RoundingMode.HALF_UP)), 2, RoundingMode.HALF_UP);
			}
			else {
				price = getRentPayable();
			}
		}
		invLine.setPriceActual(price);
		invLine.setPriceList(price);
		invLine.setPriceLimit(price);
		invLine.setPriceEntered(price);
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
			if(inv.getDocStatus().equals(DOCSTATUS_Completed))
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
	
	public void closeTokenNo()
	{
		if(getTF_Token_ID() > 0) {
			MToken token = new MToken(getCtx(), getTF_Token_ID(), get_TrxName());
			token.close();
			token.saveEx();
		}
	}
	
	public void reverseWeighmentEntry() {
		if(getTF_WeighmentEntry_ID() > 0) {
			MWeighmentEntry weighment = new MWeighmentEntry(getCtx(), getTF_WeighmentEntry_ID(), get_TrxName());
			weighment.reverse();
			weighment.saveEx();
		}
	}
	
	public void reverseTokenNo()
	{
		if(getTF_Token_ID() > 0) {
			MToken token = new MToken(getCtx(), getTF_Token_ID(), get_TrxName());
			token.reverse();
			token.saveEx();
		}
	}
	
	public void closeYardEntry() {
		if(getTF_YardEntry_ID() > 0 ) {
			MYardEntry ye = new MYardEntry(getCtx(), getTF_YardEntry_ID(), get_TrxName());
			//If Yard Entry created From Weighbridge App
			if(ye.getTotalAmt().doubleValue() > 0) {
				ye.close();
				ye.saveEx();
			}
		}
	}
	
	public void reverseYardEntry() {
		if(getTF_YardEntry_ID() > 0 ) {
			MYardEntry ye = new MYardEntry(getCtx(), getTF_YardEntry_ID(), get_TrxName());
			//If Yard Entry created From Weighbridge App
			if(ye.getTotalAmt().doubleValue() > 0) {
				ye.reverse();
				ye.saveEx();
			}
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
		MProductPricing pp = new MProductPricing (M_Product_ID, C_BPartner_ID, Qty, isSOTrx, null);		
		pp.setM_PriceList_ID(M_PriceList_ID);
		pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
		pp.setPriceDate(priceDate);		
		return pp;
	}
	
	public static MProductPrice addProductPricingIfNot(int M_Product_ID, int M_PriceList_ID, int C_BPartner_ID, 
			BigDecimal Qty, BigDecimal price, Timestamp priceDate, boolean isSOTrx) {
		//Get Unit Price from Latest Price List.
		if(M_Product_ID == 0)
			return null;
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
		if(!isSOTrx()) {		
			return;
		}
		else if(getC_Project_ID() == 0 && isSOTrx()) {
			//Boulder based approach - tracking issued qty
			int Boulder_ID = MSysConfig.getIntValue("BOULDER_ID", 1000099, getAD_Client_ID(), getAD_Org_ID());
			MProduct rm = MProduct.get(getCtx(), Boulder_ID);
			if(getItem1().getC_UOM_ID() == rm.getC_UOM_ID()) {
				MSubcontractMaterialMovement.createMaterialMovement(get_TrxName(), getDateAcct(), getAD_Org_ID(), getC_Order_ID(), 
						getC_BPartner_ID(), getItem1_ID(), getItem1_Qty(), getTF_WeighmentEntry_ID());
			}
			return;
		}
				
		TF_MProject proj = new TF_MProject(getCtx(), getC_Project_ID(), get_TrxName());
		if(!TF_MProject.SUBCONTRACTTYPE_CrusherProduction.equals(proj.getSubcontractType()) &&
				proj.getM_Warehouse_ID() != getM_Warehouse_ID())
			return;
		
		MSubcontractType st = new MSubcontractType(getCtx(), proj.getTF_SubcontractType_ID(), get_TrxName());
		
		if(st.isTrackMaterialMovement())
			MSubcontractMaterialMovement.createMaterialMovement(get_TrxName(), getDateAcct(),getAD_Org_ID(), getC_Project_ID(),
					getC_Invoice_ID(), proj.getC_BPartner_ID(), getItem1_ID(), getItem1_Qty(),
					getTF_WeighmentEntry_ID());
		
		//Do not create invoice from sales
		if(!st.isCreateInvFromSales())
			return;
		
		int priceItem_Id = 0;
		int priceItem2_id = 0;
		if(st.getInvoicePriceFrom().equals(MSubcontractType.INVOICEPRICEFROM_Jobwork)) { 
			priceItem_Id = proj.getJobWork_Product_ID();
			priceItem2_id = proj.getJobWork_Product_ID();
		}		
		else {
			priceItem_Id = getItem1_ID();
			priceItem2_id = getItem2_ID();
		}
		//Crusher Production Subcontract Purchase		
		BigDecimal purchasePrice = MJobworkProductPrice.getPrice(getCtx(), getC_Project_ID(), priceItem_Id, getDateAcct()) ;
		BigDecimal purchasePrice2 = MJobworkProductPrice.getPrice(getCtx(), getC_Project_ID(), priceItem2_id, getDateAcct()) ;
		
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
		invoice.setDescription("(" + getDocumentNo() + ")");
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
		invoice.setC_Project_ID(getC_Project_ID());
		
		invoice.saveEx();
		//End Invoice Header
		
		//Invoice Line - Item1
		MInvoiceLine invLine = new MInvoiceLine(invoice);
		invLine.setM_Product_ID(getItem1_ID() , true);
		invLine.setQty(getItem1_Qty());					
		invLine.setPriceActual(purchasePrice);
		invLine.setPriceList(purchasePrice);
		invLine.setPriceLimit(purchasePrice);
		invLine.setPriceEntered(purchasePrice);		
		invLine.setC_Tax_ID(1000000);
		//invLine.setDescription(getItem1_Desc());
		
		if(getOrgType().equals(TF_MOrder.ORGTYPE_SandBlockBucket) ||  
				getOrgType().equals(TF_MOrder.ORGTYPE_SandBlockWeighbridge)) {
			invLine.set_ValueOfColumn(TF_MOrderLine.COLUMNNAME_SandType, getItem1_SandType());
			invLine.set_ValueOfColumn(TF_MOrderLine.COLUMNNAME_BucketQty, getItem1_BucketQty());
			invLine.set_ValueOfColumn(TF_MOrderLine.COLUMNNAME_IsPermitSales, isItem1_IsPermitSales() );
			invLine.set_ValueOfColumn(TF_MOrderLine.COLUMNNAME_TonePerBucket, getTonePerBucket());
			invLine.set_ValueOfColumn(TF_MOrderLine.COLUMNNAME_TotalLoad, getItem1_TotalLoad());
			invLine.set_ValueOfColumn(TF_MOrderLine.COLUMNNAME_TF_VehicleType_ID, getItem1_VehicleType_ID());
		}
		
		//Invoice Line - Item2
		MInvoiceLine invLine2 = new MInvoiceLine(invoice);
		if(getItem2_ID() > 0) { 
			invLine2.setM_Product_ID(getItem2_ID() , true);
			invLine2.setQty(getItem2_Qty());					
			invLine2.setPriceActual(purchasePrice2);
			invLine2.setPriceList(purchasePrice2);
			invLine2.setPriceLimit(purchasePrice2);
			invLine2.setPriceEntered(purchasePrice2);		
			invLine2.setC_Tax_ID(1000000);
			//invLine2.setDescription(getItem2_Desc());
			if(getOrgType().equals(TF_MOrder.ORGTYPE_SandBlockBucket) ) {
				invLine2.set_ValueOfColumn(TF_MOrderLine.COLUMNNAME_SandType, getItem2_SandType());
				invLine2.set_ValueOfColumn(TF_MOrderLine.COLUMNNAME_BucketQty, getItem2_BucketQty());
				invLine2.set_ValueOfColumn(TF_MOrderLine.COLUMNNAME_IsPermitSales, isItem2_IsPermitSales() );
				invLine2.set_ValueOfColumn(TF_MOrderLine.COLUMNNAME_TonePerBucket, getItem2_TonePerBucket());
				invLine2.set_ValueOfColumn(TF_MOrderLine.COLUMNNAME_TotalLoad, getItem2_TotalLoad());
			}
		}
		
		//MM Receipt
		if(st.isCreateMRFromSales()) {
			MInOut inout = new MInOut(invoice, 1000014, getDateAcct(), getM_Warehouse_ID());
			inout.setDescription(invoice.getDescription());
			inout.setMovementType(MInOut.MOVEMENTTYPE_VendorReceipts);
			inout.saveEx(get_TrxName());
			
			//Material Receipt Line - Item 1
			MInOutLine ioLine = new MInOutLine(inout);
			MWarehouse wh = (MWarehouse) getM_Warehouse();
			ioLine.setInvoiceLine(invLine, wh.getDefaultLocator().get_ID(), getItem1_Qty());
			ioLine.setQty(getItem1_Qty());
			ioLine.saveEx(get_TrxName());
			
			//Material Receipt Line - Item 2
			MInOutLine ioLine2 = new MInOutLine(inout);
			if(getItem2_ID() > 0) {
				ioLine2.setInvoiceLine(invLine2, wh.getDefaultLocator().get_ID(), getItem2_Qty());
				ioLine2.setQty(getItem2_Qty());
				ioLine2.saveEx(get_TrxName());
			}
			
			//Material Receipt DocAction
			if (!inout.processIt(DocAction.ACTION_Complete))
				throw new AdempiereException("Failed when processing document - " + invoice.getProcessMsg());
			inout.saveEx();
			//End DocAction
			
			invLine.setM_InOutLine_ID(ioLine.getM_InOutLine_ID());
			invLine.saveEx();
			
			if(getItem2_ID() > 0) {
				invLine2.setM_InOutLine_ID(ioLine2.getM_InOutLine_ID());
				invLine2.saveEx();
			}
			
			setSubcon_Receipt_ID(inout.getM_InOut_ID());
		}
		
		//Invoice DocAction
		if (!invoice.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + invoice.getProcessMsg());
		invoice.saveEx();
		//End DocAction
		
		setSubcon_Invoice_ID(invoice.getC_Invoice_ID());
				
	}
	
	public void reverseSubcontractPurchaseEntry() {
		MSubcontractMaterialMovement.deleteSalesEntryMovement(getC_Order_ID(), get_TrxName());
		MSubcontractMaterialMovement.deleteWeighmentMovement(getTF_WeighmentEntry_ID(), get_TrxName());
		if(getSubcon_Invoice_ID() > 0) {			
			TF_MInvoice inv = new TF_MInvoice(getCtx(), getSubcon_Invoice_ID(), get_TrxName());
			if(inv.getDocStatus().equals(DOCSTATUS_Completed)) {
				inv.reverseCorrectIt();
				inv.saveEx();
			}
			MSubcontractMaterialMovement.deleteInvoiceMovement(inv.getC_Invoice_ID(), get_TrxName());
			setSubcon_Invoice_ID(0);
		}
		if(getSubcon_Receipt_ID() > 0) {
			MInOut io = new MInOut(getCtx(), getSubcon_Receipt_ID(), get_TrxName());
			if(io.getDocStatus().equals(DOCSTATUS_Completed)) {
				io.reverseCorrectIt();
				io.saveEx();
			}
			setSubcon_Receipt_ID(0);
		}
		
	}
	
	//Only for purchase
	public void purchasePermit() {
		if(!isSOTrx()) {
			TF_MProduct prod = new TF_MProduct(getCtx(), getItem1_ID(), get_TrxName());			
			if(prod.isMaintainPermitLedger()) {
				MPermitLedger pl = new MPermitLedger(getCtx(), 0, get_TrxName());
				pl.setAD_Org_ID(getAD_Org_ID());
				pl.setC_Order_ID(getC_Order_ID());
				pl.setC_OrderLine_ID(getItem1_C_OrderLine_ID());
				pl.setM_Product_ID(getItem1_ID());
				pl.setDateAcct(getDateAcct());
				pl.setDescription(getDescription());
				pl.setQtyPurchased(getItem1_Qty());
				pl.setUnitPrice(getItem1_Price());
				pl.setPurchasedAmt(getItem1_Amt());
				pl.setQtyIssued(BigDecimal.ZERO);
				pl.saveEx();
			}
		}
	}
	
	public void reversePurchasedPermit() {
		if(!isSOTrx()) {
			TF_MProduct prod = new TF_MProduct(getCtx(), getItem1_ID(), get_TrxName());
			if(prod.isMaintainPermitLedger())
				MPermitLedger.reversePurchasedPermit(getAD_Org_ID(), getItem1_C_OrderLine_ID(), get_TrxName());
		}
	}
	
	//Only For Sales
	public void issuePermit() {		
		if(!isSOTrx()) 
			return;
		
		if(!(getOrgType().equals(ORGTYPE_SandBlockBucket) || getOrgType().equals(ORGTYPE_SandBlockWeighbridge)))
			return;
		
		MSandBlockBucketConfig config = MSandBlockBucketConfig.getBucketConfig(getAD_Org_ID(), MSandBlockBucketConfig.SANDTYPE_P, getItem1_VehicleType_ID());
		if(isItem1_IsPermitSales() && (config == null || config.getM_ProductPermitLedger_ID() == 0)) {
			MVehicleType vType = new MVehicleType(getCtx(), getItem1_VehicleType_ID(), get_TrxName());
			throw new AdempiereException("For " + vType.getName() + ", please configure Permit Ledger in Sand Block Bucket Configuration!");
		}
		if(config != null && isItem1_IsPermitSales() && getItem1_ID() == config.getM_Product_ID()
				&& config.getM_ProductPermitLedger_ID() > 0) {
			BigDecimal qtyIssue = getItem1_PermitIssued(); 
					//getItem1_BucketQty().multiply(config.getPermitTonnagePerBucket());
			BigDecimal qtyPermitStock = MPermitLedger.getAvailablePermitStockQty(config.getM_ProductPermitLedger_ID(), get_TrxName());
			if(qtyPermitStock.doubleValue() < qtyIssue.doubleValue())
				throw new AdempiereException("Insufficient Permit Stock Available!");
			while(qtyIssue.doubleValue()>0) {
				MPermitLedger permit = MPermitLedger.getAvailablePermit(config.getM_ProductPermitLedger_ID(), get_TrxName());
				BigDecimal qtyIssued = BigDecimal.ZERO;
				if(permit.getQtyBalance().doubleValue() > qtyIssue.doubleValue())
					qtyIssued = qtyIssue;
				else
					qtyIssued = permit.getQtyBalance();
				
				MPermitLedgerLine pmLine = new MPermitLedgerLine(getCtx(), 0, get_TrxName());
				pmLine.setAD_Org_ID(getAD_Org_ID());
				pmLine.setC_Order_ID(getC_Order_ID());
				pmLine.setC_OrderLine_ID(getItem1_C_OrderLine_ID());
				pmLine.setTF_PermitLedger_ID(permit.getTF_PermitLedger_ID());
				pmLine.setM_Product_ID(getItem1_ID());
				pmLine.setDescription(getItem1_Desc());
				pmLine.setQtyIssued(qtyIssued);;
				pmLine.setDateAcct(getDateAcct());
				pmLine.setUnitPrice(permit.getUnitPrice());
				pmLine.setTonePerBucket(config.getPermitTonnagePerBucket());
				pmLine.saveEx();
				permit.updateQtyIssued();
				permit.saveEx();
				qtyIssue = qtyIssue.subtract(qtyIssued);
			}				
		}	
	}
	
	public void reverseIssuedPermit() {
		if(isItem1_IsPermitSales() && isSOTrx())
			MPermitLedger.reverseIssuedPermit(getAD_Org_ID(), getItem1_C_OrderLine_ID(), get_TrxName());
	}
	
	public void createTaxInvoice() {
		if(!isOnAccount() || getTF_TRTaxInvoice_ID() > 0 || !isSOTrx())
			return;
		
		/*if(getItem1_PermitIssued().doubleValue() <= 0)
			throw new AdempiereException("Invalid Permit Issued!");*/
		
		MTRTaxInvoice inv = new MTRTaxInvoice(getCtx(),0, get_TrxName());
		inv.setAD_Org_ID(getAD_Org_ID());
		inv.setDateAcct(getDateAcct());
		inv.setM_Warehouse_ID(getM_Warehouse_ID());
		inv.setPartyName(getPartyName());
		inv.setPostTaxToCustomer(false);
		inv.setPostGSTAsExpense(true);
		inv.setC_BPartner_ID(getC_BPartner_ID());
		inv.setDateSupply(getDateAcct());
		
		MWeighmentEntry wEntry = new MWeighmentEntry(getCtx(), getTF_WeighmentEntry_ID(), get_TrxName());
		TF_MBPartner partner = new TF_MBPartner(getCtx(),getC_BPartner_ID(),get_TrxName());
		if(wEntry != null && partner.getIsPOSCashBP() && getPartyName() == null) {
			inv.setPartyName(wEntry.getPartyName());
		}
		
		MDestination dest = new MDestination(getCtx(), getTF_Destination_ID(), get_TrxName());
		
		inv.setPlaceOfSupply(dest.getName());
		
		inv.setVehicleNo(getVehicleNo());		
		//inv.calcAmounts();		
		inv.setC_BankAccount_ID(TF_MBankAccount.getDefaultBankAccount(getCtx(), Env.getAD_Org_ID(getCtx()), null));
		inv.saveEx();

		MTRTaxInvoiceLine invLine = new MTRTaxInvoiceLine(inv);
		invLine.setAD_Org_ID(getAD_Org_ID());
		invLine.setM_Product_ID(getItem1_ID());
		invLine.setC_UOM_ID(getItem1_UOM_ID());
		
		TF_MBPartner bp = new TF_MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
		//MCustomerType custType = new MCustomerType(getCtx(), bp.getTF_CustomerType_ID(), get_TrxName());
		TF_MProduct prod=new TF_MProduct(getCtx(), getItem1_ID(), get_TrxName());
		
		
		//Set Price based on Customer Type Billing Price Ratio
		//BigDecimal price = getItem1_Price();		
		BigDecimal price = prod.getBillPrice();
		if(price == null || price.doubleValue() == 0)
			throw new AdempiereException("Please set Bill Price for " + prod.getName());
		/*
		if(isRentBreakup())
		{
			if(isRentInclusive()) {
				price = getItem1_UnitPrice();
			}
			else {
				price = getItem1_UnitPrice().add(getItem1_UnitRent());
			}
		}
		
		if(custType.getBillingPriceRatio().doubleValue() > 0)
			price = price.multiply(custType.getBillingPriceRatio());
		*/
		
		//Set Qty based on Customer Type Billing Qty Ratio
		// When BillingQtyRation is ZERO then Based on the amount BillingQty has to be calcualted. 
		BigDecimal qty = getItem1_Qty();
		//if(custType.getBillingQtyRatio().doubleValue() > 0)
		//	qty = qty.multiply(custType.getBillingQtyRatio());
		//else {
			qty = getGrandTotal().divide(price, 2, RoundingMode.HALF_EVEN);
		//}
		invLine.setQty(qty);
		
		
		//Exclude Tax amount from Price
		//TF_MProduct prod = new TF_MProduct(getCtx(), getItem1_ID(), get_TrxName());
		MTax tax = new MTax(getCtx(), prod.getTax_ID(true), get_TrxName());				
		BigDecimal taxRate = tax.getRate();
		BigDecimal hundred = new BigDecimal("100");				
		//BigDecimal priceExcludesTax = price.divide(BigDecimal.ONE
		//		.add(taxRate.divide(hundred,2,RoundingMode.HALF_UP)), 2, RoundingMode.HALF_UP);				
		invLine.setPrice(prod.getBillPrice());
		invLine.setTaxableAmount(prod.getBillPrice().multiply(invLine.getQty()));
						
		BigDecimal SGST_Rate = taxRate.divide(new BigDecimal(2), 2, RoundingMode.HALF_EVEN);				
		invLine.setSGST_Rate(SGST_Rate);
		invLine.setCGST_Rate(SGST_Rate);
		invLine.setIGST_Rate(BigDecimal.ZERO);
		invLine.setIGST_Amt(BigDecimal.ZERO);
		
		invLine.calcAmounts();
		invLine.saveEx();
		
		//No Rent Included
		
		setTF_TRTaxInvoice_ID(inv.getTF_TRTaxInvoice_ID());
		inv.processIt(DOCACTION_Complete);
		inv.saveEx();
	}
	
	public void voidTaxInvoice() {
		if(getTF_TaxInvoice_ID() > 0) {
			MTaxInvoice inv = new MTaxInvoice(getCtx(), getTF_TaxInvoice_ID(), get_TrxName());
			inv.reverseIt();
			inv.setDocStatus(DOCSTATUS_Voided);
			inv.setProcessed(true);
			inv.saveEx();
			setTF_TaxInvoice_ID(0);
		}
	}

	public void voidTR_TaxInvoice() {
		if(getTF_TRTaxInvoice_ID() > 0) {
			MTRTaxInvoice inv = new MTRTaxInvoice(getCtx(), getTF_TRTaxInvoice_ID(), get_TrxName());
			inv.reverseIt();
			inv.setDocStatus(DOCSTATUS_Voided);
			inv.setProcessed(true);
			inv.saveEx();
			setTF_TRTaxInvoice_ID(0);
		}
	}
	
	/** Set Cash Sales Discount.
	@param SalesDiscountAmt Cash Sales Discount	  */
	public void setSalesDiscountAmt (BigDecimal SalesDiscountAmt)
	{
		set_Value (COLUMNNAME_SalesDiscountAmt, SalesDiscountAmt);
	}
	
	/** Get Cash Sales Discount.
		@return Cash Sales Discount	  */
	public BigDecimal getSalesDiscountAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SalesDiscountAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_Payment getC_PaymentSalesDiscount() throws RuntimeException
    {
		return (org.compiere.model.I_C_Payment)MTable.get(getCtx(), org.compiere.model.I_C_Payment.Table_Name)
			.getPO(getC_PaymentSalesDiscount_ID(), get_TrxName());	}

	/** Set Sales Discount Payment.
		@param C_PaymentSalesDiscount_ID Sales Discount Payment	  */
	public void setC_PaymentSalesDiscount_ID (int C_PaymentSalesDiscount_ID)
	{
		if (C_PaymentSalesDiscount_ID < 1) 
			set_Value (COLUMNNAME_C_PaymentSalesDiscount_ID, null);
		else 
			set_Value (COLUMNNAME_C_PaymentSalesDiscount_ID, Integer.valueOf(C_PaymentSalesDiscount_ID));
	}

	/** Get Sales Discount Payment.
		@return Sales Discount Payment	  */
	public int getC_PaymentSalesDiscount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PaymentSalesDiscount_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	
	private void createCashSalesDiscountPayment() {
		if(get_Value(TF_MOrder.COLUMNNAME_SalesDiscountAmt) == null)
			return;
		BigDecimal amt = (BigDecimal) get_Value(TF_MOrder.COLUMNNAME_SalesDiscountAmt);
		if(amt.doubleValue() == 0)
			return;
		
		MGLPostingConfig glConfig = MGLPostingConfig.getMGLPostingConfig(getCtx());
		
		//Create Driver Tips Charge if it is not there already.
		//It should be in atomic transaction to get account settings of Charge for the current docaction transaction.
		TF_MCharge charge = TF_MCharge.createChargeFromAccount(getCtx(), glConfig.getSalesDiscountAcct_ID(), null);
		
		//Get Invoice Document no
		String Where=" C_Order_ID = ? AND DocStatus = 'CO'";
		
		MInvoice inv = new Query(getCtx(), MInvoice.Table_Name,Where, get_TrxName())
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(getC_Order_ID())
				.first();
		
		String invoiceNo="";
		if(inv!=null) {
			invoiceNo=inv.getDocumentNo();
		}

		//Posting Payment Document for Driver Tips
		TF_MPayment payment = new TF_MPayment(getCtx(), 0, get_TrxName());
		payment.setDateAcct(getDateAcct());
		payment.setDateTrx(getDateAcct());
		payment.setDescription("Cash Sales Discount for "+ invoiceNo);
		//* Commented for Laxmi Stone */
		//payment.setCashType(TF_MPayment.CASHTYPE_GeneralExpense);
		payment.setC_DocType_ID(false);		
		payment.setC_Charge_ID(charge.getC_Charge_ID());
		payment.setUser1_ID(getUser1_ID()); // Profit Center
		payment.setC_ElementValue_ID(glConfig.getSalesDiscountAcct_ID());
		
		payment.setC_BankAccount_ID(TF_MBankAccount.getDefaultCashAccount(getCtx(), Env.getAD_Org_ID(getCtx()), null));
		MUser user = MUser.get(getCtx(), Env.getAD_User_ID(getCtx()));
		payment.setC_BPartner_ID(user.getC_BPartner_ID());
		payment.setPayAmt(amt);
		payment.setC_Currency_ID(Env.getContextAsInt(getCtx(), "$C_Currency_ID"));
		payment.setDocStatus(TF_MOrder.DOCSTATUS_InProgress);
		payment.setTenderType(TF_MPayment.TENDERTYPE_Cash);
		payment.saveEx();
		payment.processIt(DocAction.ACTION_Complete);
		payment.saveEx();
		
		TF_MOrder mOrder=new TF_MOrder(getCtx(), get_ID(), get_TrxName());
		mOrder.set_ValueOfColumn(TF_MOrder.COLUMNNAME_C_PaymentSalesDiscount_ID, payment.getC_Payment_ID());
		mOrder.saveEx();
		
	}

}
