package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MDocTypeCounter;
import org.compiere.model.MJournalLine;
import org.compiere.model.MPayment;
import org.compiere.model.MPeriod;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.DB;
import org.compiere.util.Env;

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
	
    /** Column name Salary_Amt */
    public static final String COLUMNNAME_Salary_Amt = "Salary_Amt";
    /** Set Earned Salary.
	@param Salary_Amt Earned Salary	  */
	public void setSalary_Amt (BigDecimal Salary_Amt)
	{
		set_Value (COLUMNNAME_Salary_Amt, Salary_Amt);
	}
	
	/** Get Earned Salary.
		@return Earned Salary	  */
	public BigDecimal getSalary_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Salary_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
    /** Column name Advance_Paid */
    public static final String COLUMNNAME_Advance_Paid = "Advance_Paid";
    /** Set Advance Paid.
	@param Advance_Paid Advance Paid	  */
	public void setAdvance_Paid (BigDecimal Advance_Paid)
	{
		set_Value (COLUMNNAME_Advance_Paid, Advance_Paid);
	}
	
	/** Get Advance Paid.
		@return Advance Paid	  */
	public BigDecimal getAdvance_Paid () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Advance_Paid);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

    /** Column name Advance_Deduct */
    public static final String COLUMNNAME_Advance_Deduct = "Advance_Deduct";
    
    /** Set Deduct Advance.
	@param Advance_Deduct Deduct Advance	  */
	public void setAdvance_Deduct (BigDecimal Advance_Deduct)
	{
		set_Value (COLUMNNAME_Advance_Deduct, Advance_Deduct);
	}
	
	/** Get Deduct Advance.
		@return Deduct Advance	  */
	public BigDecimal getAdvance_Deduct () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Advance_Deduct);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	/** Column name Advance_Balance */
    public static final String COLUMNNAME_Advance_Balance = "Advance_Balance";
    /** Set Balance Advance.
	@param Advance_Balance Balance Advance	  */
	public void setAdvance_Balance (BigDecimal Advance_Balance)
	{
		set_Value (COLUMNNAME_Advance_Balance, Advance_Balance);
	}
	
	/** Get Balance Advance.
		@return Balance Advance	  */
	public BigDecimal getAdvance_Balance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Advance_Balance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

    /** Column name Salary_Payable */
    public static final String COLUMNNAME_Salary_Payable = "Salary_Payable";
    /** Set Balance Salary.
	@param Salary_Payable Balance Salary	  */
	public void setSalary_Payable (BigDecimal Salary_Payable)
	{
		set_Value (COLUMNNAME_Salary_Payable, Salary_Payable);
	}
	
	/** Get Balance Salary.
		@return Balance Salary	  */
	public BigDecimal getSalary_Payable () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Salary_Payable);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	/** Column name EmpAdv_Journal_ID */
    public static final String COLUMNNAME_EmpAdv_Journal_ID = "EmpAdv_Journal_ID";
    public org.compiere.model.I_GL_Journal getEmpAdv_Journal() throws RuntimeException
    {
		return (org.compiere.model.I_GL_Journal)MTable.get(getCtx(), org.compiere.model.I_GL_Journal.Table_Name)
			.getPO(getEmpAdv_Journal_ID(), get_TrxName());	}

	/** Set Advance Deduction Journal.
		@param EmpAdv_Journal_ID 
		Advance Deduction Journal
	  */
	public void setEmpAdv_Journal_ID (int EmpAdv_Journal_ID)
	{
		if (EmpAdv_Journal_ID < 1) 
			set_Value (COLUMNNAME_EmpAdv_Journal_ID, null);
		else 
			set_Value (COLUMNNAME_EmpAdv_Journal_ID, Integer.valueOf(EmpAdv_Journal_ID));
	}

	/** Get Advance Deduction Journal.
		@return Advance Deduction Journal
	  */
	public int getEmpAdv_Journal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EmpAdv_Journal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name IsEmployee */
    public static final String COLUMNNAME_IsEmployee = "IsEmployee";
    /** Set Employee.
	@param IsEmployee 
	Indicates if  this Business Partner is an employee
  */
	public void setIsEmployee (boolean IsEmployee)
	{
		set_Value (COLUMNNAME_IsEmployee, Boolean.valueOf(IsEmployee));
	}
	
	/** Get Employee.
		@return Indicates if  this Business Partner is an employee
	  */
	public boolean isEmployee () 
	{
		Object oo = get_Value(COLUMNNAME_IsEmployee);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

    /** Column name IsSalaryPayment */
    public static final String COLUMNNAME_IsSalaryPayment = "IsSalaryPayment";
    /** Set Salary Payment.
	@param IsSalaryPayment Salary Payment	  */
	public void setIsSalaryPayment (boolean IsSalaryPayment)
	{
		set_Value (COLUMNNAME_IsSalaryPayment, Boolean.valueOf(IsSalaryPayment));
	}
	
	/** Get Salary Payment.
		@return Salary Payment	  */
	public boolean isSalaryPayment () 
	{
		Object oo = get_Value(COLUMNNAME_IsSalaryPayment);
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
		if(isAutocomplete() && (getDocStatus().equals(DOCSTATUS_Drafted) || getDocStatus().equals(DOCSTATUS_InProgress))) {
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
		if(isEmployee())
			postAdvanceAdjustmentJournal();
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
		
		reverseAdvanceAdjustmentJournal();
		
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
	
	public static String getInterCashBookDescription(int fromBank_ID, int toBank_ID) {
		TF_MBankAccount ac1 = new TF_MBankAccount(Env.getCtx(), fromBank_ID, null);
		TF_MBankAccount ac2 = new TF_MBankAccount(Env.getCtx(), toBank_ID, null);
		String desc = "Cash transferred from " + ac1.getName() + " to " + ac2.getName();
		return desc;
	}
	
	public void postAdvanceAdjustmentJournal() {
		//Posting Opening Balance GL journal 
		int m_C_DocTypeTarget_ID = 1000000;
		MGLPostingConfig glConfig = MGLPostingConfig.getMGLPostingConfig(getCtx());
		TF_MJournal j = new TF_MJournal(getCtx(), 0, get_TrxName());
		j.setDescription("Advance Deducted from Cash Book Entry #" + getDocumentNo());
		j.setAD_Org_ID(getAD_Org_ID());
		j.setC_AcctSchema_ID(Env.getContextAsInt(getCtx(), "$C_AcctSchema_ID"));
		j.setC_Currency_ID(Env.getContextAsInt(getCtx(), "$C_Currency_ID"));
		j.setPostingType(TF_MJournal.POSTINGTYPE_Actual);
		j.setC_DocType_ID(m_C_DocTypeTarget_ID);
		j.setDateDoc(getDateTrx());
		j.setDateAcct(getDateAcct());
		j.setDocStatus(TF_MJournal.DOCSTATUS_Drafted);
		MPeriod period = MPeriod.get(getCtx(), getDateAcct());
		j.setC_Period_ID(period.getC_Period_ID());
		j.setGL_Category_ID(1000000);
		j.setC_ConversionType_ID(114);
		//j.setIsQuickEntry(true);
		//j.setAmount(getAdvance_Deduct());
		//j.setTF_DebitAcct_ID(getC_ElementValue_ID());
		//j.setTF_CreditAcct_ID(glConfig.getSalariesAdvanceAcct_ID());
		j.saveEx();
		
		//Salaries Payable Dr.
		MJournalLine jl;				
		jl = new MJournalLine(j);
		jl.setLine(10);			
		jl.setAccount_ID(getC_ElementValue_ID());
		jl.setC_BPartner_ID(getC_BPartner_ID());		
		jl.setAmtSourceDr(getAdvance_Deduct());
		jl.setAmtAcctDr(getAdvance_Deduct());
		jl.setIsGenerated(true);
		jl.saveEx();
		
		//
		jl = new MJournalLine(j);
		jl.setLine(10);			
		jl.setAccount_ID(glConfig.getSalariesAdvanceAcct_ID());
		jl.setC_BPartner_ID(getC_BPartner_ID());		
		jl.setAmtSourceCr(getAdvance_Deduct());
		jl.setAmtAcctCr(getAdvance_Deduct());
		jl.setIsGenerated(true);
		jl.saveEx();
		
		//DocAction
		if (!j.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + j.getProcessMsg());
		j.saveEx();
		
		DB.executeUpdate("UPDATE C_Payment SET " + COLUMNNAME_EmpAdv_Journal_ID +"="+ j.getGL_Journal_ID() + " WHERE C_Payment_ID="
				+ getC_Payment_ID() , get_TrxName());
	}
	
	public void reverseAdvanceAdjustmentJournal() {
		if(getEmpAdv_Journal_ID() > 0) {
			TF_MJournal j = new TF_MJournal(getCtx(), getEmpAdv_Journal_ID(), get_TrxName());
			if(j.getDocStatus().equals(TF_MInvoice.DOCSTATUS_Completed)) {
				if (!j.processIt(DocAction.ACTION_Reverse_Correct))
					throw new AdempiereException("Failed when processing document - " + j.getProcessMsg());
				j.saveEx();
			}
		}
	}
}
