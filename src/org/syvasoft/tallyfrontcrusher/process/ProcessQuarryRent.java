package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MQuarryRent;

public class ProcessQuarryRent extends SvrProcess {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception {
		MQuarryRent qRent = new MQuarryRent(getCtx(), getRecord_ID(), get_TrxName());
		qRent.processIt(MBoulderReceipt.DOCACTION_Complete);
		qRent.saveEx();
		return null;
	}

}
