package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MPayment;
import org.compiere.model.MTable;

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

	
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		
		return super.afterSave(newRecord, success);
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
		return super.beforeSave(newRecord);
	}
	
	@Override
	public String completeIt() {
		//Subcontract / Job Work
		if(getC_Project_ID() > 0) {
			MJobworkCharges.updateJobworkCharges(getCtx(), getC_Project_ID(), getC_Charge_ID(), getPayAmt(), get_TrxName());
		}
		return super.completeIt();
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
		return super.reverseCorrectIt();
	}
	
	
	
}
