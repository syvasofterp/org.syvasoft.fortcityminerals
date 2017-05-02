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

/** Generated Model for TF_Jobwork_ProductPrice
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_TF_Jobwork_ProductPrice extends PO implements I_TF_Jobwork_ProductPrice, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170501L;

    /** Standard Constructor */
    public X_TF_Jobwork_ProductPrice (Properties ctx, int TF_Jobwork_ProductPrice_ID, String trxName)
    {
      super (ctx, TF_Jobwork_ProductPrice_ID, trxName);
      /** if (TF_Jobwork_ProductPrice_ID == 0)
        {
			setM_Product_ID (0);
			setTF_Jobwork_ProductPrice_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_Jobwork_ProductPrice (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_TF_Jobwork_ProductPrice[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException
    {
		return (org.compiere.model.I_C_Project)MTable.get(getCtx(), org.compiere.model.I_C_Project.Table_Name)
			.getPO(getC_Project_ID(), get_TrxName());	}

	/** Set Project.
		@param C_Project_ID 
		Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID)
	{
		if (C_Project_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Project_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Project_ID, Integer.valueOf(C_Project_ID));
	}

	/** Get Project.
		@return Financial Project
	  */
	public int getC_Project_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Project_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set List Price.
		@param PriceList 
		List Price
	  */
	public void setPriceList (BigDecimal PriceList)
	{
		set_Value (COLUMNNAME_PriceList, PriceList);
	}

	/** Get List Price.
		@return List Price
	  */
	public BigDecimal getPriceList () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceList);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Jobwork Product Price.
		@param TF_Jobwork_ProductPrice_ID Jobwork Product Price	  */
	public void setTF_Jobwork_ProductPrice_ID (int TF_Jobwork_ProductPrice_ID)
	{
		if (TF_Jobwork_ProductPrice_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_Jobwork_ProductPrice_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_Jobwork_ProductPrice_ID, Integer.valueOf(TF_Jobwork_ProductPrice_ID));
	}

	/** Get Jobwork Product Price.
		@return Jobwork Product Price	  */
	public int getTF_Jobwork_ProductPrice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_Jobwork_ProductPrice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_Jobwork_ProductPrice_UU.
		@param TF_Jobwork_ProductPrice_UU TF_Jobwork_ProductPrice_UU	  */
	public void setTF_Jobwork_ProductPrice_UU (String TF_Jobwork_ProductPrice_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_Jobwork_ProductPrice_UU, TF_Jobwork_ProductPrice_UU);
	}

	/** Get TF_Jobwork_ProductPrice_UU.
		@return TF_Jobwork_ProductPrice_UU	  */
	public String getTF_Jobwork_ProductPrice_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_Jobwork_ProductPrice_UU);
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
}