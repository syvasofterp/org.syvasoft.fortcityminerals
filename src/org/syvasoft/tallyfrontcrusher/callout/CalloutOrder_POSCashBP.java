package org.syvasoft.tallyfrontcrusher.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBPartner;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;

public class CalloutOrder_POSCashBP implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		if(value != null) {
			int bPartnerID = (int) value;
			String sql = "SELECT IsPOSCashBP FROM C_BPartner WHERE C_BPartner_ID = " + bPartnerID;
			String isPOSCashBP = DB.getSQLValueString(null, sql);
			Env.setContext(ctx, WindowNo, "IsPOSCashBP", isPOSCashBP);
			if(isPOSCashBP.equals("Y"))
				mTab.setValue(TF_MOrder.COLUMNNAME_PaymentRule, TF_MOrder.PAYMENTRULE_Cash);
		}
		return null;
	}

}
