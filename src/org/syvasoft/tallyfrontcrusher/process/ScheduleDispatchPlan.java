package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;

import org.compiere.model.Query;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.syvasoft.tallyfrontcrusher.model.MCrusherProduction;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlan;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlanLine;

public class ScheduleDispatchPlan extends SvrProcess {
	
	private int p_TF_DispensePlan_ID = 0;
	private Timestamp ScheduleDate;
	private String ShipmentTo;
	private String ShipmentDestination;
	private BigDecimal DispenseQty;
	
	MDispensePlan dispensePlan;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();		
		for (int i = 0; i < para.length; i++){
			String name = para[i].getParameterName();
			
			if(name.toLowerCase().equals("scheduledate"))
				ScheduleDate = para[i].getParameterAsTimestamp();
			else if(name.toLowerCase().equals("shipmentto"))
				ShipmentTo = para[i].getParameterAsString();
			else if(name.toLowerCase().equals("shipmentdestination"))
				ShipmentDestination = para[i].getParameterAsString();
			else if(name.toLowerCase().equals("dispenseqty"))
				DispenseQty = para[i].getParameterAsBigDecimal();
		}
	}

	@Override
	protected String doIt() throws Exception {
		
		String sql = " trunc(scheduledate)=trunc(getdate()) ";
		
		MDispensePlan dispensePlan = new Query(getCtx(), MDispensePlan.Table_Name, sql, get_TrxName()).first();
		
		int i = 0;
		if(dispensePlan == null) {
			dispensePlan = new MDispensePlan(getCtx(), 0, get_TrxName());
			dispensePlan.setScheduleDate(ScheduleDate);
			dispensePlan.setDocStatus(MDispensePlan.DOCSTATUS_Drafted);
			dispensePlan.saveEx();
		}
		
		dispensePlan.ShipmentTo = ShipmentTo;
		dispensePlan.ShipmentDestination = ShipmentDestination;
		dispensePlan.DispatchQty = DispenseQty;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql = "SELECT COUNT(*) FROM c_order o INNER JOIN c_orderline ol ON ol.c_order_id = o.c_order_id WHERE " + 
				" (EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE T_Selection.AD_PInstance_ID="+ getAD_PInstance_ID() + " AND T_Selection.T_Selection_ID = ol.c_orderline_id))";
		
		pstmt = DB.prepareStatement(sql, get_TrxName());
		rs = pstmt.executeQuery();
		rs.next();
	    int rowCount = rs.getInt(1);
	    
	    if((ShipmentTo != null || ShipmentDestination != null) && rowCount > 1) {
	    	throw new AdempiereException("Please choose one Order for Shipment to and Shipment Destination to schedule Dispatch Plan!");
	    }
	    else {
			sql = "SELECT * FROM c_order o INNER JOIN c_orderline ol ON ol.c_order_id = o.c_order_id WHERE " + 
					" (EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE T_Selection.AD_PInstance_ID="+ getAD_PInstance_ID() + " AND T_Selection.T_Selection_ID = ol.c_orderline_id))";
			
			
			try {
				pstmt = DB.prepareStatement(sql, get_TrxName());
				rs = pstmt.executeQuery();		
				
				
				while (rs.next()) {
					dispensePlan.createDPLinesFromOrder(rs);
					i=i+1;
				}
			} catch (SQLException e) {
				rollback();
				throw new DBException(e, sql);
			} finally {
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
			
			return i + " Orders are scheduled successfully!";
	    }
	}
}
