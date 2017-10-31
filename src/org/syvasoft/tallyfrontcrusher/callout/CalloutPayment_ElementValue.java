package org.syvasoft.tallyfrontcrusher.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.TF_MPayment;

public class CalloutPayment_ElementValue implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		if(mTab.getValue(TF_MPayment.COLUMNNAME_C_ElementValue_ID) != null)
			mTab.setValue(TF_MPayment.COLUMNNAME_C_Invoice_ID, 0);
		return null;
	}

}
