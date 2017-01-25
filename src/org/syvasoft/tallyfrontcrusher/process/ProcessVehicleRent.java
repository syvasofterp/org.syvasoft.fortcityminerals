package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRent;

public class ProcessVehicleRent extends SvrProcess {
	
	@Override
	protected void prepare() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception {
		MVehicleRent vRent = new MVehicleRent(getCtx(), getRecord_ID(), get_TrxName());
		vRent.processIt(MBoulderReceipt.DOCACTION_Complete);
		vRent.saveEx();
		return null;
	}

}
