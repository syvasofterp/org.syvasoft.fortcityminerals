package org.syvasoft.tallyfrontcrusher.factory;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.compiere.model.MPayment;
import org.syvasoft.tallyfrontcrusher.callout.CalloutEmployeeSalary;
import org.syvasoft.tallyfrontcrusher.callout.CalloutInvoiceHeaderItemAmount;
import org.syvasoft.tallyfrontcrusher.callout.CalloutLabourWage;
import org.syvasoft.tallyfrontcrusher.callout.CalloutPaymentCashType;
import org.syvasoft.tallyfrontcrusher.callout.CalloutTripSheetFuelExpensed;
import org.syvasoft.tallyfrontcrusher.callout.CalloutTripSheetOpeningEntries;
import org.syvasoft.tallyfrontcrusher.callout.CalloutTripSheetRunningMeter;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalary;
import org.syvasoft.tallyfrontcrusher.model.MLabourWage;
import org.syvasoft.tallyfrontcrusher.model.MTripSheet;
import org.syvasoft.tallyfrontcrusher.model.TF_MInvoice;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;

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
		
		//C_Payment - Cash Type
		if(tableName.equals(MPayment.Table_Name) && (columnName.equals("CashType")))
			list.add(new CalloutPaymentCashType());
		
		//TF_Employee_Salary - Load Salary Config
		if(tableName.equals(MEmployeeSalary.Table_Name) && (columnName.equals(MEmployeeSalary.COLUMNNAME_C_BPartner_ID)
				|| columnName.equals(MEmployeeSalary.COLUMNNAME_DateAcct) || columnName.equals(MEmployeeSalary.COLUMNNAME_Present_Days)))
			list.add(new CalloutEmployeeSalary());
		
		//TF_Labour_Wage - Load Wage Config
		if(tableName.equals(MLabourWage.Table_Name) && (columnName.equals(MLabourWage.COLUMNNAME_C_BPartner_ID) ||
				columnName.equals(MLabourWage.COLUMNNAME_DateAcct) || columnName.equals(MLabourWage.COLUMNNAME_Present_Days) || 
				columnName.equals(MLabourWage.COLUMNNAME_TF_VehicleType_ID) || columnName.equals(MLabourWage.COLUMNNAME_Incentive)))
			list.add(new CalloutLabourWage());
		
		return list != null ? list.toArray(new IColumnCallout[0]) : new IColumnCallout[0];
	}

}
