package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MProductPrice;
import org.compiere.model.MProductPricing;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;

public class CalloutOrder_UnitPrice implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		BigDecimal price = BigDecimal.ZERO;		
		
		if(mTab.getValue(TF_MOrder.COLUMNNAME_Item1_ID)!=null && value != null
				&& mTab.getValue(TF_MOrder.COLUMNNAME_C_BPartner_ID) != null
				&& mTab.getValue(TF_MOrder.COLUMNNAME_DateAcct) != null
				&& mTab.getValue(TF_MOrder.COLUMNNAME_M_PriceList_ID) != null
				&& mTab.getValue(TF_MOrder.COLUMNNAME_Item1_UnitPrice) != null
				&& mTab.getValue(TF_MOrder.COLUMNNAME_Item1_UOM_ID) != null
				&& mTab.getValue(TF_MOrder.COLUMNNAME_PaymentRule).equals(TF_MOrder.PAYMENTRULE_Cash)) {
		
			price=(BigDecimal) mTab.getValue(TF_MOrder.COLUMNNAME_Item1_UnitPrice);
			int bPartner_ID = (int) mTab.getValue(TF_MOrder.COLUMNNAME_C_BPartner_ID);
			Timestamp dateAcct = (Timestamp) mTab.getValue(TF_MOrder.COLUMNNAME_DateAcct);
			int product_ID = (int) mTab.getValue(TF_MOrder.COLUMNNAME_Item1_ID);
			int priceList_ID = (int) mTab.getValue(TF_MOrder.COLUMNNAME_M_PriceList_ID);
			boolean isSOTrx = Env.getContext(ctx, WindowNo, "IsSOTrx").equals("Y");
			BigDecimal qty = (BigDecimal) mTab.getValue(TF_MOrder.COLUMNNAME_Item1_Qty);
			int C_UOM_ID=(int) mTab.getValue(TF_MOrder.COLUMNNAME_Item1_UOM_ID);
			
			MPriceListUOM priceUOM = MPriceListUOM.getPriceListUOM(ctx, product_ID, C_UOM_ID, bPartner_ID, isSOTrx, dateAcct);
			//MProductPricing pp = TF_MOrder.getProductPricing(product_ID, priceList_ID, bPartner_ID, qty, dateAcct, isSOTrx);
			if(price.compareTo(priceUOM.getPriceMin())<0){
				throw new AdempiereException("You cannot create sales order less than minimum price. Please create Discount Request");
			}
	   }
	return null;
  }
}
