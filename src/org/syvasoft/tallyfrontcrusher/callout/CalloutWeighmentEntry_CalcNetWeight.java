package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;

public class CalloutWeighmentEntry_CalcNetWeight implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		BigDecimal netWeight = BigDecimal.ZERO;
		BigDecimal tareWeight = BigDecimal.ZERO; 
		if(mTab.getValue(MWeighmentEntry.COLUMNNAME_TareWeight) != null)
			tareWeight = (BigDecimal) mTab.getValue(MWeighmentEntry.COLUMNNAME_TareWeight);
			
		BigDecimal grossWeight = BigDecimal.ZERO; 
		if(mTab.getValue(MWeighmentEntry.COLUMNNAME_GrossWeight) != null)
			grossWeight = (BigDecimal) mTab.getValue(MWeighmentEntry.COLUMNNAME_GrossWeight);
		
		if(tareWeight.doubleValue() > 0 && grossWeight.doubleValue() > 0)
			netWeight = grossWeight.subtract(tareWeight);
		
		mTab.setValue(MWeighmentEntry.COLUMNNAME_NetWeight, netWeight);
		
		return null;
	}
	 
}
