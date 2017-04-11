package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRent;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRentConfig;

public class PostVehicleRent extends SvrProcess {

	private int m_AD_Client_ID = 0;
	private int m_AD_Org_ID = 0;
	private boolean isSimulate = false;
	private Timestamp m_DateAcct = null;
	private Timestamp m_DateReceipt_1 = null;
	private Timestamp m_DateReceipt_2 = null;
	private int m_TF_Quarry_ID = 0;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		m_AD_Client_ID = Env.getAD_Client_ID(getCtx());
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();			
			if (name.equals("AD_Org_ID"))				
				m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("TF_Quarry_ID"))
				m_TF_Quarry_ID = ((BigDecimal)para[i].getParameter()).intValue();			
			else if (name.equals("IsSimulate"))
				isSimulate = para[i].getParameterAsBoolean();
			else if (name.equals("DateAcct"))
				m_DateAcct = para[i].getParameterAsTimestamp();
			else if (name.equals("DateReceipt")) {
				m_DateReceipt_1 = para[i].getParameterAsTimestamp();
				m_DateReceipt_2 = para[i].getParameter_ToAsTimestamp();
			}
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {
		String where = "TF_Quarry_ID = ? AND DocStatus = 'CO' AND Processed = 'Y' AND DateReceipt >= ? AND DateReceipt <= ?  " +
				" AND TF_Vehicle_Rent_ID IS NULL " ;
	
		String sql = "SELECT TF_Quarry_ID, Vehicle_ID, TF_VehicleType_ID, Count (Distinct DateReceipt) RentedDays " + 
					" FROM	TF_Boulder_Receipt br INNER JOIN M_Product p 	ON br.Vehicle_ID = p.M_Product_ID " +
					" WHERE NOT EXISTS( SELECT * FROM S_Resource, M_Product where M_Product_ID = br.Vehicle_ID AND IsRented='Y' " + 
					" AND S_Resource.S_Resource_ID = M_Product.S_Resource_ID) AND " + where +  
					" GROUP BY TF_Quarry_ID, Vehicle_ID, p.TF_VehicleType_ID ";
		int no = 0;		
		PreparedStatement pstmt =  null;
		ResultSet rs = null;
		try	{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			ArrayList<Object> params = new ArrayList<Object>();
			params.add(m_TF_Quarry_ID);
			params.add(m_DateReceipt_1);
			params.add(m_DateReceipt_2);
			DB.setParameters(pstmt,params.toArray());
			rs = pstmt.executeQuery();			
			while (rs.next()) {
				no++;
				int Vehicle_ID = rs.getInt("Vehicle_ID");
				int TF_VehicleType_ID = rs.getInt("TF_VehicleType_ID");
				BigDecimal rentedDays = rs.getBigDecimal("RentedDays");
				MVehicleRentConfig rentConfig = MVehicleRentConfig.getVehicleRentConfig(getCtx(), TF_VehicleType_ID, m_DateAcct);
				//Create Vehicle Rent Entry
				MVehicleRent rent = new MVehicleRent(getCtx(), 0, get_TrxName());
				rent.setAD_Org_ID(m_AD_Org_ID);
				rent.setDateAcct(m_DateAcct);
				rent.setVehicle_ID(Vehicle_ID);
				rent.setTF_VehicleType_ID(TF_VehicleType_ID);
				
				//If no salary config, skip to next salary entry.
				if(rentConfig == null) {					
					String errMsg = " NO RENT CONFIG -> Vehicle:" + rent.getVehicle().getName()  +
							" | VehicleType:" + rent.getTF_VehicleType().getName() + 
							" | Account Date:" + new SimpleDateFormat("MM/dd/yyyy").format(m_DateAcct);
					addLog(errMsg);
					continue;
				}
				//Create Vehicle Rent Entry
				if(!isSimulate) {
					rent.setStd_Days(rentConfig.getStd_Days());
					rent.setStd_Rent(rentConfig.getStd_Rent());
					rent.setRented_Days(rentedDays);
					rent.setIsCalculated(true);
					rent.setDescription("Generated from Boulder Receipts");
					rent.setTF_Quarry_ID(m_TF_Quarry_ID);				
					rent.setDateFrom(m_DateReceipt_1);
					rent.setDateTo(m_DateReceipt_2);
					rent.setC_ElementValue_ID(rent.getTF_Quarry().getC_ElementValue_ID());
					rent.saveEx();
					rent.processIt(MBoulderReceipt.DOCACTION_Complete);
					rent.saveEx();
					//End Vehicle Rent Entry
					
					//Update Vehicle Rent ID
					sql = " UPDATE TF_Boulder_Receipt SET TF_Vehicle_Rent_ID = ? WHERE " + where + " AND Vehicle_ID = ? ";
					params = new ArrayList<Object>();
					params.add(rent.getTF_Vehicle_Rent_ID());
					params.add(m_TF_Quarry_ID);
					params.add(m_DateReceipt_1);
					params.add(m_DateReceipt_2);
					params.add(Vehicle_ID);					
					DB.executeUpdateEx(sql, params.toArray(), get_TrxName());
					//End Update
					
					String msg = "Vehicle:" + rent.getVehicle().getName() + 
								" | Rented Days:" + rent.getRented_Days().toString() +
								" | Amount:" + rent.getRent_Amt().toString();
					addLog(0, null,null, msg, MVehicleRent.Table_ID, rent.getTF_Vehicle_Rent_ID());
				}// End Create
				else {
					String msg = "Vehicle:" + rent.getVehicle().getName() + 
							" | Rented Days:" + rentedDays.toString() +
							" | Amount:" + rentConfig.getStd_Rent().multiply(rentedDays.divide(rentConfig.getStd_Days())).toString();
					addLog(msg);
				}
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
			
		if(isSimulate)
			return no + " Vehicle Rent Entries will be posted!";
		else 
			return no + " Vehicle Rent Entries have been posted!";		
	}

}
