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

/** Generated Model for PM_MachineryType
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_PM_MachineryType extends PO implements I_PM_MachineryType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20200612L;

    /** Standard Constructor */
    public X_PM_MachineryType (Properties ctx, int PM_MachineryType_ID, String trxName)
    {
      super (ctx, PM_MachineryType_ID, trxName);
      /** if (PM_MachineryType_ID == 0)
        {
			setDescription (null);
			setName (null);
			setPM_MachineryType_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PM_MachineryType (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PM_MachineryType[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Machinery Type.
		@param PM_MachineryType_ID Machinery Type	  */
	public void setPM_MachineryType_ID (int PM_MachineryType_ID)
	{
		if (PM_MachineryType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PM_MachineryType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PM_MachineryType_ID, Integer.valueOf(PM_MachineryType_ID));
	}

	/** Get Machinery Type.
		@return Machinery Type	  */
	public int getPM_MachineryType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PM_MachineryType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set PM_MachineryType_UU.
		@param PM_MachineryType_UU PM_MachineryType_UU	  */
	public void setPM_MachineryType_UU (String PM_MachineryType_UU)
	{
		set_ValueNoCheck (COLUMNNAME_PM_MachineryType_UU, PM_MachineryType_UU);
	}

	/** Get PM_MachineryType_UU.
		@return PM_MachineryType_UU	  */
	public String getPM_MachineryType_UU () 
	{
		return (String)get_Value(COLUMNNAME_PM_MachineryType_UU);
	}
}