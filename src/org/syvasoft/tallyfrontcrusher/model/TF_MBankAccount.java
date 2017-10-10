package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MBankAccount;
import org.compiere.model.MClient;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class TF_MBankAccount extends MBankAccount {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2319405472266282417L;

	public TF_MBankAccount(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public TF_MBankAccount(Properties ctx, int C_BankAccount_ID, String trxName) {
		super(ctx, C_BankAccount_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/** Column name AccountGroup_ID */
    public static final String COLUMNNAME_AccountGroup_ID = "AccountGroup_ID";
    
    public org.compiere.model.I_C_ElementValue getAccountGroup() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getAccountGroup_ID(), get_TrxName());	}

	/** Set Account Group.
		@param AccountGroup_ID Account Group	  */
	public void setAccountGroup_ID (int AccountGroup_ID)
	{
		if (AccountGroup_ID < 1) 
			set_Value (COLUMNNAME_AccountGroup_ID, null);
		else 
			set_Value (COLUMNNAME_AccountGroup_ID, Integer.valueOf(AccountGroup_ID));
	}

	/** Get Account Group.
		@return Account Group	  */
	public int getAccountGroup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AccountGroup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name BankAsset_ID */
    public static final String COLUMNNAME_BankAsset_ID = "BankAsset_ID";
    
    public org.compiere.model.I_C_ElementValue getBankAsset() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getBankAsset_ID(), get_TrxName());	
	}

	/** Set Bank/Cash Asset Account.
		@param BankAsset_ID Bank/Cash Asset Account	  */
	public void setBankAsset_ID (int BankAsset_ID)
	{
		if (BankAsset_ID < 1) 
			set_Value (COLUMNNAME_BankAsset_ID, null);
		else 
			set_Value (COLUMNNAME_BankAsset_ID, Integer.valueOf(BankAsset_ID));
	}

	/** Get Bank/Cash Asset Account.
		@return Bank/Cash Asset Account	  */
	public int getBankAsset_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BankAsset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
    
	/** Column name BankInTransit_ID */
    public static final String COLUMNNAME_BankInTransit_ID = "BankInTransit_ID";
    public org.compiere.model.I_C_ElementValue getBankInTransit() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getBankInTransit_ID(), get_TrxName());	}

	/** Set Bank In Transit Account.
		@param BankInTransit_ID Bank In Transit Account	  */
	public void setBankInTransit_ID (int BankInTransit_ID)
	{
		if (BankInTransit_ID < 1) 
			set_Value (COLUMNNAME_BankInTransit_ID, null);
		else 
			set_Value (COLUMNNAME_BankInTransit_ID, Integer.valueOf(BankInTransit_ID));
	}

	/** Get Bank In Transit Account.
		@return Bank In Transit Account	  */
	public int getBankInTransit_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BankInTransit_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {		
		if(getBankAsset_ID() == 0) {			
			 setBankAsset_ID(createBankCashAccount(getValue(), getName()));			 
		 }		
		if(!getBankAccountType().equals(TF_MBankAccount.BANKACCOUNTTYPE_Cash) && getBankInTransit_ID()==0) {
			setBankInTransit_ID(createBankCashAccount(getValue() + " In-Transfer", getName() + " In-Transfer"));
		}
		else if (getBankAccountType().equals(TF_MBankAccount.BANKACCOUNTTYPE_Cash)){
			setBankInTransit_ID(getBankAsset_ID());
		}
		return super.beforeSave(newRecord);
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {		
		 boolean ok = super.afterSave(newRecord, success);
		 
		 if(getBankAsset_ID() > 0) {
			 MAccount account = getAccount(getBankAsset_ID());
			if(account.getC_ValidCombination_ID() == 0)
				account.saveEx(); // Create new account combination
			
			String sql = "UPDATE C_BankAccount_Acct SET B_Asset_Acct=" + account.get_ID() + 
						" WHERE C_BankAccount_ID= " + getC_BankAccount_ID() + " AND C_AcctSchema_ID= " + account.getC_AcctSchema_ID();
			DB.executeUpdate(sql, get_TrxName());
		 }
		 if(getBankInTransit_ID() > 0) {
			 MAccount account = getAccount(getBankInTransit_ID());
			if(account.getC_ValidCombination_ID() == 0)
				account.saveEx(); // Create new account combination
			
			String sql = "UPDATE C_BankAccount_Acct SET B_InTransit_Acct=" + account.get_ID() + 
						" WHERE C_BankAccount_ID= " + getC_BankAccount_ID() + " AND C_AcctSchema_ID= " + account.getC_AcctSchema_ID();
			DB.executeUpdate(sql, get_TrxName());
		 }
		 return ok;
	}

	private int createBankCashAccount(String value, String name) {
		int elementID = Env.getContextAsInt(getCtx(), "#C_Element_ID");
		String where = "C_Element_ID = ? AND Value=?";
		List<TF_MElementValue> accts = new Query(getCtx(), TF_MElementValue.Table_Name, where, get_TrxName())
				.setClient_ID().setParameters(elementID, name).list();
		if(accts.size() > 0 ) {
			return accts.get(0).getC_ElementValue_ID();
		}
		else {
			 TF_MElementValue acct = new TF_MElementValue(getCtx(), 0, get_TrxName());		 
			 acct.setC_Element_ID(elementID);
			 acct.setValue(name);
			 acct.setName(name);
			 acct.setAccountGroup_ID(getAccountGroup_ID());
			 acct.setAccountType(TF_MElementValue.ACCOUNTTYPE_Asset);
			 acct.setAccountSign(TF_MElementValue.ACCOUNTSIGN_Natural);
			 acct.setPostActual(true);
			 acct.setPostBudget(true);
			 acct.setPostStatistical(true);
			 acct.setIsSummary(false);
			 acct.setIsBankAccount(true);
			 acct.setIsDocControlled(true);
			 acct.saveEx();
		 return acct.getC_ElementValue_ID();
		}
	}
	
	 /**
     * Gets the account for the specified charge and element value.
     * The account is created if it doesn't already exist.
     * @param elementValueId    identifier for the element value
     * @return the account
     */
    private MAccount getAccount(int elementValueId)
    {
    	Properties ctx = Env.getCtx();
    	MAcctSchema m_acctSchema = MClient.get(getCtx()).getAcctSchema();
        MAccount defaultAccount = MAccount.getDefault(m_acctSchema, true); //  optional null
        MAccount account = MAccount.get(ctx,
            Env.getAD_Client_ID(ctx),
            getAD_Org_ID(),
            m_acctSchema.getC_AcctSchema_ID(),
            elementValueId,
            defaultAccount.getC_SubAcct_ID(),
            defaultAccount.getM_Product_ID(),
            defaultAccount.getC_BPartner_ID(),
            defaultAccount.getAD_OrgTrx_ID(),
            defaultAccount.getC_LocFrom_ID(),
            defaultAccount.getC_LocTo_ID(),
            defaultAccount.getC_SalesRegion_ID(),
            defaultAccount.getC_Project_ID(),
            defaultAccount.getC_Campaign_ID(),
            defaultAccount.getC_Activity_ID(),
            defaultAccount.getUser1_ID(),
            defaultAccount.getUser2_ID(),
            defaultAccount.getUserElement1_ID(),
            defaultAccount.getUserElement2_ID(),
            get_TrxName());

        return account;
    }
	
}
