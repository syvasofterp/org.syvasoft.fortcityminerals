package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MSysConfig;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
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
		
		//If the Employee is created from Quick Entry
		if(!getC_BPartner().isEmployee()) {
			MBPartner bp = new MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
			bp.setIsEmployee(true);
			bp.setIsCustomer(false);
			bp.setIsVendor(false);
			bp.saveEx();
		}
		
		//Set Issued Resource/Vehicle
		if(getC_Project_ID() > 0) {
			MJobworkIssuedResource res = MJobworkIssuedResource.getByResource(getCtx(), getC_Project_ID(), getVehicle_ID(), get_TrxName());
			setTF_Jobwork_IssuedResource_ID(res.getTF_Jobwork_IssuedResource_ID());
		}
		else {
			setTF_Jobwork_IssuedResource_ID(0);
		}
		
		return super.beforeSave(newRecord);
	}

	private void issueDiesel() {
		String dieselIssue = MSysConfig.getValue("TF_DIESEL_ISSUE_FROM_TRIPSHEET", "N");
		if(dieselIssue.equals("Y") && getReceived_Fuel().doubleValue() > 0) {
			MFuelIssue issue = new MFuelIssue(getCtx(), 0, get_TrxName());
			issue.setDateAcct(getDateReport());
			issue.setM_Warehouse_ID(Env.getContextAsInt(getCtx(), "#M_Warehouse_ID"));
			int dieselID = MGLPostingConfig.getMGLPostingConfig(getCtx()).getFuel_Product_ID();
			issue.setM_Product_ID(dieselID);
			issue.setVehicle_ID(getVehicle_ID());
			issue.setQty(getReceived_Fuel());
			issue.setIsCalculated(true);
			issue.setDocStatus(DOCSTATUS_Drafted);
			issue.setTF_TripSheet_ID(getTF_TripSheet_ID());
			issue.saveEx();
			issue.processIt(DocAction.ACTION_Complete);
			issue.saveEx();
		}
	}
	
	public void processIt(String docAction) {
		if(DocAction.ACTION_Prepare.equals(docAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(DocAction.ACTION_Complete.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			
			String dieselIssue = MSysConfig.getValue("TF_DIESEL_ISSUE_FROM_TRIPSHEET", "N");
			if(dieselIssue.equals("N")) { 
				String sql = "UPDATE TF_Fuel_Issue SET TF_TripSheet_ID = ?" +  
						"  WHERE TF_TripSheet_ID IS NULL AND Vehicle_ID = ? " +  
						" AND DateAcct <= ? AND DocStatus = 'CO' ";			
				Object[] obj = new Object[3];
				obj[0] = getTF_TripSheet_ID();
				obj[1] = getVehicle_ID();
				obj[2] = getDateReport();			
				DB.executeUpdateEx(sql,obj, get_TrxName());
			}
			else {
				issueDiesel();
			}
			
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
			
			//Update Subcontract Issued Items for Diesel
			MJobworkIssuedResource issuedResource = MJobworkIssuedResource.getByResource(getCtx(), getC_Project_ID(), getVehicle_ID(), get_TrxName());
			if(issuedResource != null && !issuedResource.isFuelIncluded()) {
				
				MGLPostingConfig glConfig = MGLPostingConfig.getMGLPostingConfig(getCtx());  
				MJobworkIssuedItems.addIssuedItem(getCtx(), getC_Project_ID(), 
						glConfig.getFuel_Product_ID(), glConfig.getFuel_Product().getC_UOM_ID(), getExpensed_Fuel() , get_TrxName());
				
			}
						
		}
	}
	
	public void reverseIt() {
		if(getSubcon_Invoice_ID()>0) {			
			throw new AdempiereException("You cannot modify this entry before Reverse Correct Subcontractor Invoice!");
		}
		if(getTF_Labour_Wage_ID()>0) {
			MLabourWage wage = new MLabourWage(getCtx(), getTF_Labour_Wage_ID(), get_TrxName());
			wage.reverseIt();
			wage.saveEx();
			
			setTF_Labour_Wage_ID(0);
			wage.deleteEx(true);
		}
		String dieselIssue = MSysConfig.getValue("TF_DIESEL_ISSUE_FROM_TRIPSHEET", "N");
		if(dieselIssue.equals("Y") && getReceived_Fuel().doubleValue() > 0) {
			List<MFuelIssue> issues = new Query(getCtx(), MFuelIssue.Table_Name, "DocStatus='CO' AND TF_TripSheet_ID=?", get_TrxName())
				.setParameters(getTF_TripSheet_ID()).list();
			for(MFuelIssue issue : issues) {
				issue.reverseIt();
				issue.saveEx();
				issue.deleteEx(true,get_TrxName());
			}
		}
		//Update Subcontract Issued Items for Diesel
		MJobworkIssuedResource issuedResource = MJobworkIssuedResource.getByResource(getCtx(), getC_Project_ID(), getVehicle_ID(), get_TrxName());
		if(issuedResource != null && !issuedResource.isFuelIncluded()) {
			
			MGLPostingConfig glConfig = MGLPostingConfig.getMGLPostingConfig(getCtx());  
			MJobworkIssuedItems.addIssuedItem(getCtx(), getC_Project_ID(), 
					glConfig.getFuel_Product_ID(), glConfig.getFuel_Product().getC_UOM_ID(), getExpensed_Fuel().negate() , get_TrxName());
			
		}
		
		//if(issuedResource != null && !issuedResource.isOperatorWageIncluded()) {			
		//	issuedResource.setOperatorTotalWage(issuedResource.getOperatorTotalWage().subtract(getTotal_Wage()));
		//	issuedResource.saveEx();
		//}
		
		setProcessed(false);
		setDocStatus(DOCSTATUS_Drafted);
	}
}
