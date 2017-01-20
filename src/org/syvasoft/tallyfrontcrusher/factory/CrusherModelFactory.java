package org.syvasoft.tallyfrontcrusher.factory;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MEmpSalaryConfig;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalary;
import org.syvasoft.tallyfrontcrusher.model.MQuarry;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRent;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRentConfig;

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
		return null;
	}

}
