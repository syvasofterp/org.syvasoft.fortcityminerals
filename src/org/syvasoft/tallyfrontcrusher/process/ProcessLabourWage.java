package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.DocAction;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MLabourWage;

public class ProcessLabourWage extends SvrProcess {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception {
		MLabourWage wage = new MLabourWage(getCtx(), getRecord_ID(), get_TrxName());
		wage.processIt(DocAction.ACTION_Complete);
		wage.saveEx();
		return null;
	}

}
