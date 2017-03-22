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
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryAdvance;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryIssue;
import org.syvasoft.tallyfrontcrusher.model.MFuelIssue;
import org.syvasoft.tallyfrontcrusher.model.MGLPostingConfig;
import org.syvasoft.tallyfrontcrusher.model.MLabourWage;
import org.syvasoft.tallyfrontcrusher.model.MLabourWageAdvance;
import org.syvasoft.tallyfrontcrusher.model.MLabourWageConfig;
import org.syvasoft.tallyfrontcrusher.model.MLabourWageIssue;
import org.syvasoft.tallyfrontcrusher.model.MQuarry;
import org.syvasoft.tallyfrontcrusher.model.MQuarryRent;
import org.syvasoft.tallyfrontcrusher.model.MQuarryRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRent;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRentalContract;
import org.syvasoft.tallyfrontcrusher.model.MVehicleType;
import org.syvasoft.tallyfrontcrusher.model.TF_MCharge;
import org.syvasoft.tallyfrontcrusher.model.TF_MInvoice;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MPayment;
import org.syvasoft.tallyfrontcrusher.model.TF_MResource;
import org.syvasoft.tallyfrontcrusher.model.TF_MResourceType;

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
		else if (TF_MInvoice.Table_Name.equals(tableName))
			return TF_MInvoice.class;
		else if (TF_MOrder.Table_Name.equals(tableName))			
			return TF_MOrder.class;
		else if (MFuelIssue.Table_Name.equals(tableName))
			return MFuelIssue.class;
		else if (MLabourWage.Table_Name.equals(tableName))
			return MLabourWage.class;
		else if (MLabourWageConfig.Table_Name.equals(tableName))			
			return MLabourWageConfig.class;
		else if (TF_MPayment.Table_Name.equals(tableName))
			return TF_MPayment.class;
		else if (TF_MCharge.Table_Name.equals(tableName))
			return TF_MCharge.class;
		else if (MLabourWageAdvance.Table_Name.equals(tableName))
			return MLabourWageAdvance.class;
		else if (MEmployeeSalaryAdvance.Table_Name.equals(tableName))
			return MEmployeeSalaryAdvance.class;
		else if (MLabourWageIssue.Table_Name.equals(tableName))
			return MLabourWageIssue.class;
		else if (MEmployeeSalaryIssue.Table_Name.equals(tableName))
			return MEmployeeSalaryIssue.class;
		else if (TF_MResourceType.Table_Name.equals(tableName))
			return TF_MResourceType.class;
		else if (TF_MResource.Table_Name.equals(tableName))
			return TF_MResource.class;
		else if (MVehicleRentalContract.Table_Name.equals(tableName))
			return MVehicleRentalContract.class;		
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
		else if (TF_MInvoice.Table_Name.equals(tableName))
			return new TF_MInvoice(ctx, Record_ID, trxName);
		else if (TF_MOrder.Table_Name.equals(tableName))
			return new TF_MOrder(ctx, Record_ID, trxName);
		else if (MFuelIssue.Table_Name.equals(tableName))
			return new MFuelIssue(ctx, Record_ID, trxName);
		else if (MLabourWage.Table_Name.equals(tableName))			
			return new MLabourWage(ctx, Record_ID, trxName);
		else if (MLabourWageConfig.Table_Name.equals(tableName))
			return new MLabourWageConfig(ctx, Record_ID, trxName);
		else if (TF_MPayment.Table_Name.equals(tableName))
			return new TF_MPayment(ctx, Record_ID, trxName);
		else if (TF_MCharge.Table_Name.equals(tableName))
			return new TF_MCharge(ctx, Record_ID, trxName);
		else if (MLabourWageAdvance.Table_Name.equals(tableName))
			return new MLabourWageAdvance(ctx, Record_ID, trxName);
		else if (MEmployeeSalaryAdvance.Table_Name.equals(tableName))
			return new MEmployeeSalaryAdvance(ctx, Record_ID, trxName);
		else if (MLabourWageIssue.Table_Name.equals(tableName))
			return new MLabourWageIssue(ctx, Record_ID, trxName);
		else if (MEmployeeSalaryIssue.Table_Name.equals(tableName))
			return new MEmployeeSalaryIssue(ctx, Record_ID, trxName);
		else if (TF_MResourceType.Table_Name.equals(tableName))
			return new TF_MResourceType(ctx, Record_ID, trxName);
		else if (TF_MResource.Table_Name.equals(tableName))
			return new TF_MResource(ctx, Record_ID, trxName);
		else if (MVehicleRentalContract.Table_Name.equals(tableName))
			return new MVehicleRentalContract(ctx, Record_ID, trxName);
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
		else if (TF_MInvoice.Table_Name.equals(tableName))
			return new TF_MInvoice(ctx, rs, trxName);
		else if (TF_MOrder.Table_Name.equals(tableName))
			return new TF_MOrder(ctx, rs, trxName);
		else if (MFuelIssue.Table_Name.equals(tableName))
			return new MFuelIssue(ctx, rs, trxName);
		else if (MLabourWage.Table_Name.equals(tableName))			
			return new MLabourWage(ctx, rs, trxName);
		else if (MLabourWageConfig.Table_Name.equals(tableName))
			return new MLabourWageConfig(ctx, rs, trxName);
		else if (TF_MPayment.Table_Name.equals(tableName))
			return new TF_MPayment(ctx, rs, trxName);
		else if (TF_MCharge.Table_Name.equals(tableName))
			return new TF_MCharge(ctx, rs, trxName);
		else if (MLabourWageAdvance.Table_Name.equals(tableName))
			return new MLabourWageAdvance(ctx, rs, trxName);
		else if (MEmployeeSalaryAdvance.Table_Name.equals(tableName))
			return new MEmployeeSalaryAdvance(ctx, rs, trxName);
		else if (MLabourWageIssue.Table_Name.equals(tableName))
			return new MLabourWageIssue(ctx, rs, trxName);
		else if (MEmployeeSalaryIssue.Table_Name.equals(tableName))
			return new MEmployeeSalaryIssue(ctx, rs, trxName);
		else if (TF_MResourceType.Table_Name.equals(tableName))
			return new TF_MResourceType(ctx, rs, trxName);
		else if (TF_MResource.Table_Name.equals(tableName))
			return new TF_MResource(ctx, rs, trxName);
		else if (MVehicleRentalContract.Table_Name.equals(tableName))
			return new MVehicleRentalContract(ctx, rs, trxName);
		return null;
	}

}
