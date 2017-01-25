package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MJournal;
import org.compiere.model.MJournalLine;
import org.compiere.model.MPeriod;
import org.compiere.util.Env;

public class MVehicleRent extends X_TF_Vehicle_Rent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4964964952091340862L;

	public MVehicleRent(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MVehicleRent(Properties ctx, int TF_Vehicle_Rent_ID, String trxName) {
		super(ctx, TF_Vehicle_Rent_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public void processIt(String DocAction) {
		if(MBoulderReceipt.DOCACTION_Prepare.equals(DocAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(MBoulderReceipt.DOCACTION_Complete.equals(DocAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			
			MJournal j = new MJournal(getCtx(), 0, get_TrxName());
			j.setDescription("Generated from Vehicle Rent Entry - " + getTF_Vehicle_Rent_ID());
			j.setC_AcctSchema_ID(Env.getContextAsInt(getCtx(), "$C_AcctSchema_ID"));
			j.setC_Currency_ID(Env.getContextAsInt(getCtx(), "$C_Currency_ID"));
			j.setPostingType(MJournal.POSTINGTYPE_Actual);
			j.setC_DocType_ID(1000000);
			j.setDateDoc(getDateAcct());
			j.setDateAcct(getDateAcct());
			j.setDocStatus(DOCSTATUS_Drafted);
			MPeriod period = MPeriod.get(getCtx(), getDateAcct());
			j.setC_Period_ID(period.getC_Period_ID());
			j.setGL_Category_ID(1000000);
			j.setC_ConversionType_ID(114);
			j.saveEx();
			
			//Vehicle Expense
			MJournalLine jl = new MJournalLine(j);
			jl.setLine(10);			
			jl.setAccount_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getVehicleExp_Acct());
			jl.setUser1_ID(getC_ElementValue_ID()); // Quarry Profit Center
			jl.setAmtSourceDr(getRent_Amt());
			jl.setAmtAcctDr(getRent_Amt());
			jl.setIsGenerated(true);
			jl.saveEx();
			
			//Vehicle Rent
			jl = new MJournalLine(j);
			jl.setLine(20);			
			jl.setAccount_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getVehicleRent_Acct());
			jl.setUser1_ID(getCreditC_ElementValue_ID()); // Vehicle Profit Center
			jl.setAmtSourceCr(getRent_Amt());
			jl.setAmtAcctCr(getRent_Amt());
			jl.setIsGenerated(true);
			jl.saveEx();
			
			j.processIt(MJournal.ACTION_Complete);
			j.saveEx();
			
			setGL_Journal_ID(j.getGL_Journal_ID());
		}
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(isCalculated()) {
			setRent_Amt(getStd_Rent().multiply(getRented_Days().divide(getStd_Days())));
		}
		//TODO: set credit profit center from Vehicle.
		return super.beforeSave(newRecord);
	}
	
}
