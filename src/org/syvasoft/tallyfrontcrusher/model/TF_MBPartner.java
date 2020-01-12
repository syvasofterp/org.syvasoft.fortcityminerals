package org.syvasoft.tallyfrontcrusher.model;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_Location;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MLocation;
import org.compiere.model.MPriceList;
import org.compiere.model.MTable;
import org.compiere.model.MUser;
import org.compiere.model.Query;
import org.compiere.model.X_I_BPartner;
import org.compiere.process.DocAction;
import org.compiere.util.DB;
import org.compiere.util.Env;


public class TF_MBPartner extends MBPartner {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8766467785915122120L;

	public TF_MBPartner(X_I_BPartner impBP) {
		super(impBP);		
	}

	public TF_MBPartner(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);		
	}

	public TF_MBPartner(Properties ctx, int C_BPartner_ID, String trxName) {
		super(ctx, C_BPartner_ID, trxName);		
	}

	public TF_MBPartner(Properties ctx) {
		super(ctx);		
	}

	/** Column name Address1 */
    public static final String COLUMNNAME_Address1 = "Address1";
    
    /** Set Address 1.
	@param Address1 
	Address line 1 for this location
    */
	public void setAddress1 (String Address1)
	{
		set_Value (COLUMNNAME_Address1, Address1);
	}
	
	/** Get Address 1.
		@return Address line 1 for this location
	  */
	public String getAddress1 () 
	{
		return (String)get_Value(COLUMNNAME_Address1);
	}
	
	/** Column name Address2 */
    public static final String COLUMNNAME_Address2 = "Address2";
    /** Set Address 2.
	@param Address2 
	Address line 2 for this location
     */
	public void setAddress2 (String Address2)
	{
		set_Value (COLUMNNAME_Address2, Address2);
	}
	
	/** Get Address 2.
		@return Address line 2 for this location
	  */
	public String getAddress2 () 
	{
		return (String)get_Value(COLUMNNAME_Address2);
	}

	/** Column name Address3 */
    public static final String COLUMNNAME_Address3 = "Address3";
    /** Set Address 3.
	@param Address3 
	Address Line 3 for the location
    */
	public void setAddress3 (String Address3)
	{
		set_Value (COLUMNNAME_Address3, Address3);
	}
	
	/** Get Address 3.
		@return Address Line 3 for the location
	  */
	public String getAddress3 () 
	{
		return (String)get_Value(COLUMNNAME_Address3);
	}

	 /** Column name Address4 */
    public static final String COLUMNNAME_Address4 = "Address4";
    /** Set Address 4.
	@param Address4 
	Address Line 4 for the location
     */
	public void setAddress4 (String Address4)
	{
		set_Value (COLUMNNAME_Address4, Address4);
	}
	
	/** Get Address 4.
		@return Address Line 4 for the location
	  */
	public String getAddress4 () 
	{
		return (String)get_Value(COLUMNNAME_Address4);
	}
	
	 /** Column name C_Country_ID */
    public static final String COLUMNNAME_C_Country_ID = "C_Country_ID";
    
    public org.compiere.model.I_C_Country getC_Country() throws RuntimeException
    {
		return (org.compiere.model.I_C_Country)MTable.get(getCtx(), org.compiere.model.I_C_Country.Table_Name)
			.getPO(getC_Country_ID(), get_TrxName());	}

	/** Set Country.
		@param C_Country_ID 
		Country 
	  */
	public void setC_Country_ID (int C_Country_ID)
	{
		if (C_Country_ID < 1) 
			set_Value (COLUMNNAME_C_Country_ID, null);
		else 
			set_Value (COLUMNNAME_C_Country_ID, Integer.valueOf(C_Country_ID));
	}

	/** Get Country.
		@return Country 
	  */
	public int getC_Country_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Country_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name C_Location_ID */
    public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";
    public I_C_Location getC_Location() throws RuntimeException
    {
		return (I_C_Location)MTable.get(getCtx(), I_C_Location.Table_Name)
			.getPO(getC_Location_ID(), get_TrxName());	}

	/** Set Address.
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1) 
			set_Value (COLUMNNAME_C_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
	}

	/** Get Address.
		@return Location or Address
	  */
	public int getC_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	 /** Column name City */
    public static final String COLUMNNAME_City = "City";
    /** Set City.
	@param City 
	Identifies a City
     */
	public void setCity (String City)
	{
		set_Value (COLUMNNAME_City, City);
	}
	
	/** Get City.
		@return Identifies a City
	  */
	public String getCity () 
	{
		return (String)get_Value(COLUMNNAME_City);
	}

	/** Column name ContactName */
    public static final String COLUMNNAME_ContactName = "ContactName";
    /** Set Contact Name.
	@param ContactName 
	Business Partner Contact Name
    */
	public void setContactName (String ContactName)
	{
		set_Value (COLUMNNAME_ContactName, ContactName);
	}
	
	/** Get Contact Name.
		@return Business Partner Contact Name
	  */
	public String getContactName () 
	{
		return (String)get_Value(COLUMNNAME_ContactName);
	}
	
	/** Column name Postal */
    public static final String COLUMNNAME_Postal = "Postal";
    
    /** Set ZIP.
	@param Postal 
	Postal code
    */
	public void setPostal (String Postal)
	{
		set_Value (COLUMNNAME_Postal, Postal);
	}
	
	/** Get ZIP.
		@return Postal code
	  */
	public String getPostal () 
	{
		return (String)get_Value(COLUMNNAME_Postal);
	}
	
	/** Column name RegionName */
    public static final String COLUMNNAME_RegionName = "RegionName";
    
    /** Set Region.
	@param RegionName 
	Name of the Region
     */
	public void setRegionName (String RegionName)
	{
		set_Value (COLUMNNAME_RegionName, RegionName);
	}
	
	/** Get Region.
		@return Name of the Region
	  */
	public String getRegionName () 
	{
		return (String)get_Value(COLUMNNAME_RegionName);
	}
	
	/** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";
    public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getAD_User_ID(), get_TrxName());	}

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Column name Phone */
    public static final String COLUMNNAME_Phone = "Phone";
    /** Set Phone.
	@param Phone 
	Identifies a telephone number
     */
	public void setPhone (String Phone)
	{
		set_Value (COLUMNNAME_Phone, Phone);
	}
	
	/** Get Phone.
		@return Identifies a telephone number
	  */
	public String getPhone () 
	{
		return (String)get_Value(COLUMNNAME_Phone);
	}
    
	/** Column name OpeningDate */
    public static final String COLUMNNAME_OpeningDate = "OpeningDate";
    
    /** Set AS On.
	@param OpeningDate AS On	  */
	public void setOpeningDate (Timestamp OpeningDate)
	{
		set_Value (COLUMNNAME_OpeningDate, OpeningDate);
	}
	
	/** Get AS On.
		@return AS On	  */
	public Timestamp getOpeningDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_OpeningDate);
	}

	/** Column name DebitBalance */
    public static final String COLUMNNAME_DebitBalance = "DebitBalance";
    /** Set Debit Balance.
	@param DebitBalance Debit Balance	  */
	public void setDebitBalance (BigDecimal DebitBalance)
	{
		set_Value (COLUMNNAME_DebitBalance, DebitBalance);
	}
	
	/** Get Debit Balance.
		@return Debit Balance	  */
	public BigDecimal getDebitBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DebitBalance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	/** Column name CreditBalance */
    public static final String COLUMNNAME_CreditBalance = "CreditBalance";
    /** Set Credit Balance.
	@param CreditBalance Credit Balance	  */
	public void setCreditBalance (BigDecimal CreditBalance)
	{
		set_Value (COLUMNNAME_CreditBalance, CreditBalance);
	}
	
	/** Get Credit Balance.
		@return Credit Balance	  */
	public BigDecimal getCreditBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CreditBalance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Column name C_Invoice_ID */
    public static final String COLUMNNAME_C_Invoice_ID = "C_Invoice_ID";
    public org.compiere.model.I_C_Invoice getC_Invoice() throws RuntimeException
    {
		return (org.compiere.model.I_C_Invoice)MTable.get(getCtx(), org.compiere.model.I_C_Invoice.Table_Name)
			.getPO(getC_Invoice_ID(), get_TrxName());	}

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Invoice_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	 /** Column name Std_Days */
    public static final String COLUMNNAME_Std_Days = "Std_Days";
    /** Set Standard Days.
	@param Std_Days Standard Days	  */
	public void setStd_Days (BigDecimal Std_Days)
	{
		set_Value (COLUMNNAME_Std_Days, Std_Days);
	}
	
	/** Get Standard Days.
		@return Standard Days	  */
	public BigDecimal getStd_Days () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Std_Days);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	/** Set Standard Wage.
	@param Std_Wage Standard Wage	  */
	public void setStd_Wage (BigDecimal Std_Wage)
	{
		set_Value (COLUMNNAME_Std_Wage, Std_Wage);
	}
	
	/** Get Standard Wage.
		@return Standard Wage	  */
	public BigDecimal getStd_Wage () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Std_Wage);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

    
    /** Column name Std_Wage */
    public static final String COLUMNNAME_Std_Wage = "Std_Wage";
	
    /** Column name IsRentBreakup */
    public static final String COLUMNNAME_IsRentBreakup = "IsRentBreakup";
    /** Set Show Rent Breakup.
	@param IsRentBreakup Show Rent Breakup	  */
	public void setIsRentBreakup (boolean IsRentBreakup)
	{
		set_Value (COLUMNNAME_IsRentBreakup, Boolean.valueOf(IsRentBreakup));
	}
	
	/** Get Show Rent Breakup.
		@return Show Rent Breakup	  */
	public boolean isRentBreakup () 
	{
		Object oo = get_Value(COLUMNNAME_IsRentBreakup);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
    
	 /** Column name IsRentInclusive */
    public static final String COLUMNNAME_IsRentInclusive = "IsRentInclusive";
    
    /** Set Rent Inclusive.
	@param IsRentInclusive 
	Whether Unit Price includes rent?
  */
	public void setIsRentInclusive (boolean IsRentInclusive)
	{
		set_Value (COLUMNNAME_IsRentInclusive, Boolean.valueOf(IsRentInclusive));
	}
	
	/** Get Rent Inclusive.
		@return Whether Unit Price includes rent?
	  */
	public boolean isRentInclusive () 
	{
		Object oo = get_Value(COLUMNNAME_IsRentInclusive);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	 /** Column name IsTaxIncluded */
    public static final String COLUMNNAME_IsTaxIncluded = "IsTaxIncluded";
    /** Set Price includes Tax.
	@param IsTaxIncluded 
	Tax is included in the price 
  */
	public void setIsTaxIncluded (boolean IsTaxIncluded)
	{
		set_Value (COLUMNNAME_IsTaxIncluded, Boolean.valueOf(IsTaxIncluded));
	}
	
	/** Get Price includes Tax.
		@return Tax is included in the price 
	  */
	public boolean isTaxIncluded () 
	{
		Object oo = get_Value(COLUMNNAME_IsTaxIncluded);
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
		
		MUser user = new MUser(getCtx(), getAD_User_ID(), get_TrxName());		
		user.setC_BPartner_ID(getC_BPartner_ID());
		user.setAD_Org_ID(getAD_Org_ID());
		user.setName(getContactName());
		user.setPhone(getPhone());
		user.setNotificationType(MUser.NOTIFICATIONTYPE_EMail);
		user.set_TrxName(get_TrxName());
		user.saveEx();
		
		if(getAD_User_ID() == 0) {
			DB.executeUpdate("Update C_BPartner SET AD_USer_ID = " + user.getAD_User_ID() 
			  + " WHERE C_BPartner_ID = " + getC_BPartner_ID(), get_TrxName());
		}
		
		MLocation loc = new MLocation(getCtx(), getC_Location_ID(), get_TrxName());
		loc.setAD_Org_ID(getAD_Org_ID());
		loc.setAddress1(getAddress1());
		loc.setAddress2(getAddress2());
		loc.setAddress3(getAddress3());
		loc.setAddress4(getAddress4());
		loc.setCity(getCity());
		loc.setPostal(getPostal());
		loc.setRegionName(getRegionName());
		loc.setC_Country_ID(getC_Country_ID());
		loc.saveEx();
		
		MBPartnerLocation bpLoc = new Query(getCtx(), MBPartnerLocation.Table_Name, "C_BPartner_ID = ? AND C_Location_ID= ? ", get_TrxName())
				.setParameters(getC_BPartner_ID(), getC_Location_ID()).first();
		if(bpLoc == null) {
			bpLoc = new MBPartnerLocation(this);
		}		
		
		bpLoc.set_TrxName(get_TrxName());
		bpLoc.setName(getCity());
		bpLoc.setC_Location_ID(loc.getC_Location_ID());
		bpLoc.setPhone(getPhone());
		bpLoc.setIsBillTo(true);
		bpLoc.setIsPayFrom(true);
		bpLoc.setIsRemitTo(true);
		bpLoc.setIsShipTo(true);
		bpLoc.saveEx();
		
		if(getC_Location_ID() == 0) {
			DB.executeUpdate("UPDATE C_BPartner SET C_Location_ID = " + loc.getC_Location_ID() + 
				"  WHERE C_BPartner_ID = " + getC_BPartner_ID(), get_TrxName());
		}			
		
		setOpeningBalance(newRecord);
		
		return ok;
	}
	
	public void setOpeningBalance(boolean newRecord) {
		if(newRecord || is_ValueChanged(COLUMNNAME_DebitBalance) || is_ValueChanged(COLUMNNAME_CreditBalance)
				|| is_ValueChanged(COLUMNNAME_OpeningDate)) {
			if(getC_Invoice_ID() > 0) {
				TF_MInvoice prevInv = new TF_MInvoice(getCtx(), getC_Invoice_ID(), get_TrxName());
				if(prevInv.getDocStatus().equals(TF_MInvoice.DOCSTATUS_Completed)) {
					if (!prevInv.processIt(DocAction.ACTION_Reverse_Correct))
						throw new AdempiereException("Failed when processing document - " + prevInv.getProcessMsg());
					prevInv.saveEx();
				}								
			}
			
			BigDecimal DebitAmt = getDebitBalance();
			BigDecimal CreditAmt = getCreditBalance();
			boolean isSOTrx;			
			int m_C_DocTypeTarget_ID;
			int C_ElementValue_ID;
						
			
			MGLPostingConfig glConfig = MGLPostingConfig.getMGLPostingConfig(getCtx());
			if(DebitAmt.doubleValue()!=0) {
				m_C_DocTypeTarget_ID = glConfig.getBP_DebitDocType_ID();
				C_ElementValue_ID = glConfig.getBP_DebitBalanceAcct_ID();
				isSOTrx = true;
			}				
			else if(CreditAmt.doubleValue() != 0) {
				m_C_DocTypeTarget_ID = glConfig.getBP_CreditDocType_ID();
				C_ElementValue_ID = glConfig.getBP_CreditBalanceAcct_ID();
				isSOTrx = false;
			}
			else {
				DB.executeUpdate("UPDATE C_BPartner SET C_Invoice_ID=NULL  WHERE C_BPartner_ID ="
						+ getC_BPartner_ID(), get_TrxName());
				return;
			}
				
			
			if(getAD_Org_ID() == 0) {
				throw new AdempiereException("Opening Balance cannot be set for Global Business Partner (Organization=*)!");
			}
			
			//Invoice Header
			TF_MInvoice invoice = new TF_MInvoice(getCtx(), 0, get_TrxName());
			invoice.setClientOrg(getAD_Client_ID(), getAD_Org_ID());
			invoice.setC_DocTypeTarget_ID(m_C_DocTypeTarget_ID);			
			invoice.setDateInvoiced(getOpeningDate());
			invoice.setDateAcct(getOpeningDate());
			//
			invoice.setSalesRep_ID(Env.getAD_User_ID(getCtx()));
			//
			invoice.setBPartner(this);
			invoice.setIsSOTrx(isSOTrx);		
			
			
			//Price List
			int m_M_PriceList_ID = Env.getContextAsInt(getCtx(), "#M_PriceList_ID");
			if(getPO_PriceList_ID() > 0)
				m_M_PriceList_ID = getPO_PriceList_ID();			
			invoice.setM_PriceList_ID(m_M_PriceList_ID);
			invoice.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
			invoice.setDescription("Opening Balance Entered");
			invoice.saveEx();
			//End Invoice Header
			
			//Invoice Line
			MInvoiceLine invLine = new MInvoiceLine(invoice);
			TF_MCharge chrg = TF_MCharge.createChargeFromAccount(getCtx(), C_ElementValue_ID, null);
			invLine.setC_Charge_ID(chrg.getC_Charge_ID());			
			invLine.setQty(BigDecimal.ONE);
			if(isSOTrx)
				invLine.setPrice(DebitAmt);
			else
				invLine.setPrice(CreditAmt);
			invLine.saveEx();
			//End Invoice Line
			
			//DocAction
			if (!invoice.processIt(DocAction.ACTION_Complete))
				throw new AdempiereException("Failed when processing document - " + invoice.getProcessMsg());
			invoice.saveEx();
			
			DB.executeUpdate("UPDATE C_BPartner SET C_Invoice_ID=" + invoice.getC_Invoice_ID() + " WHERE C_BPartner_ID ="
					+ getC_BPartner_ID(), get_TrxName());
			
		}
	}
	
}
