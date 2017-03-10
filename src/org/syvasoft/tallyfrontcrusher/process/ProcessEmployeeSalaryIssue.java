package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.DocAction;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryIssue;

public class ProcessEmployeeSalaryIssue extends SvrProcess {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception {
		MEmployeeSalaryIssue salaryIssue = new MEmployeeSalaryIssue(getCtx(), getRecord_ID(), get_TrxName());
		salaryIssue.processIt(DocAction.ACTION_Complete);
		salaryIssue.saveEx();
		return null;
	}

}
