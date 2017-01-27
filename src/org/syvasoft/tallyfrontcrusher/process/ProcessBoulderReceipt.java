package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;

public class ProcessBoulderReceipt extends SvrProcess {

	int m_recordID = 0;
	@Override
	protected void prepare() {
		m_recordID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		String m_processMsg = null;
		MBoulderReceipt br = new MBoulderReceipt(getCtx(), m_recordID, get_TrxName());
		m_processMsg = br.processIt(MBoulderReceipt.DOCACTION_Complete);
		if(m_processMsg == null)
			br.saveEx();
		else
			rollback();
		return m_processMsg;
	}

}
