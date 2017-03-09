package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.DocAction;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalaryAdvance;

public class ProcessEmployeeSalaryAdvance extends SvrProcess {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception {
		MEmployeeSalaryAdvance salaryAdv = new MEmployeeSalaryAdvance(getCtx(), getRecord_ID(), get_TrxName());
		salaryAdv.processIt(DocAction.ACTION_Complete);
		salaryAdv.saveEx();
		return null;
	}

}
