package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.syvasoft.tallyfrontcrusher.model.MTyreAssignment;

public class CalloutTyreAssignment implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		int tyreID = 0;
		int tyreTypeID = 0;
		//Release Tyre Fields
		int fromVehicleID = 0;
		int fromTyrePositionID = 0;
		Timestamp assignedDate = null;
		BigDecimal releasedStartMeter = BigDecimal.ZERO;
		BigDecimal releasedEndMeter = BigDecimal.ZERO;
		//Assign Tyre Fields
		Timestamp movementDate = null;
		int toVehicleID = 0;
		int toTyrePositionID = 0;
		BigDecimal assignedStartMeter = null;
		
		if(mTab.getValue(MTyreAssignment.COLUMNNAME_TF_Tyre_ID) !=null) {
			tyreID = (int) mTab.getValue(MTyreAssignment.COLUMNNAME_TF_Tyre_ID);
			tyreTypeID = DB.getSQLValue(null, "SELECT COALESCE(Current_TyreType_ID,0) FROM TF_Tyre WHERE TF_Tyre_ID = "+ tyreID);			
		}
		
		mTab.setValue(MTyreAssignment.COLUMNNAME_TF_TyreType_ID, tyreTypeID);
		
		return null;
	}

}
