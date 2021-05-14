package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.process.SvrProcess;
import org.compiere.util.Env;

public class SetWhatsAppContextVariables extends SvrProcess {

	@Override
	protected void prepare() {
		

	}

	@Override
	protected String doIt() throws Exception {
		
		Env.setContext(Env.getCtx(), "#WhatsAppPhone", "9941335361");
		Env.setContext(Env.getCtx(), "#WhatsAppMessage", "Thank you for your business!, Fortcity Minerals");
		
		return null;
	}

}
