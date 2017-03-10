package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryIssue;

public class CalloutEmployeeSalaryIssue_CalcBalanceAmts implements
		IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		BigDecimal salaryAmt = (BigDecimal) mTab.getValue(MEmployeeSalaryIssue.COLUMNNAME_Salary_Amt);
		BigDecimal advancePaid = (BigDecimal) mTab.getValue(MEmployeeSalaryIssue.COLUMNNAME_Advance_Paid);
		BigDecimal advanceDeduct = (BigDecimal) mTab.getValue(MEmployeeSalaryIssue.COLUMNNAME_Advance_Deduct);
		BigDecimal advanceBalance = (BigDecimal) mTab.getValue(MEmployeeSalaryIssue.COLUMNNAME_Advance_Balance);
		BigDecimal salaryPaid = (BigDecimal) mTab.getValue(MEmployeeSalaryIssue.COLUMNNAME_Salary_Paid);
		BigDecimal salaryBalance = (BigDecimal) mTab.getValue(MEmployeeSalaryIssue.COLUMNNAME_Salary_Payable);
		
		advanceBalance = advancePaid.subtract(advanceDeduct);
		if(!mField.getColumnName().equals(MEmployeeSalaryIssue.COLUMNNAME_Salary_Paid))
			salaryPaid = salaryAmt.subtract(advanceDeduct);
		salaryBalance = salaryAmt.subtract(advanceDeduct.add(salaryPaid));
		
		mTab.setValue(MEmployeeSalaryIssue.COLUMNNAME_Advance_Balance, advanceBalance);
		mTab.setValue(MEmployeeSalaryIssue.COLUMNNAME_Salary_Paid, salaryPaid);
		mTab.setValue(MEmployeeSalaryIssue.COLUMNNAME_Salary_Payable, salaryBalance);
		return null;
	}

}
