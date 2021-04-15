package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.DBException;
import org.compiere.model.Query;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;

public class MDispensePlan extends X_TF_DispensePlan {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8086375269293054622L;

	public String ShipmentTo;
	
	public String ShipmentDestination;
	
	public BigDecimal DispatchQty;
	
	
	public MDispensePlan(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDispensePlan(Properties ctx, int TF_Destination_ID, String trxName) {
		super(ctx, TF_Destination_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if(newRecord) {
			
		}
		return super.afterSave(newRecord, success);
	}

	public void processIt(String docAction) {
		if(MDispensePlan.DOCSTATUS_Completed.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
		}			
	}
	public void reverseIt() {
		setProcessed(false);
		setDocStatus(DOCSTATUS_Drafted);
	}
	
	public void createDP(boolean recreate)
	{
		
	}
	
	public void createDPLinesFromOrder(ResultSet rs) throws SQLException {
		
		String sql = " TF_DispensePlan_ID = "+ getTF_DispensePlan_ID() + " AND C_OrderLine_ID = " + rs.getInt(MDispensePlanLine.COLUMNNAME_C_OrderLine_ID);
		
		MDispensePlanLine dispensePlan = new Query(getCtx(), MDispensePlanLine.Table_Name, sql, get_TrxName()).first();
		
		if(dispensePlan == null) {
			MDispensePlanLine dispenseLine = new MDispensePlanLine(getCtx(), 0, get_TrxName());
			BigDecimal balanceQty = (rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_QtyOrdered)).subtract(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_QtyDelivered));
			
			dispenseLine.setPriority(MDispensePlanLine.PRIORITY_Normal);
			dispenseLine.setType(MDispensePlanLine.TYPE_Order);
			dispenseLine.setC_OrderLine_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_OrderLine_ID));
			dispenseLine.setPaymentRule(rs.getString(MDispensePlanLine.COLUMNNAME_PaymentRule));
			dispenseLine.setC_BPartner_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_BPartner_ID));
			dispenseLine.setDateOrdered(rs.getTimestamp(MDispensePlanLine.COLUMNNAME_DateOrdered));
			dispenseLine.setTF_Destination_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_TF_Destination_ID));
			dispenseLine.setLine(10);
			dispenseLine.setM_Product_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_M_Product_ID));
			dispenseLine.setM_Warehouse_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_M_Warehouse_ID));
			dispenseLine.setDescription(rs.getString(MDispensePlanLine.COLUMNNAME_Description));
			
			dispenseLine.setDispenseQty(DispatchQty);
			dispenseLine.setC_UOM_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_UOM_ID));
			dispenseLine.setBalanceDPQty(balanceQty);
			dispenseLine.setDeliveredDPQty(BigDecimal.ZERO);
			
			dispenseLine.setQtyOrdered(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_QtyOrdered));
			dispenseLine.setQtyDelivered(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_QtyDelivered));
			dispenseLine.setBalanceQty(balanceQty);
			
			dispenseLine.setC_Tax_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_Tax_ID));
			dispenseLine.setIsTaxIncluded(rs.getBoolean(MDispensePlanLine.COLUMNNAME_IsTaxIncluded));
			dispenseLine.setIsRoyaltyPassInclusive(rs.getBoolean(MDispensePlanLine.COLUMNNAME_IsRoyaltyPassInclusive));
			dispenseLine.setIsRentInclusive(rs.getBoolean(MDispensePlanLine.COLUMNNAME_IsRentInclusive));
			dispenseLine.setUnitPrice(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_UnitPrice));
			dispenseLine.setPriceEntered(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_PriceEntered));
			dispenseLine.setDiscount(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_Discount));
			dispenseLine.setFreightAmt(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_FreightAmt));
			dispenseLine.setLineNetAmt(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_LineNetAmt));	
			dispenseLine.setTF_DispensePlan_ID(getTF_DispensePlan_ID());
			
			if(ShipmentTo != "") {
				dispenseLine.setShipmentTo(ShipmentTo);
			}

			if(ShipmentDestination != null) {
				dispenseLine.setShipmentDestination(Integer.parseInt(ShipmentDestination));
			}

			
			dispenseLine.saveEx();
		}
	}
	
	public void createDPLinesFromPendingDP(ResultSet rs) throws SQLException {
		
		String sql = " TF_DispensePlan_ID = "+ getTF_DispensePlan_ID() + " AND Parent_ID = " + rs.getInt(MDispensePlanLine.COLUMNNAME_Parent_ID);
		
		MDispensePlanLine dispensePlan = new Query(getCtx(), MDispensePlanLine.Table_Name, sql, get_TrxName()).first();
		
		if(dispensePlan == null) {
			MDispensePlanLine dispenseLine = new MDispensePlanLine(getCtx(), 0, get_TrxName());
			BigDecimal balanceQty = (rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_DispenseQty)).subtract(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_DeliveredDPQty));
			
			dispenseLine.setPriority(MDispensePlanLine.PRIORITY_Normal);
			dispenseLine.setType(MDispensePlanLine.TYPE_Instant);		
			dispenseLine.setC_OrderLine_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_OrderLine_ID));
			dispenseLine.setPaymentRule(rs.getString(MDispensePlanLine.COLUMNNAME_PaymentRule));
			dispenseLine.setC_BPartner_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_BPartner_ID));
			dispenseLine.setDateOrdered(rs.getTimestamp(MDispensePlanLine.COLUMNNAME_DateOrdered));
			dispenseLine.setTF_Destination_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_TF_Destination_ID));
			dispenseLine.setLine(10);
			dispenseLine.setM_Product_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_M_Product_ID));
			dispenseLine.setM_Warehouse_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_M_Warehouse_ID));
			dispenseLine.setDescription(rs.getString(MDispensePlanLine.COLUMNNAME_Description));
			dispenseLine.setShipmentTo(rs.getString(MDispensePlanLine.COLUMNNAME_ShipmentTo));
			dispenseLine.setShipmentDestination(rs.getInt(MDispensePlanLine.COLUMNNAME_ShipmentDestination));
			
			dispenseLine.setDispenseQty(balanceQty);
			dispenseLine.setC_UOM_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_UOM_ID));
			dispenseLine.setBalanceDPQty(balanceQty);
			dispenseLine.setDeliveredDPQty(BigDecimal.ZERO);
			
			dispenseLine.setQtyOrdered(BigDecimal.ZERO);
			dispenseLine.setQtyDelivered(BigDecimal.ZERO);
			dispenseLine.setBalanceQty(BigDecimal.ZERO);
			
			dispenseLine.setC_Tax_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_Tax_ID));
			dispenseLine.setIsTaxIncluded(rs.getBoolean(MDispensePlanLine.COLUMNNAME_IsTaxIncluded));
			dispenseLine.setIsRoyaltyPassInclusive(rs.getBoolean(MDispensePlanLine.COLUMNNAME_IsRoyaltyPassInclusive));
			dispenseLine.setIsRentInclusive(rs.getBoolean(MDispensePlanLine.COLUMNNAME_IsRentInclusive));
			dispenseLine.setUnitPrice(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_UnitPrice));
			dispenseLine.setPriceEntered(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_PriceEntered));
			dispenseLine.setDiscount(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_Discount));
			dispenseLine.setFreightAmt(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_FreightAmt));
			dispenseLine.setLineNetAmt(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_LineNetAmt));	
			dispenseLine.setTF_DispensePlan_ID(getTF_DispensePlan_ID());
			
			dispenseLine.saveEx();
		}
	}
}
