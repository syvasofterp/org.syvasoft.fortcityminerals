package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.DocAction;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MLabourWageIssue;

public class ProcessLabourWageIssue extends SvrProcess {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception {
		MLabourWageIssue wageIssue = new MLabourWageIssue(getCtx(), getRecord_ID(), get_TrxName());
		wageIssue.processIt(DocAction.ACTION_Complete);
		wageIssue.saveEx();
		return null;
	}

}
