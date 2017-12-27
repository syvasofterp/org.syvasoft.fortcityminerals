package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MOrg;
import org.compiere.model.MTable;

public class TF_MOrg extends MOrg {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3099755752711559455L;

	public TF_MOrg(Properties ctx, int AD_Org_ID, String trxName) {
		super(ctx, AD_Org_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public TF_MOrg(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	/** Column name AD_OrgHO_ID */
    public static final String COLUMNNAME_AD_OrgHO_ID = "AD_OrgHO_ID";
    /** Set Head Office.
	@param AD_OrgHO_ID Head Office	  */
	public void setAD_OrgHO_ID (int AD_OrgHO_ID)
	{
		if (AD_OrgHO_ID < 1) 
			set_Value (COLUMNNAME_AD_OrgHO_ID, null);
		else 
			set_Value (COLUMNNAME_AD_OrgHO_ID, Integer.valueOf(AD_OrgHO_ID));
	}
	
	/** Get Head Office.
		@return Head Office	  */
	public int getAD_OrgHO_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgHO_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Column name InvestmentAcct_ID */
    public static final String COLUMNNAME_InvestmentAcct_ID = "InvestmentAcct_ID";
    
    public org.compiere.model.I_C_ElementValue getInvestmentAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getInvestmentAcct_ID(), get_TrxName());	}

	/** Set Investment Account.
		@param InvestmentAcct_ID Investment Account	  */
	public void setInvestmentAcct_ID (int InvestmentAcct_ID)
	{
		if (InvestmentAcct_ID < 1) 
			set_Value (COLUMNNAME_InvestmentAcct_ID, null);
		else 
			set_Value (COLUMNNAME_InvestmentAcct_ID, Integer.valueOf(InvestmentAcct_ID));
	}

	/** Get Investment Account.
		@return Investment Account	  */
	public int getInvestmentAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_InvestmentAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	public TF_MOrg getHeadOffice() {
		TF_MOrg org = null;
		if(getAD_OrgHO_ID() > 0 )
			org = new TF_MOrg(getCtx(), getAD_OrgHO_ID(), get_TrxName());
		return org;
	}
	
}
