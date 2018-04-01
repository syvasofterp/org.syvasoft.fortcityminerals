package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.DB;

public class MPermitPurchase extends X_TF_PermitPurchase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7851195614811119351L;

	public MPermitPurchase(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MPermitPurchase(Properties ctx, int TF_PermitPurchase_ID, String trxName) {
		super(ctx, TF_PermitPurchase_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public BigDecimal getRemainingBreakupQty() {
		String sql = "SELECT SUM(PermitQty) FROM TF_PermitPurchaseLine WHERE TF_PermitPurchase_ID = ?";
		BigDecimal balanceQty = DB.getSQLValueBD(get_TrxName(), sql, getTF_PermitPurchase_ID());
		if(balanceQty == null)
			balanceQty = BigDecimal.ZERO;
		return balanceQty;
	}
	
	public void processIt(String docAction) {
		if(DocAction.ACTION_Prepare.equals(docAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(DocAction.ACTION_Complete.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			
			if(getPermitQty().doubleValue() - getRemainingBreakupQty().doubleValue() != 0) {
				throw new AdempiereException("Please add / adjust Permit Breakup Quantities for Materials without any balance in Permit Purchase Qty!");
			}
			
			MCrusherPermitLedger.purchasePermit(this);						
		}
	}
	public void reverseIt() {
		setDocStatus(DOCSTATUS_InProgress);
		setProcessed(false);		
		MCrusherPermitLedger.reversePurchasedPermit(getAD_Org_ID(), getTF_PermitPurchase_ID(), get_TrxName());		
	}
		
}
