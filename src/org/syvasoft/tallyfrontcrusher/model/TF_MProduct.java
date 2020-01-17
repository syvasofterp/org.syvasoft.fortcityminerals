package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MClient;
import org.compiere.model.MCost;
import org.compiere.model.MCostDetail;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.model.MTable;
import org.compiere.model.MTax;
import org.compiere.model.MWarehouse;
import org.compiere.model.MWarehousePrice;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class TF_MProduct extends MProduct {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3654172346880192140L;

	public TF_MProduct(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public TF_MProduct(Properties ctx, int M_Product_ID, String trxName) {
		super(ctx, M_Product_ID, trxName);
		// TODO Auto-generated constructor stub
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
	
	/** Column name Qty */
    public static final String COLUMNNAME_Qty = "Qty";
    /** Set Quantity.
	@param Qty 
	Quantity
  */
	public void setQty (BigDecimal Qty)
	{
		set_Value (COLUMNNAME_Qty, Qty);
	}
	
	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

    /** Column name Price */
    public static final String COLUMNNAME_Price = "Price";
    /** Set Price.
	@param Price 
	Price
  */
	public void setPrice (BigDecimal Price)
	{
		set_Value (COLUMNNAME_Price, Price);
	}
	
	/** Get Price.
		@return Price
	  */
	public BigDecimal getPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Price);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

    /** Column name ValueNumber */
    public static final String COLUMNNAME_ValueNumber = "ValueNumber";
    /** Set Value.
	@param ValueNumber 
	Numeric Value
  */
	public void setValueNumber (BigDecimal ValueNumber)
	{
		set_Value (COLUMNNAME_ValueNumber, ValueNumber);
	}
	
	/** Get Value.
		@return Numeric Value
	  */
	public BigDecimal getValueNumber () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ValueNumber);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	/** Column name M_Inventory_ID */
    public static final String COLUMNNAME_M_Inventory_ID = "M_Inventory_ID";
    public org.compiere.model.I_M_Inventory getM_Inventory() throws RuntimeException
    {
		return (org.compiere.model.I_M_Inventory)MTable.get(getCtx(), org.compiere.model.I_M_Inventory.Table_Name)
			.getPO(getM_Inventory_ID(), get_TrxName());	}

	/** Set Phys.Inventory.
		@param M_Inventory_ID 
		Parameters for a Physical Inventory
	  */
	public void setM_Inventory_ID (int M_Inventory_ID)
	{
		if (M_Inventory_ID < 1) 
			set_Value (COLUMNNAME_M_Inventory_ID, null);
		else 
			set_Value (COLUMNNAME_M_Inventory_ID, Integer.valueOf(M_Inventory_ID));
	}

	/** Get Phys.Inventory.
		@return Parameters for a Physical Inventory
	  */
	public int getM_Inventory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Inventory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
    /** Column name CostAdj_Inventory_ID */
    public static final String COLUMNNAME_CostAdj_Inventory_ID = "CostAdj_Inventory_ID";
    public org.compiere.model.I_M_Inventory getCostAdj_Inventory() throws RuntimeException
    {
		return (org.compiere.model.I_M_Inventory)MTable.get(getCtx(), org.compiere.model.I_M_Inventory.Table_Name)
			.getPO(getCostAdj_Inventory_ID(), get_TrxName());	}

	/** Set Cost Adjustment.
		@param CostAdj_Inventory_ID Cost Adjustment	  */
	public void setCostAdj_Inventory_ID (int CostAdj_Inventory_ID)
	{
		if (CostAdj_Inventory_ID < 1) 
			set_Value (COLUMNNAME_CostAdj_Inventory_ID, null);
		else 
			set_Value (COLUMNNAME_CostAdj_Inventory_ID, Integer.valueOf(CostAdj_Inventory_ID));
	}

	/** Get Cost Adjustment.
		@return Cost Adjustment	  */
	public int getCostAdj_Inventory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CostAdj_Inventory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Column name MaintainPermitLedger */
    public static final String COLUMNNAME_MaintainPermitLedger = "MaintainPermitLedger";
    /** Set Maintain Permit Ledger.
	@param MaintainPermitLedger Maintain Permit Ledger	  */
	public void setMaintainPermitLedger (boolean MaintainPermitLedger)
	{
		set_Value (COLUMNNAME_MaintainPermitLedger, Boolean.valueOf(MaintainPermitLedger));
	}
	
	/** Get Maintain Permit Ledger.
		@return Maintain Permit Ledger	  */
	public boolean isMaintainPermitLedger () 
	{
		Object oo = get_Value(COLUMNNAME_MaintainPermitLedger);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public int getPermitExpenseAccount_ID () {
		MProductCategory pc = (MProductCategory) getM_Product_Category();
		int permitExpAcct_ID = pc.get_ValueAsInt("C_ElementValuePermitExpense_ID");
		return permitExpAcct_ID;
	}
	
	/** Column name GSTRate */
    public static final String COLUMNNAME_GSTRate = "GSTRate";
    /** Set GST %.
	@param GSTRate GST %	  */
	public void setGSTRate (BigDecimal GSTRate)
	{
		set_Value (COLUMNNAME_GSTRate, GSTRate);
	}
	
	/** Get GST %.
		@return GST %	  */
	public BigDecimal getGSTRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GSTRate);
		if (bd == null || bd.equals(BigDecimal.ZERO)) {
			MProductCategory pc = (MProductCategory) getM_Product_Category();
			bd = (BigDecimal) pc.get_Value(COLUMNNAME_GSTRate);
			if(bd == null)
				return Env.ZERO;
		}
		return bd;
	}
	
	public int getTax_ID(boolean isTaxIncluded) {
		String whereClause = "Rate=? AND IsSummary=? AND ad_org_id=0";
		MTax tax = new Query(getCtx(), MTax.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(isTaxIncluded?getGSTRate():Env.ZERO, isTaxIncluded ? "Y" : "N")
				.first();
		
		if(tax != null)
			return tax.getC_Tax_ID();
		else
			return 0;
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {		
		boolean ok = super.afterSave(newRecord, success);
		setOpeningBalance(newRecord);		
		
		return ok;
	}
	
	public void setOpeningBalance(boolean newRecord) {
		if(newRecord || is_ValueChanged(COLUMNNAME_Price)
				|| is_ValueChanged(COLUMNNAME_OpeningDate)) {
			//if(getM_Inventory_ID() > 0) {
			//	MInventory prevInv = new MInventory(getCtx(), getCostAdj_Inventory_ID(), get_TrxName());
			//	if (!prevInv.processIt(DocAction.ACTION_Reverse_Correct))
			//		throw new AdempiereException("Failed when processing document - " + prevInv.getProcessMsg());
			//	prevInv.saveEx();
			//	//Update Inventory ID back to Product Master.
			//	DB.executeUpdate("UPDATE M_Product SET CostAdj_Inventory_ID=NULL WHERE M_Product_ID ="
			//			+ getM_Product_ID(), get_TrxName());
			//}			
			setOpeningCost(newRecord);
		}
				
		if(newRecord || is_ValueChanged(COLUMNNAME_Qty)
				|| is_ValueChanged(COLUMNNAME_OpeningDate)) {
			if(getM_Inventory_ID() > 0) {
				MInventory prevInv = new MInventory(getCtx(), getM_Inventory_ID(), get_TrxName());
				if (!prevInv.processIt(DocAction.ACTION_Reverse_Correct))
					throw new AdempiereException("Failed when processing document - " + prevInv.getProcessMsg());
				prevInv.saveEx();
				DB.executeUpdate("UPDATE M_Product SET M_Inventory_ID=NULL  WHERE M_Product_ID ="
						+ getM_Product_ID(), get_TrxName());				
			}
			setOpeningStock(newRecord);
		}
				
		//Cost Adjustment
		
	}
	public void setOpeningCost(boolean newRecord) {
		if(getOpeningDate() == null || getPrice().equals(BigDecimal.ZERO))
			return;
		
		MAcctSchema as = (MAcctSchema) MGLPostingConfig.getMGLPostingConfig(getCtx()).getC_AcctSchema();
		MCost cost = getCostingRecord(as, getAD_Org_ID(), 0, MInventory.COSTINGMETHOD_StandardCosting);
		
		if (cost != null && cost.getCurrentCostPrice().equals(BigDecimal.ZERO)) 
			return;
				
		
		//Cost Adjustment Header
		MWarehouse[] whs = MWarehouse.getForOrg(getCtx(), getAD_Org_ID());
		if(whs.length==0)
			throw new AdempiereException("Create Warehouse for this Organization!");
		MWarehouse wh = whs[0];		
		MInventory inv = new MInventory(getCtx(), 0, get_TrxName());
		inv.setC_DocType_ID(1000027); //Cost Adjustment		
		String desc = getName() + " Opening Cost Entry";
		inv.setAD_Org_ID(getAD_Org_ID());
		inv.setDescription(desc);
		inv.setC_Currency_ID(as.getC_Currency_ID());
		inv.setMovementDate(getOpeningDate());
		inv.setCostingMethod(MInventory.COSTINGMETHOD_StandardCosting);
		inv.setDocStatus(MInventory.DOCSTATUS_Drafted);		
		inv.saveEx();
		//End Physical Inventory Header
		
		
		//Inventory Line
		int M_Locator_ID = wh.getDefaultLocator().get_ID();		
		MInventoryLine costingLine = new MInventoryLine(getCtx(), 0, get_TrxName());
		costingLine.setM_Inventory_ID(inv.getM_Inventory_ID());
		costingLine.setM_Product_ID(getM_Product_ID());		
		costingLine.setCurrentCostPrice(cost==null?BigDecimal.ZERO:cost.getCurrentCostPrice());
		costingLine.setNewCostPrice(getPrice());
		costingLine.setM_Locator_ID(M_Locator_ID);
		costingLine.setAD_Org_ID(getAD_Org_ID());
		costingLine.setM_AttributeSetInstance_ID(0);
		costingLine.saveEx();
		//Inventory Line	
		
		//inv.processIt(DocAction.ACTION_Prepare);
		inv.processIt(DocAction.ACTION_Complete);
		inv.saveEx();
					
				
		//Update Inventory ID back to Product Master.
		DB.executeUpdate("UPDATE M_Product SET CostAdj_Inventory_ID=" + inv.getM_Inventory_ID() + " WHERE M_Product_ID ="
				+ getM_Product_ID(), get_TrxName());
	}
	
	public void setOpeningStock(boolean newRecord) {	
		if(getOpeningDate() == null || getQty().equals(BigDecimal.ZERO))
			return;
		//Physical Inventory Header
		MInventory inv = new MInventory(getCtx(), 0, get_TrxName());
		inv.setC_DocType_ID(1000023); //Physical Inventory
		inv.setAD_Org_ID(getAD_Org_ID());
		String desc = getName() + " Opening Stock Entry ";
		MWarehouse[] whs = MWarehouse.getForOrg(getCtx(), getAD_Org_ID());
		if(whs.length==0)
			throw new AdempiereException("Create Warehouse for this Organization!");
		MWarehouse wh = whs[0];
		inv.setM_Warehouse_ID(wh.getM_Warehouse_ID());
		inv.setDescription(desc);
		inv.setMovementDate(getOpeningDate());
		inv.setDocStatus(MInventory.DOCSTATUS_Drafted);		
		inv.saveEx();
		//End Physical Inventory Header
		
		
		//Inventory Line
		int M_Locator_ID = wh.getDefaultLocator().get_ID();
		String sql = "SELECT SUM(QtyOnHand) FROM M_StorageOnHand "
				+ "WHERE M_Product_ID=?"	//	1
				+ " AND M_Locator_ID=?";
		BigDecimal QtyBook = DB.getSQLValueBD(get_TrxName(), sql, getM_Product_ID(), M_Locator_ID);
		if(QtyBook == null)
			QtyBook = BigDecimal.ZERO;
		BigDecimal QtyCount = QtyBook.add(getQty());
		MInventoryLine invLine = new MInventoryLine(inv, M_Locator_ID, getM_Product_ID(), 0, QtyBook, QtyCount, null); 
		invLine.setInventoryType(MInventoryLine.INVENTORYTYPE_InventoryDifference);
		invLine.setDescription(inv.getDescription());
		invLine.saveEx();
		//Inventory Line
		
		inv.processIt(DocAction.ACTION_Complete);
		inv.saveEx();
		
		//Update Inventory ID back to Product Master.
		DB.executeUpdate("UPDATE M_Product SET M_Inventory_ID=" + inv.getM_Inventory_ID() + " WHERE M_Product_ID ="
				+ getM_Product_ID(), get_TrxName());
				
	}
	
	public static BigDecimal getCurrentCost(int AD_Org_ID, int M_Product_ID) {
		
		MProduct product = MProduct.get(Env.getCtx(), M_Product_ID);
		MClient client = MClient.get(Env.getCtx());
		MAcctSchema as = client.getAcctSchema();
		String costingMethod = product.getCostingMethod(as);		
		MCost cost = product.getCostingRecord(as, AD_Org_ID, 0, costingMethod);
		if(cost == null)
			throw new AdempiereException("No Costing Info for : "  + product.getName());
		
		return cost.getCurrentCostPrice();		
	}
	
}
