package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MPriceList;
import org.compiere.model.MProductPricing;
import org.compiere.model.MSysConfig;
import org.compiere.model.MUser;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;

public class CalloutOrder_WeighmentEntry implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		boolean isSOTrx = Env.getContext(ctx, WindowNo, "IsSOTrx").equals("Y");
		
		if(mTab.getValue(TF_MOrder.COLUMNNAME_TF_WeighmentEntry_ID) != null) {
			int weighment_id = (int) mTab.getValue(TF_MOrder.COLUMNNAME_TF_WeighmentEntry_ID);
			MWeighmentEntry weighment = new MWeighmentEntry(ctx, weighment_id, null);
			mTab.setValue(TF_MOrder.COLUMNNAME_DateOrdered, weighment.getGrossWeightTime());
			mTab.setValue(TF_MOrder.COLUMNNAME_DateAcct, weighment.getGrossWeightTime());
			int bPartner_id = weighment.getC_BPartner_ID();
			mTab.setValue(TF_MOrder.COLUMNNAME_C_BPartner_ID, bPartner_id);
			mTab.setValue(TF_MOrder.COLUMNNAME_Bill_BPartner_ID, bPartner_id);
			MBPartnerLocation[] loc = MBPartnerLocation.getForBPartner(ctx, bPartner_id, null);
			MUser[] users = MUser.getOfBPartner(ctx, bPartner_id,null);
			if(users.length > 0) {
				mTab.setValue(TF_MOrder.COLUMNNAME_AD_User_ID, users[0].getAD_User_ID());
				mTab.setValue(TF_MOrder.COLUMNNAME_Bill_User_ID, users[0].getAD_User_ID());
			}
			if(loc.length > 0) {
				mTab.setValue(TF_MOrder.COLUMNNAME_C_BPartner_Location_ID, loc[0].getC_BPartner_Location_ID());
				mTab.setValue(TF_MOrder.COLUMNNAME_Bill_Location_ID, loc[0].getC_BPartner_Location_ID());
			}
			MBPartner bp = new MBPartner(ctx, bPartner_id, null);
			int priceList_id = Env.getContextAsInt(ctx, "#M_PriceList_ID");
			if(bp.getPO_PriceList_ID() > 0 && !isSOTrx) {
				priceList_id = bp.getPO_PriceList_ID();
			}
			else if( bp.getM_PriceList_ID() > 0 && isSOTrx) {
				priceList_id = bp.getM_PriceList_ID();
			}
			
			mTab.setValue(TF_MOrder.COLUMNNAME_M_PriceList_ID, priceList_id);
			MPriceList pl = new MPriceList(ctx, priceList_id, null);
			mTab.setValue(TF_MOrder.COLUMNNAME_C_Currency_ID, pl.getC_Currency_ID());

			int uom_id = weighment.getM_Product().getC_UOM_ID();
			int tonnage_uom_id = MSysConfig.getIntValue("TONNAGE_UOM", 1000069, Env.getAD_Client_ID(ctx));
			
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_ID, weighment.getM_Product_ID());
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_Tax_ID, Env.getContext(ctx, "#C_Tax_ID"));
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_UOM_ID, uom_id);
			
			BigDecimal qty = weighment.getNetWeight();
			
			if(uom_id == tonnage_uom_id) {
				qty = qty.divide(new BigDecimal(1000));
			}			
			
			MProductPricing pp = TF_MOrder.getProductPricing(weighment.getM_Product_ID(), priceList_id, bPartner_id,
					qty, weighment.getGrossWeightTime(), isSOTrx);
			BigDecimal price = pp.getPriceStd();
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_Qty, qty);
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_Price, price);			
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_Amt, price.multiply(qty));
			
			if(weighment.getTF_RentedVehicle_ID() > 0)
				mTab.setValue(TF_MOrder.COLUMNNAME_TF_RentedVehicle_ID, weighment.getTF_RentedVehicle_ID());
			
			if(weighment.getTF_Destination_ID() > 0)
				mTab.setValue(TF_MOrder.COLUMNNAME_TF_Destination_ID, weighment.getTF_Destination_ID());
			
		}
		return null;
	}

}
