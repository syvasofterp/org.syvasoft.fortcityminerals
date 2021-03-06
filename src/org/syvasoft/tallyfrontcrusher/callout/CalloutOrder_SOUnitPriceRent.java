package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;

public class CalloutOrder_SOUnitPriceRent implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		boolean isSOTrx = Env.getContext(ctx, WindowNo, "IsSOTrx").equals("Y");
		
		if(isSOTrx) {
			BigDecimal unitPrice = (BigDecimal) mTab.getValue(TF_MOrder.COLUMNNAME_Item1_UnitPrice);			
			BigDecimal rentAmount = (BigDecimal) mTab.getValue(TF_MOrder.COLUMNNAME_Rent_Amt);
			BigDecimal qty = (BigDecimal) mTab.getValue(TF_MOrder.COLUMNNAME_Item1_Qty);
			if(qty.equals(BigDecimal.ZERO))
				qty = BigDecimal.ONE;
			BigDecimal unitRent = BigDecimal.ZERO;
			if(qty.doubleValue() != 0)
				unitRent = rentAmount.divide(qty,2,RoundingMode.HALF_UP);
			
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_UnitRent, unitRent);
			BigDecimal price = unitPrice;
			boolean isRentBreakup = mTab.getValueAsBoolean(TF_MOrder.COLUMNNAME_IsRentBreakup);
			boolean isRentInclusive = mTab.getValueAsBoolean(TF_MOrder.COLUMNNAME_IsRentInclusive);
			if(isRentInclusive) { 
				if(isRentBreakup) {
					price = unitPrice.subtract(unitRent);
				}
				else {
					price = unitPrice;
				}
			}
			else {
				if(isRentBreakup) {
					price = unitPrice;
				}
				else {
					price = unitPrice.add(unitRent);
				}
			}
			String orgType = (String) mTab.getValue(TF_MOrder.COLUMNNAME_OrgType);
			if(orgType.equals(TF_MOrder.ORGTYPE_SandBlockWeighbridge)) {
				MWeighmentEntry weighment = null;
				if(mTab.getValue(TF_MOrder.COLUMNNAME_TF_WeighmentEntry_ID) != null) {
					int weighment_id = (int) mTab.getValue(TF_MOrder.COLUMNNAME_TF_WeighmentEntry_ID);
					weighment = new MWeighmentEntry(ctx, weighment_id, null);
				}
				if(weighment != null && weighment.getPrice() != null) 
					price = weighment.getPrice();
			}
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_Price, price);		
		}
		return null;
	}

}
