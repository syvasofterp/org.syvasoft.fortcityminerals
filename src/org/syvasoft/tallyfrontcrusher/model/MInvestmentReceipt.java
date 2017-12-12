package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.process.DocAction;

public class MInvestmentReceipt extends X_TF_InvestmentReceipt {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3277316969251826275L;

	public MInvestmentReceipt(Properties ctx, int TF_InvestmentReceipt_ID, String trxName) {
		super(ctx, TF_InvestmentReceipt_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MInvestmentReceipt(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public void processIt(String docAction) {
		if(DocAction.ACTION_Prepare.equals(docAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(DocAction.ACTION_Complete.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			
		}
	}
	
	public void reverseIt() {
		
	}
}

