package org.syvasoft.tallyfrontcrusher.event;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventTopics;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.osgi.service.event.Event;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MGLPostingConfig;


public class CrusherEventHandler extends AbstractEventHandler {

	CLogger log = CLogger.getCLogger(CrusherEventHandler.class);
	@Override
	protected void initialize() {
		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, MInvoice.Table_Name);

	}

	@Override
	protected void doHandleEvent(Event event) {
		PO po = getPO(event);
		if(po instanceof MInvoice) {
			MInvoice inv = (MInvoice) po;
			MInvoiceLine[] lines = inv.getLines();
			//Post Jobwork Expense Variance Journal for Subcontractor Invoice
			MGLPostingConfig glConfig = MGLPostingConfig.getMGLPostingConfig(inv.getCtx());
			for(MInvoiceLine line : lines) {
				if(line.getM_Product_ID() == glConfig.getJobWork_Product_ID()) {
					MBoulderReceipt.postJobworkExpenseVarianceJournal(inv.getCtx(), inv, line.getPriceEntered(), inv.get_TrxName());
					break;
				}
			}
		}
	}

}
