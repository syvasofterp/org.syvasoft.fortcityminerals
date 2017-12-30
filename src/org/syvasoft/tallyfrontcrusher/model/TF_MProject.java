package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MProject;
import org.compiere.model.MProjectType;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class TF_MProject extends MProject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8652950032687595946L;

	public TF_MProject(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public TF_MProject(Properties ctx, int C_Project_ID, String trxName) {
		super(ctx, C_Project_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	 /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";
    
	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getC_UOM_ID(), get_TrxName());	}

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	 /** Column name Contract_Amt_Act */
    public static final String COLUMNNAME_Contract_Amt_Act = "Contract_Amt_Act";
    
    /** Set Contract Amt (Actual).
	@param Contract_Amt_Act Contract Amt (Actual)	  */
	public void setContract_Amt_Act (BigDecimal Contract_Amt_Act)
	{
		set_Value (COLUMNNAME_Contract_Amt_Act, Contract_Amt_Act);
	}
	
	/** Get Contract Amt (Actual).
		@return Contract Amt (Actual)	  */
	public BigDecimal getContract_Amt_Act () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Contract_Amt_Act);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	 /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";
    
    /** DocStatus AD_Reference_ID=131 */
	public static final int DOCSTATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String DOCSTATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String DOCSTATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String DOCSTATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String DOCSTATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String DOCSTATUS_WaitingConfirmation = "WC";
	/** Suspended = SU */
	public static final String DOCSTATUS_Suspended = "SU";
	/** Activated = AC */
	public static final String DOCSTATUS_Activated = "AC";
	/** Canceled = CA */
	public static final String DOCSTATUS_Canceled = "CA";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{

		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	
	/** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";
    
    /** Set Document No.
	@param DocumentNo 
	Document sequence number of the document
    */
	public void setDocumentNo (String DocumentNo)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}
	
	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}
	
	 /** Column name IsSOTrx */
    public static final String COLUMNNAME_IsSOTrx = "IsSOTrx";
    
    /** Set Sales Transaction.
	@param IsSOTrx 
	This is a Sales Transaction
     */
	public void setIsSOTrx (boolean IsSOTrx)
	{
		set_ValueNoCheck (COLUMNNAME_IsSOTrx, Boolean.valueOf(IsSOTrx));
	}
	
	/** Get Sales Transaction.
		@return This is a Sales Transaction
	  */
	public boolean isSOTrx () 
	{
		Object oo = get_Value(COLUMNNAME_IsSOTrx);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
	
	/** Column name JobWork_Product_ID */
    public static final String COLUMNNAME_JobWork_Product_ID = "JobWork_Product_ID";
    
    public org.compiere.model.I_M_Product getJobWork_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getJobWork_Product_ID(), get_TrxName());	}

	/** Set Job Work.
		@param JobWork_Product_ID Job Work	  */
	public void setJobWork_Product_ID (int JobWork_Product_ID)
	{
		if (JobWork_Product_ID < 1) 
			set_Value (COLUMNNAME_JobWork_Product_ID, null);
		else 
			set_Value (COLUMNNAME_JobWork_Product_ID, Integer.valueOf(JobWork_Product_ID));
	}

	/** Get Job Work.
		@return Job Work	  */
	public int getJobWork_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JobWork_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name QtyProcessed */
    public static final String COLUMNNAME_QtyProcessed = "QtyProcessed";
    
    /** Set Quantity Processed.
	@param QtyProcessed Quantity Processed	  */
	public void setQtyProcessed (BigDecimal QtyProcessed)
	{
		set_Value (COLUMNNAME_QtyProcessed, QtyProcessed);
	}
	
	/** Get Quantity Processed.
		@return Quantity Processed	  */
	public BigDecimal getQtyProcessed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyProcessed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	/** Column name TF_Processing */
    public static final String COLUMNNAME_TF_Processing = "TF_Processing";
    
    /** Set Process Now.
	@param TF_Processing Process Now	  */
	public void setTF_Processing (String TF_Processing)
	{
		set_Value (COLUMNNAME_TF_Processing, TF_Processing);
	}
	
	/** Get Process Now.
		@return Process Now	  */
	public String getTF_Processing () 
	{
		return (String)get_Value(COLUMNNAME_TF_Processing);
	}
	
	/** Column name Unit_Price */
    public static final String COLUMNNAME_Unit_Price = "Unit_Price";
    /** Set Unit Price.
	@param Unit_Price Unit Price	  */
	public void setUnit_Price (BigDecimal Unit_Price)
	{
		set_Value (COLUMNNAME_Unit_Price, Unit_Price);
	}
	
	/** Get Unit Price.
		@return Unit Price	  */
	public BigDecimal getUnit_Price () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Unit_Price);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Column name DocAction */
    public static final String COLUMNNAME_DocAction = "DocAction";
    
    /** Start = S */
	public static final String DOCACTION_Start = "S";
	/** End = E */
	public static final String DOCACTION_End = "E";
	/** Modify = D */
	public static final String DOCACTION_Modify = "D";
	/** Force Close = X */
	public static final String DOCACTION_ForceClose = "X";
	/** Set Document Action.
		@param DocAction 
		The targeted status of the document
	  */
	public void setDocAction (String DocAction)
	{

		set_Value (COLUMNNAME_DocAction, DocAction);
	}

	/** Get Document Action.
		@return The targeted status of the document
	  */
	public String getDocAction () 
	{
		return (String)get_Value(COLUMNNAME_DocAction);
	}
	
	/** Column name SubcontractType */
    public static final String COLUMNNAME_SubcontractType = "SubcontractType";
    /** Crusher Production = CP */
	public static final String SUBCONTRACTTYPE_CrusherProduction = "CP";
	/** Quarry Producton = QP */
	public static final String SUBCONTRACTTYPE_QuarryProducton = "QP";
	/** Set Subcontract Type.
		@param SubcontractType Subcontract Type	  */
	public void setSubcontractType (String SubcontractType)
	{

		set_Value (COLUMNNAME_SubcontractType, SubcontractType);
	}

	/** Get Subcontract Type.
		@return Subcontract Type	  */
	public String getSubcontractType () 
	{
		return (String)get_Value(COLUMNNAME_SubcontractType);
	}

	/** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";
    public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException
    {
		return (org.compiere.model.I_M_Warehouse)MTable.get(getCtx(), org.compiere.model.I_M_Warehouse.Table_Name)
			.getPO(getM_Warehouse_ID(), get_TrxName());	}

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1) 
			set_Value (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name TF_SubcontractType_ID */
    public static final String COLUMNNAME_TF_SubcontractType_ID = "TF_SubcontractType_ID";
    /** Set Subcontract Type.
	@param TF_SubcontractType_ID Subcontract Type	  */
	public void setTF_SubcontractType_ID (int TF_SubcontractType_ID)
	{
		if (TF_SubcontractType_ID < 1) 
			set_Value (COLUMNNAME_TF_SubcontractType_ID, null);
		else 
			set_Value (COLUMNNAME_TF_SubcontractType_ID, Integer.valueOf(TF_SubcontractType_ID));
	}
	
	/** Get Subcontract Type.
		@return Subcontract Type	  */
	public int getTF_SubcontractType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_SubcontractType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
    /** Column name TF_Quarry_ID */
    public static final String COLUMNNAME_TF_Quarry_ID = "TF_Quarry_ID";
    /** Set Quarry.
	@param TF_Quarry_ID Quarry	  */
	public void setTF_Quarry_ID (int TF_Quarry_ID)
	{
		if (TF_Quarry_ID < 1) 
			set_Value (COLUMNNAME_TF_Quarry_ID, null);
		else 
			set_Value (COLUMNNAME_TF_Quarry_ID, Integer.valueOf(TF_Quarry_ID));
	}
	
	/** Get Quarry.
		@return Quarry	  */
	public int getTF_Quarry_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_Quarry_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(getDocumentNo() != null && (getValue() == null || getValue().length()==0))
			setValue(getDocumentNo());
		
		//Set Default Project Type - Subcontract
		MProjectType projType = new MProjectType(getCtx(), 1000000, get_TrxName());		
		setProjectType(projType);
		
		//Update Contract Amt Actual
		if(is_ValueChanged(COLUMNNAME_QtyProcessed) || is_ValueChanged(COLUMNNAME_Unit_Price))
			setContract_Amt_Act(getUnit_Price().multiply(getQtyProcessed()));
				
		//1. Jobwork is Mandatory when SubcontractType.Invoice For : Jobwork
		//2. Quarry is mandatory when SubcontractType.CreateBoulderReceipt is true
		
		MSubcontractType contractType = new MSubcontractType(getCtx(), getTF_SubcontractType_ID(), get_TrxName());
		setSubcontractType(contractType.getSubcontractType());
		if(contractType.getInvoiceFor().equals(MSubcontractType.INVOICEFOR_Jobwork) && getJobWork_Product_ID() == 0) {
			throw new AdempiereException("The selected Subcontract Type enforces that the Jobwork is mandatory");
		}
		if(contractType.isCreateBoulderReceipt() && getTF_Quarry_ID() == 0) {
			throw new AdempiereException("The selected Subcontract Type enforces that the Quarry is mandatory");
		}
		
		return super.beforeSave(newRecord);
	}
	
	public void processIt(String docAction) {
		if(docAction.equals(DOCACTION_Start)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(docAction.equals(DOCACTION_Modify)) {
			setDocStatus(DOCSTATUS_Drafted);
		}
	}
	
	public void updateBoulderReceiptBasedFields(MBoulderReceipt br) {
		
		if(getC_Project_ID() == br.getC_Project_ID() && getJobWork_Product_ID() == br.getJobWork_Product_ID()) {
			//Set Qty Processed
			setQtyProcessed(getQtyProcessed().add(br.getQtyReceived()));
			
			//Update Received Items
			MJobworkReceivedItems.addReceivedItem(getCtx(), getC_Project_ID(), br.getM_Product_ID(),
						br.getQtyReceived(), br.getC_UOM_ID(), br.get_TrxName());
		}
		
	}
	
	public void reverseBoulderReceiptBasedFields(MBoulderReceipt br) {
		
		if(getC_Project_ID() == br.getC_Project_ID() && getJobWork_Product_ID() == br.getJobWork_Product_ID()) {
			//Set Qty Processed
			setQtyProcessed(getQtyProcessed().subtract(br.getQtyReceived()));
			
			//Update Received Items
			MJobworkReceivedItems.addReceivedItem(getCtx(), getC_Project_ID(), br.getM_Product_ID(),
						br.getQtyReceived().negate(), br.getC_UOM_ID(), br.get_TrxName());
		}
		
	}
	
	public static TF_MProject getCrusherProductionSubcontractByWarehouse(int M_Warehouse_ID) {
		TF_MProject proj = new Query(Env.getCtx(), TF_MProject.Table_Name, "M_Warehouse_ID=? AND DocStatus='IP' "
				+ " AND C_Project.TF_SubcontractType_ID IN (SELECT a.TF_SubcontractType_ID FROM TF_SubcontractType a "
				+ " WHERE a.SubcontractType='CP')", null)
				.setParameters(M_Warehouse_ID).first();
		return proj;
	}

}
