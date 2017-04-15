package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.process.DocAction;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MTripSheet extends X_TF_TripSheet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3586090598937825044L;

	public MTripSheet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MTripSheet(Properties ctx, int TF_TripSheet_ID, String trxName) {
		super(ctx, TF_TripSheet_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public static BigDecimal getReceivedFuel(int vehicle_ID, Timestamp dateReport) {
		String sql = "SELECT SUM(Qty) FROM TF_Fuel_Issue WHERE TF_TripSheet_ID IS NULL AND Vehicle_ID = ? " +
					" AND DateAcct <= ? AND DocStatus = 'CO' ";
		BigDecimal receivedFuel = DB.getSQLValueBD(null, sql, vehicle_ID, dateReport);
		if(receivedFuel == null)
			receivedFuel = BigDecimal.ZERO; 
		return receivedFuel;		
	}

	public static BigDecimal getOpeningMeter(int vehicle_ID, Timestamp dateReport) {
		String sql = " SELECT Closing_Meter FROM TF_TripSheet WHERE Vehicle_ID=? AND DateReport <= ? AND " +
				" DocStatus = 'CO' ORDER BY DateReport DESC, Updated DESC ";		
		BigDecimal openingMeter = DB.getSQLValueBD(null, sql, vehicle_ID, dateReport);
		if(openingMeter == null)
			openingMeter = BigDecimal.ZERO;
		return openingMeter;
	}
	
	public static BigDecimal getOpeningFuel(int vehicle_ID, Timestamp dateReport) {
		String sql = " SELECT Closing_Fuel FROM TF_TripSheet WHERE Vehicle_ID=? AND DateReport <= ? AND " +
				" DocStatus = 'CO' ORDER BY DateReport DESC, Updated DESC ";		
		BigDecimal openingFuel = DB.getSQLValueBD(null, sql, vehicle_ID, dateReport);
		if(openingFuel == null)
			openingFuel = BigDecimal.ZERO;
		return openingFuel;
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		setTotal_Wage(getEarned_Wage().add(getIncentive()));
		return super.beforeSave(newRecord);
	}

	public void processIt(String docAction) {
		if(DocAction.ACTION_Prepare.equals(docAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(DocAction.ACTION_Complete.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			
			String sql = "UPDATE TF_Fuel_Issue SET TF_TripSheet_ID = ?" +  
					"  WHERE TF_TripSheet_ID IS NULL AND Vehicle_ID = ? " +  
					" AND DateAcct <= ? AND DocStatus = 'CO' ";			
			Object[] obj = new Object[3];
			obj[0] = getTF_TripSheet_ID();
			obj[1] = getVehicle_ID();
			obj[2] = getDateReport();			
			DB.executeUpdateEx(sql,obj, get_TrxName());
			
			if(getTotal_Wage().doubleValue() != 0 && getC_BPartner_ID() > 0){
				// Create Wage Entry
				MLabourWage wage = new MLabourWage(getCtx(), 0, get_TrxName());
				wage.setAD_Org_ID(Env.getAD_Org_ID(getCtx()));
				wage.setDateAcct(getDateReport());
				wage.setC_BPartner_ID(getC_BPartner_ID());
				//wage.setTF_VehicleType_ID();
						
				wage.setStd_Days(BigDecimal.ONE);
				wage.setStd_Wage(getEarned_Wage());
				wage.setPresent_Days(BigDecimal.ONE);
				wage.setIncentive(getIncentive());
				wage.setEarned_Wage(getEarned_Wage());
				wage.setIsCalculated(false);				
				wage.setDescription("Generated from TripSheet" );
				if(getTF_Quarry_ID() > 0) {
					MQuarry quarry = new MQuarry(getCtx(), getTF_Quarry_ID(), get_TrxName());
					wage.setC_ElementValue_ID(quarry.getC_ElementValue_ID());
				}
				wage.saveEx();
				wage.processIt(DocAction.ACTION_Complete);
				wage.saveEx();
				//End Create
				
				setTF_Labour_Wage_ID(wage.getTF_Labour_Wage_ID());
			}
			
		}
	}
	
	public void reverseIt() {
		if(getTF_Labour_Wage_ID()>0) {
			MLabourWage wage = new MLabourWage(getCtx(), getTF_Labour_Wage_ID(), get_TrxName());
			wage.reverseIt();
			wage.saveEx();
			
			setTF_Labour_Wage_ID(0);
			wage.deleteEx(true);			
			
		}
		setProcessed(false);
		setDocStatus(DOCSTATUS_Drafted);
	}
}
