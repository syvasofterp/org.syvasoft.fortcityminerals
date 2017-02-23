package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.DocAction;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MFuelIssue;

public class ProcessFuelIssue extends SvrProcess {

	@Override
	protected void prepare() {
		
	}

	@Override
	protected String doIt() throws Exception {
		MFuelIssue issue = new MFuelIssue(getCtx(), getRecord_ID(), get_TrxName());
		issue.processIt(DocAction.ACTION_Complete);
		issue.saveEx();
		return null;
	}

}
