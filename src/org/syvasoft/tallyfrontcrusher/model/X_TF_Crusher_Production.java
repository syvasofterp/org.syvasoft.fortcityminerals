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

/** Generated Model for TF_Crusher_Production
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_TF_Crusher_Production extends PO implements I_TF_Crusher_Production, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170201L;

    /** Standard Constructor */
    public X_TF_Crusher_Production (Properties ctx, int TF_Crusher_Production_ID, String trxName)
    {
      super (ctx, TF_Crusher_Production_ID, trxName);
      /** if (TF_Crusher_Production_ID == 0)
        {
			setC_UOM_ID (0);
			setDocAction (null);
// CO
			setDocStatus (null);
// DR
			setDocumentNo (null);
			setM_Warehouse_ID (0);
			setMovementDate (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setProcessed (false);
			setRM_Product_ID (0);
			setTF_BlueMetal_Type (null);
			setTF_Crusher_Production_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TF_Crusher_Production (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_TF_Crusher_Production[")
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

	/** Set Create lines from.
		@param CreateFrom 
		Process which will generate a new document lines based on an existing document
	  */
	public void setCreateFrom (String CreateFrom)
	{
		set_Value (COLUMNNAME_CreateFrom, CreateFrom);
	}

	/** Get Create lines from.
		@return Process which will generate a new document lines based on an existing document
	  */
	public String getCreateFrom () 
	{
		return (String)get_Value(COLUMNNAME_CreateFrom);
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

	/** DocAction AD_Reference_ID=135 */
	public static final int DOCACTION_AD_Reference_ID=135;
	/** Complete = CO */
	public static final String DOCACTION_Complete = "CO";
	/** Approve = AP */
	public static final String DOCACTION_Approve = "AP";
	/** Reject = RJ */
	public static final String DOCACTION_Reject = "RJ";
	/** Post = PO */
	public static final String DOCACTION_Post = "PO";
	/** Void = VO */
	public static final String DOCACTION_Void = "VO";
	/** Close = CL */
	public static final String DOCACTION_Close = "CL";
	/** Reverse - Correct = RC */
	public static final String DOCACTION_Reverse_Correct = "RC";
	/** Reverse - Accrual = RA */
	public static final String DOCACTION_Reverse_Accrual = "RA";
	/** Invalidate = IN */
	public static final String DOCACTION_Invalidate = "IN";
	/** Re-activate = RE */
	public static final String DOCACTION_Re_Activate = "RE";
	/** <None> = -- */
	public static final String DOCACTION_None = "--";
	/** Prepare = PR */
	public static final String DOCACTION_Prepare = "PR";
	/** Unlock = XL */
	public static final String DOCACTION_Unlock = "XL";
	/** Wait Complete = WC */
	public static final String DOCACTION_WaitComplete = "WC";
	/** Suspend = SU */
	public static final String DOCACTION_Suspend = "SU";
	/** Cancel = CA */
	public static final String DOCACTION_Cancel = "CA";
	/** Activate = AC */
	public static final String DOCACTION_Activate = "AC";
	/** Set Document Action.
		@param DocAction 
		The targeted status of the document
	  */
	public void setDocAction (String DocAction)
	{

		set_Value (COLUMNNAME_DocAction, DocAction);
	}

	/** Get Document Action.
		@return The targeted status of the document
	  */
	public String getDocAction () 
	{
		return (String)get_Value(COLUMNNAME_DocAction);
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

	/** IsCreated AD_Reference_ID=319 */
	public static final int ISCREATED_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISCREATED_Yes = "Y";
	/** No = N */
	public static final String ISCREATED_No = "N";
	/** Set Records created.
		@param IsCreated Records created	  */
	public void setIsCreated (String IsCreated)
	{

		set_Value (COLUMNNAME_IsCreated, IsCreated);
	}

	/** Get Records created.
		@return Records created	  */
	public String getIsCreated () 
	{
		return (String)get_Value(COLUMNNAME_IsCreated);
	}

	public org.compiere.model.I_M_Locator getM_Locator() throws RuntimeException
    {
		return (org.compiere.model.I_M_Locator)MTable.get(getCtx(), org.compiere.model.I_M_Locator.Table_Name)
			.getPO(getM_Locator_ID(), get_TrxName());	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Locator_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException
    {
		return (org.compiere.model.I_M_Warehouse)MTable.get(getCtx(), org.compiere.model.I_M_Warehouse.Table_Name)
			.getPO(getM_Warehouse_ID(), get_TrxName());	}

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Movement Date.
		@param MovementDate 
		Date a product was moved in or out of inventory
	  */
	public void setMovementDate (Timestamp MovementDate)
	{
		set_Value (COLUMNNAME_MovementDate, MovementDate);
	}

	/** Get Movement Date.
		@return Date a product was moved in or out of inventory
	  */
	public Timestamp getMovementDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_MovementDate);
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

	/** Set Quantity Used.
		@param QtyUsed Quantity Used	  */
	public void setQtyUsed (BigDecimal QtyUsed)
	{
		set_Value (COLUMNNAME_QtyUsed, QtyUsed);
	}

	/** Get Quantity Used.
		@return Quantity Used	  */
	public BigDecimal getQtyUsed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyUsed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_M_Product getRM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getRM_Product_ID(), get_TrxName());	}

	/** Set Raw Material.
		@param RM_Product_ID Raw Material	  */
	public void setRM_Product_ID (int RM_Product_ID)
	{
		if (RM_Product_ID < 1) 
			set_Value (COLUMNNAME_RM_Product_ID, null);
		else 
			set_Value (COLUMNNAME_RM_Product_ID, Integer.valueOf(RM_Product_ID));
	}

	/** Get Raw Material.
		@return Raw Material	  */
	public int getRM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RM_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Regular = R */
	public static final String TF_BLUEMETAL_TYPE_Regular = "R";
	/** GSB = G */
	public static final String TF_BLUEMETAL_TYPE_GSB = "G";
	/** WMM = W */
	public static final String TF_BLUEMETAL_TYPE_WMM = "W";
	/** Set Blue Metal Type.
		@param TF_BlueMetal_Type Blue Metal Type	  */
	public void setTF_BlueMetal_Type (String TF_BlueMetal_Type)
	{

		set_Value (COLUMNNAME_TF_BlueMetal_Type, TF_BlueMetal_Type);
	}

	/** Get Blue Metal Type.
		@return Blue Metal Type	  */
	public String getTF_BlueMetal_Type () 
	{
		return (String)get_Value(COLUMNNAME_TF_BlueMetal_Type);
	}

	/** Set Crusher Production.
		@param TF_Crusher_Production_ID Crusher Production	  */
	public void setTF_Crusher_Production_ID (int TF_Crusher_Production_ID)
	{
		if (TF_Crusher_Production_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TF_Crusher_Production_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TF_Crusher_Production_ID, Integer.valueOf(TF_Crusher_Production_ID));
	}

	/** Get Crusher Production.
		@return Crusher Production	  */
	public int getTF_Crusher_Production_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_Crusher_Production_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TF_Crusher_Production_UU.
		@param TF_Crusher_Production_UU TF_Crusher_Production_UU	  */
	public void setTF_Crusher_Production_UU (String TF_Crusher_Production_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TF_Crusher_Production_UU, TF_Crusher_Production_UU);
	}

	/** Get TF_Crusher_Production_UU.
		@return TF_Crusher_Production_UU	  */
	public String getTF_Crusher_Production_UU () 
	{
		return (String)get_Value(COLUMNNAME_TF_Crusher_Production_UU);
	}
}