package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

public class MCrusherProductionConfig extends X_TF_CrusherProduction_Config {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2432513476309373042L;

	public MCrusherProductionConfig(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCrusherProductionConfig(Properties ctx,int TF_CrusherProduction_Config_ID, String trxName) {
		super(ctx, TF_CrusherProduction_Config_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public static MCrusherProductionConfig[] getMCrusherProductionConfig(Properties ctx, String TF_BlueMetal_Type) {
		String where = " TF_BlueMetal_Type=?";
		List<MCrusherProductionConfig> prodConfigs = new Query(ctx, Table_Name, where, null)
		.setClient_ID().setParameters(TF_BlueMetal_Type).setOnlyActiveRecords(true)
		.setOrderBy("TF_CrusherProduction_Config_ID").list();	
		MCrusherProductionConfig configs[] = new MCrusherProductionConfig[prodConfigs.size()];
		return prodConfigs.toArray(configs);
	}

}
