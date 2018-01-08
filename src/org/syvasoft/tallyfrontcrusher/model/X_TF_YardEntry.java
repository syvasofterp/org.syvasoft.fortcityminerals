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

/** Generated Model for TF_YardEntry
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_TF_YardEntry extends PO implements I_TF_YardEntry, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180108L;

    /** Standard Constructor */
    public X_TF_YardEntry (Properties ctx, int TF_YardEntry_ID, String trxName)
    {
      super (ctx, TF_YardEntry_ID, trxName);
      /** if (TF_YardEntry_ID == 0)
        {
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
			setDocumentNo (null);
			setProcessed (false);
			setTF_VehicleType_ID (0);
			setTF_YardEntry_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_YardEntry (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_YardEntry[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Account Date.
		@param DateAcct 
		Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct)
	{
		set_ValueNoCheck (COLUMNNAME_DateAcct, DateAcct);
	}

	/** Get Account Date.
		@return Accounting Date
	  */
	public Timestamp getDateAcct () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAcct);
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

	/** DocStatus AD_Reference_ID=131 */
	public static final int DOCSTATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String DOCSTATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String DOCSTATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String DOCSTATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String DOCSTATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String DOCSTATUS_WaitingConfirmation = "WC";
	/** Suspended = SU */
	public static final String DOCSTATUS_Suspended = "SU";
	/** Activated = AC */
	public static final String DOCSTATUS_Activated = "AC";
	/** Canceled = CA */
	public static final String DOCSTATUS_Canceled = "CA";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{

		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Extra Bucket Amount.
		@param ExtraBucketAmount Extra Bucket Amount	  */
	public void setExtraBucketAmount (BigDecimal ExtraBucketAmount)
	{
		set_Value (COLUMNNAME_ExtraBucketAmount, ExtraBucketAmount);
	}

	/** Get Extra Bucket Amount.
		@return Extra Bucket Amount	  */
	public BigDecimal getExtraBucketAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ExtraBucketAmount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Extra Bucket Price.
		@param ExtraBucketPrice Extra Bucket Price	  */
	public void setExtraBucketPrice (BigDecimal ExtraBucketPrice)
	{
		set_Value (COLUMNNAME_ExtraBucketPrice, ExtraBucketPrice);
	}

	/** Get Extra Bucket Price.
		@return Extra Bucket Price	  */
	public BigDecimal getExtraBucketPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ExtraBucketPrice);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Extra Bucket Qty.
		@param ExtraBucketQty Extra Bucket Qty	  */
	public void setExtraBucketQty (BigDecimal ExtraBucketQty)
	{
		set_Value (COLUMNNAME_ExtraBucketQty, ExtraBucketQty);
	}

	/** Get Extra Bucket Qty.
		@return Extra Bucket Qty	  */
	public BigDecimal getExtraBucketQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ExtraBucketQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Permit Amount.
		@param PermitAmount Permit Amount	  */
	public void setPermitAmount (BigDecimal PermitAmount)
	{
		set_Value (COLUMNNAME_PermitAmount, PermitAmount);
	}

	/** Get Permit Amount.
		@return Permit Amount	  */
	public BigDecimal getPermitAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PermitAmount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Permit Cancelled Qty.
		@param PermitCancelledQty Permit Cancelled Qty	  */
	public void setPermitCancelledQty (BigDecimal PermitCancelledQty)
	{
		set_Value (COLUMNNAME_PermitCancelledQty, PermitCancelledQty);
	}

	/** Get Permit Cancelled Qty.
		@return Permit Cancelled Qty	  */
	public BigDecimal getPermitCancelledQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PermitCancelledQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Permit Issued Qty.
		@param PermitIssuedQty Permit Issued Qty	  */
	public void setPermitIssuedQty (BigDecimal PermitIssuedQty)
	{
		set_Value (COLUMNNAME_PermitIssuedQty, PermitIssuedQty);
	}

	/** Get Permit Issued Qty.
		@return Permit Issued Qty	  */
	public BigDecimal getPermitIssuedQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PermitIssuedQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Permit Price.
		@param PermitPrice Permit Price	  */
	public void setPermitPrice (BigDecimal PermitPrice)
	{
		set_Value (COLUMNNAME_PermitPrice, PermitPrice);
	}

	/** Get Permit Price.
		@return Permit Price	  */
	public BigDecimal getPermitPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PermitPrice);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Permit Sales Qty.
		@param PermitSalesQty Permit Sales Qty	  */
	public void setPermitSalesQty (BigDecimal PermitSalesQty)
	{
		set_Value (COLUMNNAME_PermitSalesQty, PermitSalesQty);
	}

	/** Get Permit Sales Qty.
		@return Permit Sales Qty	  */
	public BigDecimal getPermitSalesQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PermitSalesQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Yard Entry.
		@param TF_YardEntry_ID Yard Entry	  */
	public void setTF_YardEntry_ID (int TF_YardEntry_ID)
	{
		if (TF_YardEntry_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_YardEntry_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_YardEntry_ID, Integer.valueOf(TF_YardEntry_ID));
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

	/** Set TF_YardEntry_UU.
		@param TF_YardEntry_UU TF_YardEntry_UU	  */
	public void setTF_YardEntry_UU (String TF_YardEntry_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_YardEntry_UU, TF_YardEntry_UU);
	}

	/** Get TF_YardEntry_UU.
		@return TF_YardEntry_UU	  */
	public String getTF_YardEntry_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_YardEntry_UU);
	}

	/** Set Total Load.
		@param TotalLoad Total Load	  */
	public void setTotalLoad (BigDecimal TotalLoad)
	{
		set_Value (COLUMNNAME_TotalLoad, TotalLoad);
	}

	/** Get Total Load.
		@return Total Load	  */
	public BigDecimal getTotalLoad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalLoad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

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

	/** Set W/P Amount.
		@param WPAmount W/P Amount	  */
	public void setWPAmount (BigDecimal WPAmount)
	{
		set_Value (COLUMNNAME_WPAmount, WPAmount);
	}

	/** Get W/P Amount.
		@return W/P Amount	  */
	public BigDecimal getWPAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_WPAmount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set W/P Price.
		@param WpPrice W/P Price	  */
	public void setWpPrice (BigDecimal WpPrice)
	{
		set_Value (COLUMNNAME_WpPrice, WpPrice);
	}

	/** Get W/P Price.
		@return W/P Price	  */
	public BigDecimal getWpPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_WpPrice);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set W/P Qty.
		@param WPQty W/P Qty	  */
	public void setWPQty (BigDecimal WPQty)
	{
		set_Value (COLUMNNAME_WPQty, WPQty);
	}

	/** Get W/P Qty.
		@return W/P Qty	  */
	public BigDecimal getWPQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_WPQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}