package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MDocTypeCounter;
import org.compiere.model.MPayment;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.process.DocAction;

public class TF_MPayment extends MPayment {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6395097676538568134L;
	public TF_MPayment(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	public TF_MPayment(Properties ctx, int C_Payment_ID, String trxName) {
		super(ctx, C_Payment_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	 /** Column name CashType */
    public static final String COLUMNNAME_CashType = "CashType";
    
    /** General Expense = E */
	public static final String CASHTYPE_GeneralExpense = "E";
	/** General Receipt = R */
	public static final String CASHTYPE_GeneralReceipt = "R";
	/** Vendor Payment = V */
	public static final String CASHTYPE_VendorPayment = "V";
	/** Customer Payment = C */
	public static final String CASHTYPE_CustomerPayment = "C";
	/** Employee Payment = Y */
	public static final String CASHTYPE_EmployeePayment = "Y";
	/** Set Cash Type.
		@param CashType 
		Source of Cash
	  */
	public void setCashType (String CashType)
	{

		set_Value (COLUMNNAME_CashType, CashType);
	}

	/** Get Cash Type.
		@return Source of Cash
	  */
	public String getCashType () 
	{
		return (String)get_Value(COLUMNNAME_CashType);
	}
	
	
	/** Column name C_ElementValue_ID */
    public static final String COLUMNNAME_C_ElementValue_ID = "C_ElementValue_ID";
    
    public org.compiere.model.I_C_ElementValue getC_ElementValue() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getC_ElementValue_ID(), get_TrxName());	}

	/** Set Account Element.
		@param C_ElementValue_ID 
		Account Element
	  */
	public void setC_ElementValue_ID (int C_ElementValue_ID)
	{
		if (C_ElementValue_ID < 1) 
			set_Value (COLUMNNAME_C_ElementValue_ID, null);
		else 
			set_Value (COLUMNNAME_C_ElementValue_ID, Integer.valueOf(C_ElementValue_ID));
	}

	/** Get Account Element.
		@return Account Element
	  */
	public int getC_ElementValue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ElementValue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name Subcon_Invoice_ID */
    public static final String COLUMNNAME_Subcon_Invoice_ID = "Subcon_Invoice_ID";
	
    public org.compiere.model.I_C_Invoice getSubcon_Invoice() throws RuntimeException
    {
		return (org.compiere.model.I_C_Invoice)MTable.get(getCtx(), org.compiere.model.I_C_Invoice.Table_Name)
			.getPO(getSubcon_Invoice_ID(), get_TrxName());	}

	/** Set Subcontractor Invoice.
		@param Subcon_Invoice_ID Subcontractor Invoice	  */
	public void setSubcon_Invoice_ID (int Subcon_Invoice_ID)
	{
		if (Subcon_Invoice_ID < 1) 
			set_Value (COLUMNNAME_Subcon_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_Subcon_Invoice_ID, Integer.valueOf(Subcon_Invoice_ID));
	}

	/** Get Subcontractor Invoice.
		@return Subcontractor Invoice	  */
	public int getSubcon_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Subcon_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	
	/** Column name TF_BPartner_ID */
    public static final String COLUMNNAME_TF_BPartner_ID = "TF_BPartner_ID";
    public org.compiere.model.I_C_BPartner getTF_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getTF_BPartner_ID(), get_TrxName());	}

	/** Set Customer / Vendor.
		@param TF_BPartner_ID Customer / Vendor	  */
	public void setTF_BPartner_ID (int TF_BPartner_ID)
	{
		if (TF_BPartner_ID < 1) 
			set_Value (COLUMNNAME_TF_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_TF_BPartner_ID, Integer.valueOf(TF_BPartner_ID));
	}

	/** Get Customer / Vendor.
		@return Customer / Vendor	  */
	public int getTF_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
	
	/** Column name FromTo_BankAccount_ID */
    public static final String COLUMNNAME_FromTo_BankAccount_ID = "FromTo_BankAccount_ID";
	public org.compiere.model.I_C_BankAccount getFromTo_BankAccount() throws RuntimeException
    {
		return (org.compiere.model.I_C_BankAccount)MTable.get(getCtx(), org.compiere.model.I_C_BankAccount.Table_Name)
			.getPO(getFromTo_BankAccount_ID(), get_TrxName());	}

	/** Set From/To Bank/Cash Account .
		@param FromTo_BankAccount_ID From/To Bank/Cash Account 	  */
	public void setFromTo_BankAccount_ID (int FromTo_BankAccount_ID)
	{
		if (FromTo_BankAccount_ID < 1) 
			set_Value (COLUMNNAME_FromTo_BankAccount_ID, null);
		else 
			set_Value (COLUMNNAME_FromTo_BankAccount_ID, Integer.valueOf(FromTo_BankAccount_ID));
	}

	/** Get From/To Bank/Cash Account .
		@return From/To Bank/Cash Account 	  */
	public int getFromTo_BankAccount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FromTo_BankAccount_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
    
	/** Column name IsInterCashBookEntry */
    public static final String COLUMNNAME_IsInterCashBookEntry = "IsInterCashBookEntry";
    /** Set Inter Cash Book Entry.
     * 
	@param IsInterCashBookEntry Inter Cash Book Entry	  */
	public void setIsInterCashBookEntry (boolean IsInterCashBookEntry)
	{
		set_Value (COLUMNNAME_IsInterCashBookEntry, Boolean.valueOf(IsInterCashBookEntry));
	}
	
	/** Get Inter Cash Book Entry.
		@return Inter Cash Book Entry	  */
	public boolean isInterCashBookEntry () 
	{
		Object oo = get_Value(COLUMNNAME_IsInterCashBookEntry);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
    
	/** Column name IsAutocomplete */
    public static final String COLUMNNAME_IsAutocomplete = "IsAutocomplete";
    /** Set Autocomplete.
	@param IsAutocomplete 
	Automatic completion for textfields
	  */
	public void setIsAutocomplete (boolean IsAutocomplete)
	{
		set_Value (COLUMNNAME_IsAutocomplete, Boolean.valueOf(IsAutocomplete));
	}
	
	/** Get Autocomplete.
		@return Automatic completion for textfields
	  */
	public boolean isAutocomplete () 
	{
		Object oo = get_Value(COLUMNNAME_IsAutocomplete);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		
		boolean ok = super.afterSave(newRecord, success);
		if(isAutocomplete() && getDocStatus().equals(DOCSTATUS_Drafted)) {
			if(!processIt(DocAction.ACTION_Complete))
				throw new AdempiereException("Failed when processing document - " + getProcessMsg());			
		}
		return ok;
	}
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(newRecord || is_ValueChanged(COLUMNNAME_C_ElementValue_ID)) {
			//if(getC_ElementValue_ID()>0 ) { 
				TF_MCharge charge = TF_MCharge.createChargeFromAccount(getCtx(), getC_ElementValue_ID(), get_TrxName());
				if(charge != null )
					setC_Charge_ID(charge.get_ID());
			//}
		}
		
		setIsReceipt(getC_DocType().isSOTrx());
		
		if(getC_ElementValue_ID()==0)
			setC_ElementValue_ID(0);
		
		if(getC_Invoice_ID() > 0 && isReceipt() != getC_Invoice().isSOTrx())
			throw new AdempiereException("Invalid Invoice for the selected Document Type!");
		
		if(getC_Invoice_ID() > 0 && getC_BPartner_ID() > 0 && getTF_BPartner_ID() ==0)
			setTF_BPartner_ID(getC_BPartner_ID());
		
		return super.beforeSave(newRecord);
	}
	
	@Override
	public String completeIt() {
		//Subcontract / Job Work
		if(getC_Project_ID() > 0) {
			MJobworkCharges.updateJobworkCharges(getCtx(), getC_Project_ID(), getC_Charge_ID(), getPayAmt(), get_TrxName());
		}		
		String msg = super.completeIt();
		createInterCashBookEntry();
		return msg;
	}
	@Override
	public boolean reverseCorrectIt() {		
		if(getSubcon_Invoice_ID()>0) {			
			throw new AdempiereException("You cannot modify this entry before Reverse Correct Subcontractor Invoice!");
		}
		
		//Subcontract / Job Work
		if(getC_Project_ID() > 0) {
			MJobworkCharges.updateJobworkCharges(getCtx(), getC_Project_ID(), getC_Charge_ID(), getPayAmt().negate(), get_TrxName());
		}		
		boolean ok = super.reverseCorrectIt();
		ok = ok && reverseInterCashBookEntry();
		return ok;
	}
	
	public void createInterCashBookEntry() {
		if(!isInterCashBookEntry() || getRef_Payment_ID() > 0)
			return;
		
		MDocTypeCounter counterDoc = new Query(getCtx(), MDocTypeCounter.Table_Name, "C_DocType_ID=? OR Counter_C_DocType_ID=?", null)
				.setClient_ID().setParameters(getC_DocType_ID(), getC_DocType_ID()).first();
		int c_doctype_id = 0;
		
		if(counterDoc != null ) {
			if(getC_DocType_ID() == counterDoc.getC_DocType_ID())
				c_doctype_id = counterDoc.getCounter_C_DocType_ID();
			else
				c_doctype_id = counterDoc.getC_DocType_ID();
		}
		
		TF_MPayment payment = new TF_MPayment(getCtx(), 0, get_TrxName());
		payment.setAD_Org_ID(getAD_Org_ID());
		payment.setRef_Payment_ID(getC_Payment_ID());
		payment.setDateTrx(getDateTrx());
		payment.setDateAcct(getDateAcct());		
		payment.setC_BankAccount_ID(getFromTo_BankAccount_ID());
		payment.setIsInterCashBookEntry(true);
		payment.setFromTo_BankAccount_ID(getC_BankAccount_ID());
		payment.setC_DocType_ID(c_doctype_id);
		payment.setIsReceipt(!isReceipt());
		payment.setC_ElementValue_ID(getC_ElementValue_ID());
		payment.setUser1_ID(getUser1_ID());
		payment.setUser2_ID(getUser2_ID());
		payment.setC_BPartner_ID(getC_BPartner_ID());
		payment.setC_Charge_ID(getC_Charge_ID());
		payment.setC_Currency_ID(getC_Currency_ID());
		payment.setPayAmt(getPayAmt());
		payment.setTenderType(getTenderType());
		payment.setDescription(getDescription());		
		payment.saveEx();
		
		if(!payment.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + payment.getProcessMsg());
		payment.saveEx();
		
		setRef_Payment_ID(payment.getC_Payment_ID());
		
	}
	
	
	public boolean reverseInterCashBookEntry() {
		if(!isInterCashBookEntry())
			return true;
		
		//Call this method before current document is reversed.
		//Because following condition enforces so
		MPayment counter = new MPayment(getCtx(), getRef_Payment_ID(), get_TrxName());
		if(counter.getDocStatus().equals(DOCSTATUS_Completed)){
			 boolean ok = counter.reverseCorrectIt();
			 counter.saveEx();
			 return ok;
		}
		
		return true;
		
	}
	
}
