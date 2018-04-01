package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.DocAction;

public class MTaxInvoice extends X_TF_TaxInvoice {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5345401658705556449L;

	public MTaxInvoice(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MTaxInvoice(Properties ctx, int TF_TaxInvoice_ID, String trxName) {
		super(ctx, TF_TaxInvoice_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
		
	
	public void processIt(String docAction) {
		if(DocAction.ACTION_Prepare.equals(docAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(DocAction.ACTION_Complete.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);			
			MCrusherPermitLedger.issuePermit(this);			
		}
	}
	
	public void reverseIt() {
		setDocStatus(DOCSTATUS_InProgress);
		setProcessed(false);		
		MCrusherPermitLedger.reverseIssuedPermit(this);
	}

}
