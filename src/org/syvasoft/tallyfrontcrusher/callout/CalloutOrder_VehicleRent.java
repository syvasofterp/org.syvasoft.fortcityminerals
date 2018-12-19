package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MLumpSumRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRentConfig;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;

public class CalloutOrder_VehicleRent implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		int TF_VehicleType_ID=0;
		BigDecimal Distance=BigDecimal.ZERO;
		BigDecimal RentAmt=BigDecimal.ZERO;
		int TF_Destination_ID = 0;
		int AD_Org_ID = 0;
		int TF_RentedVehicle_ID=0;

		if(mTab.getValue(TF_MOrder.COLUMNNAME_AD_Org_ID) != null) {
			AD_Org_ID = (int) mTab.getValue(TF_MOrder.COLUMNNAME_AD_Org_ID);
		}
		if(mTab.getValue(TF_MOrder.COLUMNNAME_TF_Destination_ID) != null) {
			TF_Destination_ID = (int) mTab.getValue(TF_MOrder.COLUMNNAME_TF_Destination_ID);
		}
		if(mTab.getValue(TF_MOrder.COLUMNNAME_Item1_VehicleType_ID) != null) {
			TF_VehicleType_ID = (int) mTab.getValue(TF_MOrder.COLUMNNAME_Item1_VehicleType_ID);
		}
		if(mTab.getValue(TF_MOrder.COLUMNNAME_Distance) != null) {
			Distance = (BigDecimal) mTab.getValue(TF_MOrder.COLUMNNAME_Distance);
		}

		if(mTab.getValue(TF_MOrder.COLUMNNAME_TF_RentedVehicle_ID) != null) {
			TF_RentedVehicle_ID = (int) mTab.getValue(TF_MOrder.COLUMNNAME_TF_RentedVehicle_ID);
		}

		if(TF_RentedVehicle_ID>0) {
			BigDecimal RateKm=MLumpSumRentConfig.getRateKm(ctx, AD_Org_ID, TF_Destination_ID, TF_VehicleType_ID, Distance, null);
			mTab.setValue(TF_MOrder.COLUMNNAME_Rate, RateKm);
			
			if(RateKm.compareTo(BigDecimal.ZERO)==1) {
				mTab.setValue(TF_MOrder.COLUMNNAME_IsLumpSumRent, false);
			}
			else {
				mTab.setValue(TF_MOrder.COLUMNNAME_IsLumpSumRent, true);
			}
			RentAmt=MLumpSumRentConfig.getLumpSumRent(ctx,AD_Org_ID, TF_Destination_ID, TF_VehicleType_ID, Distance, null);
			mTab.setValue(TF_MOrder.COLUMNNAME_Rent_Amt, RentAmt);						
		}
		return null;
	}

}
