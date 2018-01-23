package org.syvasoft.tallyfrontcrusher.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MUser;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MJobworkAssignedBPartner;
import org.syvasoft.tallyfrontcrusher.model.MJobworkAssignedEmployee;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MPayment;

public class CalloutPayment_TFBPartner implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		int bPartnerID = 0;		
		boolean isEmployee = false;
		if(mTab.getValue(TF_MPayment.COLUMNNAME_TF_BPartner_ID) != null) {
			bPartnerID = (int) mTab.getValue(TF_MPayment.COLUMNNAME_TF_BPartner_ID);
			TF_MBPartner bp = new TF_MBPartner(ctx, bPartnerID, null);
			isEmployee = bp.isEmployee();
			if(isEmployee) {
				int AD_Org_ID = (int) mTab.getValue(TF_MPayment.COLUMNNAME_AD_Org_ID);
				MJobworkAssignedEmployee jwEmp = MJobworkAssignedEmployee.getJobwork(AD_Org_ID, bPartnerID);
				if(jwEmp != null) {
					mTab.setValue(TF_MPayment.COLUMNNAME_C_Project_ID, jwEmp.getC_Project_ID());
				}
				else {
					mTab.setValue(TF_MPayment.COLUMNNAME_C_Project_ID, null);
				}
			}
			else {
				int AD_Org_ID = (int) mTab.getValue(TF_MPayment.COLUMNNAME_AD_Org_ID);
				MJobworkAssignedBPartner jwBP = MJobworkAssignedBPartner.getJobwork(AD_Org_ID, bPartnerID);
				if(jwBP != null) {
					mTab.setValue(TF_MPayment.COLUMNNAME_C_Project_ID, jwBP.getC_Project_ID());
				}
				else {
					mTab.setValue(TF_MPayment.COLUMNNAME_C_Project_ID, null);
				}
			}
		}
		else {
			MUser user = MUser.get(ctx, Env.getAD_User_ID(ctx));				
			bPartnerID = user.getC_BPartner_ID();			
		}
		mTab.setValue(TF_MPayment.COLUMNNAME_IsEmployee, isEmployee);
		mTab.setValue(TF_MPayment.COLUMNNAME_C_BPartner_ID, bPartnerID);
				
		return null;
	}

}
