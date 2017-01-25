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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for TF_GLPosting_Config
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_TF_GLPosting_Config extends PO implements I_TF_GLPosting_Config, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170125L;

    /** Standard Constructor */
    public X_TF_GLPosting_Config (Properties ctx, int TF_GLPosting_Config_ID, String trxName)
    {
      super (ctx, TF_GLPosting_Config_ID, trxName);
      /** if (TF_GLPosting_Config_ID == 0)
        {
			setQuarryExp_Acct (0);
			setQuarryRent_Acct (0);
			setTF_GLPosting_Config_ID (0);
			setVehicleExp_Acct (0);
			setVehicleRent_Acct (0);
        } */
    }

    /** Load Constructor */
    public X_TF_GLPosting_Config (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_GLPosting_Config[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_ElementValue getQuarryExp_A() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getQuarryExp_Acct(), get_TrxName());	}

	/** Set Quarry Expense.
		@param QuarryExp_Acct Quarry Expense	  */
	public void setQuarryExp_Acct (int QuarryExp_Acct)
	{
		set_Value (COLUMNNAME_QuarryExp_Acct, Integer.valueOf(QuarryExp_Acct));
	}

	/** Get Quarry Expense.
		@return Quarry Expense	  */
	public int getQuarryExp_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_QuarryExp_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getQuarryRent_A() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getQuarryRent_Acct(), get_TrxName());	}

	/** Set Quarry Rent.
		@param QuarryRent_Acct Quarry Rent	  */
	public void setQuarryRent_Acct (int QuarryRent_Acct)
	{
		set_Value (COLUMNNAME_QuarryRent_Acct, Integer.valueOf(QuarryRent_Acct));
	}

	/** Get Quarry Rent.
		@return Quarry Rent	  */
	public int getQuarryRent_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_QuarryRent_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GL Posting Configuratoin.
		@param TF_GLPosting_Config_ID GL Posting Configuratoin	  */
	public void setTF_GLPosting_Config_ID (int TF_GLPosting_Config_ID)
	{
		if (TF_GLPosting_Config_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_GLPosting_Config_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_GLPosting_Config_ID, Integer.valueOf(TF_GLPosting_Config_ID));
	}

	/** Get GL Posting Configuratoin.
		@return GL Posting Configuratoin	  */
	public int getTF_GLPosting_Config_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_GLPosting_Config_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_GLPosting_Config_UU.
		@param TF_GLPosting_Config_UU TF_GLPosting_Config_UU	  */
	public void setTF_GLPosting_Config_UU (String TF_GLPosting_Config_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_GLPosting_Config_UU, TF_GLPosting_Config_UU);
	}

	/** Get TF_GLPosting_Config_UU.
		@return TF_GLPosting_Config_UU	  */
	public String getTF_GLPosting_Config_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_GLPosting_Config_UU);
	}

	public org.compiere.model.I_C_ElementValue getVehicleExp_A() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getVehicleExp_Acct(), get_TrxName());	}

	/** Set Vehicle Expense.
		@param VehicleExp_Acct Vehicle Expense	  */
	public void setVehicleExp_Acct (int VehicleExp_Acct)
	{
		set_Value (COLUMNNAME_VehicleExp_Acct, Integer.valueOf(VehicleExp_Acct));
	}

	/** Get Vehicle Expense.
		@return Vehicle Expense	  */
	public int getVehicleExp_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_VehicleExp_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getVehicleRent_A() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getVehicleRent_Acct(), get_TrxName());	}

	/** Set Vehicle Rent.
		@param VehicleRent_Acct Vehicle Rent	  */
	public void setVehicleRent_Acct (int VehicleRent_Acct)
	{
		set_Value (COLUMNNAME_VehicleRent_Acct, Integer.valueOf(VehicleRent_Acct));
	}

	/** Get Vehicle Rent.
		@return Vehicle Rent	  */
	public int getVehicleRent_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_VehicleRent_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}