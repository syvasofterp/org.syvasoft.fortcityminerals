package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.adempiere.exceptions.DBException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.syvasoft.tallyfrontcrusher.model.MCrusherProduction;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlanLine;

public class CreateDispenseLines extends SvrProcess {
	
	private int p_TF_DispensePlan_ID = 0;
	private boolean recreate = false;
	
	@Override
	protected void prepare() {
		for(ProcessInfoParameter para : getParameter())
		{
			String name = para.getParameterName();
			if("Recreate".equals(name))
				recreate = "Y".equals(para.getParameter());
		}
		p_TF_DispensePlan_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		
		if(recreate) {
			String sqlDelete = "DELETE FROM TF_DispensePlanLine WHERE TF_DispensePlan_ID = " + p_TF_DispensePlan_ID;
			DB.executeUpdate(sqlDelete, get_TrxName());
		}
		
		String sql = "SELECT * FROM c_order o INNER JOIN c_orderline ol ON ol.c_order_id = o.c_order_id WHERE " + 
				" (trunc(o.dateordered)=trunc(getdate()) OR (trunc(o.dateordered) < trunc(getdate()) AND (qtyordered - qtydelivered) > 0)) " + 
				"AND c_doctypetarget_id = 1000032 AND docstatus IN ('CO','CL')";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();		
			while (rs.next()) {
				createDPLines(rs);
			}
		} catch (SQLException e) {
			rollback();
			throw new DBException(e, sql);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		return null;
	}
	
	private void createDPLines(ResultSet rs) throws SQLException {
		MDispensePlanLine dispenseLine = new MDispensePlanLine(getCtx(), 0, get_TrxName());
		BigDecimal balanceQty = (rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_QtyOrdered)).subtract(rs.getBigDecimal(MDispensePlanLine.COLUMNNAME_QtyDelivered));
		
		dispenseLine.setC_OrderLine_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_OrderLine_ID));
		dispenseLine.setPaymentRule(rs.getString(MDispensePlanLine.COLUMNNAME_PaymentRule));
		dispenseLine.setC_BPartner_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_C_BPartner_ID));
		dispenseLine.setDateOrdered(rs.getTimestamp(MDispensePlanLine.COLUMNNAME_DateOrdered));
		dispenseLine.setTF_Destination_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_TF_Destination_ID));
		dispenseLine.setLine(10);
		dispenseLine.setM_Product_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_M_Product_ID));
		dispenseLine.setM_Warehouse_ID(rs.getInt(MDispensePlanLine.COLUMNNAME_M_Warehouse_ID));
		dispenseLine.setDescription(rs.getString(MDispensePlanLine.COLUMNNAME_Description));
		
		dispenseLine.setDispenseQty(balanceQty);
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
		dispenseLine.setTF_DispensePlan_ID(p_TF_DispensePlan_ID);
		
		dispenseLine.saveEx();
	}
}
