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
	
    /** Column name ShortName */
    public static final String COLUMNNAME_ShortName = "ShortName";
    /** Set Short Name.
	@param ShortName Short Name	  */
	public void setShortName (String ShortName)
	{
		set_Value (COLUMNNAME_ShortName, ShortName);
	}
	
	/** Get Short Name.
		@return Short Name	  */
	public String getShortName () 
	{
		String shortName = (String)get_Value(COLUMNNAME_ShortName); 
		if(shortName == null || shortName.trim().length() == 0)
			shortName = getName();
		return shortName;		
	}
	
	/** Column name IsDemo */
    public static final String COLUMNNAME_IsDemo = "IsDemo";
    /** Set Demo.
	@param IsDemo Demo	  */
	public void setIsDemo (boolean IsDemo)
	{
		set_Value (COLUMNNAME_IsDemo, Boolean.valueOf(IsDemo));
	}
	
	/** Get Demo.
		@return Demo	  */
	public boolean isDemo () 
	{
		Object oo = get_Value(COLUMNNAME_IsDemo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
	
    /** Column name OrgType */
    public static final String COLUMNNAME_OrgType = "OrgType";
    /** Crusher = C */
	public static final String ORGTYPE_Crusher = "C";
	/** Sand Block = S */
	public static final String ORGTYPE_SandBlock = "S";
	/** Trading = T */
	public static final String ORGTYPE_Trading = "T";
	/** Set Organization Type.
		@param OrgType Organization Type	  */
    /** Set Organization Type.
	@param OrgType Organization Type	  */
	public void setOrgType (String OrgType)
	{
	
		set_Value (COLUMNNAME_OrgType, OrgType);
	}
	
	/** Get Organization Type.
		@return Organization Type	  */
	public String getOrgType () 
	{
		return (String)get_Value(COLUMNNAME_OrgType);
	}
	
	/** Column name OrganizationAcct_ID */
    public static final String COLUMNNAME_OrganizationAcct_ID = "OrganizationAcct_ID";
    public org.compiere.model.I_C_ElementValue getOrganizationAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getOrganizationAcct_ID(), get_TrxName());	}

	/** Set Organization Account.
		@param OrganizationAcct_ID Organization Account	  */
	public void setOrganizationAcct_ID (int OrganizationAcct_ID)
	{
		if (OrganizationAcct_ID < 1) 
			set_Value (COLUMNNAME_OrganizationAcct_ID, null);
		else 
			set_Value (COLUMNNAME_OrganizationAcct_ID, Integer.valueOf(OrganizationAcct_ID));
	}

	/** Get Organization Account.
		@return Organization Account	  */
	public int getOrganizationAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_OrganizationAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

}
