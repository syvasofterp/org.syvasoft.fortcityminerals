/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for TF_Quarry
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_Quarry extends PO implements I_TF_Quarry, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210703L;

    /** Standard Constructor */
    public X_TF_Quarry (Properties ctx, int TF_Quarry_ID, String trxName)
    {
      super (ctx, TF_Quarry_ID, trxName);
      /** if (TF_Quarry_ID == 0)
        {
			setIsLeased (false);
// N
			setName (null);
			setTF_Quarry_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_TF_Quarry (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_TF_Quarry[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Actual Quantity.
		@param ActualQty 
		The actual quantity
	  */
	public void setActualQty (BigDecimal ActualQty)
	{
		set_Value (COLUMNNAME_ActualQty, ActualQty);
	}

	/** Get Actual Quantity.
		@return The actual quantity
	  */
	public BigDecimal getActualQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ActualQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Address.
		@param Address Address	  */
	public void setAddress (String Address)
	{
		set_Value (COLUMNNAME_Address, Address);
	}

	/** Get Address.
		@return Address	  */
	public String getAddress () 
	{
		return (String)get_Value(COLUMNNAME_Address);
	}

	/** Set Balance Qty.
		@param BalanceQty Balance Qty	  */
	public void setBalanceQty (BigDecimal BalanceQty)
	{
		set_Value (COLUMNNAME_BalanceQty, BalanceQty);
	}

	/** Get Balance Qty.
		@return Balance Qty	  */
	public BigDecimal getBalanceQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BalanceQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getC_ElementValue() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getC_ElementValue_ID(), get_TrxName());	}

	/** Set Account Element.
		@param C_ElementValue_ID 
		Account Element
	  */
	public void setC_ElementValue_ID (int C_ElementValue_ID)
	{
		if (C_ElementValue_ID < 1) 
			set_Value (COLUMNNAME_C_ElementValue_ID, null);
		else 
			set_Value (COLUMNNAME_C_ElementValue_ID, Integer.valueOf(C_ElementValue_ID));
	}

	/** Get Account Element.
		@return Account Element
	  */
	public int getC_ElementValue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ElementValue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getC_ElementValuePermitExp() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getC_ElementValuePermitExp_ID(), get_TrxName());	}

	/** Set Permit Expense Account Head.
		@param C_ElementValuePermitExp_ID Permit Expense Account Head	  */
	public void setC_ElementValuePermitExp_ID (int C_ElementValuePermitExp_ID)
	{
		if (C_ElementValuePermitExp_ID < 1) 
			set_Value (COLUMNNAME_C_ElementValuePermitExp_ID, null);
		else 
			set_Value (COLUMNNAME_C_ElementValuePermitExp_ID, Integer.valueOf(C_ElementValuePermitExp_ID));
	}

	/** Get Permit Expense Account Head.
		@return Permit Expense Account Head	  */
	public int getC_ElementValuePermitExp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ElementValuePermitExp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getC_UOM_ID(), get_TrxName());	}

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Create Profit Center.
		@param CreateProfitCenter Create Profit Center	  */
	public void setCreateProfitCenter (String CreateProfitCenter)
	{
		set_Value (COLUMNNAME_CreateProfitCenter, CreateProfitCenter);
	}

	/** Get Create Profit Center.
		@return Create Profit Center	  */
	public String getCreateProfitCenter () 
	{
		return (String)get_Value(COLUMNNAME_CreateProfitCenter);
	}

	/** Set Delivered TP Quantity.
		@param DeliveredTPQty Delivered TP Quantity	  */
	public void setDeliveredTPQty (BigDecimal DeliveredTPQty)
	{
		set_Value (COLUMNNAME_DeliveredTPQty, DeliveredTPQty);
	}

	/** Get Delivered TP Quantity.
		@return Delivered TP Quantity	  */
	public BigDecimal getDeliveredTPQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DeliveredTPQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Leased.
		@param IsLeased Leased	  */
	public void setIsLeased (boolean IsLeased)
	{
		set_Value (COLUMNNAME_IsLeased, Boolean.valueOf(IsLeased));
	}

	/** Get Leased.
		@return Leased	  */
	public boolean isLeased () 
	{
		Object oo = get_Value(COLUMNNAME_IsLeased);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set License No.
		@param LicenseNo License No	  */
	public void setLicenseNo (String LicenseNo)
	{
		set_Value (COLUMNNAME_LicenseNo, LicenseNo);
	}

	/** Get License No.
		@return License No	  */
	public String getLicenseNo () 
	{
		return (String)get_Value(COLUMNNAME_LicenseNo);
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Available Quantity.
		@param QtyAvailable 
		Available Quantity (On Hand - Reserved)
	  */
	public void setQtyAvailable (BigDecimal QtyAvailable)
	{
		set_Value (COLUMNNAME_QtyAvailable, QtyAvailable);
	}

	/** Get Available Quantity.
		@return Available Quantity (On Hand - Reserved)
	  */
	public BigDecimal getQtyAvailable () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyAvailable);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Balance Quantity.
		@param QtyBalance Balance Quantity	  */
	public void setQtyBalance (BigDecimal QtyBalance)
	{
		throw new IllegalArgumentException ("QtyBalance is virtual column");	}

	/** Get Balance Quantity.
		@return Balance Quantity	  */
	public BigDecimal getQtyBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyBalance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Consumed Quantity.
		@param QtyConsumed Consumed Quantity	  */
	public void setQtyConsumed (BigDecimal QtyConsumed)
	{
		set_Value (COLUMNNAME_QtyConsumed, QtyConsumed);
	}

	/** Get Consumed Quantity.
		@return Consumed Quantity	  */
	public BigDecimal getQtyConsumed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyConsumed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Tender Amount.
		@param TenderAmount 
		Tender Amount
	  */
	public void setTenderAmount (BigDecimal TenderAmount)
	{
		set_Value (COLUMNNAME_TenderAmount, TenderAmount);
	}

	/** Get Tender Amount.
		@return Tender Amount
	  */
	public BigDecimal getTenderAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TenderAmount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_TF_Destination getTF_Destination() throws RuntimeException
    {
		return (I_TF_Destination)MTable.get(getCtx(), I_TF_Destination.Table_Name)
			.getPO(getTF_Destination_ID(), get_TrxName());	}

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

	/** Set Quarry.
		@param TF_Quarry_ID Quarry	  */
	public void setTF_Quarry_ID (int TF_Quarry_ID)
	{
		if (TF_Quarry_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_Quarry_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_Quarry_ID, Integer.valueOf(TF_Quarry_ID));
	}

	/** Get Quarry.
		@return Quarry	  */
	public int getTF_Quarry_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_Quarry_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_Quarry_UU.
		@param TF_Quarry_UU TF_Quarry_UU	  */
	public void setTF_Quarry_UU (String TF_Quarry_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_Quarry_UU, TF_Quarry_UU);
	}

	/** Get TF_Quarry_UU.
		@return TF_Quarry_UU	  */
	public String getTF_Quarry_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_Quarry_UU);
	}

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
		set_Value (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}

	/** Set Valid to.
		@param ValidTo 
		Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo)
	{
		set_Value (COLUMNNAME_ValidTo, ValidTo);
	}

	/** Get Valid to.
		@return Valid to including this date (last day)
	  */
	public Timestamp getValidTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidTo);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

	/** Sales = 1SO */
	public static final String WEIGHMENTENTRYTYPE_Sales = "1SO";
	/** Input = 2PO */
	public static final String WEIGHMENTENTRYTYPE_Input = "2PO";
	/** Own Production Receipt = 3PR */
	public static final String WEIGHMENTENTRYTYPE_OwnProductionReceipt = "3PR";
	/** Subcontract Production Receipt = 4SR */
	public static final String WEIGHMENTENTRYTYPE_SubcontractProductionReceipt = "4SR";
	/** Stock to Crusher = 5KA */
	public static final String WEIGHMENTENTRYTYPE_StockToCrusher = "5KA";
	/** Other Purchase = 8OP */
	public static final String WEIGHMENTENTRYTYPE_OtherPurchase = "8OP";
	/** Set Type.
		@param WeighmentEntryType Type	  */
	public void setWeighmentEntryType (String WeighmentEntryType)
	{

		set_Value (COLUMNNAME_WeighmentEntryType, WeighmentEntryType);
	}

	/** Get Type.
		@return Type	  */
	public String getWeighmentEntryType () 
	{
		return (String)get_Value(COLUMNNAME_WeighmentEntryType);
	}
}