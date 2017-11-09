package org.syvasoft.tallyfrontcrusher.factory;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.compiere.model.MPayment;
import org.jfree.chart.block.ColumnArrangement;
import org.syvasoft.tallyfrontcrusher.callout.CalloutBoulderReceipt_JobWork;
import org.syvasoft.tallyfrontcrusher.callout.CalloutElementValue_AccountGroup;
import org.syvasoft.tallyfrontcrusher.callout.CalloutEmployeeSalary;
import org.syvasoft.tallyfrontcrusher.callout.CalloutEmployeeSalaryIssue_CalcBalanceAmts;
import org.syvasoft.tallyfrontcrusher.callout.CalloutEmployeeSalaryIssue_SetOpenAmt;
import org.syvasoft.tallyfrontcrusher.callout.CalloutInvoiceHeaderItemAmount;
import org.syvasoft.tallyfrontcrusher.callout.CalloutJournal_QuickEntryMode;
import org.syvasoft.tallyfrontcrusher.callout.CalloutLabourWage;
import org.syvasoft.tallyfrontcrusher.callout.CalloutLabourWageIssue_CalcBalanceAmts;
import org.syvasoft.tallyfrontcrusher.callout.CalloutLabourWageIssue_SetOpenAmt;
import org.syvasoft.tallyfrontcrusher.callout.CalloutMJobworkResourceRentEntry_CalcContractAmt;
import org.syvasoft.tallyfrontcrusher.callout.CalloutOrderQuickEntry_CalcAmt;
import org.syvasoft.tallyfrontcrusher.callout.CalloutOrderQuickEntry_SetPrice;
import org.syvasoft.tallyfrontcrusher.callout.CalloutOrderQuickEntry_SetVehicleNo;
import org.syvasoft.tallyfrontcrusher.callout.CalloutOrder_CalcRentAmount;
import org.syvasoft.tallyfrontcrusher.callout.CalloutOrder_CalcRentPayable;
import org.syvasoft.tallyfrontcrusher.callout.CalloutOrder_Destination;
import org.syvasoft.tallyfrontcrusher.callout.CalloutOrder_POSCashBP;
import org.syvasoft.tallyfrontcrusher.callout.CalloutOrder_RentedVehicle;
import org.syvasoft.tallyfrontcrusher.callout.CalloutOrder_SOUnitPriceRent;
import org.syvasoft.tallyfrontcrusher.callout.CalloutOrder_SetTonnage;
import org.syvasoft.tallyfrontcrusher.callout.CalloutOrder_WeighmentEntry;
import org.syvasoft.tallyfrontcrusher.callout.CalloutPaymentCashType;
import org.syvasoft.tallyfrontcrusher.callout.CalloutPayment_DocumentType;
import org.syvasoft.tallyfrontcrusher.callout.CalloutPayment_ElementValue;
import org.syvasoft.tallyfrontcrusher.callout.CalloutPayment_FromToBankAccount;
import org.syvasoft.tallyfrontcrusher.callout.CalloutPayment_Org;
import org.syvasoft.tallyfrontcrusher.callout.CalloutPayment_TFBPartner;
import org.syvasoft.tallyfrontcrusher.callout.CalloutRentalContract_ResourceType;
import org.syvasoft.tallyfrontcrusher.callout.CalloutRentalContract_VehicleNo;
import org.syvasoft.tallyfrontcrusher.callout.CalloutTripSheetFuelExpensed;
import org.syvasoft.tallyfrontcrusher.callout.CalloutTripSheetJobwork;
import org.syvasoft.tallyfrontcrusher.callout.CalloutTripSheetOpeningEntries;
import org.syvasoft.tallyfrontcrusher.callout.CalloutTripSheetRunningMeter;
import org.syvasoft.tallyfrontcrusher.callout.CalloutTripSheetRentalContract;
import org.syvasoft.tallyfrontcrusher.callout.CalloutTyreAssignment;
import org.syvasoft.tallyfrontcrusher.callout.CalloutTyreAssignment_CalcRunningMeter;
import org.syvasoft.tallyfrontcrusher.callout.CalloutTyreAssignment_ReleaseTyreMovement;
import org.syvasoft.tallyfrontcrusher.callout.CalloutTyreStatusChange_Tyre;
import org.syvasoft.tallyfrontcrusher.callout.CalloutVehicleRentConfig_Destination;
import org.syvasoft.tallyfrontcrusher.callout.CalloutWeighmentEntry_CalcNetWeight;
import org.syvasoft.tallyfrontcrusher.callout.CalloutWeighmentEntry_Vehicle;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalary;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryIssue;
import org.syvasoft.tallyfrontcrusher.model.MJobworkResourceRentEntry;
import org.syvasoft.tallyfrontcrusher.model.MLabourWage;
import org.syvasoft.tallyfrontcrusher.model.MLabourWageIssue;
import org.syvasoft.tallyfrontcrusher.model.MTripSheet;
import org.syvasoft.tallyfrontcrusher.model.MTyreAssignment;
import org.syvasoft.tallyfrontcrusher.model.MTyreStatusChange;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRentalContract;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MElementValue;
import org.syvasoft.tallyfrontcrusher.model.TF_MInvoice;
import org.syvasoft.tallyfrontcrusher.model.TF_MJournal;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MPayment;

public class CrusherColumnCalloutFactory implements IColumnCalloutFactory {

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName,
			String columnName) {
		List<IColumnCallout> list = new ArrayList<IColumnCallout>();
		
		//C_Invoice / C_Order - Calc Header Item Amount
		if((tableName.equals(TF_MInvoice.Table_Name) || tableName.equals(TF_MOrder.Table_Name)) && 
				(columnName.equals(TF_MInvoice.COLUMNNAME_Item1_Qty) || columnName.equals(TF_MInvoice.COLUMNNAME_Item1_Price) ||
				 columnName.equals(TF_MInvoice.COLUMNNAME_Item2_Qty) || columnName.equals(TF_MInvoice.COLUMNNAME_Item2_Price)))
			list.add(new CalloutInvoiceHeaderItemAmount());
		
		//TF_MOrder - Set Cash Payment Rule for POS BP
		if(tableName.equals(TF_MOrder.Table_Name) && columnName.equals(TF_MOrder.COLUMNNAME_C_BPartner_ID))
			list.add(new CalloutOrder_POSCashBP());
		
		//TF_MOrder / C_Invoice - Set Unit Price
		if((tableName.equals(TF_MOrder.Table_Name) || tableName.equals(TF_MInvoice.Table_Name)) && (columnName.equals(TF_MOrder.COLUMNNAME_Item1_ID)				
				|| columnName.equals(TF_MOrder.COLUMNNAME_Item2_ID)))
			list.add(new CalloutOrderQuickEntry_SetPrice());		
		
		//TF_MOrder - Set Vehicle No
		if(tableName.equals(TF_MOrder.Table_Name) && columnName.equals(TF_MOrder.COLUMNNAME_Vehicle_ID))
				list.add(new CalloutOrderQuickEntry_SetVehicleNo());
				
		//TF_TripSheet - Calc Running Meter
		if(tableName.equals(MTripSheet.Table_Name) && (columnName.equals(MTripSheet.COLUMNNAME_Opening_Meter) || 
				columnName.equals(MTripSheet.COLUMNNAME_Closing_Meter)))
			list.add(new CalloutTripSheetRunningMeter());
		
		//TF_TripSheet - Calc Running Meter
		if(tableName.equals(MTripSheet.Table_Name) && (columnName.equals(MTripSheet.COLUMNNAME_Opening_Fuel) || 
				columnName.equals(MTripSheet.COLUMNNAME_Closing_Fuel) || columnName.equals(MTripSheet.COLUMNNAME_Received_Fuel)) )
			list.add(new CalloutTripSheetFuelExpensed());
		
		//TF_TripSheet - Set Opening Meter / Fuel
		if(tableName.equals(MTripSheet.Table_Name) && columnName.equals(MTripSheet.COLUMNNAME_Vehicle_ID))
			list.add(new CalloutTripSheetOpeningEntries());
		
		//TF_TripSheet - Set Rental Contract 
		if(tableName.equals(MTripSheet.Table_Name) && columnName.equals(MTripSheet.COLUMNNAME_Vehicle_ID))
			list.add(new CalloutTripSheetRentalContract());
		
		//TF_TripSheet - Set Subcontract / Jobwork 
		if(tableName.equals(MTripSheet.Table_Name) && columnName.equals(MTripSheet.COLUMNNAME_Vehicle_ID))
			list.add(new CalloutTripSheetJobwork());
		
		//C_Payment - Cash Type
		//if(tableName.equals(MPayment.Table_Name) && (columnName.equals("CashType")))
		//	list.add(new CalloutPaymentCashType());
		if(tableName.equals(MPayment.Table_Name)) { 
			if(columnName.equals(TF_MPayment.COLUMNNAME_TF_BPartner_ID)) 
				list.add(new CalloutPayment_TFBPartner());
			if(columnName.equals(TF_MPayment.COLUMNNAME_C_ElementValue_ID))
				list.add(new CalloutPayment_ElementValue());
			if(columnName.equals(TF_MPayment.COLUMNNAME_C_DocType_ID))
				list.add(new CalloutPayment_DocumentType());
			if(columnName.equals(TF_MPayment.COLUMNNAME_AD_Org_ID))
				list.add(new CalloutPayment_Org());
			if(columnName.equals(TF_MPayment.COLUMNNAME_FromTo_BankAccount_ID) || 
					columnName.equals(TF_MPayment.COLUMNNAME_IsInterCashBookEntry))
				list.add(new CalloutPayment_FromToBankAccount());
		}
		//TF_Employee_Salary - Load Salary Config
		if(tableName.equals(MEmployeeSalary.Table_Name) && (columnName.equals(MEmployeeSalary.COLUMNNAME_C_BPartner_ID)
				|| columnName.equals(MEmployeeSalary.COLUMNNAME_DateAcct) || columnName.equals(MEmployeeSalary.COLUMNNAME_Present_Days)))
			list.add(new CalloutEmployeeSalary());
		
		//TF_Labour_Wage - Load Wage Config
		if(tableName.equals(MLabourWage.Table_Name) && (columnName.equals(MLabourWage.COLUMNNAME_C_BPartner_ID) ||
				columnName.equals(MLabourWage.COLUMNNAME_DateAcct) || columnName.equals(MLabourWage.COLUMNNAME_Present_Days) || 
				columnName.equals(MLabourWage.COLUMNNAME_TF_VehicleType_ID) || columnName.equals(MLabourWage.COLUMNNAME_Incentive)))
			list.add(new CalloutLabourWage());
		
		//TF_Labour_Wage_Issue - Load Earned Wage and Advance Paid
		if(tableName.equals(MLabourWageIssue.Table_Name) && (columnName.equals(MLabourWageIssue.COLUMNNAME_DateAcct) ||
				columnName.equals(MLabourWageIssue.COLUMNNAME_C_BPartner_ID)))
			list.add(new CalloutLabourWageIssue_SetOpenAmt());
		
		//TF_Labour_Wage_Issue - Calculate Balance amounts
		if(tableName.equals(MLabourWageIssue.Table_Name) && (columnName.equals(MLabourWageIssue.COLUMNNAME_Advance_Deduct) ||
				columnName.equals(MLabourWageIssue.COLUMNNAME_Wages_Payable) || columnName.equals(MLabourWageIssue.COLUMNNAME_Wages_Paid)))
			list.add(new CalloutLabourWageIssue_CalcBalanceAmts());
		
		//TF_Employee_Salary_Issue - Load Earned Salary and Advance Paid
		if(tableName.equals(MEmployeeSalaryIssue.Table_Name) && (columnName.equals(MEmployeeSalaryIssue.COLUMNNAME_DateAcct) ||
				columnName.equals(MEmployeeSalaryIssue.COLUMNNAME_C_BPartner_ID)))
			list.add(new CalloutEmployeeSalaryIssue_SetOpenAmt());
				
		//TF_Employee_Salary_Issue - Calculate Balance amounts
		if(tableName.equals(MEmployeeSalaryIssue.Table_Name) && (columnName.equals(MEmployeeSalaryIssue.COLUMNNAME_Advance_Deduct) ||
				columnName.equals(MEmployeeSalaryIssue.COLUMNNAME_Loan_Deduct) ||
				columnName.equals(MEmployeeSalaryIssue.COLUMNNAME_Salary_Paid) || columnName.equals(MEmployeeSalaryIssue.COLUMNNAME_Salary_Payable)))
			list.add(new CalloutEmployeeSalaryIssue_CalcBalanceAmts());
		
		//TF_Vehicle_Rental_Contract - Vehicle No
		if(tableName.equals(MVehicleRentalContract.Table_Name) && columnName.equals(MVehicleRentalContract.COLUMNNAME_VehicleNo))
			list.add(new CalloutRentalContract_VehicleNo());
		
		//TF_Vehicle_Rental_Contract - Resource Type
		if(tableName.equals(MVehicleRentalContract.Table_Name) && columnName.equals(MVehicleRentalContract.COLUMNNAME_S_ResourceType_ID))
			list.add(new CalloutRentalContract_ResourceType());
		
		//TF_Boulder Receipt - Subcontract / Jobwork
		if(tableName.equals(MBoulderReceipt.Table_Name) && columnName.equals(MBoulderReceipt.COLUMNNAME_C_Project_ID))
			list.add(new CalloutBoulderReceipt_JobWork());
		
		//TF_TyreAssignment - Tyre, Tyre Assignment Type
		if(tableName.equals(MTyreAssignment.Table_Name) && (columnName.equals(MTyreAssignment.COLUMNNAME_TF_Tyre_ID) 
				|| columnName.equals(MTyreAssignment.COLUMNNAME_TyreAssignmentType)))
			list.add(new CalloutTyreAssignment());
		
		//TF_TyreAssignment - Release Tyre Movement
		if(tableName.equals(MTyreAssignment.Table_Name) && columnName.equals(MTyreAssignment.COLUMNNAME_RD_TF_TyreMovement_ID))
			list.add(new CalloutTyreAssignment_ReleaseTyreMovement());
		
		//TF_TyreAssignment - Calc Running Meter
		if(tableName.equals(MTyreAssignment.Table_Name) && (columnName.equals(MTyreAssignment.COLUMNNAME_RD_TF_TyreMovement_ID)
				|| columnName.equals(MTyreAssignment.COLUMNNAME_RD_End_Meter)))
			list.add(new CalloutTyreAssignment_CalcRunningMeter());
		
		//TF_TyreStatusChange - Tyre
		if(tableName.equals(MTyreStatusChange.Table_Name) && columnName.equals(MTyreStatusChange.COLUMNNAME_TF_Tyre_ID))
			list.add(new CalloutTyreStatusChange_Tyre());
		
		//TF_Jobwork_ResRentEntry - Calc Contract Amount
		if(tableName.equals(MJobworkResourceRentEntry.Table_Name) && (columnName.equals(MJobworkResourceRentEntry.COLUMNNAME_Qty))
				|| (columnName.equals(MJobworkResourceRentEntry.COLUMNNAME_Unit_Price)))
				list.add(new CalloutMJobworkResourceRentEntry_CalcContractAmt());
		
		//C_ElementValue - Update Account Type and Account Sign from Account Group.
		if(tableName.equals(TF_MElementValue.Table_Name) && columnName.equals(TF_MElementValue.COLUMNNAME_AccountGroup_ID))
			list.add(new CalloutElementValue_AccountGroup());
		
		//TF_VehicleRent_TajConfig - Get default for destination from Destination Master
		if(tableName.equals(MVehicleRentConfig.Table_Name) && columnName.equals(MVehicleRentConfig.COLUMNNAME_TF_Destination_ID))
			list.add(new CalloutVehicleRentConfig_Destination());
		
		if(tableName.equals(TF_MOrder.Table_Name) && (columnName.equals(TF_MOrder.COLUMNNAME_TF_Destination_ID) ||
				columnName.equals(TF_MOrder.COLUMNNAME_TF_RentedVehicle_ID))) {
			list.add(new CalloutOrder_Destination());
			list.add(new CalloutOrder_RentedVehicle());
			list.add(new CalloutOrder_CalcRentAmount());
			list.add(new CalloutOrder_SOUnitPriceRent());
		}
		
		if(tableName.equals(TF_MOrder.Table_Name) && (columnName.equals(TF_MOrder.COLUMNNAME_Distance) ||
				columnName.equals(TF_MOrder.COLUMNNAME_Rate) || columnName.equals(TF_MOrder.COLUMNNAME_IsLumpSumRent))) {			
			list.add(new CalloutOrder_CalcRentAmount());
			list.add(new CalloutOrder_SOUnitPriceRent());
		}
		
		if(tableName.equals(MWeighmentEntry.Table_Name)) {
			if(columnName.equals(MWeighmentEntry.COLUMNNAME_TareWeight) || 
					columnName.equals(MWeighmentEntry.COLUMNNAME_GrossWeight)) {
						list.add(new CalloutWeighmentEntry_CalcNetWeight());
					}
			if(columnName.equals(MWeighmentEntry.COLUMNNAME_TF_RentedVehicle_ID)) {
				list.add(new CalloutWeighmentEntry_Vehicle());
			}
		}
		
		if(tableName.equals(TF_MOrder.Table_Name) && columnName.equals(TF_MOrder.COLUMNNAME_TF_WeighmentEntry_ID)) {
			list.add(new CalloutOrder_WeighmentEntry());
			list.add(new CalloutOrder_SOUnitPriceRent());
		}
		
		if(tableName.equals(TF_MOrder.Table_Name) && columnName.equals(TF_MOrder.COLUMNNAME_Item1_Qty)) {
			list.add(new CalloutOrder_SetTonnage());
			list.add(new CalloutOrder_CalcRentAmount());
			list.add(new CalloutOrder_SOUnitPriceRent());
			list.add(new CalloutOrder_CalcRentPayable());
		}
		
		if(tableName.equals(TF_MOrder.Table_Name) && columnName.equals(TF_MOrder.COLUMNNAME_Rent_Amt)) {			
			list.add(new CalloutOrder_SOUnitPriceRent());
			list.add(new CalloutOrder_CalcRentPayable());
		}
		
		if(tableName.equals(TF_MOrder.Table_Name) && (columnName.equals(TF_MOrder.COLUMNNAME_IsRentBreakup)
				|| columnName.equals(TF_MOrder.COLUMNNAME_Item1_UnitPrice)) ) {
			list.add(new CalloutOrder_SOUnitPriceRent());
		}
		
		if(tableName.equals(TF_MOrder.Table_Name) && columnName.equals(TF_MOrder.COLUMNNAME_RentMargin)) {
			list.add(new CalloutOrder_CalcRentPayable());
		}
		
		if(tableName.equals(TF_MJournal.Table_Name) && columnName.equals(TF_MJournal.COLUMNNAME_IsQuickEntry)) {
			list.add(new CalloutJournal_QuickEntryMode());
		}
		return list != null ? list.toArray(new IColumnCallout[0]) : new IColumnCallout[0];
	}

}
