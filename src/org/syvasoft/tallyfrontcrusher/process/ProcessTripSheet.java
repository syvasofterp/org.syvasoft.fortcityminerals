package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.DocAction;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MTripSheet;

public class ProcessTripSheet extends SvrProcess {

	@Override
	protected void prepare() {
		
	}

	@Override
	protected String doIt() throws Exception {
		MTripSheet tripSheet = new MTripSheet(getCtx(), getRecord_ID(), get_TrxName());
		tripSheet.processIt(DocAction.ACTION_Complete);
		tripSheet.saveEx();
		return null;
	}

}
