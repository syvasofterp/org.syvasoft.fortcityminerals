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
package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for TF_Quarry
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_TF_Quarry 
{

    /** TableName=TF_Quarry */
    public static final String Table_Name = "TF_Quarry";

    /** AD_Table_ID=1000167 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name ActualQty */
    public static final String COLUMNNAME_ActualQty = "ActualQty";

	/** Set Actual Quantity.
	  * The actual quantity
	  */
	public void setActualQty (BigDecimal ActualQty);

	/** Get Actual Quantity.
	  * The actual quantity
	  */
	public BigDecimal getActualQty();

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Address */
    public static final String COLUMNNAME_Address = "Address";

	/** Set Address	  */
	public void setAddress (String Address);

	/** Get Address	  */
	public String getAddress();

    /** Column name BalanceQty */
    public static final String COLUMNNAME_BalanceQty = "BalanceQty";

	/** Set Balance Qty	  */
	public void setBalanceQty (BigDecimal BalanceQty);

	/** Get Balance Qty	  */
	public BigDecimal getBalanceQty();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_ElementValue_ID */
    public static final String COLUMNNAME_C_ElementValue_ID = "C_ElementValue_ID";

	/** Set Account Element.
	  * Account Element
	  */
	public void setC_ElementValue_ID (int C_ElementValue_ID);

	/** Get Account Element.
	  * Account Element
	  */
	public int getC_ElementValue_ID();

	public org.compiere.model.I_C_ElementValue getC_ElementValue() throws RuntimeException;

    /** Column name C_ElementValuePermitExp_ID */
    public static final String COLUMNNAME_C_ElementValuePermitExp_ID = "C_ElementValuePermitExp_ID";

	/** Set Permit Expense Account Head	  */
	public void setC_ElementValuePermitExp_ID (int C_ElementValuePermitExp_ID);

	/** Get Permit Expense Account Head	  */
	public int getC_ElementValuePermitExp_ID();

	public org.compiere.model.I_C_ElementValue getC_ElementValuePermitExp() throws RuntimeException;

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name CreateProfitCenter */
    public static final String COLUMNNAME_CreateProfitCenter = "CreateProfitCenter";

	/** Set Create Profit Center	  */
	public void setCreateProfitCenter (String CreateProfitCenter);

	/** Get Create Profit Center	  */
	public String getCreateProfitCenter();

    /** Column name DeliveredTPQty */
    public static final String COLUMNNAME_DeliveredTPQty = "DeliveredTPQty";

	/** Set Delivered TP Quantity	  */
	public void setDeliveredTPQty (BigDecimal DeliveredTPQty);

	/** Get Delivered TP Quantity	  */
	public BigDecimal getDeliveredTPQty();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsLeased */
    public static final String COLUMNNAME_IsLeased = "IsLeased";

	/** Set Leased	  */
	public void setIsLeased (boolean IsLeased);

	/** Get Leased	  */
	public boolean isLeased();

    /** Column name LicenseNo */
    public static final String COLUMNNAME_LicenseNo = "LicenseNo";

	/** Set License No	  */
	public void setLicenseNo (String LicenseNo);

	/** Get License No	  */
	public String getLicenseNo();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name QtyAvailable */
    public static final String COLUMNNAME_QtyAvailable = "QtyAvailable";

	/** Set Available Quantity.
	  * Available Quantity (On Hand - Reserved)
	  */
	public void setQtyAvailable (BigDecimal QtyAvailable);

	/** Get Available Quantity.
	  * Available Quantity (On Hand - Reserved)
	  */
	public BigDecimal getQtyAvailable();

    /** Column name QtyBalance */
    public static final String COLUMNNAME_QtyBalance = "QtyBalance";

	/** Set Balance Quantity	  */
	public void setQtyBalance (BigDecimal QtyBalance);

	/** Get Balance Quantity	  */
	public BigDecimal getQtyBalance();

    /** Column name QtyConsumed */
    public static final String COLUMNNAME_QtyConsumed = "QtyConsumed";

	/** Set Consumed Quantity	  */
	public void setQtyConsumed (BigDecimal QtyConsumed);

	/** Get Consumed Quantity	  */
	public BigDecimal getQtyConsumed();

    /** Column name TenderAmount */
    public static final String COLUMNNAME_TenderAmount = "TenderAmount";

	/** Set Tender Amount.
	  * Tender Amount
	  */
	public void setTenderAmount (BigDecimal TenderAmount);

	/** Get Tender Amount.
	  * Tender Amount
	  */
	public BigDecimal getTenderAmount();

    /** Column name TF_Destination_ID */
    public static final String COLUMNNAME_TF_Destination_ID = "TF_Destination_ID";

	/** Set Destination	  */
	public void setTF_Destination_ID (int TF_Destination_ID);

	/** Get Destination	  */
	public int getTF_Destination_ID();

	public I_TF_Destination getTF_Destination() throws RuntimeException;

    /** Column name TF_Quarry_ID */
    public static final String COLUMNNAME_TF_Quarry_ID = "TF_Quarry_ID";

	/** Set Quarry	  */
	public void setTF_Quarry_ID (int TF_Quarry_ID);

	/** Get Quarry	  */
	public int getTF_Quarry_ID();

    /** Column name TF_Quarry_UU */
    public static final String COLUMNNAME_TF_Quarry_UU = "TF_Quarry_UU";

	/** Set TF_Quarry_UU	  */
	public void setTF_Quarry_UU (String TF_Quarry_UU);

	/** Get TF_Quarry_UU	  */
	public String getTF_Quarry_UU();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name ValidFrom */
    public static final String COLUMNNAME_ValidFrom = "ValidFrom";

	/** Set Valid from.
	  * Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom);

	/** Get Valid from.
	  * Valid from including this date (first day)
	  */
	public Timestamp getValidFrom();

    /** Column name ValidTo */
    public static final String COLUMNNAME_ValidTo = "ValidTo";

	/** Set Valid to.
	  * Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo);

	/** Get Valid to.
	  * Valid to including this date (last day)
	  */
	public Timestamp getValidTo();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();

    /** Column name WeighmentEntryType */
    public static final String COLUMNNAME_WeighmentEntryType = "WeighmentEntryType";

	/** Set Type	  */
	public void setWeighmentEntryType (String WeighmentEntryType);

	/** Get Type	  */
	public String getWeighmentEntryType();
}
