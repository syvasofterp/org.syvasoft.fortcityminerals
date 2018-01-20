package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MFuelIssue;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CalloutFuelIssue_SetPrice implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		String issueType = (String) mTab.getValue(MFuelIssue.COLUMNNAME_IssueType);
		if(issueType.equals(MFuelIssue.ISSUETYPE_Payment)) {
			mTab.setValue(MFuelIssue.COLUMNNAME_Rate, BigDecimal.ZERO);
			return null;
		}
		
		int AD_Org_ID = (int) mTab.getValue(MFuelIssue.COLUMNNAME_AD_Org_ID);
		int M_Product_ID = 0;
		if(mTab.getValue(MFuelIssue.COLUMNNAME_M_Product_ID) != null)
			M_Product_ID = (int) mTab.getValue(MFuelIssue.COLUMNNAME_M_Product_ID);
		else
			return null;
		BigDecimal currentCost = TF_MProduct.getCurrentCost(AD_Org_ID, M_Product_ID);
		mTab.setValue(MFuelIssue.COLUMNNAME_Rate, currentCost);
		return null;
	}

}
