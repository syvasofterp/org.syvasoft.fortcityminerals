package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.DocAction;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MLabourWageAdvance;

public class ProcessLabourWageAdvance extends SvrProcess {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception {
		MLabourWageAdvance wageAdv = new MLabourWageAdvance(getCtx(), getRecord_ID(), get_TrxName());
		wageAdv.processIt(DocAction.ACTION_Complete);
		wageAdv.saveEx();
		return null;
	}

}
