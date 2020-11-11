package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.model.MSysConfig;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CreateConsolidateCustomerOrderLines extends SvrProcess {

	TF_MOrder ord = null;
	
	Timestamp dateFrom = null;
	Timestamp dateTo = null;
	
	@Override
	protected void prepare() {
		
		ord = new TF_MOrder(getCtx(), getRecord_ID(), get_TrxName());
		
		for(ProcessInfoParameter para : getParameter())
		{
			String name = para.getParameterName();			
			if("DateFrom".equals(name))
				dateFrom = para.getParameterAsTimestamp();
			else if("DateTo".equals(name) ) 
				dateTo = para.getParameterAsTimestamp();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {
		
		String sqlUpate = "UPDATE M_Inout io SET C_Order_ID = ? WHERE io.AD_Org_ID = ? AND C_DocType_ID = ? AND io.C_BPartner_ID = ? "
				+ " AND io.Docstatus IN ('CO','CL') AND "  
				+ "	io.MovementDate >= ? AND io.MovementDate <= ? AND C_Order_ID IS NULL";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(ord.getC_Order_ID());
		params.add(ord.getAD_Org_ID());
		params.add(ord.getC_DocTypeTarget_ID());
		params.add(ord.getC_BPartner_ID());
		params.add(dateFrom);
		params.add(dateTo);
		DB.executeUpdateEx(sqlUpate, params.toArray(), get_TrxName());
						
		int RoyaltyPassProduct_ID = MSysConfig.getIntValue("ROYALTY_PASS_PRODUCT_ID", 1000329, getAD_Client_ID(), ord.getAD_Org_ID());
		
		String sql = "SELECT M_Product_ID, C_UOM_ID, SUM(MovementQty) qtyShipped FROM "
				+ " M_InOut io INNER JOIN M_InOutLine ioLine ON io.M_InOut_ID = ioLine.M_InOut_ID "
				+ " WHERE io.C_Order_ID = ? AND ioLine.M_Product_ID != ? " //Excluding Royalty Pass Shipment Line 
				+ " GROUP BY M_Product_ID, C_UOM_ID ";
						
		PreparedStatement pstmt =  null;
		ResultSet rs = null;
		try	{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			params = new ArrayList<Object>();
			params.add(ord.getC_Order_ID());
			params.add(RoyaltyPassProduct_ID);
			
			DB.setParameters(pstmt,params.toArray());
			rs = pstmt.executeQuery();
									
			while (rs.next()) {
				int M_Product_ID = rs.getInt("M_Product_ID");
				int C_UOM_ID = rs.getInt("C_UOM_ID");
				BigDecimal qtyShipped = rs.getBigDecimal("qtyShipped");
				
				// create order line
				//Assumed the Credit Customer material price always include Royalty Pass amount
				//Later it has to calculate the Material Price based on the Royalty Pass Issued
								
				TF_MOrderLine ordLine = new TF_MOrderLine(ord);
				ordLine.setM_Product_ID(M_Product_ID, C_UOM_ID);				
				ordLine.setQty(qtyShipped);
				BigDecimal price = MPriceListUOM.getPrice(getCtx(), M_Product_ID, C_UOM_ID, ord.getC_BPartner_ID(), ord.isSOTrx(), ord.getDateAcct());
				ordLine.setPriceActual(price);
				ordLine.setPriceList(price);
				ordLine.setPriceLimit(price);
				ordLine.setPriceEntered(price);
				TF_MProduct prod = new TF_MProduct(getCtx(), M_Product_ID, get_TrxName());
				ordLine.setC_Tax_ID(prod.getTax_ID(true));				
				ordLine.saveEx();
			}	
			
		}
		catch (SQLException e) {
			rollback();
			//log.log(Level.SEVERE, "", e);
			throw new DBException(e, sql.toString());
		}
		finally	{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		
		return null;
	}
	
}
