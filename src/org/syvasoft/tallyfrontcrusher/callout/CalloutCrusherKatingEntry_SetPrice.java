package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MCrusherKatingEntry;
import org.syvasoft.tallyfrontcrusher.model.MRentedVehicle;

public class CalloutCrusherKatingEntry_SetPrice implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		BigDecimal TransportPrice = BigDecimal.ZERO;
		BigDecimal LoadingPrice = BigDecimal.ZERO;
		MRentedVehicle rv = null;
		if(mTab.getValue(MCrusherKatingEntry.COLUMNNAME_LoaderVehicle_ID) != null) {
			int loader_ID = (int) mTab.getValue(MCrusherKatingEntry.COLUMNNAME_LoaderVehicle_ID);
			rv = new MRentedVehicle(ctx, loader_ID, null);
			LoadingPrice = rv.getUnitPrice();			
		}
		
		if(mTab.getValue(MCrusherKatingEntry.COLUMNNAME_TF_RentedVehicle_ID) != null) {
			int vehicle_ID = (int) mTab.getValue(MCrusherKatingEntry.COLUMNNAME_TF_RentedVehicle_ID);
			rv = new MRentedVehicle(ctx, vehicle_ID, null);			
			TransportPrice = rv.getUnitPrice();
			
			BigDecimal tonnage = (BigDecimal) mTab.getValue(MCrusherKatingEntry.COLUMNNAME_Tonnage);
			if(tonnage.equals(BigDecimal.ZERO))
				mTab.setValue(MCrusherKatingEntry.COLUMNNAME_Tonnage, rv.getTonnagePerLoad());			
			
		}
		
		mTab.setValue(MCrusherKatingEntry.COLUMNNAME_Loading_Price, LoadingPrice);
		mTab.setValue(MCrusherKatingEntry.COLUMNNAME_Transport_Price, TransportPrice);
		return null;
	}

}
