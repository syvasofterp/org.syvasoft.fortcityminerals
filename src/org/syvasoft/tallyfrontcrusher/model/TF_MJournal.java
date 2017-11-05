package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MJournal;
import org.compiere.model.MJournalBatch;
import org.compiere.model.MJournalLine;
import org.compiere.model.MTable;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class TF_MJournal extends MJournal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8714595661068888689L;

	public TF_MJournal(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public TF_MJournal(Properties ctx, int GL_Journal_ID, String trxName) {
		super(ctx, GL_Journal_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public TF_MJournal(MJournalBatch parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

	public TF_MJournal(MJournal original) {
		super(original);
		// TODO Auto-generated constructor stub
	}

	/** Column name IsQuickEntry */
    public static final String COLUMNNAME_IsQuickEntry = "IsQuickEntry";
    
    /** Set Quick Entry.
	@param IsQuickEntry Quick Entry	  */
    public void setIsQuickEntry (boolean IsQuickEntry)
	{
		set_Value (COLUMNNAME_IsQuickEntry, Boolean.valueOf(IsQuickEntry));
	}
	
	/** Get Quick Entry.
		@return Quick Entry	  */
	public boolean isQuickEntry () 
	{
		Object oo = get_Value(COLUMNNAME_IsQuickEntry);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Column name TF_DebitAcct_ID */
    public static final String COLUMNNAME_TF_DebitAcct_ID = "TF_DebitAcct_ID";
    
    public org.compiere.model.I_C_ElementValue getTF_DebitAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getTF_DebitAcct_ID(), get_TrxName());	}

	/** Set Debit Account.
		@param TF_DebitAcct_ID Debit Account	  */
	public void setTF_DebitAcct_ID (int TF_DebitAcct_ID)
	{
		if (TF_DebitAcct_ID < 1) 
			set_Value (COLUMNNAME_TF_DebitAcct_ID, null);
		else 
			set_Value (COLUMNNAME_TF_DebitAcct_ID, Integer.valueOf(TF_DebitAcct_ID));
	}

	/** Get Debit Account.
		@return Debit Account	  */
	public int getTF_DebitAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_DebitAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Column name TF_DebitLine_ID */
    public static final String COLUMNNAME_TF_DebitLine_ID = "TF_DebitLine_ID";
    
    public org.compiere.model.I_GL_JournalLine getTF_DebitLine() throws RuntimeException
    {
		return (org.compiere.model.I_GL_JournalLine)MTable.get(getCtx(), org.compiere.model.I_GL_JournalLine.Table_Name)
			.getPO(getTF_DebitLine_ID(), get_TrxName());	}

	/** Set Journal Debit Account Line.
		@param TF_DebitLine_ID Journal Debit Account Line	  */
	public void setTF_DebitLine_ID (int TF_DebitLine_ID)
	{
		if (TF_DebitLine_ID < 1) 
			set_Value (COLUMNNAME_TF_DebitLine_ID, null);
		else 
			set_Value (COLUMNNAME_TF_DebitLine_ID, Integer.valueOf(TF_DebitLine_ID));
	}

	/** Get Journal Debit Account Line.
		@return Journal Debit Account Line	  */
	public int getTF_DebitLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_DebitLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name TF_CreditAcct_ID */
    public static final String COLUMNNAME_TF_CreditAcct_ID = "TF_CreditAcct_ID";
    
    public org.compiere.model.I_C_ElementValue getTF_CreditAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getTF_CreditAcct_ID(), get_TrxName());	}

	/** Set Credit Account.
		@param TF_CreditAcct_ID Credit Account	  */
	public void setTF_CreditAcct_ID (int TF_CreditAcct_ID)
	{
		if (TF_CreditAcct_ID < 1) 
			set_Value (COLUMNNAME_TF_CreditAcct_ID, null);
		else 
			set_Value (COLUMNNAME_TF_CreditAcct_ID, Integer.valueOf(TF_CreditAcct_ID));
	}

	/** Get Credit Account.
		@return Credit Account	  */
	public int getTF_CreditAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_CreditAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name TF_CreditLine_ID */
    public static final String COLUMNNAME_TF_CreditLine_ID = "TF_CreditLine_ID";
    
    public org.compiere.model.I_GL_JournalLine getTF_CreditLine() throws RuntimeException
    {
		return (org.compiere.model.I_GL_JournalLine)MTable.get(getCtx(), org.compiere.model.I_GL_JournalLine.Table_Name)
			.getPO(getTF_CreditLine_ID(), get_TrxName());	}

	/** Set Journal Credit Account Line.
		@param TF_CreditLine_ID Journal Credit Account Line	  */
	public void setTF_CreditLine_ID (int TF_CreditLine_ID)
	{
		if (TF_CreditLine_ID < 1) 
			set_Value (COLUMNNAME_TF_CreditLine_ID, null);
		else 
			set_Value (COLUMNNAME_TF_CreditLine_ID, Integer.valueOf(TF_CreditLine_ID));
	}

	/** Get Journal Credit Account Line.
		@return Journal Credit Account Line	  */
	public int getTF_CreditLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_CreditLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Column name Amount */
    public static final String COLUMNNAME_Amount = "Amount";
    
    /** Set Amount.
	@param Amount 
	Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount)
	{
		set_Value (COLUMNNAME_Amount, Amount);
	}
	
	/** Get Amount.
		@return Amount in a defined currency
	  */
	public BigDecimal getAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}


	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		// TODO Auto-generated method stub
		boolean ok = super.afterSave(newRecord, success);
		updateQuickEntryLines(newRecord);
		return ok;
	}
	
	private void updateQuickEntryLines(boolean newRecord) {
		boolean isDebitDeleted = false;
		boolean isCreditDeleted = false;
		
		//Delete Debit Line when Debit Account is empty.
		if(getTF_DebitAcct_ID() == 0 && getTF_DebitLine_ID() > 0) {
			MJournalLine jl = new MJournalLine(getCtx(), getTF_DebitLine_ID(), get_TrxName());			
			jl.deleteEx(true);
			DB.executeUpdate("UPDATE GL_Journal SET " + COLUMNNAME_TF_DebitLine_ID + " = NULL "
					+ " WHERE GL_Journal_ID = " + getGL_Journal_ID(), get_TrxName());
			
			isDebitDeleted = true;
		}			
		
		//Delete Credit Line when Credit Account is empty.
		if(getTF_CreditAcct_ID() == 0 && getTF_CreditLine_ID() > 0) {
			MJournalLine jl = new MJournalLine(getCtx(), getTF_CreditLine_ID(), get_TrxName());
			jl.deleteEx(true);
			DB.executeUpdate("UPDATE GL_Journal SET " + COLUMNNAME_TF_CreditLine_ID + " = NULL "
					+ " WHERE GL_Journal_ID = " + getGL_Journal_ID(), get_TrxName());
			isCreditDeleted = true;
		}			
		
		if(isDebitDeleted && isCreditDeleted)
			return;
		
		boolean isAmountChanged = is_ValueChanged(COLUMNNAME_Amount);
		
		//Create/Update Debit Journal Line from Quick Entry field.
		if((getTF_DebitAcct_ID() > 0  && getTF_DebitLine_ID() == 0) || 
				((is_ValueChanged(COLUMNNAME_TF_DebitAcct_ID) || isAmountChanged) && getTF_DebitLine_ID() > 0)) {
			MJournalLine drjl;
			if(getTF_DebitLine_ID() > 0) {
				drjl = new MJournalLine(getCtx(), getTF_DebitLine_ID(), get_TrxName());
			}
			else {
				drjl = new MJournalLine(this);
				drjl.setLine(10);
			}
			drjl.setAccount_ID(getTF_DebitAcct_ID());
			drjl.setAmtSourceDr(getAmount());
			drjl.setAmtAcctDr(getAmount());
			drjl.setIsGenerated(true);
			drjl.saveEx();
			
			if(getTF_DebitLine_ID() == 0)
			DB.executeUpdate("UPDATE GL_Journal SET " + COLUMNNAME_TF_DebitLine_ID + " = "
					+ drjl.getGL_JournalLine_ID() + " WHERE GL_Journal_ID = " + getGL_Journal_ID(), get_TrxName());			
		}
		
		//Create/Update Credit Journal Line from Quick Entry field.
		if((getTF_CreditAcct_ID() > 0  && getTF_CreditLine_ID() == 0) || 
				((is_ValueChanged(COLUMNNAME_TF_CreditAcct_ID) || isAmountChanged) && getTF_CreditLine_ID() > 0)) {
			MJournalLine drjl;
			if(getTF_CreditLine_ID() > 0) {
				drjl = new MJournalLine(getCtx(), getTF_CreditLine_ID(), get_TrxName());
			}
			else {
				drjl = new MJournalLine(this);
				drjl.setLine(20);
			}
			drjl.setAccount_ID(getTF_CreditAcct_ID());
			drjl.setAmtSourceCr(getAmount());
			drjl.setAmtAcctCr(getAmount());
			drjl.setIsGenerated(true);
			drjl.saveEx();
			
			if(getTF_CreditLine_ID() == 0)
			DB.executeUpdate("UPDATE GL_Journal SET " + COLUMNNAME_TF_CreditLine_ID + " = "
					+ drjl.getGL_JournalLine_ID() + " WHERE GL_Journal_ID = " + getGL_Journal_ID(), get_TrxName());			
		}
			
	}

}
