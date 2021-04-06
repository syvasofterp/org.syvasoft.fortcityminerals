package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MCrusherProduction;
import org.syvasoft.tallyfrontcrusher.model.MDispensePlan;
import org.syvasoft.tallyfrontcrusher.model.MPMJob;

public class ProcessDispensePlanLine extends SvrProcess {

	int m_recordID = 0;
	MDispensePlan dispensePlan;
	private String docAction="CO";
	@Override
	protected void prepare() {
		m_recordID = getRecord_ID();
		dispensePlan = new MDispensePlan(getCtx(), m_recordID, get_TrxName());
		ProcessInfoParameter[] para = getParameter();		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();
			if (name.equals("DocAction"))
				docAction =  para[i].getParameterAsString();
		}
	}

	@Override
	protected String doIt() throws Exception {
		String m_processMsg = null;
		if(!docAction.equals("VO")) {
			dispensePlan.processIt(docAction);
		}
		else if(docAction.equals("VO")) {
			dispensePlan.reverseIt();
		}
		
		dispensePlan.saveEx();
		return m_processMsg;
	}

}
