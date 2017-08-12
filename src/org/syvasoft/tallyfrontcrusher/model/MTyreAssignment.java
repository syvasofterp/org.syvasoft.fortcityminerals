package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.DocAction;
import org.compiere.util.DB;

public class MTyreAssignment extends X_TF_TyreAssignment {

	
	public MTyreAssignment(Properties ctx, int TF_TyreAssignment_ID,
			String trxName) {
		super(ctx, TF_TyreAssignment_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MTyreAssignment(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void updateTyreLifeRunningMeter() {
		//Update Tyre Life's Running Meter
		MTyreLife tlife = MTyreLife.getTyreLife(getTF_Tyre_ID(), getTF_TyreType_ID(), get_TrxName());
		if(tlife == null) {					
			throw new AdempiereException("Please Create Tyre Life Record for Tyre Type : " + getTF_TyreType().getName());
		}
		tlife.calcActualRunningMeter();
		tlife.saveEx();
	}	
	
	private boolean isLatestAssignedTyreMovement() {
		String sql = "SELECT COUNT(*) FROM TF_TyreMovement WHERE TF_Tyre_ID = ? AND TF_TyreType_ID = ? AND MovementDate>?";
		int count = DB.getSQLValue(get_TrxName(), sql, getTF_Tyre_ID(), getTF_TyreType_ID(), getAD_MovementDate());
		return (count == 0);
	}
	
	private boolean isLatestReleasedTyreMovement() {
		String sql = "SELECT COUNT(*) FROM TF_TyreMovement WHERE TF_Tyre_ID = ? AND TF_TyreType_ID = ? AND EndDate>?";
		int count = DB.getSQLValue(get_TrxName(), sql, getTF_Tyre_ID(), getTF_TyreType_ID(), getRD_ReleasedDate());
		return (count == 0);
	}
	public void processIt(String docAction) {
		if(DocAction.ACTION_Prepare.equals(docAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(DocAction.ACTION_Complete.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			MTyre tyre = new MTyre(getCtx(), getTF_Tyre_ID(), get_TrxName());
			//Release Tyre
			if(getTyreAssignmentType().equals(TYREASSIGNMENTTYPE_ReleaseToStock) ||
					getTyreAssignmentType().equals(TYREASSIGNMENTTYPE_ReleaseAndAssign)) {					
				
				if(getRD_TF_TyreMovement_ID()==0) {
					throw new AdempiereException("Invalid Tyre Release!");
				}
				
				//Update Tyre Movement with End Meter and Date
				MTyreMovement tmov = new MTyreMovement(getCtx(), getRD_TF_TyreMovement_ID(), get_TrxName());
				tmov.setEnd_Meter(getRD_End_Meter());
				tmov.setEndDate(getRD_ReleasedDate());
				tmov.setRunning_Meter(getRD_Running_Meter());
				tmov.saveEx();
				
				updateTyreLifeRunningMeter();
				
				//Update Tyre Status info
				if(isLatestReleasedTyreMovement()) {
					tyre.setTF_TyreStatus_ID(MTyreStatus.getTyreStatus(MTyreStatus.TYRESTATUSTYPE_Stocked));				
					tyre.setMounted_To_ID(0);
					tyre.setTF_TyrePosition_ID(0);
				}
				tyre.calcRunningMeter();
				
			}
			
			//Assign Tyre
			if(getTyreAssignmentType().equals(TYREASSIGNMENTTYPE_AssignFromStock) || 
					getTyreAssignmentType().equals(TYREASSIGNMENTTYPE_ReleaseAndAssign)) {
				//Create Tyre Movement
				MTyreMovement tmov = new MTyreMovement(getCtx(), 0, get_TrxName());
				tmov.setTF_Tyre_ID(getTF_Tyre_ID());
				tmov.setTF_TyreType_ID(getTF_TyreType_ID());
				tmov.setMovementDate(getAD_MovementDate());
				tmov.setVehicle_ID(getAD_To_Vehicle_ID());
				tmov.setTF_TyrePosition_ID(getAD_TF_TyrePosition_ID());
				tmov.setStart_Meter(getAD_Start_Meter());
				tmov.setRunning_Meter(BigDecimal.ZERO);
				tmov.saveEx();
				
				setAD_TF_TyreMovement_ID(tmov.getTF_TyreMovement_ID());
				
				//Update Tyre Status info
				if(isLatestAssignedTyreMovement()) {
					tyre.setTF_TyreStatus_ID(MTyreStatus.getTyreStatus(MTyreStatus.TYRESTATUSTYPE_Running));
					tyre.setMounted_To_ID(tmov.getVehicle_ID());
					tyre.setTF_TyrePosition_ID(tmov.getTF_TyrePosition_ID());
				}
				tyre.calcRunningMeter();				
			}
			tyre.saveEx();
		}
	}
	
	public void reverseIt() {
		setProcessed(false);
		setDocStatus(DOCSTATUS_Drafted);
		
		MTyre tyre = new MTyre(getCtx(), getTF_Tyre_ID(), get_TrxName());
		
		//Reverse Assign Tyre Entry
		if(getAD_TF_TyreMovement_ID() > 0) {
			//Assigned Tyre Movement
			MTyreMovement tMov = new MTyreMovement(getCtx(),getAD_TF_TyreMovement_ID() , get_TrxName());
			if(tMov.getEndDate() != null) {
				throw new AdempiereException("You cannot reverse the assigned tyre entry until you reverse the released tyre entry!");
			}
			setAD_TF_TyreMovement_ID(0);
			tMov.delete(true);
			
			
			//Reverse Tyre Status info
			if(isLatestAssignedTyreMovement()) {
				tyre.setTF_TyreStatus_ID(MTyreStatus.getTyreStatus(MTyreStatus.TYRESTATUSTYPE_Stocked));				
				tyre.setMounted_To_ID(0);
				tyre.setTF_TyrePosition_ID(0);
			}
			tyre.calcRunningMeter();
		}
		
		//Reverse Release Tyre Entry
		if(getRD_TF_TyreMovement_ID() > 0) {						
			//Reverse TyreMovement
			MTyreMovement tMov = new MTyreMovement(getCtx(),getRD_TF_TyreMovement_ID() , get_TrxName());
			tMov.setEnd_Meter(null);
			tMov.setRunning_Meter(BigDecimal.ZERO);
			tMov.setEndDate(null);
			tMov.saveEx();
			
			//Reverse Actual Running Meter in Tyre Life Record			
			updateTyreLifeRunningMeter();		
			
			//Reverse Tyre Status info
			if(isLatestReleasedTyreMovement()) {
				tyre.setTF_TyreStatus_ID(MTyreStatus.getTyreStatus(MTyreStatus.TYRESTATUSTYPE_Running));				
				tyre.setMounted_To_ID(getRD_From_Vehicle_ID());
				tyre.setTF_TyrePosition_ID(getRD_TF_TyrePosition_ID());
			}
			tyre.calcRunningMeter();
		}		
		tyre.saveEx();
		
	}
}
