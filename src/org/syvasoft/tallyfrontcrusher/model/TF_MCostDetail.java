package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MCostDetail;
import org.compiere.util.CLogger;

public class TF_MCostDetail extends MCostDetail {

	/**	Logger	*/
	private static CLogger 	s_log = CLogger.getCLogger (MCostDetail.class);
	
	public TF_MCostDetail(Properties ctx, int M_CostDetail_ID, String trxName) {
		super(ctx, M_CostDetail_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5808339235483312810L;

	/**
	 * 	Create New Invoice Cost Detail for AP Invoices.
	 * 	Called from Doc_Invoice - for Invoice Adjustments
	 *	@param as accounting schema
	 *	@param AD_Org_ID org
	 *	@param M_Product_ID product
	 *	@param M_AttributeSetInstance_ID asi
	 *	@param C_InvoiceLine_ID invoice
	 *	@param M_CostElement_ID optional cost element for Freight
	 *	@param Amt amt
	 *	@param Qty qty
	 *	@param Description optional description
	 *	@param trxName transaction
	 *	@return true if created
	 */
	public static boolean createBoulderReceipt (MAcctSchema as, int AD_Org_ID, 
		int M_Product_ID, int M_AttributeSetInstance_ID,
		int TF_Boulder_Receipt_ID, int M_CostElement_ID, 
		BigDecimal Amt, BigDecimal Qty,
		String Description, String trxName)
	{
		MCostDetail cd = get (as.getCtx(), "TF_Boulder_Receipt_ID=? AND Coalesce(M_CostElement_ID,0)="+M_CostElement_ID+" AND M_Product_ID="+M_Product_ID, 
			TF_Boulder_Receipt_ID, M_AttributeSetInstance_ID, as.getC_AcctSchema_ID(), trxName);
		//
		if (cd == null)		//	createNew
		{
			cd = new MCostDetail (as, AD_Org_ID, 
				M_Product_ID, M_AttributeSetInstance_ID, 
				M_CostElement_ID, 
				Amt, Qty, Description, trxName);
			cd.set_ValueOfColumn("TF_Boulder_Receipt_ID", TF_Boulder_Receipt_ID);
		}
		else
		{
			if (cd.isProcessed())
			{
				// MZ Goodwill
				// set deltaAmt=Amt, deltaQty=qty, and set Cost Detail for Amt and Qty	 
				cd.setDeltaAmt(Amt.subtract(cd.getAmt()));
				cd.setDeltaQty(Qty.subtract(cd.getQty()));
			}
			else
			{
				cd.setDeltaAmt(BigDecimal.ZERO);
				cd.setDeltaQty(BigDecimal.ZERO);
				cd.setAmt(Amt);
				cd.setQty(Qty);
			}
			if (cd.isDelta())
			{
				cd.setProcessed(false);
				cd.setAmt(Amt);
				cd.setQty(Qty);
			}
			// end MZ
			else if (cd.isProcessed())
				return true;	//	nothing to do
		}
		boolean ok = cd.save();
		if (ok && !cd.isProcessed())
		{
			ok = cd.process();
		}
		if (s_log.isLoggable(Level.CONFIG)) s_log.config("(" + ok + ") " + cd);
		return ok;
	}	//	createInvoice
}
