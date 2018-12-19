package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MDriverBetaConfig;
import org.syvasoft.tallyfrontcrusher.model.MLumpSumRentConfig;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;

public class CalloutOrder_VehicleType implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		boolean isSOTrx = Env.getContext(ctx, WindowNo, "IsSOTrx").equals("Y");
		int TF_VehicleType_ID=0;

		if(mTab.getValue(TF_MOrder.COLUMNNAME_Item1_VehicleType_ID) != null) {

			if(mTab.getValue(TF_MOrder.COLUMNNAME_VehicleNo)!="" && mTab.getValue(TF_MOrder.COLUMNNAME_Vehicle_ID)==null && isSOTrx)
			{
				TF_VehicleType_ID=(int)mTab.getValue(TF_MOrder.COLUMNNAME_Item1_VehicleType_ID);
				BigDecimal betaAmt= MDriverBetaConfig.getDriverBetaAmount(ctx, Env.getAD_Org_ID(ctx),TF_VehicleType_ID, null);
				mTab.setValue(TF_MOrder.COLUMNNAME_DriverTips, betaAmt);
			}
			else
			{
				mTab.setValue(TF_MOrder.COLUMNNAME_DriverTips, BigDecimal.ZERO);
			}

		}
		return null;
	}

}
