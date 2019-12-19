package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;

public class MTRTaxInvoiceLine extends X_TF_TRTaxInvoiceLine {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7092237924258680168L;

	public MTRTaxInvoiceLine(Properties ctx, int TF_TRTaxInvoiceLine_ID, String trxName) {
		super(ctx, TF_TRTaxInvoiceLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MTRTaxInvoiceLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		updateHeaderTotal();
		return super.afterSave(newRecord, success);
	}
	
	@Override
	protected boolean afterDelete(boolean success) {
		updateHeaderTotal();
		return super.afterDelete(success);
	}
	
	private void updateHeaderTotal() {
		String Sql="SELECT SUM(LineTotalAmt) FROM TF_TRTaxInvoiceLine WHERE TF_TRTaxInvoice_ID=?";
		BigDecimal RoundOffAmt = BigDecimal.ZERO;
		BigDecimal GrandTotal = DB.getSQLValueBD(get_TrxName(), Sql, getTF_TRTaxInvoice_ID());
		
		MTRTaxInvoice ti= new MTRTaxInvoice(getCtx(), getTF_TRTaxInvoice_ID(), get_TrxName());
		ti.setGrandTotal(GrandTotal);
		RoundOffAmt = ti.getRoundOff();
		ti.setTotal(GrandTotal.add(RoundOffAmt));
		ti.saveEx();
	}

}
