package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MElement;
import org.compiere.model.MElementValue;
import org.compiere.model.MTable;
import org.compiere.model.MTree;
import org.compiere.model.X_I_ElementValue;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class TF_MElementValue extends MElementValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 599110180395281014L;

	public TF_MElementValue(X_I_ElementValue imp) {
		super(imp);
		// TODO Auto-generated constructor stub
	}

	public TF_MElementValue(Properties ctx, String Value, String Name, String Description, String AccountType,
			String AccountSign, boolean IsDocControlled, boolean IsSummary, String trxName) {
		super(ctx, Value, Name, Description, AccountType, AccountSign, IsDocControlled, IsSummary, trxName);
		// TODO Auto-generated constructor stub
	}

	public TF_MElementValue(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public TF_MElementValue(Properties ctx, int C_ElementValue_ID, String trxName) {
		super(ctx, C_ElementValue_ID, trxName);
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

	@Override
	protected boolean beforeSave(boolean newRecord) {		
		boolean ok = super.beforeSave(newRecord);
		if(getC_Element_ID() == 0) {
			setC_Element_ID(Env.getContextAsInt(getCtx(), "#C_Element_ID"));
		}
		return ok;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {		
		boolean ok = super.afterSave(newRecord, success);
		//It works only for Chart of Account to manage tree.
		if(getC_Element().getElementType().equals(MElement.ELEMENTTYPE_Account)) {
			int ad_Tree_ID= (new MElement(getCtx(), getC_Element_ID(), get_TrxName())).getAD_Tree_ID();
			String treeType= (new MTree(getCtx(),ad_Tree_ID,get_TrxName())).getTreeType();
			
			String sql = " UPDATE AD_TreeNode SET Parent_ID = " + getAccountGroup_ID() + 
					" WHERE AD_Tree_ID = " + ad_Tree_ID  + 
					"  AND Node_ID = " + getC_ElementValue_ID();			
			DB.executeUpdate(sql, get_TrxName());
			
			update_Tree(treeType);
		}
		return ok;
	}

	

}