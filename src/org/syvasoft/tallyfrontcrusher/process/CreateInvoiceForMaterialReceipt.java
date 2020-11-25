package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.model.MInvoiceLine;


import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;

import org.syvasoft.tallyfrontcrusher.model.TF_MInvoice;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CreateInvoiceForMaterialReceipt extends SvrProcess {

	private int recordID = 0;	
	private int m_AD_Org_ID = 0;	
	private Timestamp DateFrom = null;
	private Timestamp DateTo = null;
	boolean reCreate = false;	
	
	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		recordID = getRecord_ID();
		ProcessInfoParameter[] para = getParameter();		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();			
			if (name.equals("AD_Org_ID"))				
				m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DateFrom")) {
				DateFrom = para[i].getParameterAsTimestamp();
			}
			else if (name.equals("DateTo"))
				DateTo = para[i].getParameterAsTimestamp();
			else if (name.equals("reCreate"))
				reCreate = para[i].getParameterAsBoolean();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception {
		TF_MInvoice invoice=new TF_MInvoice(getCtx(), recordID, get_TrxName());
		String sqlUpdate = null;
		
		if(reCreate) {
			sqlUpdate = "UPDATE M_InOut SET C_Invoice_ID = NULL WHERE C_Invoice_ID = " + invoice.getC_Invoice_ID();
			DB.executeUpdate(sqlUpdate, get_TrxName());
			for(MInvoiceLine invLine : invoice.getLines(true)) {
				invLine.delete(true, get_TrxName());
			}
		}
		
		
		
		sqlUpdate = "UPDATE M_InOut SET C_Invoice_ID = ? WHERE AD_Org_ID = ? AND C_BPartner_ID = ? AND DocStatus IN ('CO','CL') "
				+ "	AND MovementDate >= ? AND MovementDate <= ? AND C_Invoice_ID IS NULL";

		ArrayList<Object> params = new ArrayList<Object>();
		params.add(invoice.getC_Invoice_ID());
		params.add(m_AD_Org_ID);
		params.add(invoice.getC_BPartner_ID());
		params.add(DateFrom);
		params.add(DateTo);
		
		DB.executeUpdateEx(sqlUpdate, params.toArray(), get_TrxName());
		
		
		// Consolidated invoice line will be created only for the new Products which are not in Invoice Lines already.
		/*String where = "io.AD_Org_ID = ? AND io.C_BPartner_ID = ? AND io.DocStatus IN ('CO','CL') " + 
				"	AND io.MovementDate >= ? AND io.MovementDate <= ?" +
				" AND  (SELECT COALESCE(SUM(mi.Qty),0) FROM M_MatchInv mi WHERE mi.M_InOutLine_ID =inl.M_InOutLine_ID) = 0 "
				+ " AND inl.M_Product_ID NOT IN (SELECT invLine.M_Product_ID FROM C_InvoiceLine invLine WHERE invLine.C_Invoice_ID = ? ) ";
		*/
		
		String sql = "SELECT inl.M_Product_ID, inl.C_UOM_ID, sum (inl.QtyEntered) QtyEntered " + 
				" FROM	M_InOut io INNER JOIN M_InOutLine inl ON inl.M_InOut_ID=io.M_InOut_ID " +
				" WHERE io.C_Invoice_ID = ? AND inl.M_Product_ID NOT IN (SELECT invLine.M_Product_ID FROM C_InvoiceLine invLine WHERE invLine.C_Invoice_ID = io.C_Invoice_ID ) "  + 
				" GROUP BY inl.M_Product_ID, inl.C_UOM_ID";
				
		PreparedStatement pstmt =  null;
		ResultSet rs = null;
		int i = 0;
		try	{
			
			
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			params = new ArrayList<Object>();
			params.add(invoice.getC_Invoice_ID());
			DB.setParameters(pstmt,params.toArray());
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
			
				//Create Invoice Line
				MInvoiceLine invLine = new MInvoiceLine(invoice);				
				invLine.setM_Product_ID(rs.getInt("M_Product_ID"), true);
				invLine.setQty(rs.getBigDecimal("QtyEntered"));
				invLine.setC_UOM_ID(rs.getInt("C_UOM_ID"));
				
				BigDecimal price =BigDecimal.ZERO;
				
				MPriceListUOM pprice = MPriceListUOM.getPriceListUOM(getCtx(), rs.getInt("M_Product_ID"),
						rs.getInt("C_UOM_ID"), invoice.getC_BPartner_ID(), false, invoice.getDateInvoiced());
				
				TF_MProduct prod = new TF_MProduct(getCtx(), invLine.getM_Product_ID(), get_TrxName());
				
				if( pprice == null)
					throw new AdempiereException("Please configure the Purchase Price for " + prod.getName() + "!");
				
				price = pprice.getPrice();				
				invLine.setPriceActual(price);
				invLine.setPriceList(price);
				invLine.setPriceLimit(price);
				invLine.setPriceEntered(price);				
			
				invLine.setC_Tax_ID(prod.getTax_ID(true));								
				invLine.saveEx();				
				
				i++;
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
		return i + " invoice lines are created successfully!";
	}

}
