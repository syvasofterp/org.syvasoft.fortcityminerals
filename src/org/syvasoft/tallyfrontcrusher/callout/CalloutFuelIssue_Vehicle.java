package org.syvasoft.tallyfrontcrusher.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MFuelIssue;
import org.syvasoft.tallyfrontcrusher.model.MJobworkAssignedVehicle;

public class CalloutFuelIssue_Vehicle implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		String IssueType = (String) mTab.getValue(MFuelIssue.COLUMNNAME_IssueType);
		
		if(IssueType.equals(MFuelIssue.ISSUETYPE_Payment))
			return null;
		
		int Vehicle_ID = 0;
		int AD_Org_ID = (int) mTab.getValue(MFuelIssue.COLUMNNAME_AD_Org_ID);
		if(mTab.getValue(MFuelIssue.COLUMNNAME_Vehicle_ID) != null) 
			Vehicle_ID = (int) mTab.getValue(MFuelIssue.COLUMNNAME_Vehicle_ID);
		MJobworkAssignedVehicle jwVehicle = MJobworkAssignedVehicle.getJobwork(AD_Org_ID, Vehicle_ID);		
		if(jwVehicle != null) {
			mTab.setValue(MFuelIssue.COLUMNNAME_C_Project_ID, jwVehicle.getC_Project_ID());
		}
		else {
			mTab.setValue(MFuelIssue.COLUMNNAME_C_Project_ID, null);
		}
		return null;
	}

}
