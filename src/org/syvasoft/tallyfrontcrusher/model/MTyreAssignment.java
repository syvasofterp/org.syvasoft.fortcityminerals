package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.DocAction;

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
				
				MTyreMovement tmov = new MTyreMovement(getCtx(), getRD_TF_TyreMovement_ID(), get_TrxName());
				tmov.setEnd_Meter(getRD_End_Meter());
				tmov.setEndDate(getRD_ReleasedDate());
				tmov.setRunning_Meter(getRD_Running_Meter());
				tmov.saveEx();
				
				
				tyre.setTF_TyreStatus_ID(MTyreStatus.getTyreStatus(MTyreStatus.TYRESTATUSTYPE_Stocked));
				tyre.setMounted_To(0);
				tyre.saveEx();
				
				
			}
			
			//Assign Tyre
			if(getTyreAssignmentType().equals(TYREASSIGNMENTTYPE_AssignFromStock) || 
					getTyreAssignmentType().equals(TYREASSIGNMENTTYPE_ReleaseAndAssign)) {
				MTyreMovement tmov = new MTyreMovement(getCtx(), 0, get_TrxName());
				tmov.setTF_Tyre_ID(getTF_Tyre_ID());
				tmov.setTF_TyreType_ID(getTF_TyreType_ID());
				tmov.setMovementDate(getAD_MovementDate());
				tmov.setVehicle_ID(getAD_To_Vehicle_ID());
				tmov.setTF_TyrePosition_ID(getAD_TF_TyrePosition_ID());
				tmov.setStart_Meter(getAD_Start_Meter());
				tmov.setRunning_Meter(BigDecimal.ZERO);
				tmov.saveEx();
				
				
				tyre.setTF_TyreStatus_ID(MTyreStatus.getTyreStatus(MTyreStatus.TYRESTATUSTYPE_Running));
				tyre.setMounted_To(tmov.getVehicle_ID());
				tyre.saveEx();
				
				setAD_TF_TyreMovement_ID(tmov.getTF_TyreMovement_ID());				
			}
			
		}
	}
	
	public void reverseIt() {
		
	}
}
