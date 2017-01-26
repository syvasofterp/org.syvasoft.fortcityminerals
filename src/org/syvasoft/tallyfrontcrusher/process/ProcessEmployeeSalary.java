package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MEmployeeSalary;

public class ProcessEmployeeSalary extends SvrProcess {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception {
		MEmployeeSalary salary = new MEmployeeSalary(getCtx(), getRecord_ID(), get_TrxName());
		salary.processIt(MBoulderReceipt.DOCACTION_Complete);
		salary.saveEx();
		return null;
	}

}
