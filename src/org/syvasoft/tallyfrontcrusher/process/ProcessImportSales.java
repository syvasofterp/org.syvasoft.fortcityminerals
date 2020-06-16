package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MPriceList;
import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.syvasoft.tallyfrontcrusher.model.MImportSales;
import org.syvasoft.tallyfrontcrusher.model.MRentedVehicle;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrg;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class ProcessImportSales extends SvrProcess {

	private int				m_AD_Org_ID = 0;
	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();			
			if (name.equals("AD_Org_ID"))
				m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());			
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sql = null;
		int no = 0;
		StringBuilder orgCheck = new StringBuilder(" AND AD_Org_ID=").append(m_AD_Org_ID);

		//	****	Prepare	****

		//	Delete Old Imported
		if (m_deleteOldImported)
		{
			sql = new StringBuilder ("DELETE TF_ImportSales ")
				  .append("WHERE Imported='Y'").append (orgCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (log.isLoggable(Level.FINE)) log.fine("Delete Old Imported =" + no);
		}
		
		//Set C_BPartner_ID
		sql = new StringBuilder ("UPDATE TF_ImportSales i ")
				  .append("SET C_BPartner_ID = (SELECT bp.C_BPartner_ID FROM C_BPartner bp")
				  .append(" WHERE TRIM(LOWER(i.PartyName))= TRIM(LOWER(bp.Name)) AND bp.AD_Client_ID = i.AD_Client_ID ) ")
				  .append("WHERE i.C_BPartner_ID IS NULL AND i.PartyName IS NOT NULL")
				  .append(" AND Imported<>'Y'").append (orgCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Set BP=" + no);

		//Set M_Product_ID
		sql = new StringBuilder ("UPDATE TF_ImportSales i ")
				  .append("SET M_Product_ID=(SELECT MAX(p.M_Product_ID) FROM M_Product p")
				  .append(" WHERE TRIM(LOWER(i.Material)) = TRIM(LOWER(p.Value)) AND i.AD_Client_ID=p.AD_Client_ID) ")
				  .append("WHERE Material IS NOT NULL")
				  .append(" AND Imported<>'Y'").append (orgCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Set Product from Value=" + no);
		
		//Set TF_RentedVehicle_ID
		sql = new StringBuilder ("UPDATE TF_ImportSales i ")
				  .append("SET TF_RentedVehicle_ID=(SELECT MAX(rv.TF_RentedVehicle_ID) FROM TF_RentedVehicle rv")
				  .append(" WHERE TRIM(LOWER(i.vno)) = TRIM(LOWER(rv.VehicleNo)) AND i.AD_Client_ID=rv.AD_Client_ID) ")
				  .append("WHERE vno IS NOT NULL")
				  .append(" AND Imported<>'Y'").append (orgCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Set Product from Value=" + no);

		//Set TF_WeighmentEntry_ID
		sql = new StringBuilder ("UPDATE TF_ImportSales i ")
				  .append("SET TF_WeighmentEntry_ID=(SELECT MAX(w.TF_WeighmentEntry_ID) FROM TF_WeighmentEntry w")
				  .append(" WHERE TRIM(LOWER(i.weighmentno)) = TRIM(LOWER(w.DocumentNo)) AND i.AD_Client_ID=w.AD_Client_ID) ")
				  .append("WHERE weighmentno IS NOT NULL")
				  .append(" AND Imported<>'Y'").append (orgCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Set Weighment from Value=" + no);

		
		/*
		//Set VG_UOM
		sql = new StringBuilder ("UPDATE TF_ImportSales i ")
				  .append("SET VG_UOM =(SELECT p.VG_UOM FROM M_Product p")
				  .append(" WHERE i.M_Product_ID = p.M_Product_ID AND i.AD_Client_ID=p.AD_Client_ID) ")
				  .append("WHERE i.M_Product_ID IS NOT NULL")
				  .append(" AND I_IsImported<>'Y'").append (orgCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Set Product from Value=" + no);

		//Set Std Weight
		sql = new StringBuilder ("UPDATE TF_ImportSales i ")
				  .append("SET StdWeight =(SELECT p.StdWeight FROM M_Product p")
				  .append(" WHERE i.M_Product_ID = p.M_Product_ID AND i.AD_Client_ID=p.AD_Client_ID AND p.StdWeight > 0 ) ")
				  .append("WHERE i.M_Product_ID IS NOT NULL")
				  .append(" AND I_IsImported<>'Y'").append (orgCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Set Standard Weight from Value=" + no);
		//Set Movement Qty In Kgs
		sql = new StringBuilder ("UPDATE TF_ImportSales i ")
				  .append("SET MovementQtyInKgs = MovementQtyInNos * StdWeight ")
				  .append("WHERE i.M_Product_ID IS NOT NULL AND i.StdWeight > 0 AND i.MovementQtyInNos > 0 ")
				  .append(" AND I_IsImported<>'Y'").append (orgCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Set Standard Weight from Value=" + no);
		*/
		sql = new StringBuilder ("UPDATE TF_ImportSales i ")
				  .append("SET Imported='E', ImportMessage= COALESCE(ImportMessage,'') || 'ERR=Invalid Rented Vehicle, ' ")
				  .append("WHERE TF_RentedVehicle_ID IS NULL ")
				  .append(" AND Imported<>'Y'").append (orgCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid Rented Vehicle =" + no);
		
		sql = new StringBuilder ("UPDATE TF_ImportSales i ")
				  .append("SET Imported='E', ImportMessage= COALESCE(ImportMessage,'') || 'ERR=Invalid Product Code, ' ")
				  .append("WHERE M_Product_ID IS NULL ")
				  .append(" AND Imported<>'Y'").append (orgCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid Product =" + no);
		
		sql = new StringBuilder ("UPDATE TF_ImportSales i ")
				  .append("SET Imported='E', ImportMessage= COALESCE(ImportMessage,'') || 'ERR=Invalid Weighment No, ' ")
				  .append("WHERE TF_WeighmentEntry_ID IS NULL ")
				  .append(" AND Imported<>'Y'").append (orgCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid Weighment =" + no);

		
		//Validate Qty
		sql = new StringBuilder ("UPDATE TF_ImportSales i ")
				  .append("SET Imported='E', ImportMessage=COALESCE(ImportMessage,'') || 'ERR=Invalid Qty, ' ")
				  .append("WHERE qty = 0")
				  .append(" AND Imported<>'Y'").append (orgCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid Qty =" + no);
		
		commitEx();
		
		
		//Create Customer
		sql = new StringBuilder ("SELECT * FROM TF_ImportSales ")
				  .append("WHERE Imported='N' AND C_BPartner_ID IS NULL ").append (orgCheck);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql.toString(), get_TrxName());
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				MImportSales imp = new MImportSales(getCtx(), rs, get_TrxName());
				if(imp.getC_BPartner_ID() == 0) {
					String sql1 = "SELECT C_BPartner_ID FROM C_BPartner WHERE Name = ? " + orgCheck;
					int C_BPartner_ID = DB.getSQLValue(get_TrxName(), sql1, imp.getPartyName());
					if(C_BPartner_ID > 0) {
						imp.setC_BPartner_ID(C_BPartner_ID);
					}
					else {
						
						if(imp.getPartyName() == null || imp.getPartyName().length() ==0)
							imp.setPartyName(imp.getPartyName());
						
						/*
						 * if(imp.getCity() == null || imp.getCity().length() ==0)
						 		imp.setCity("Chennai");
						*/	
						
						TF_MBPartner bp = new TF_MBPartner(getCtx(), 0, get_TrxName());
						bp.setAD_Org_ID(imp.getAD_Org_ID());
						bp.setName(imp.getPartyName());
						bp.setIsCustomer(true);
						//sql1 = " SELECT MIN(C_BP_Group_ID) FROM C_BP_Group  WHERE IsCustomer='Y' AND AD_Client_ID =" + imp.getAD_Client_ID() ;
						//int C_BP_Group_ID = DB.getSQLValue(get_TrxName(), sql1);
						int C_BP_Group_ID =1000001;
						bp.setC_BP_Group_ID(C_BP_Group_ID);
						bp.setContactName(imp.getPartyName());
						bp.setAddress1("Chennai");
						bp.setCity("Chennai");
						bp.setC_Country_ID(208);
						bp.saveEx();
						
						imp.setC_BPartner_ID(bp.getC_BPartner_ID());						
					}
					imp.saveEx();
				}
			}
		}
		
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "CreateBP", e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}		
		
		
		//Create Sales Entry		
		sql = new StringBuilder ("SELECT * FROM TF_ImportSales ")
				  .append("WHERE Imported='N' AND C_BPartner_ID IS NOT NULL AND C_Order_ID IS NULL").append (orgCheck);
		pstmt = null;
		rs = null;
		int oldC_BPartner_ID = 0;
		int lineNo = 0;
		TF_MOrder sale = null;
		Trx trx = Trx.get(get_TrxName(), false);
		Savepoint sp = null;
		
		try
		{
			pstmt = DB.prepareStatement (sql.toString(), get_TrxName());
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				MImportSales imp = new MImportSales(getCtx(), rs, get_TrxName());
			    MWeighmentEntry wEntry=new MWeighmentEntry(getCtx(), imp.getTF_WeighmentEntry_ID(), get_TrxName());
			    TF_MOrg org=new TF_MOrg(getCtx(), imp.getAD_Org_ID(), get_TrxName());
			    MOrgInfo oInfo=new MOrgInfo(org);
			    
				MRentedVehicle rv=new MRentedVehicle(getCtx(), imp.getTF_RentedVehicle_ID(), get_TrxName());
			    
			    TF_MOrder ord = new TF_MOrder(getCtx(), 0, get_TrxName());
				ord.setAD_Org_ID(imp.getAD_Org_ID());
				ord.setC_DocTypeTarget_ID(1000057);
				ord.setC_DocType_ID(1000057);
				ord.setM_Warehouse_ID(oInfo.getM_Warehouse_ID());
				ord.setDateAcct(imp.getCreated());
				ord.setDateOrdered(imp.getCreated());
				int C_BParner_ID = imp.getC_BPartner_ID();
				if(C_BParner_ID == 0)
					C_BParner_ID = 1000020;		
				TF_MBPartner bp = new TF_MBPartner(getCtx(), C_BParner_ID, get_TrxName());
				ord.setBPartner(bp);
				ord.setDescription("");
				if(ord.getDescription() != null)
					ord.addDescription("Customer Name : " + imp.getPartyName());
				else
					ord.setDescription("Customer Name : " + imp.getPartyName());
				ord.setPaymentRule("C");		
				//Price List
				int m_M_PriceList_ID = Env.getContextAsInt(getCtx(), "#M_PriceList_ID");
				if(bp.getM_PriceList_ID() > 0)
					m_M_PriceList_ID = bp.getM_PriceList_ID();			
				ord.setM_PriceList_ID(m_M_PriceList_ID);
				ord.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
				ord.setIsSOTrx(true);
				ord.setTF_WeighmentEntry_ID(imp.getTF_WeighmentEntry_ID());	
				ord.setTF_Destination_ID(100000);
				ord.setVehicleNo(imp.getVNo());
				ord.setTF_RentedVehicle_ID(imp.getTF_RentedVehicle_ID());
				ord.setItem1_BucketQty(null);
				
				
				//Item
				ord.setItem1_IsPermitSales(false);
				ord.setItem1_VehicleType_ID(rv.getTF_VehicleType_ID());
				if(wEntry.isHasBalance())
					ord.setItem1_SandType(TF_MOrder.ITEM1_SANDTYPE_PermitSand);
				else
					ord.setItem1_SandType(TF_MOrder.ITEM1_SANDTYPE_WithoutPermit);
				ord.setItem1_ID(imp.getM_Product_ID());
				
				int tonnage_uom_id = MSysConfig.getIntValue("TONNAGE_UOM", 1000069, Env.getAD_Client_ID(getCtx()));
				int uom_id = imp.getM_Product().getC_UOM_ID();
				ord.setItem1_UOM_ID(ord.getItem1().getC_UOM_ID());
				ord.setItem1_Tax_ID(1000000);
				BigDecimal qty = imp.getQty();
				if(uom_id == tonnage_uom_id)
					qty = qty.divide(new BigDecimal(1000));
				ord.setItem1_TotalLoad(BigDecimal.ONE);
				ord.setItem1_PermitIssued(qty); 
				ord.setMDPNo("");
				ord.setItem1_Qty(qty);
				BigDecimal price = imp.getPrice();
				ord.setItem1_Price(price);
				ord.setItem1_UnitPrice(price);
				ord.setItem1_Amt(imp.getAmount());
				
				ord.saveEx();				
				
				sp = trx.setSavepoint(wEntry.getDocumentNo());
				ord.setDocAction(DocAction.ACTION_Complete);
				ord.completeIt();
				ord.setDocStatus(TF_MOrder.DOCSTATUS_Completed);
				ord.saveEx();

				imp.setImported("Y");
				imp.saveEx();

				String error = DocumentEngine.postImmediate(Env.getCtx(), ord.getAD_Client_ID(), ord.get_Table_ID(), ord.get_ID(), true, ord.get_TrxName());				
				if (! Util.isEmpty(error)) {
						throw new AdempiereException(error);
				}
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "CreateBP", e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}		
		
		commitEx();
		
		String whereClause = "C_Order_ID IN (SELECT C_Order_ID FROM TF_ImportSales ) AND DocStatus = 'DR' "; 
		List<TF_MOrder> sales = new Query(getCtx(), TF_MOrder.Table_Name, whereClause, get_TrxName())
				.setClient_ID().list();
		int i = 0;		

		for(TF_MOrder entry : sales) {
	
			try {
				sp = trx.setSavepoint(entry.getDocumentNo());
				entry.processIt(DocAction.ACTION_Complete);
				entry.saveEx();				
				trx.releaseSavepoint(sp);
				sql = new StringBuilder ("UPDATE TF_ImportSales i ")
						  .append("SET ImportMessage='' ")						  
						  .append("WHERE C_Order_ID =  " + entry.getC_Order_ID())
						  .append(" ").append (orgCheck);
				no = DB.executeUpdate(sql.toString(), get_TrxName());								
			}
			catch (AdempiereException ex) {
				if(sp != null)
					trx.rollback(sp);
								//
				sql = new StringBuilder ("UPDATE TF_ImportSales i ")
						  .append("SET ImportMessage='")
						  .append( ex.getMessage() +  "'  ")
						  .append("WHERE C_Order_ID =  " + entry.getC_Order_ID())
						  .append(" ").append (orgCheck);
				no = DB.executeUpdate(sql.toString(), get_TrxName());
				commitEx();
				addLog(1, null, null, "Not Posted: " + entry.getDocumentNo(), TF_MOrder.Table_ID, entry.getC_Order_ID());
				i--;
			}
			finally{
				
			}
			i++;			
		}
		
		//	Set Error to indicator to not imported
		sql = new StringBuilder ("UPDATE TF_ImportSales ")
			.append("SET Imported='N', Updated=SysDate ")
			.append("WHERE Imported<>'Y'").append(orgCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (i), "@TF_ImportSales_ID@: Posted Successfully");
		
		return null;
	}

}