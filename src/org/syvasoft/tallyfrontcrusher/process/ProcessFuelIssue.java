package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MFuelIssue;

public class ProcessFuelIssue extends SvrProcess {
	
	private String docAction="CO";	
	MFuelIssue issue;
	@Override
	protected void prepare() {
		issue = new MFuelIssue(getCtx(), getRecord_ID(), get_TrxName());
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
		
		if(!issue.isProcessed())
			issue.processIt(DocAction.ACTION_Complete);		
		else if(docAction.equals("MO"))
			issue.reverseIt();
		
		issue.saveEx();
		return null;
	}

}
