package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MRentedVehicle;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MVehicleType;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;

public class CalloutOrder_RentedVehicle implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		BigDecimal rate = BigDecimal.ZERO;
		if(mTab.getValue(TF_MOrder.COLUMNNAME_TF_RentedVehicle_ID) == null) {
			mTab.setValue(TF_MOrder.COLUMNNAME_Rent_Amt, BigDecimal.ZERO);
			//mTab.setValue(TF_MOrder.COLUMNNAME_IsLumpSumRent, false);
			mTab.setValue(TF_MOrder.COLUMNNAME_RentMargin, BigDecimal.ZERO);
			mTab.setValue(TF_MOrder.COLUMNNAME_RentPayable, BigDecimal.ZERO);
			mTab.setValue(TF_MOrder.COLUMNNAME_VehicleNo, null);
			mTab.setValue(TF_MOrder.COLUMNNAME_IsRentBreakup, false);
		}
		if(mTab.getValue(TF_MOrder.COLUMNNAME_TF_RentedVehicle_ID) != null) {
			int vehicle_id = (int) mTab.getValue(TF_MOrder.COLUMNNAME_TF_RentedVehicle_ID);
			MRentedVehicle rv = new MRentedVehicle(ctx, vehicle_id, null);
			int TF_VehicleType_ID = (int) rv.get_ValueAsInt("TF_VehicleType_ID");
			mTab.setValue(TF_MOrder.COLUMNNAME_VehicleNo, rv.getVehicleNo());
			mTab.setValue(TF_MOrder.COLUMNNAME_IsRentBreakup, true);
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_VehicleType_ID, TF_VehicleType_ID);
			if(mTab.getValue(TF_MOrder.COLUMNNAME_TF_Destination_ID) != null) {
				int destination_id = (int) mTab.getValue(TF_MOrder.COLUMNNAME_TF_Destination_ID);
				rate = MVehicleRentConfig.getRate(vehicle_id, destination_id);
			}
			
		}		
		
		mTab.setValue(TF_MOrder.COLUMNNAME_Rate, rate);
		return null;
	}

}
