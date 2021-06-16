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

/** Generated Interface for TF_DispensePlanLine
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_TF_DispensePlanLine 
{

    /** TableName=TF_DispensePlanLine */
    public static final String Table_Name = "TF_DispensePlanLine";

    /** AD_Table_ID=1000334 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

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

    /** Column name AllowCarryForward */
    public static final String COLUMNNAME_AllowCarryForward = "AllowCarryForward";

	/** Set Allow Carry Forward Previous Day Dispatch Pending	  */
	public void setAllowCarryForward (boolean AllowCarryForward);

	/** Get Allow Carry Forward Previous Day Dispatch Pending	  */
	public boolean isAllowCarryForward();

    /** Column name ArrangeTransport */
    public static final String COLUMNNAME_ArrangeTransport = "ArrangeTransport";

	/** Set Arrange Transport	  */
	public void setArrangeTransport (boolean ArrangeTransport);

	/** Get Arrange Transport	  */
	public boolean isArrangeTransport();

    /** Column name BalanceDPQty */
    public static final String COLUMNNAME_BalanceDPQty = "BalanceDPQty";

	/** Set Balance Dispatch Qty	  */
	public void setBalanceDPQty (BigDecimal BalanceDPQty);

	/** Get Balance Dispatch Qty	  */
	public BigDecimal getBalanceDPQty();

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

    /** Column name C_BPartner_Location_ID */
    public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";

	/** Set Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID);

	/** Get Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID();

	public org.compiere.model.I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException;

    /** Column name C_Charge_ID */
    public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";

	/** Set Charge.
	  * Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID);

	/** Get Charge.
	  * Additional document charges
	  */
	public int getC_Charge_ID();

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException;

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException;

    /** Column name C_OrderLine_ID */
    public static final String COLUMNNAME_C_OrderLine_ID = "C_OrderLine_ID";

	/** Set Sales Order Line.
	  * Sales Order Line
	  */
	public void setC_OrderLine_ID (int C_OrderLine_ID);

	/** Get Sales Order Line.
	  * Sales Order Line
	  */
	public int getC_OrderLine_ID();

	public org.compiere.model.I_C_OrderLine getC_OrderLine() throws RuntimeException;

    /** Column name C_OrderLine_UU */
    public static final String COLUMNNAME_C_OrderLine_UU = "C_OrderLine_UU";

	/** Set C_OrderLine_UU	  */
	public void setC_OrderLine_UU (String C_OrderLine_UU);

	/** Get C_OrderLine_UU	  */
	public String getC_OrderLine_UU();

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Subcontract / Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Subcontract / Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException;

    /** Column name C_Tax_ID */
    public static final String COLUMNNAME_C_Tax_ID = "C_Tax_ID";

	/** Set Tax.
	  * Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID);

	/** Get Tax.
	  * Tax identifier
	  */
	public int getC_Tax_ID();

	public org.compiere.model.I_C_Tax getC_Tax() throws RuntimeException;

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

    /** Column name CloseDispatchPlan */
    public static final String COLUMNNAME_CloseDispatchPlan = "CloseDispatchPlan";

	/** Set Close Dispatch Plan	  */
	public void setCloseDispatchPlan (String CloseDispatchPlan);

	/** Get Close Dispatch Plan	  */
	public String getCloseDispatchPlan();

    /** Column name ContactPerson */
    public static final String COLUMNNAME_ContactPerson = "ContactPerson";

	/** Set Contact Person	  */
	public void setContactPerson (String ContactPerson);

	/** Get Contact Person	  */
	public String getContactPerson();

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

    /** Column name CustomerGSTIN */
    public static final String COLUMNNAME_CustomerGSTIN = "CustomerGSTIN";

	/** Set Customer GST No	  */
	public void setCustomerGSTIN (String CustomerGSTIN);

	/** Get Customer GST No	  */
	public String getCustomerGSTIN();

    /** Column name CustomerTransporter */
    public static final String COLUMNNAME_CustomerTransporter = "CustomerTransporter";

	/** Set Customer's Transporter	  */
	public void setCustomerTransporter (boolean CustomerTransporter);

	/** Get Customer's Transporter	  */
	public boolean isCustomerTransporter();

    /** Column name DateOrdered */
    public static final String COLUMNNAME_DateOrdered = "DateOrdered";

	/** Set Date Ordered.
	  * Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered);

	/** Get Date Ordered.
	  * Date of Order
	  */
	public Timestamp getDateOrdered();

    /** Column name DeliveredDPQty */
    public static final String COLUMNNAME_DeliveredDPQty = "DeliveredDPQty";

	/** Set Delivered Dispatch Qty	  */
	public void setDeliveredDPQty (BigDecimal DeliveredDPQty);

	/** Get Delivered Dispatch Qty	  */
	public BigDecimal getDeliveredDPQty();

    /** Column name DeliveryContact */
    public static final String COLUMNNAME_DeliveryContact = "DeliveryContact";

	/** Set Delivery Contact	  */
	public void setDeliveryContact (String DeliveryContact);

	/** Get Delivery Contact	  */
	public String getDeliveryContact();

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

    /** Column name Discount */
    public static final String COLUMNNAME_Discount = "Discount";

	/** Set Discount %.
	  * Discount in percent
	  */
	public void setDiscount (BigDecimal Discount);

	/** Get Discount %.
	  * Discount in percent
	  */
	public BigDecimal getDiscount();

    /** Column name DispenseQty */
    public static final String COLUMNNAME_DispenseQty = "DispenseQty";

	/** Set Dispatch Qty	  */
	public void setDispenseQty (BigDecimal DispenseQty);

	/** Get Dispatch Qty	  */
	public BigDecimal getDispenseQty();

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";

	/** Set Document Status.
	  * The current status of the document
	  */
	public void setDocStatus (String DocStatus);

	/** Get Document Status.
	  * The current status of the document
	  */
	public String getDocStatus();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name FreightAmt */
    public static final String COLUMNNAME_FreightAmt = "FreightAmt";

	/** Set Freight Amount.
	  * Freight Amount 
	  */
	public void setFreightAmt (BigDecimal FreightAmt);

	/** Get Freight Amount.
	  * Freight Amount 
	  */
	public BigDecimal getFreightAmt();

    /** Column name FreightUOM_ID */
    public static final String COLUMNNAME_FreightUOM_ID = "FreightUOM_ID";

	/** Set Freight UOM	  */
	public void setFreightUOM_ID (int FreightUOM_ID);

	/** Get Freight UOM	  */
	public int getFreightUOM_ID();

	public org.compiere.model.I_C_UOM getFreightUOM() throws RuntimeException;

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

    /** Column name IsPriceConfidential */
    public static final String COLUMNNAME_IsPriceConfidential = "IsPriceConfidential";

	/** Set Price Confidential	  */
	public void setIsPriceConfidential (boolean IsPriceConfidential);

	/** Get Price Confidential	  */
	public boolean isPriceConfidential();

    /** Column name IsRentInclusive */
    public static final String COLUMNNAME_IsRentInclusive = "IsRentInclusive";

	/** Set Freight Inclusive.
	  * Whether Unit Price includes rent?
	  */
	public void setIsRentInclusive (boolean IsRentInclusive);

	/** Get Freight Inclusive.
	  * Whether Unit Price includes rent?
	  */
	public boolean isRentInclusive();

    /** Column name IsRoyaltyPassInclusive */
    public static final String COLUMNNAME_IsRoyaltyPassInclusive = "IsRoyaltyPassInclusive";

	/** Set Royalty Pass Inclusive	  */
	public void setIsRoyaltyPassInclusive (boolean IsRoyaltyPassInclusive);

	/** Get Royalty Pass Inclusive	  */
	public boolean isRoyaltyPassInclusive();

    /** Column name IsTaxIncluded */
    public static final String COLUMNNAME_IsTaxIncluded = "IsTaxIncluded";

	/** Set Price includes Tax.
	  * Tax is included in the price 
	  */
	public void setIsTaxIncluded (boolean IsTaxIncluded);

	/** Get Price includes Tax.
	  * Tax is included in the price 
	  */
	public boolean isTaxIncluded();

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();

    /** Column name LineNetAmt */
    public static final String COLUMNNAME_LineNetAmt = "LineNetAmt";

	/** Set Line Amount.
	  * Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public void setLineNetAmt (BigDecimal LineNetAmt);

	/** Get Line Amount.
	  * Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public BigDecimal getLineNetAmt();

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

    /** Column name M_Shipper_ID */
    public static final String COLUMNNAME_M_Shipper_ID = "M_Shipper_ID";

	/** Set Shipper.
	  * Method or manner of product delivery
	  */
	public void setM_Shipper_ID (int M_Shipper_ID);

	/** Get Shipper.
	  * Method or manner of product delivery
	  */
	public int getM_Shipper_ID();

	public org.compiere.model.I_M_Shipper getM_Shipper() throws RuntimeException;

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException;

    /** Column name OriginDate */
    public static final String COLUMNNAME_OriginDate = "OriginDate";

	/** Set Origin Date	  */
	public void setOriginDate (Timestamp OriginDate);

	/** Get Origin Date	  */
	public Timestamp getOriginDate();

    /** Column name OverUnitDelivery */
    public static final String COLUMNNAME_OverUnitDelivery = "OverUnitDelivery";

	/** Set Allow Over Delivery Qty	  */
	public void setOverUnitDelivery (boolean OverUnitDelivery);

	/** Get Allow Over Delivery Qty	  */
	public boolean isOverUnitDelivery();

    /** Column name Parent_ID */
    public static final String COLUMNNAME_Parent_ID = "Parent_ID";

	/** Set Parent.
	  * Parent of Entity
	  */
	public void setParent_ID (int Parent_ID);

	/** Get Parent.
	  * Parent of Entity
	  */
	public int getParent_ID();

	public I_TF_DispensePlanLine getParent() throws RuntimeException;

    /** Column name PartyName */
    public static final String COLUMNNAME_PartyName = "PartyName";

	/** Set Party Name	  */
	public void setPartyName (String PartyName);

	/** Get Party Name	  */
	public String getPartyName();

    /** Column name PaymentRule */
    public static final String COLUMNNAME_PaymentRule = "PaymentRule";

	/** Set Payment Rule.
	  * How you pay the invoice
	  */
	public void setPaymentRule (String PaymentRule);

	/** Get Payment Rule.
	  * How you pay the invoice
	  */
	public String getPaymentRule();

    /** Column name PriceActual */
    public static final String COLUMNNAME_PriceActual = "PriceActual";

	/** Set Unit Price.
	  * Actual Price 
	  */
	public void setPriceActual (BigDecimal PriceActual);

	/** Get Unit Price.
	  * Actual Price 
	  */
	public BigDecimal getPriceActual();

    /** Column name PriceEntered */
    public static final String COLUMNNAME_PriceEntered = "PriceEntered";

	/** Set Price.
	  * Price Entered - the price based on the selected/base UoM
	  */
	public void setPriceEntered (BigDecimal PriceEntered);

	/** Get Price.
	  * Price Entered - the price based on the selected/base UoM
	  */
	public BigDecimal getPriceEntered();

    /** Column name PriceLimit */
    public static final String COLUMNNAME_PriceLimit = "PriceLimit";

	/** Set Limit Price.
	  * Lowest price for a product
	  */
	public void setPriceLimit (BigDecimal PriceLimit);

	/** Get Limit Price.
	  * Lowest price for a product
	  */
	public BigDecimal getPriceLimit();

    /** Column name PriceList */
    public static final String COLUMNNAME_PriceList = "PriceList";

	/** Set List Price.
	  * List Price
	  */
	public void setPriceList (BigDecimal PriceList);

	/** Get List Price.
	  * List Price
	  */
	public BigDecimal getPriceList();

    /** Column name Priority */
    public static final String COLUMNNAME_Priority = "Priority";

	/** Set Priority.
	  * Indicates if this request is of a high, medium or low priority.
	  */
	public void setPriority (String Priority);

	/** Get Priority.
	  * Indicates if this request is of a high, medium or low priority.
	  */
	public String getPriority();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name QtyDelivered */
    public static final String COLUMNNAME_QtyDelivered = "QtyDelivered";

	/** Set Delivered Quantity.
	  * Delivered Quantity
	  */
	public void setQtyDelivered (BigDecimal QtyDelivered);

	/** Get Delivered Quantity.
	  * Delivered Quantity
	  */
	public BigDecimal getQtyDelivered();

    /** Column name QtyEntered */
    public static final String COLUMNNAME_QtyEntered = "QtyEntered";

	/** Set Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public void setQtyEntered (BigDecimal QtyEntered);

	/** Get Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public BigDecimal getQtyEntered();

    /** Column name QtyInvoiced */
    public static final String COLUMNNAME_QtyInvoiced = "QtyInvoiced";

	/** Set Quantity Invoiced.
	  * Invoiced Quantity
	  */
	public void setQtyInvoiced (BigDecimal QtyInvoiced);

	/** Get Quantity Invoiced.
	  * Invoiced Quantity
	  */
	public BigDecimal getQtyInvoiced();

    /** Column name QtyOrdered */
    public static final String COLUMNNAME_QtyOrdered = "QtyOrdered";

	/** Set Ordered Quantity.
	  * Ordered Quantity
	  */
	public void setQtyOrdered (BigDecimal QtyOrdered);

	/** Get Ordered Quantity.
	  * Ordered Quantity
	  */
	public BigDecimal getQtyOrdered();

    /** Column name QtyReserved */
    public static final String COLUMNNAME_QtyReserved = "QtyReserved";

	/** Set Reserved Quantity.
	  * Reserved Quantity
	  */
	public void setQtyReserved (BigDecimal QtyReserved);

	/** Get Reserved Quantity.
	  * Reserved Quantity
	  */
	public BigDecimal getQtyReserved();

    /** Column name ScheduleDate */
    public static final String COLUMNNAME_ScheduleDate = "ScheduleDate";

	/** Set Schedule Date	  */
	public void setScheduleDate (Timestamp ScheduleDate);

	/** Get Schedule Date	  */
	public Timestamp getScheduleDate();

    /** Column name ShipmentAddress */
    public static final String COLUMNNAME_ShipmentAddress = "ShipmentAddress";

	/** Set Shipment Address	  */
	public void setShipmentAddress (String ShipmentAddress);

	/** Get Shipment Address	  */
	public String getShipmentAddress();

    /** Column name ShipmentDestination */
    public static final String COLUMNNAME_ShipmentDestination = "ShipmentDestination";

	/** Set Shipment Destination	  */
	public void setShipmentDestination (String ShipmentDestination);

	

    /** Column name ShipmentDestination_ID */
    public static final String COLUMNNAME_ShipmentDestination_ID = "ShipmentDestination_ID";

	/** Set Shipment Destination	  */
	public void setShipmentDestination_ID (int ShipmentDestination_ID);

	/** Get Shipment Destination	  */
	public int getShipmentDestination_ID();

	public I_TF_Destination getShipmentDestination() throws RuntimeException;

    /** Column name ShipmentRate */
    public static final String COLUMNNAME_ShipmentRate = "ShipmentRate";

	/** Set Shipment Rate	  */
	public void setShipmentRate (BigDecimal ShipmentRate);

	/** Get Shipment Rate	  */
	public BigDecimal getShipmentRate();

    /** Column name ShipmentTo */
    public static final String COLUMNNAME_ShipmentTo = "ShipmentTo";

	/** Set Shipment To	  */
	public void setShipmentTo (String ShipmentTo);

	/** Get Shipment To	  */
	public String getShipmentTo();

    /** Column name TF_Destination_ID */
    public static final String COLUMNNAME_TF_Destination_ID = "TF_Destination_ID";

	/** Set Destination	  */
	public void setTF_Destination_ID (int TF_Destination_ID);

	/** Get Destination	  */
	public int getTF_Destination_ID();

	public I_TF_Destination getTF_Destination() throws RuntimeException;

    /** Column name TF_DispensePlan_ID */
    public static final String COLUMNNAME_TF_DispensePlan_ID = "TF_DispensePlan_ID";

	/** Set Dispatch Plan	  */
	public void setTF_DispensePlan_ID (int TF_DispensePlan_ID);

	/** Get Dispatch Plan	  */
	public int getTF_DispensePlan_ID();

	public I_TF_DispensePlan getTF_DispensePlan() throws RuntimeException;

    /** Column name TF_DispensePlanLine_ID */
    public static final String COLUMNNAME_TF_DispensePlanLine_ID = "TF_DispensePlanLine_ID";

	/** Set Dispatch Plan Line	  */
	public void setTF_DispensePlanLine_ID (int TF_DispensePlanLine_ID);

	/** Get Dispatch Plan Line	  */
	public int getTF_DispensePlanLine_ID();

    /** Column name TF_DispensePlanLine_UU */
    public static final String COLUMNNAME_TF_DispensePlanLine_UU = "TF_DispensePlanLine_UU";

	/** Set TF_DispensePlanLine_UU	  */
	public void setTF_DispensePlanLine_UU (String TF_DispensePlanLine_UU);

	/** Get TF_DispensePlanLine_UU	  */
	public String getTF_DispensePlanLine_UU();

    /** Column name TF_VehicleType_ID */
    public static final String COLUMNNAME_TF_VehicleType_ID = "TF_VehicleType_ID";

	/** Set Vehicle Type	  */
	public void setTF_VehicleType_ID (int TF_VehicleType_ID);

	/** Get Vehicle Type	  */
	public int getTF_VehicleType_ID();

	public I_TF_VehicleType getTF_VehicleType() throws RuntimeException;

    /** Column name Type */
    public static final String COLUMNNAME_Type = "Type";

	/** Set Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type);

	/** Get Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType();

    /** Column name UnitPrice */
    public static final String COLUMNNAME_UnitPrice = "UnitPrice";

	/** Set Unit Price	  */
	public void setUnitPrice (BigDecimal UnitPrice);

	/** Get Unit Price	  */
	public BigDecimal getUnitPrice();

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
}
