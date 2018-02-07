package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MYardEntryApprove;

public class ApproveYardEntry extends SvrProcess {
	private String docAction="CO";
	MYardEntryApprove appYEntries = null;
	@Override
	protected void prepare() {
		appYEntries = new MYardEntryApprove(getCtx(), getRecord_ID(), get_TrxName());
		
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
		if(!appYEntries.isProcessed())
			appYEntries.processIt(MBoulderReceipt.DOCACTION_Complete);
		else if(appYEntries.isProcessed() && docAction.equals("MO"))
			appYEntries.reverseIt();
		
		appYEntries.saveEx();	
		return null;
	}

}
