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

/** Generated Model for PM_Meter_Log
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_PM_Meter_Log extends PO implements I_PM_Meter_Log, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20200612L;

    /** Standard Constructor */
    public X_PM_Meter_Log (Properties ctx, int PM_Meter_Log_ID, String trxName)
    {
      super (ctx, PM_Meter_Log_ID, trxName);
      /** if (PM_Meter_Log_ID == 0)
        {
			setC_UOM_ID (0);
			setClosing_Meter (Env.ZERO);
			setDateReport (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setOpening_Meter (Env.ZERO);
			setPM_Machinery_ID (0);
			setPM_Meter_Log_ID (0);
			setRunning_Meter (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_PM_Meter_Log (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PM_Meter_Log[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Closing Meter.
		@param Closing_Meter Closing Meter	  */
	public void setClosing_Meter (BigDecimal Closing_Meter)
	{
		set_Value (COLUMNNAME_Closing_Meter, Closing_Meter);
	}

	/** Get Closing Meter.
		@return Closing Meter	  */
	public BigDecimal getClosing_Meter () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Closing_Meter);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Report Date.
		@param DateReport 
		Expense/Time Report Date
	  */
	public void setDateReport (Timestamp DateReport)
	{
		set_Value (COLUMNNAME_DateReport, DateReport);
	}

	/** Get Report Date.
		@return Expense/Time Report Date
	  */
	public Timestamp getDateReport () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateReport);
	}

	/** Set Opening Meter.
		@param Opening_Meter Opening Meter	  */
	public void setOpening_Meter (BigDecimal Opening_Meter)
	{
		set_Value (COLUMNNAME_Opening_Meter, Opening_Meter);
	}

	/** Get Opening Meter.
		@return Opening Meter	  */
	public BigDecimal getOpening_Meter () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Opening_Meter);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_PM_Machinery getPM_Machinery() throws RuntimeException
    {
		return (I_PM_Machinery)MTable.get(getCtx(), I_PM_Machinery.Table_Name)
			.getPO(getPM_Machinery_ID(), get_TrxName());	}

	/** Set Machinery.
		@param PM_Machinery_ID Machinery	  */
	public void setPM_Machinery_ID (int PM_Machinery_ID)
	{
		if (PM_Machinery_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PM_Machinery_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PM_Machinery_ID, Integer.valueOf(PM_Machinery_ID));
	}

	/** Get Machinery.
		@return Machinery	  */
	public int getPM_Machinery_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PM_Machinery_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Machinery Meter Log.
		@param PM_Meter_Log_ID Machinery Meter Log	  */
	public void setPM_Meter_Log_ID (int PM_Meter_Log_ID)
	{
		if (PM_Meter_Log_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PM_Meter_Log_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PM_Meter_Log_ID, Integer.valueOf(PM_Meter_Log_ID));
	}

	/** Get Machinery Meter Log.
		@return Machinery Meter Log	  */
	public int getPM_Meter_Log_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PM_Meter_Log_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set PM_Meter_Log_UU.
		@param PM_Meter_Log_UU PM_Meter_Log_UU	  */
	public void setPM_Meter_Log_UU (String PM_Meter_Log_UU)
	{
		set_ValueNoCheck (COLUMNNAME_PM_Meter_Log_UU, PM_Meter_Log_UU);
	}

	/** Get PM_Meter_Log_UU.
		@return PM_Meter_Log_UU	  */
	public String getPM_Meter_Log_UU () 
	{
		return (String)get_Value(COLUMNNAME_PM_Meter_Log_UU);
	}

	/** Set Running Meter.
		@param Running_Meter Running Meter	  */
	public void setRunning_Meter (BigDecimal Running_Meter)
	{
		set_Value (COLUMNNAME_Running_Meter, Running_Meter);
	}

	/** Get Running Meter.
		@return Running Meter	  */
	public BigDecimal getRunning_Meter () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Running_Meter);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}