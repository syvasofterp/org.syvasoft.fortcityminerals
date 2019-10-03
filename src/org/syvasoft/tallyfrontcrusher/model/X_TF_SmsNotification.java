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

/** Generated Model for TF_SmsNotification
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_TF_SmsNotification extends PO implements I_TF_SmsNotification, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191003L;

    /** Standard Constructor */
    public X_TF_SmsNotification (Properties ctx, int TF_SmsNotification_ID, String trxName)
    {
      super (ctx, TF_SmsNotification_ID, trxName);
      /** if (TF_SmsNotification_ID == 0)
        {
			setDeliveryTime (null);
			setMessage (null);
			setName (null);
			setSql (null);
			setTF_SmsNotification_ID (0);
			setUnicode (false);
        } */
    }

    /** Load Constructor */
    public X_TF_SmsNotification (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_SmsNotification[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Delivery Time.
		@param DeliveryTime Delivery Time	  */
	public void setDeliveryTime (String DeliveryTime)
	{
		set_Value (COLUMNNAME_DeliveryTime, DeliveryTime);
	}

	/** Get Delivery Time.
		@return Delivery Time	  */
	public String getDeliveryTime () 
	{
		return (String)get_Value(COLUMNNAME_DeliveryTime);
	}

	/** Set Footer Message.
		@param FooterMsg Footer Message	  */
	public void setFooterMsg (String FooterMsg)
	{
		set_Value (COLUMNNAME_FooterMsg, FooterMsg);
	}

	/** Get Footer Message.
		@return Footer Message	  */
	public String getFooterMsg () 
	{
		return (String)get_Value(COLUMNNAME_FooterMsg);
	}

	/** Set Footer Sql.
		@param FooterSql Footer Sql	  */
	public void setFooterSql (String FooterSql)
	{
		set_Value (COLUMNNAME_FooterSql, FooterSql);
	}

	/** Get Footer Sql.
		@return Footer Sql	  */
	public String getFooterSql () 
	{
		return (String)get_Value(COLUMNNAME_FooterSql);
	}

	/** Set Header Message.
		@param HeaderMsg Header Message	  */
	public void setHeaderMsg (String HeaderMsg)
	{
		set_Value (COLUMNNAME_HeaderMsg, HeaderMsg);
	}

	/** Get Header Message.
		@return Header Message	  */
	public String getHeaderMsg () 
	{
		return (String)get_Value(COLUMNNAME_HeaderMsg);
	}

	/** Set Header Sql.
		@param HeaderSql Header Sql	  */
	public void setHeaderSql (String HeaderSql)
	{
		set_Value (COLUMNNAME_HeaderSql, HeaderSql);
	}

	/** Get Header Sql.
		@return Header Sql	  */
	public String getHeaderSql () 
	{
		return (String)get_Value(COLUMNNAME_HeaderSql);
	}

	/** Set Message.
		@param Message 
		EMail Message
	  */
	public void setMessage (String Message)
	{
		set_Value (COLUMNNAME_Message, Message);
	}

	/** Get Message.
		@return EMail Message
	  */
	public String getMessage () 
	{
		return (String)get_Value(COLUMNNAME_Message);
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

	/** Set Recipient SQL.
		@param RecipientSQL Recipient SQL	  */
	public void setRecipientSQL (String RecipientSQL)
	{
		set_Value (COLUMNNAME_RecipientSQL, RecipientSQL);
	}

	/** Get Recipient SQL.
		@return Recipient SQL	  */
	public String getRecipientSQL () 
	{
		return (String)get_Value(COLUMNNAME_RecipientSQL);
	}

	/** Set Sql.
		@param Sql Sql	  */
	public void setSql (String Sql)
	{
		set_Value (COLUMNNAME_Sql, Sql);
	}

	/** Get Sql.
		@return Sql	  */
	public String getSql () 
	{
		return (String)get_Value(COLUMNNAME_Sql);
	}

	/** Set TF_SmsNotification.
		@param TF_SmsNotification_ID TF_SmsNotification	  */
	public void setTF_SmsNotification_ID (int TF_SmsNotification_ID)
	{
		if (TF_SmsNotification_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_SmsNotification_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_SmsNotification_ID, Integer.valueOf(TF_SmsNotification_ID));
	}

	/** Get TF_SmsNotification.
		@return TF_SmsNotification	  */
	public int getTF_SmsNotification_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_SmsNotification_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_SmsNotification_UU.
		@param TF_SmsNotification_UU TF_SmsNotification_UU	  */
	public void setTF_SmsNotification_UU (String TF_SmsNotification_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_SmsNotification_UU, TF_SmsNotification_UU);
	}

	/** Get TF_SmsNotification_UU.
		@return TF_SmsNotification_UU	  */
	public String getTF_SmsNotification_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_SmsNotification_UU);
	}

	/** Set Unicode.
		@param Unicode Unicode	  */
	public void setUnicode (boolean Unicode)
	{
		set_Value (COLUMNNAME_Unicode, Boolean.valueOf(Unicode));
	}

	/** Get Unicode.
		@return Unicode	  */
	public boolean isUnicode () 
	{
		Object oo = get_Value(COLUMNNAME_Unicode);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}