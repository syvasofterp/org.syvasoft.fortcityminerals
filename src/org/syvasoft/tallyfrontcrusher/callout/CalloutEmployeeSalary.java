package org.syvasoft.tallyfrontcrusher.callout;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.syvasoft.tallyfrontcrusher.model.MEmpSalaryConfig;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalary;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryAdvance;

public class CalloutEmployeeSalary implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		BigDecimal stdDays = BigDecimal.ZERO;
		BigDecimal stdWage = BigDecimal.ZERO;
		BigDecimal presentDays = BigDecimal.ZERO;
		boolean isCalculated = mTab.getValueAsBoolean(MEmployeeSalary.COLUMNNAME_IsCalculated);
		
		if(mTab.getValue(MEmployeeSalary.COLUMNNAME_Present_Days) != null)
			presentDays = (BigDecimal) mTab.getValue(MEmployeeSalary.COLUMNNAME_Present_Days);
		
		if(value == null || mTab.getValue(MEmployeeSalary.COLUMNNAME_DateAcct) == null) {
			mTab.setValue(MEmployeeSalary.COLUMNNAME_Std_Days, stdDays);
			mTab.setValue(MEmployeeSalary.COLUMNNAME_Std_Wage, stdWage);
			mTab.setValue(MEmployeeSalary.COLUMNNAME_Salary_Amt, BigDecimal.ZERO);		
		}
		else {
			Timestamp dateAcct = null;
			dateAcct = (Timestamp) mTab.getValue(MEmployeeSalary.COLUMNNAME_DateAcct);		
			int bpartner_ID = (int)  mTab.getValue(MEmployeeSalary.COLUMNNAME_C_BPartner_ID);
			MEmpSalaryConfig salaryConfig = MEmpSalaryConfig.getEmpSalaryConfig(ctx, bpartner_ID, dateAcct);
			
			if(salaryConfig != null && isCalculated) {
				stdDays = salaryConfig.getStd_Days();
				stdWage = salaryConfig.getStd_Wage();
			}
			
			BigDecimal earnedSalary = BigDecimal.ZERO;
			if(stdDays.doubleValue() !=0  && isCalculated)
				earnedSalary = stdWage.multiply(presentDays.divide(stdDays));
			
			mTab.setValue(MEmployeeSalary.COLUMNNAME_Std_Days, stdDays);
			mTab.setValue(MEmployeeSalary.COLUMNNAME_Std_Wage, stdWage);
			mTab.setValue(MEmployeeSalary.COLUMNNAME_Salary_Amt, earnedSalary);
		}
		return null;
	}

	
}
