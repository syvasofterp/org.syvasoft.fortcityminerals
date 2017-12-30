package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MSandBlockBucketConfig;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;

public class CalloutOrder_SandBlockLine1 implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		String orgType  = (String) mTab.getValue(TF_MOrder.COLUMNNAME_OrgType);
		if(!orgType.equals(TF_MOrder.ORGTYPE_SandBlock))
			return null;
		
		if(mTab.getValue(TF_MOrder.COLUMNNAME_C_BPartner_ID) == null)
			return null;
		
		boolean isPermitSales = mTab.getValueAsBoolean(TF_MOrder.COLUMNNAME_Item1_IsPermitSales);
		int orgID = (int) mTab.getValue(TF_MOrder.COLUMNNAME_AD_Org_ID) ;
		if(isPermitSales) {
			MSandBlockBucketConfig config = MSandBlockBucketConfig.getBucketConfig(orgID, MSandBlockBucketConfig.SANDTYPE_PermitSand);
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_ID, config.getM_Product_ID());
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_BucketRate, config.getSalesRatePerBucket());
			mTab.setValue(TF_MOrder.COLUMNNAME_TonePerBucket, config.getSalesTonnagePerBucket());
			
			config = MSandBlockBucketConfig.getBucketConfig(orgID, MSandBlockBucketConfig.SANDTYPE_ExtraPermit);
			mTab.setValue(TF_MOrder.COLUMNNAME_Item2_ID, config.getM_Product_ID());
			mTab.setValue(TF_MOrder.COLUMNNAME_Item2_BucketRate, config.getSalesRatePerBucket());
			mTab.setValue(TF_MOrder.COLUMNNAME_Item2_TonePerBucket, config.getSalesTonnagePerBucket());
									
		}
		else {
			MSandBlockBucketConfig config = MSandBlockBucketConfig.getBucketConfig(orgID, MSandBlockBucketConfig.SANDTYPE_WithoutPermit);
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_ID, config.getM_Product_ID());
			mTab.setValue(TF_MOrder.COLUMNNAME_Item1_BucketRate, config.getSalesRatePerBucket());
			mTab.setValue(TF_MOrder.COLUMNNAME_TonePerBucket, config.getSalesTonnagePerBucket());
		}
		//mTab.setValue(TF_MOrder.COLUMNNAME_Item2_ID, item1_id);
		//mTab.setValue(TF_MOrder.COLUMNNAME_Item2_Tax_ID, item1_tax);
		//mTab.setValue(TF_MOrder.COLUMNNAME_Item2_UOM_ID, item1_uom);
		//mTab.setValue(TF_MOrder.COLUMNNAME_Item2_Price, unitPrice);		
		
		return null;
	}

}
