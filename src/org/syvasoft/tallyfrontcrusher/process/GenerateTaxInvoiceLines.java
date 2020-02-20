package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MGenerateTaxInvoice;
import org.syvasoft.tallyfrontcrusher.model.MYardEntryApprove;

public class GenerateTaxInvoiceLines extends SvrProcess {
	private boolean recreate = false;
	MGenerateTaxInvoice taxInvoice = null;
	
	protected void prepare() {
		for(ProcessInfoParameter para : getParameter())
		{
			String name = para.getParameterName();
			if("Recreate".equals(name))
				recreate = "Y".equals(para.getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		taxInvoice = new MGenerateTaxInvoice(getCtx(), getRecord_ID(), get_TrxName());
	}

	@Override
	protected String doIt() throws Exception {
		if(taxInvoice.isProcessed())
			return "Already Processed";
		taxInvoice.setRoundOff(BigDecimal.ZERO);		
		taxInvoice.saveEx();
		taxInvoice.createInvoiceLines(recreate);		
		taxInvoice.saveEx();
		return null;
	}
}
