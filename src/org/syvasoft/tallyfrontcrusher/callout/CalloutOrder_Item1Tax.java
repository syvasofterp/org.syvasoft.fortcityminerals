package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.model.MProductPricing;
import org.compiere.model.MTax;
import org.compiere.model.M_Registration;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.MRentedVehicle;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;


public class CalloutOrder_Item1Tax implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {		
		if(value != null){
			BigDecimal unitPrice = (BigDecimal) mTab.getValue(TF_MOrder.COLUMNNAME_Item1_UnitPrice);
			
			if(((boolean)mTab.getValue(TF_MOrder.COLUMNNAME_IsTaxIncluded)) == true) {
				BigDecimal priceExcludesTax;
				int tax_ID = (int) mTab.getValue(TF_MOrder.COLUMNNAME_Item1_Tax_ID);
				MTax tax = new MTax(ctx, tax_ID, null);
				BigDecimal taxRate = (BigDecimal)tax.getRate();
				BigDecimal hundred = new BigDecimal("100");
				
				priceExcludesTax = unitPrice.divide(BigDecimal.ONE.add(taxRate.divide(hundred,2,RoundingMode.HALF_UP)), 2, RoundingMode.HALF_UP);
				mTab.setValue(TF_MOrder.COLUMNNAME_Item1_UnitPrice, priceExcludesTax);
			}
			else {
				BigDecimal price = BigDecimal.ZERO;		
				
				if((mField.getColumnName().equals(TF_MOrder.COLUMNNAME_Item1_ID) || mField.getColumnName().equals(TF_MOrder.COLUMNNAME_IsTaxIncluded)) && value != null
						&& mTab.getValue(TF_MOrder.COLUMNNAME_C_BPartner_ID) != null
						&& mTab.getValue(TF_MOrder.COLUMNNAME_DateAcct) != null
						&& mTab.getValue(TF_MOrder.COLUMNNAME_M_PriceList_ID) != null) {
					int bPartner_ID = (int) mTab.getValue(TF_MOrder.COLUMNNAME_C_BPartner_ID);
					Timestamp dateAcct = (Timestamp) mTab.getValue(TF_MOrder.COLUMNNAME_DateAcct);
					int product_ID = (int) mTab.getValue(TF_MOrder.COLUMNNAME_Item1_ID);
					int priceList_ID = (int) mTab.getValue(TF_MOrder.COLUMNNAME_M_PriceList_ID);
					boolean isSOTrx = Env.getContext(ctx, WindowNo, "IsSOTrx").equals("Y");
					BigDecimal qty = (BigDecimal) mTab.getValue(TF_MOrder.COLUMNNAME_Item1_Qty);
					
					MProductPricing pp = TF_MOrder.getProductPricing(product_ID, priceList_ID, bPartner_ID, qty, dateAcct, isSOTrx);
					price = pp.getPriceStd();
					if(price == null)
						price = BigDecimal.ZERO;
					mTab.setValue(TF_MOrder.COLUMNNAME_Item1_Price, price);
					mTab.setValue(TF_MOrder.COLUMNNAME_Item1_UnitPrice, price);
					if(qty != null)
						mTab.setValue(TF_MOrder.COLUMNNAME_Item1_Amt, price.multiply(qty).divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP));
				}
			}
				
		}
		return null;
	}
}
