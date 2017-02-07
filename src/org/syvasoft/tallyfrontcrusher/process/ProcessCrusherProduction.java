package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MCrusherProduction;

public class ProcessCrusherProduction extends SvrProcess {

	int m_recordID = 0;
	@Override
	protected void prepare() {
		m_recordID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		String m_processMsg = null;
		MCrusherProduction crProd = new MCrusherProduction(getCtx(), m_recordID, get_TrxName());
		m_processMsg = crProd.processIt(MBoulderReceipt.DOCACTION_Complete);
		if(m_processMsg == null)
			crProd.saveEx();
		else
			rollback();
		return m_processMsg;
	}

}
