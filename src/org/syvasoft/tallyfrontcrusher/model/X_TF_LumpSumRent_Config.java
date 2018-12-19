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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for TF_LumpSumRent_Config
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_LumpSumRent_Config extends PO implements I_TF_LumpSumRent_Config, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181217L;

    /** Standard Constructor */
    public X_TF_LumpSumRent_Config (Properties ctx, int TF_LumpSumRent_Config_ID, String trxName)
    {
      super (ctx, TF_LumpSumRent_Config_ID, trxName);
      /** if (TF_LumpSumRent_Config_ID == 0)
        {
			setTF_LumpSumRent_Config_ID (0);
			setTF_VehicleType_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_LumpSumRent_Config (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_LumpSumRent_Config[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set MaxKM.
		@param MaxKM MaxKM	  */
	public void setMaxKM (int MaxKM)
	{
		set_Value (COLUMNNAME_MaxKM, Integer.valueOf(MaxKM));
	}

	/** Get MaxKM.
		@return MaxKM	  */
	public int getMaxKM () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MaxKM);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set MinKM.
		@param MinKM MinKM	  */
	public void setMinKM (int MinKM)
	{
		set_Value (COLUMNNAME_MinKM, Integer.valueOf(MinKM));
	}

	/** Get MinKM.
		@return MinKM	  */
	public int getMinKM () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MinKM);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set RateKM.
		@param ratekm RateKM	  */
	public void setratekm (BigDecimal ratekm)
	{
		set_Value (COLUMNNAME_ratekm, ratekm);
	}

	/** Get RateKM.
		@return RateKM	  */
	public BigDecimal getratekm () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ratekm);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Lumpsum Rent Amount.
		@param Rent_Amt Lumpsum Rent Amount	  */
	public void setRent_Amt (BigDecimal Rent_Amt)
	{
		set_Value (COLUMNNAME_Rent_Amt, Rent_Amt);
	}

	/** Get Lumpsum Rent Amount.
		@return Lumpsum Rent Amount	  */
	public BigDecimal getRent_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rent_Amt);
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
			set_ValueNoCheck (COLUMNNAME_TF_Destination_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_Destination_ID, Integer.valueOf(TF_Destination_ID));
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

	/** Set TF_LumpSumRent_Config.
		@param TF_LumpSumRent_Config_ID TF_LumpSumRent_Config	  */
	public void setTF_LumpSumRent_Config_ID (int TF_LumpSumRent_Config_ID)
	{
		if (TF_LumpSumRent_Config_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_LumpSumRent_Config_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_LumpSumRent_Config_ID, Integer.valueOf(TF_LumpSumRent_Config_ID));
	}

	/** Get TF_LumpSumRent_Config.
		@return TF_LumpSumRent_Config	  */
	public int getTF_LumpSumRent_Config_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_LumpSumRent_Config_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_LumpSumRent_Config_UU.
		@param TF_LumpSumRent_Config_UU TF_LumpSumRent_Config_UU	  */
	public void setTF_LumpSumRent_Config_UU (String TF_LumpSumRent_Config_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_LumpSumRent_Config_UU, TF_LumpSumRent_Config_UU);
	}

	/** Get TF_LumpSumRent_Config_UU.
		@return TF_LumpSumRent_Config_UU	  */
	public String getTF_LumpSumRent_Config_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_LumpSumRent_Config_UU);
	}

	public I_TF_VehicleType getTF_VehicleType() throws RuntimeException
    {
		return (I_TF_VehicleType)MTable.get(getCtx(), I_TF_VehicleType.Table_Name)
			.getPO(getTF_VehicleType_ID(), get_TrxName());	}

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
}