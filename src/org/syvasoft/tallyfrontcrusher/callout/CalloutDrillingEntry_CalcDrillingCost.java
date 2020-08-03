package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MDrillingEntry;

public class CalloutDrillingEntry_CalcDrillingCost implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		BigDecimal holes = BigDecimal.ZERO;
		BigDecimal feet = BigDecimal.ZERO;
		BigDecimal rate = BigDecimal.ZERO;
		holes = (BigDecimal) mTab.getValue(MDrillingEntry.COLUMNNAME_Holes);
		feet = (BigDecimal) mTab.getValue(MDrillingEntry.COLUMNNAME_Feet);
		rate = (BigDecimal) mTab.getValue(MDrillingEntry.COLUMNNAME_FeetRate);
		mTab.setValue(MDrillingEntry.COLUMNNAME_TotalFeet, holes.multiply(feet));
		mTab.setValue(MDrillingEntry.COLUMNNAME_DrillingCost, holes.multiply(feet).multiply(rate));
		return null;
	}

}
