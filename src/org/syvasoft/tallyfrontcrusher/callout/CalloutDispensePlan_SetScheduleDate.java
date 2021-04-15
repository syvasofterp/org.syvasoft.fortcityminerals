package org.syvasoft.tallyfrontcrusher.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MSysConfig;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlan;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlanLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CalloutDispensePlan_SetScheduleDate implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		Env.setContext(Env.getCtx(), "#DPDate", mTab.get_ValueAsString(MDispensePlan.COLUMNNAME_ScheduleDate));	
		return null;
	}
	
	

}
