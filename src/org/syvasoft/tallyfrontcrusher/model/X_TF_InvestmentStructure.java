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

/** Generated Model for TF_InvestmentStructure
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_TF_InvestmentStructure extends PO implements I_TF_InvestmentStructure, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171212L;

    /** Standard Constructor */
    public X_TF_InvestmentStructure (Properties ctx, int TF_InvestmentStructure_ID, String trxName)
    {
      super (ctx, TF_InvestmentStructure_ID, trxName);
      /** if (TF_InvestmentStructure_ID == 0)
        {
			setC_ElementValue_ID (0);
			setTF_InvestmentStructure_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_InvestmentStructure (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_InvestmentStructure[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_ElementValue getC_ElementValue() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getC_ElementValue_ID(), get_TrxName());	}

	/** Set Account Head.
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

	/** Get Account Head.
		@return Account Element
	  */
	public int getC_ElementValue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ElementValue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Amount Paid.
		@param Paid_Amount Amount Paid	  */
	public void setPaid_Amount (BigDecimal Paid_Amount)
	{
		set_Value (COLUMNNAME_Paid_Amount, Paid_Amount);
	}

	/** Get Amount Paid.
		@return Amount Paid	  */
	public BigDecimal getPaid_Amount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Paid_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Amount Payable.
		@param Payable_Amount Amount Payable	  */
	public void setPayable_Amount (BigDecimal Payable_Amount)
	{
		set_Value (COLUMNNAME_Payable_Amount, Payable_Amount);
	}

	/** Get Amount Payable.
		@return Amount Payable	  */
	public BigDecimal getPayable_Amount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Payable_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Investment Struture.
		@param TF_InvestmentStructure_ID Investment Struture	  */
	public void setTF_InvestmentStructure_ID (int TF_InvestmentStructure_ID)
	{
		if (TF_InvestmentStructure_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_InvestmentStructure_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_InvestmentStructure_ID, Integer.valueOf(TF_InvestmentStructure_ID));
	}

	/** Get Investment Struture.
		@return Investment Struture	  */
	public int getTF_InvestmentStructure_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_InvestmentStructure_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_InvestmentStructure_UU.
		@param TF_InvestmentStructure_UU TF_InvestmentStructure_UU	  */
	public void setTF_InvestmentStructure_UU (String TF_InvestmentStructure_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_InvestmentStructure_UU, TF_InvestmentStructure_UU);
	}

	/** Get TF_InvestmentStructure_UU.
		@return TF_InvestmentStructure_UU	  */
	public String getTF_InvestmentStructure_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_InvestmentStructure_UU);
	}
}