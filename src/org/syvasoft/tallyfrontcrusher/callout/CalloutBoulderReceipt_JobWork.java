package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.TF_MProject;

public class CalloutBoulderReceipt_JobWork implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		
		//1. Fill Subcontractor
		//2. Fill Jobwork Standard price
		int C_BPartner_ID = 0;
		BigDecimal stdPrice = BigDecimal.ZERO;
		
		if(value != null) {
			int C_Project_ID = (int) value;
			TF_MProject jobwork = new TF_MProject(ctx, C_Project_ID, null);
			stdPrice = jobwork.getUnit_Price();
			C_BPartner_ID = jobwork.getC_BPartner_ID();
		}
		
		mTab.setValue(MBoulderReceipt.COLUMNNAME_Subcontractor_ID, C_BPartner_ID);
		mTab.setValue(MBoulderReceipt.COLUMNNAME_Jobwork_StdPrice, stdPrice);
		
		return null;
	}

}
