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
		BigDecimal Distance=BigDecimal.ZERO;
		BigDecimal RentAmt=BigDecimal.ZERO;
		int TF_Destination_ID = 0;
		int AD_Org_ID = 0;
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
				if(mTab.getValue(TF_MOrder.COLUMNNAME_TF_RentedVehicle_ID)!=null) {
					Boolean IsLumpSumRent=mTab.getValueAsBoolean(TF_MOrder.COLUMNNAME_IsLumpSumRent);
					if(IsLumpSumRent) {
						TF_VehicleType_ID = (int)mTab.getValue(TF_MOrder.COLUMNNAME_Item1_VehicleType_ID);
						TF_Destination_ID = (int)mTab.getValue(TF_MOrder.COLUMNNAME_TF_Destination_ID);
						AD_Org_ID = (int)mTab.getValue(TF_MOrder.COLUMNNAME_AD_Org_ID);
						Distance = (BigDecimal)mTab.getValue(TF_MOrder.COLUMNNAME_Distance); 
						RentAmt=MLumpSumRentConfig.getLumpSumRent(ctx,AD_Org_ID, TF_Destination_ID, TF_VehicleType_ID, Distance, null);
						mTab.setValue(TF_MOrder.COLUMNNAME_Rent_Amt, RentAmt);						
					}
					
				}
			}

		}
		return null;
	}

}
