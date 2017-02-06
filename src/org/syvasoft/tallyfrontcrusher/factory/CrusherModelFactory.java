package org.syvasoft.tallyfrontcrusher.factory;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MCrusherProduction;
import org.syvasoft.tallyfrontcrusher.model.MCrusherProductionConfig;
import org.syvasoft.tallyfrontcrusher.model.MEmpSalaryConfig;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalary;
import org.syvasoft.tallyfrontcrusher.model.MGLPostingConfig;
import org.syvasoft.tallyfrontcrusher.model.MQuarry;
import org.syvasoft.tallyfrontcrusher.model.MQuarryRent;
import org.syvasoft.tallyfrontcrusher.model.MQuarryRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRent;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MVehicleType;

public class CrusherModelFactory implements IModelFactory {

	@Override
	public Class<?> getClass(String tableName) {
		if (MBoulderReceipt.Table_Name.equals(tableName))
			return MBoulderReceipt.class;
		else if (MQuarry.Table_Name.equals(tableName))
			return MQuarry.class;
		else if (MEmpSalaryConfig.Table_Name.equals(tableName))
			return MEmpSalaryConfig.class;
		else if (MEmployeeSalary.Table_Name.equals(tableName))
			return MEmployeeSalary.class;
		else if (MVehicleRentConfig.Table_Name.equals(tableName))
			return MVehicleRentConfig.class;
		else if (MVehicleRent.Table_Name.equals(tableName))
			return MVehicleRent.class;
		else if (MQuarryRentConfig.Table_Name.equals(tableName))
			return MQuarryRentConfig.class;
		else if (MQuarryRent.Table_Name.equals(tableName))
			return MQuarryRent.class;
		else if (MVehicleType.Table_Name.equals(tableName))
			return MVehicleType.class;
		else if (MGLPostingConfig.Table_Name.equals(tableName))
			return MGLPostingConfig.class;
		else if (MCrusherProduction.Table_Name.equals(tableName))
			return MCrusherProduction.class;
		else if (MCrusherProductionConfig.Table_Name.equals(tableName))
			return MCrusherProductionConfig.class;
		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		Properties ctx = Env.getCtx();
		if (MBoulderReceipt.Table_Name.equals(tableName))
			return new MBoulderReceipt(ctx, Record_ID, trxName);
		else if (MQuarry.Table_Name.equals(tableName))
			return new MQuarry(ctx, Record_ID, trxName);
		else if (MEmpSalaryConfig.Table_Name.equals(tableName))
			return new MEmpSalaryConfig(ctx, Record_ID, trxName);
		else if (MEmployeeSalary.Table_Name.equals(tableName))
			return new MEmployeeSalary(ctx, Record_ID, trxName);
		else if (MVehicleRentConfig.Table_Name.equals(tableName))
			return new MVehicleRentConfig(ctx, Record_ID, trxName);
		else if (MVehicleRent.Table_Name.equals(tableName))
			return new MVehicleRent(ctx, Record_ID, trxName);
		else if (MQuarryRentConfig.Table_Name.equals(tableName))
			return new MQuarryRentConfig(ctx, Record_ID, trxName);
		else if (MQuarryRent.Table_Name.equals(tableName))
			return new MQuarryRent(ctx, Record_ID, trxName);
		else if (MVehicleType.Table_Name.equals(tableName))
			return new MVehicleType(ctx, Record_ID, trxName);
		else if (MGLPostingConfig.Table_Name.equals(tableName))
			return new MGLPostingConfig(ctx, Record_ID, trxName);
		else if (MCrusherProduction.Table_Name.equals(tableName))
			return new MCrusherProduction(ctx, Record_ID, trxName);
		else if (MCrusherProductionConfig.Table_Name.equals(tableName))
			return new MCrusherProductionConfig(ctx, Record_ID, trxName);
		return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		Properties ctx = Env.getCtx();
		if (MBoulderReceipt.Table_Name.equals(tableName))
			return new MBoulderReceipt(ctx, rs, trxName);
		else if (MQuarry.Table_Name.equals(tableName))
			return new MQuarry(ctx, rs, trxName);
		else if (MEmpSalaryConfig.Table_Name.equals(tableName))
			return new MEmpSalaryConfig(ctx, rs, trxName);
		else if (MEmployeeSalary.Table_Name.equals(tableName))
			return new MEmployeeSalary(ctx, rs, trxName);
		else if (MVehicleRentConfig.Table_Name.equals(tableName))
			return new MVehicleRentConfig(ctx, rs, trxName);
		else if (MVehicleRent.Table_Name.equals(tableName))
			return new MVehicleRent(ctx, rs, trxName);
		else if (MQuarryRentConfig.Table_Name.equals(tableName))
			return new MQuarryRentConfig(ctx, rs, trxName);
		else if (MQuarryRent.Table_Name.equals(tableName))
			return new MQuarryRent(ctx, rs, trxName);
		else if (MVehicleType.Table_Name.equals(tableName))
			return new MVehicleType(ctx, rs, trxName);
		else if (MGLPostingConfig.Table_Name.equals(tableName))
			return new MGLPostingConfig(ctx, rs, trxName);
		else if (MCrusherProduction.Table_Name.equals(tableName))
			return new MCrusherProduction(ctx, rs, trxName);
		else if (MCrusherProductionConfig.Table_Name.equals(tableName))
			return new MCrusherProductionConfig(ctx, rs, trxName);
		return null;
	}

}
