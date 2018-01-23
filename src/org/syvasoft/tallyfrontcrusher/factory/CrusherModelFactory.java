package org.syvasoft.tallyfrontcrusher.factory;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MBPOpeningBalance;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MCOAOpeningBalance;
import org.syvasoft.tallyfrontcrusher.model.MCrusherProduction;
import org.syvasoft.tallyfrontcrusher.model.MCrusherProductionConfig;
import org.syvasoft.tallyfrontcrusher.model.MDestination;
import org.syvasoft.tallyfrontcrusher.model.MEmpSalaryConfig;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalary;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryAdvance;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryIssue;
import org.syvasoft.tallyfrontcrusher.model.MFuelIssue;
import org.syvasoft.tallyfrontcrusher.model.MGLPostingConfig;
import org.syvasoft.tallyfrontcrusher.model.MHomePageShortcuts;
import org.syvasoft.tallyfrontcrusher.model.MInterOrgCashTransfer;
import org.syvasoft.tallyfrontcrusher.model.MInvestmentReceipt;
import org.syvasoft.tallyfrontcrusher.model.MInvestmentStructure;
import org.syvasoft.tallyfrontcrusher.model.MItemReceiptOtherSrc;
import org.syvasoft.tallyfrontcrusher.model.MJobworkAssignedAccount;
import org.syvasoft.tallyfrontcrusher.model.MJobworkAssignedBPartner;
import org.syvasoft.tallyfrontcrusher.model.MJobworkAssignedEmployee;
import org.syvasoft.tallyfrontcrusher.model.MJobworkAssignedVehicle;
import org.syvasoft.tallyfrontcrusher.model.MJobworkCharges;
import org.syvasoft.tallyfrontcrusher.model.MJobworkExpense;
import org.syvasoft.tallyfrontcrusher.model.MJobworkExpenseEntry;
import org.syvasoft.tallyfrontcrusher.model.MJobworkIssuedItems;
import org.syvasoft.tallyfrontcrusher.model.MJobworkIssuedResource;
import org.syvasoft.tallyfrontcrusher.model.MJobworkItemIssue;
import org.syvasoft.tallyfrontcrusher.model.MJobworkProductPrice;
import org.syvasoft.tallyfrontcrusher.model.MJobworkReceivedItems;
import org.syvasoft.tallyfrontcrusher.model.MJobworkResourceRentEntry;
import org.syvasoft.tallyfrontcrusher.model.MKatingEntry;
import org.syvasoft.tallyfrontcrusher.model.MLabourWage;
import org.syvasoft.tallyfrontcrusher.model.MLabourWageAdvance;
import org.syvasoft.tallyfrontcrusher.model.MLabourWageConfig;
import org.syvasoft.tallyfrontcrusher.model.MLabourWageIssue;
import org.syvasoft.tallyfrontcrusher.model.MPermitLedger;
import org.syvasoft.tallyfrontcrusher.model.MPermitLedgerLine;
import org.syvasoft.tallyfrontcrusher.model.MQuarry;
import org.syvasoft.tallyfrontcrusher.model.MQuarryRent;
import org.syvasoft.tallyfrontcrusher.model.MQuarryRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MRentedVehicle;
import org.syvasoft.tallyfrontcrusher.model.MSandBlockBucketConfig;
import org.syvasoft.tallyfrontcrusher.model.MShareholder;
import org.syvasoft.tallyfrontcrusher.model.MShareholderType;
import org.syvasoft.tallyfrontcrusher.model.MSubcontractMaterialMovement;
import org.syvasoft.tallyfrontcrusher.model.MSubcontractType;
import org.syvasoft.tallyfrontcrusher.model.MTripSheet;
import org.syvasoft.tallyfrontcrusher.model.MTyre;
import org.syvasoft.tallyfrontcrusher.model.MTyreAssignment;
import org.syvasoft.tallyfrontcrusher.model.MTyreLife;
import org.syvasoft.tallyfrontcrusher.model.MTyrePosition;
import org.syvasoft.tallyfrontcrusher.model.MTyreStatus;
import org.syvasoft.tallyfrontcrusher.model.MTyreStatusChange;
import org.syvasoft.tallyfrontcrusher.model.MTyreType;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRent;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRentalContract;
import org.syvasoft.tallyfrontcrusher.model.MVehicleType;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentErrorLog;
import org.syvasoft.tallyfrontcrusher.model.MYardEntry;
import org.syvasoft.tallyfrontcrusher.model.MYardEntryConfig;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MBankAccount;
import org.syvasoft.tallyfrontcrusher.model.TF_MCharge;
import org.syvasoft.tallyfrontcrusher.model.TF_MElementValue;
import org.syvasoft.tallyfrontcrusher.model.TF_MInvoice;
import org.syvasoft.tallyfrontcrusher.model.TF_MJournal;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MPayment;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;
import org.syvasoft.tallyfrontcrusher.model.TF_MProject;
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
		else if (TF_MOrderLine.Table_Name.equals(tableName))			
			return TF_MOrderLine.class;
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
		else if (MTripSheet.Table_Name.equals(tableName))
			return MTripSheet.class;
		else if (TF_MProject.Table_Name.equals(tableName))
			return TF_MProject.class;
		else if (MJobworkCharges.Table_Name.equals(tableName))
			return MJobworkCharges.class;
		else if (MJobworkIssuedItems.Table_Name.equals(tableName))
			return MJobworkIssuedItems.class;
		else if (MJobworkIssuedResource.Table_Name.equals(tableName))
			return MJobworkIssuedResource.class;
		else if (MJobworkProductPrice.Table_Name.equals(tableName))
			return MJobworkProductPrice.class;
		else if (MJobworkReceivedItems.Table_Name.equals(tableName))
			return MJobworkReceivedItems.class;
		else if (MJobworkItemIssue.Table_Name.equals(tableName))
			return MJobworkItemIssue.class;
		else if (MJobworkExpense.Table_Name.equals(tableName))
			return MJobworkExpense.class;
		else if (MJobworkExpenseEntry.Table_Name.equals(tableName))
			return MJobworkExpenseEntry.class;
		else if (MItemReceiptOtherSrc.Table_Name.equals(tableName))
			return MItemReceiptOtherSrc.class;
		else if (MTyre.Table_Name.equals(tableName))
			return MTyre.class;
		else if (MTyreType.Table_Name.equals(tableName))
			return MTyreType.class;
		else if (MTyreStatus.Table_Name.equals(tableName))
			return MTyreStatus.class;
		else if (MTyrePosition.Table_Name.equals(tableName))
			return MTyrePosition.class;
		else if (MTyreAssignment.Table_Name.equalsIgnoreCase(tableName))
			return MTyreAssignment.class;
		else if (MTyreStatusChange.Table_Name.equals(tableName))
			return MTyreStatusChange.class;
		else if (MTyreLife.Table_Name.equals(tableName))
			return MTyreLife.class;
		else if (MJobworkResourceRentEntry.Table_Name.equals(tableName))
			return MJobworkResourceRentEntry.class;
		else if (TF_MElementValue.Table_Name.equals(tableName))
			return TF_MElementValue.class;
		else if (TF_MBankAccount.Table_Name.equals(tableName))
			return TF_MBankAccount.class;
		else if (TF_MBPartner.Table_Name.equals(tableName)) 
			return TF_MBPartner.class;
		else if (MDestination.Table_Name.equals(tableName)) 
			return MDestination.class;
		else if (MRentedVehicle.Table_Name.equals(tableName)) 
			return MRentedVehicle.class;
		else if (MVehicleRentConfig.Table_Name.equals(tableName)) 
			return MVehicleRentConfig.class;
		else if (MWeighmentEntry.Table_Name.equals(tableName)) 
			return MWeighmentEntry.class;
		else if (TF_MJournal.Table_Name.equals(tableName)) 
			return TF_MJournal.class;
		else if (MCOAOpeningBalance.Table_Name.equals(tableName))
			return MCOAOpeningBalance.class;
		else if (MBPOpeningBalance.Table_Name.equals(tableName))
			return MBPOpeningBalance.class;
		else if (MInterOrgCashTransfer.Table_Name.equals(tableName))
			return MInterOrgCashTransfer.class;
		else if (TF_MProduct.Table_Name.equals(tableName))
			return TF_MProduct.class;
		else if (MSubcontractMaterialMovement.Table_Name.equals(tableName))
			return MSubcontractMaterialMovement.class;
		else if (MShareholderType.Table_Name.equals(tableName))
			return MShareholderType.class;
		else if (MShareholder.Table_Name.equals(tableName))
			return MShareholder.class;
		else if (MInvestmentStructure.Table_Name.equals(tableName))
			return MInvestmentStructure.class;
		else if (MInvestmentReceipt.Table_Name.equals(tableName))
			return MInvestmentReceipt.class;
		//else if (MHomePageShortcuts.Table_Name.equals(tableName))
		//	return MHomePageShortcuts.class;
		else if(MSubcontractType.Table_Name.equals(tableName))
			return MSubcontractType.class;
		else if(MWeighmentErrorLog.Table_Name.equals(tableName))
			return MWeighmentErrorLog.class;
		else if(MSandBlockBucketConfig.Table_Name.equals(tableName))
			return MSandBlockBucketConfig.class;
		else if(MPermitLedger.Table_Name.equals(tableName))
			return MPermitLedger.class;
		else if(MPermitLedgerLine.Table_Name.equals(tableName))
			return MPermitLedgerLine.class;
		else if(MYardEntry.Table_Name.equals(tableName))
			return MYardEntry.class;
		else if(MYardEntryConfig.Table_Name.equals(tableName))
			return MYardEntryConfig.class;
		else if(MJobworkAssignedEmployee.Table_Name.equals(tableName))
			return MJobworkAssignedEmployee.class;
		else if(MJobworkAssignedBPartner.Table_Name.equals(tableName))
			return MJobworkAssignedBPartner.class;
		else if(MJobworkAssignedAccount.Table_Name.equals(tableName))
			return MJobworkAssignedAccount.class;
		else if(MJobworkAssignedVehicle.Table_Name.equals(tableName))
			return MJobworkAssignedVehicle.class;
		else if(MKatingEntry.Table_Name.equals(tableName))
			return MKatingEntry.class;
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
		else if (TF_MOrderLine.Table_Name.equals(tableName))
			return new TF_MOrderLine(ctx, Record_ID, trxName);
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
		else if (MTripSheet.Table_Name.equals(tableName))
			return new MTripSheet(ctx, Record_ID, trxName);
		else if (TF_MProject.Table_Name.equals(tableName))
			return new TF_MProject(ctx, Record_ID, trxName);
		else if (MJobworkCharges.Table_Name.equals(tableName))
			return new MJobworkCharges(ctx, Record_ID, trxName);
		else if (MJobworkIssuedItems.Table_Name.equals(tableName))
			return new MJobworkIssuedItems(ctx, Record_ID, trxName);
		else if (MJobworkIssuedResource.Table_Name.equals(tableName))
			return new MJobworkIssuedResource(ctx, Record_ID, trxName);
		else if (MJobworkProductPrice.Table_Name.equals(tableName))
			return new MJobworkProductPrice(ctx, Record_ID, trxName);
		else if (MJobworkReceivedItems.Table_Name.equals(tableName))
			return new MJobworkReceivedItems(ctx, Record_ID, trxName);
		else if (MJobworkItemIssue.Table_Name.equals(tableName))
			return new MJobworkItemIssue(ctx, Record_ID, trxName);
		else if (MJobworkExpense.Table_Name.equals(tableName))
			return new MJobworkExpense(ctx, Record_ID, trxName);
		else if (MJobworkExpenseEntry.Table_Name.equals(tableName))
			return new MJobworkExpenseEntry(ctx, Record_ID, trxName);
		else if (MItemReceiptOtherSrc.Table_Name.equals(tableName))
			return new MItemReceiptOtherSrc(ctx, Record_ID, trxName);
		else if (MTyre.Table_Name.equals(tableName))
			return new MTyre(ctx, Record_ID, trxName);
		else if (MTyreType.Table_Name.equals(tableName))
			return new MTyreType(ctx, Record_ID, trxName);
		else if (MTyreStatus.Table_Name.equals(tableName))
			return new MTyreStatus(ctx, Record_ID, trxName);
		else if (MTyrePosition.Table_Name.equals(tableName))
			return new MTyrePosition(ctx, Record_ID, trxName);
		else if (MTyreAssignment.Table_Name.equalsIgnoreCase(tableName))
			return new MTyreAssignment(ctx, Record_ID, trxName);
		else if (MTyreStatusChange.Table_Name.equals(tableName))
			return new MTyreStatusChange(ctx, Record_ID, trxName);
		else if (MTyreLife.Table_Name.equals(tableName))
			return new MTyreLife(ctx, Record_ID, trxName);
		else if (MJobworkResourceRentEntry.Table_Name.equals(tableName))
			return new MJobworkResourceRentEntry(ctx, Record_ID, trxName);
		else if (TF_MElementValue.Table_Name.equals(tableName))
			return new TF_MElementValue(ctx, Record_ID, trxName);
		else if (TF_MBankAccount.Table_Name.equals(tableName))
			return new TF_MBankAccount(ctx, Record_ID, trxName);
		else if (TF_MBPartner.Table_Name.equals(tableName)) 
			return new TF_MBPartner(ctx, Record_ID, trxName);
		else if (MDestination.Table_Name.equals(tableName)) 
			return new MDestination(ctx, Record_ID, trxName);
		else if (MRentedVehicle.Table_Name.equals(tableName)) 
			return new MRentedVehicle(ctx, Record_ID, trxName);
		else if (MVehicleRentConfig.Table_Name.equals(tableName)) 
			return new MVehicleRentConfig(ctx, Record_ID, trxName);
		else if (MWeighmentEntry.Table_Name.equals(tableName)) 
			return new MWeighmentEntry(ctx, Record_ID, trxName);
		else if (TF_MJournal.Table_Name.equals(tableName)) 
			return new TF_MJournal(ctx, Record_ID, trxName);
		else if (MCOAOpeningBalance.Table_Name.equals(tableName))
			return new MCOAOpeningBalance(ctx, Record_ID, trxName);
		else if (MBPOpeningBalance.Table_Name.equals(tableName))
			return new MBPOpeningBalance(ctx, Record_ID, trxName);
		else if (MInterOrgCashTransfer.Table_Name.equals(tableName))
			return new MInterOrgCashTransfer(ctx, Record_ID, trxName);
		else if (TF_MProduct.Table_Name.equals(tableName))
			return new TF_MProduct(ctx, Record_ID, trxName);
		else if (MSubcontractMaterialMovement.Table_Name.equals(tableName))
			return new MSubcontractMaterialMovement(ctx, Record_ID, trxName);
		else if (MShareholderType.Table_Name.equals(tableName))
			return new MShareholderType(ctx, Record_ID, trxName); 
		else if (MShareholder.Table_Name.equals(tableName))
			return new MShareholder(ctx, Record_ID, trxName);
		else if (MInvestmentStructure.Table_Name.equals(tableName))
			return new MInvestmentStructure(ctx, Record_ID, trxName);
		else if (MInvestmentReceipt.Table_Name.equals(tableName))
			return new MInvestmentReceipt(ctx, Record_ID, trxName);
		//else if (MHomePageShortcuts.Table_Name.equals(tableName))
		//	return new MHomePageShortcuts(ctx, Record_ID, tableName);
		else if(MSubcontractType.Table_Name.equals(tableName))
			return new MSubcontractType(ctx, Record_ID, trxName);
		else if(MWeighmentErrorLog.Table_Name.equals(tableName))
			return new MWeighmentErrorLog(ctx, Record_ID, trxName);
		else if(MSandBlockBucketConfig.Table_Name.equals(tableName))
			return new MSandBlockBucketConfig(ctx, Record_ID, trxName);
		else if(MPermitLedger.Table_Name.equals(tableName))
			return new MPermitLedger(ctx, Record_ID, trxName);
		else if(MPermitLedgerLine.Table_Name.equals(tableName))
			return new MPermitLedgerLine(ctx, Record_ID, trxName);
		else if(MYardEntry.Table_Name.equals(tableName))
			return new MYardEntry(ctx, Record_ID, trxName);
		else if(MYardEntryConfig.Table_Name.equals(tableName))
			return new MYardEntryConfig(ctx, Record_ID, trxName);
		else if(MJobworkAssignedEmployee.Table_Name.equals(tableName))			
			return new MJobworkAssignedEmployee(ctx, Record_ID, trxName);
		else if(MJobworkAssignedBPartner.Table_Name.equals(tableName))
			return new MJobworkAssignedBPartner(ctx, Record_ID, trxName);
		else if(MJobworkAssignedAccount.Table_Name.equals(tableName))
			return new MJobworkAssignedAccount(ctx, Record_ID, trxName);
		else if(MJobworkAssignedVehicle.Table_Name.equals(tableName))
			return new MJobworkAssignedVehicle(ctx, Record_ID, trxName);
		else if(MKatingEntry.Table_Name.equals(tableName))
			return new MKatingEntry(ctx, Record_ID, trxName);
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
		else if (TF_MOrderLine.Table_Name.equals(tableName))
			return new TF_MOrderLine(ctx, rs, trxName);
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
		else if (MTripSheet.Table_Name.equals(tableName))
			return new MTripSheet(ctx, rs, trxName);
		else if (TF_MProject.Table_Name.equals(tableName))
			return new TF_MProject(ctx, rs, trxName);
		else if (MJobworkCharges.Table_Name.equals(tableName))
			return new MJobworkCharges(ctx, rs, trxName);
		else if (MJobworkIssuedItems.Table_Name.equals(tableName))
			return new MJobworkIssuedItems(ctx, rs, trxName);
		else if (MJobworkIssuedResource.Table_Name.equals(tableName))
			return new MJobworkIssuedResource(ctx, rs, trxName);
		else if (MJobworkProductPrice.Table_Name.equals(tableName))
			return new MJobworkProductPrice(ctx, rs, trxName);
		else if (MJobworkReceivedItems.Table_Name.equals(tableName))
			return new MJobworkReceivedItems(ctx, rs, trxName);
		else if (MJobworkItemIssue.Table_Name.equals(tableName))
			return new MJobworkItemIssue(ctx, rs, trxName);
		else if (MJobworkExpense.Table_Name.equals(tableName))
			return new MJobworkExpense(ctx, rs, trxName);
		else if (MJobworkExpenseEntry.Table_Name.equals(tableName))
			return new MJobworkExpenseEntry(ctx, rs, trxName);
		else if (MItemReceiptOtherSrc.Table_Name.equals(tableName))
			return new MItemReceiptOtherSrc(ctx, rs, trxName);
		else if (MTyre.Table_Name.equals(tableName))
			return new MTyre(ctx, rs, trxName);
		else if (MTyreType.Table_Name.equals(tableName))
			return new MTyreType(ctx, rs, trxName);
		else if (MTyreStatus.Table_Name.equals(tableName))
			return new MTyreStatus(ctx, rs, trxName);
		else if (MTyrePosition.Table_Name.equals(tableName))
			return new MTyrePosition(ctx, rs, trxName);
		else if (MTyreAssignment.Table_Name.equalsIgnoreCase(tableName))
			return new MTyreAssignment(ctx, rs, trxName);
		else if (MTyreStatusChange.Table_Name.equals(tableName))
			return new MTyreStatusChange(ctx, rs, trxName);
		else if (MTyreLife.Table_Name.equals(tableName))
			return new MTyreLife(ctx, rs, trxName);
		else if (MJobworkResourceRentEntry.Table_Name.equals(tableName))
			return new MJobworkResourceRentEntry(ctx, rs, trxName);
		else if (TF_MElementValue.Table_Name.equals(tableName))
			return new TF_MElementValue(ctx, rs, trxName);
		else if (TF_MBankAccount.Table_Name.equals(tableName))
			return new TF_MBankAccount(ctx, rs, trxName);
		else if (TF_MBPartner.Table_Name.equals(tableName)) 
			return new TF_MBPartner(ctx, rs, trxName);
		else if (MDestination.Table_Name.equals(tableName)) 
			return new MDestination(ctx, rs, trxName);
		else if (MRentedVehicle.Table_Name.equals(tableName)) 
			return new MRentedVehicle(ctx, rs, trxName);
		else if (MVehicleRentConfig.Table_Name.equals(tableName)) 
			return new MVehicleRentConfig(ctx, rs, trxName);
		else if (MWeighmentEntry.Table_Name.equals(tableName)) 
			return new MWeighmentEntry(ctx, rs, trxName);
		else if (TF_MJournal.Table_Name.equals(tableName)) 
			return new TF_MJournal(ctx, rs, trxName);
		else if (MCOAOpeningBalance.Table_Name.equals(tableName))
			return new MCOAOpeningBalance(ctx, rs, trxName);
		else if (MBPOpeningBalance.Table_Name.equals(tableName))
			return new MBPOpeningBalance(ctx, rs, trxName);
		else if (MInterOrgCashTransfer.Table_Name.equals(tableName))
			return new MInterOrgCashTransfer(ctx, rs, trxName);
		else if (TF_MProduct.Table_Name.equals(tableName))
			return new TF_MProduct(ctx, rs, trxName);
		else if (MSubcontractMaterialMovement.Table_Name.equals(tableName))
			return new MSubcontractMaterialMovement(ctx, rs, trxName);
		else if (MShareholderType.Table_Name.equals(tableName))
			return new MShareholderType(ctx, rs, trxName); 
		else if (MShareholder.Table_Name.equals(tableName))
			return new MShareholder(ctx, rs, trxName);
		else if (MInvestmentStructure.Table_Name.equals(tableName))
			return new MInvestmentStructure(ctx, rs, trxName);
		else if (MInvestmentReceipt.Table_Name.equals(tableName))
			return new MInvestmentReceipt(ctx, rs, trxName);
		//else if (MHomePageShortcuts.Table_Name.equals(tableName))
		//	return new MHomePageShortcuts(ctx, rs, tableName);
		else if(MSubcontractType.Table_Name.equals(tableName))
			return new MSubcontractType(ctx, rs, trxName);
		else if(MWeighmentErrorLog.Table_Name.equals(tableName))
			return new MWeighmentErrorLog(ctx, rs, trxName);
		else if(MSandBlockBucketConfig.Table_Name.equals(tableName))
			return new MSandBlockBucketConfig(ctx, rs, trxName);
		else if(MPermitLedger.Table_Name.equals(tableName))
			return new MPermitLedger(ctx, rs, trxName);
		else if(MPermitLedgerLine.Table_Name.equals(tableName))
			return new MPermitLedgerLine(ctx, rs, trxName);
		else if(MYardEntry.Table_Name.equals(tableName))
			return new MYardEntry(ctx, rs, trxName);
		else if(MYardEntryConfig.Table_Name.equals(tableName))
			return new MYardEntryConfig(ctx, rs, trxName);
		else if(MJobworkAssignedEmployee.Table_Name.equals(tableName))
			return new MJobworkAssignedEmployee(ctx, rs, trxName);
		else if(MJobworkAssignedBPartner.Table_Name.equals(tableName))
			return new MJobworkAssignedBPartner(ctx, rs, trxName);
		else if(MJobworkAssignedAccount.Table_Name.equals(tableName))
			return new MJobworkAssignedAccount(ctx, rs, trxName);
		else if(MJobworkAssignedVehicle.Table_Name.equals(tableName))
			return new MJobworkAssignedVehicle(ctx, rs, trxName);
		else if(MKatingEntry.Table_Name.equals(tableName))
			return new MKatingEntry(ctx, rs, trxName);
		return null;
	}

}
