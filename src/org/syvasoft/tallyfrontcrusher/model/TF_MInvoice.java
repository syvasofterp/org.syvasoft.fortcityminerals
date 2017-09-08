package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MJournal;
import org.compiere.model.MProduct;
import org.compiere.model.MQuery;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class TF_MInvoice extends MInvoice {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7877217492066713390L;
	
	 /** Column name Item1_Amt */
    public static final String COLUMNNAME_Item1_Amt = "Item1_Amt";
    
	
	public TF_MInvoice(Properties ctx, int C_Invoice_ID, String trxName) {
		super(ctx, C_Invoice_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public TF_MInvoice(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
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
	
	/** Set Item1 Amount.
	@param Item1_Amt Item1 Amount	  */
	public void setItem1_Amt (BigDecimal Item1_Amt)
	{
		set_Value (COLUMNNAME_Item1_Amt, Item1_Amt);
	}
	
	/** Get Item1 Amount.
		@return Item1 Amount	  */
	public BigDecimal getItem1_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item1_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	public org.compiere.model.I_C_InvoiceLine getItem1_C_InvoiceLine() throws RuntimeException
	{
		return (org.compiere.model.I_C_InvoiceLine)MTable.get(getCtx(), org.compiere.model.I_C_InvoiceLine.Table_Name)
			.getPO(getItem1_C_InvoiceLine_ID(), get_TrxName());	}
	
	/** Column name Item1_C_InvoiceLine_ID */
    public static final String COLUMNNAME_Item1_C_InvoiceLine_ID = "Item1_C_InvoiceLine_ID";
    
	/** Set Item1 InvoiceLine ID.
		@param Item1_C_InvoiceLine_ID Item1 InvoiceLine ID	  */
	public void setItem1_C_InvoiceLine_ID (int Item1_C_InvoiceLine_ID)
	{
		if (Item1_C_InvoiceLine_ID < 1) 
			set_Value (COLUMNNAME_Item1_C_InvoiceLine_ID, null);
		else 
			set_Value (COLUMNNAME_Item1_C_InvoiceLine_ID, Integer.valueOf(Item1_C_InvoiceLine_ID));
	}
	
	/** Get Item1 InvoiceLine ID.
		@return Item1 InvoiceLine ID	  */
	public int getItem1_C_InvoiceLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Item1_C_InvoiceLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	public org.compiere.model.I_M_Product getItem1() throws RuntimeException
	{
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getItem1_ID(), get_TrxName());	}
	
	/** Column name Item1_ID */
    public static final String COLUMNNAME_Item1_ID = "Item1_ID";
    
	/** Set Item1.
		@param Item1_ID Item1	  */
	public void setItem1_ID (int Item1_ID)
	{
		if (Item1_ID < 1) 
			set_Value (COLUMNNAME_Item1_ID, null);
		else 
			set_Value (COLUMNNAME_Item1_ID, Integer.valueOf(Item1_ID));
	}
	
	/** Get Item1.
		@return Item1	  */
	public int getItem1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Item1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name Item1_Price */
    public static final String COLUMNNAME_Item1_Price = "Item1_Price";
    
	/** Set Item1 Price.
		@param Item1_Price Item1 Price	  */
	public void setItem1_Price (BigDecimal Item1_Price)
	{
		set_Value (COLUMNNAME_Item1_Price, Item1_Price);
	}
	
	/** Get Item1 Price.
		@return Item1 Price	  */
	public BigDecimal getItem1_Price () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item1_Price);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	/** Column name Item1_Qty */
    public static final String COLUMNNAME_Item1_Qty = "Item1_Qty";
    
	/** Set Item1 Qty.
		@param Item1_Qty Item1 Qty	  */
	public void setItem1_Qty (BigDecimal Item1_Qty)
	{
		set_Value (COLUMNNAME_Item1_Qty, Item1_Qty);
	}
	
	/** Get Item1 Qty.
		@return Item1 Qty	  */
	public BigDecimal getItem1_Qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item1_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	/** Column name Item2_Amt */
    public static final String COLUMNNAME_Item2_Amt = "Item2_Amt";
    
	/** Set Item2 Amount.
		@param Item2_Amt Item2 Amount	  */
	public void setItem2_Amt (BigDecimal Item2_Amt)
	{
		set_Value (COLUMNNAME_Item2_Amt, Item2_Amt);
	}
	
	/** Get Item2 Amount.
		@return Item2 Amount	  */
	public BigDecimal getItem2_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item2_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	public org.compiere.model.I_C_InvoiceLine getItem2_C_InvoiceLine() throws RuntimeException
	{
		return (org.compiere.model.I_C_InvoiceLine)MTable.get(getCtx(), org.compiere.model.I_C_InvoiceLine.Table_Name)
			.getPO(getItem2_C_InvoiceLine_ID(), get_TrxName());	}
	
	/** Column name Item2_C_InvoiceLine_ID */
    public static final String COLUMNNAME_Item2_C_InvoiceLine_ID = "Item2_C_InvoiceLine_ID";
    
	/** Set Item2 InvoiceLine ID.
		@param Item2_C_InvoiceLine_ID Item2 InvoiceLine ID	  */
	public void setItem2_C_InvoiceLine_ID (int Item2_C_InvoiceLine_ID)
	{
		if (Item2_C_InvoiceLine_ID < 1) 
			set_Value (COLUMNNAME_Item2_C_InvoiceLine_ID, null);
		else 
			set_Value (COLUMNNAME_Item2_C_InvoiceLine_ID, Integer.valueOf(Item2_C_InvoiceLine_ID));
	}
	
	/** Get Item2 InvoiceLine ID.
		@return Item2 InvoiceLine ID	  */
	public int getItem2_C_InvoiceLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Item2_C_InvoiceLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	public org.compiere.model.I_M_Product getItem2() throws RuntimeException
	{
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getItem2_ID(), get_TrxName());	}
	
	/** Column name Item2_ID */
    public static final String COLUMNNAME_Item2_ID = "Item2_ID";
    
	/** Set Item2.
		@param Item2_ID Item2	  */
	public void setItem2_ID (int Item2_ID)
	{
		if (Item2_ID < 1) 
			set_Value (COLUMNNAME_Item2_ID, null);
		else 
			set_Value (COLUMNNAME_Item2_ID, Integer.valueOf(Item2_ID));
	}
	
	/** Get Item2.
		@return Item2	  */
	public int getItem2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Item2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Column name Item2_Price */
    public static final String COLUMNNAME_Item2_Price = "Item2_Price";
    
	/** Set Item2 Price.
		@param Item2_Price Item2 Price	  */
	public void setItem2_Price (BigDecimal Item2_Price)
	{
		set_Value (COLUMNNAME_Item2_Price, Item2_Price);
	}
	
	/** Get Item2 Price.
		@return Item2 Price	  */
	public BigDecimal getItem2_Price () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item2_Price);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	/** Column name Item2_Qty */
    public static final String COLUMNNAME_Item2_Qty = "Item2_Qty";
    
	/** Set Item2 Qty.
		@param Item2_Qty Item2 Qty	  */
	public void setItem2_Qty (BigDecimal Item2_Qty)
	{
		set_Value (COLUMNNAME_Item2_Qty, Item2_Qty);
	}
	
	/** Get Item2 Qty.
		@return Item2 Qty	  */
	public BigDecimal getItem2_Qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Item2_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	
	
	@Override
	protected boolean beforeSave(boolean newRecord) {		
		boolean result = super.beforeSave(newRecord);
		MBPartner bp = MBPartner.get(getCtx(), getC_BPartner_ID());
		setBPartner(bp);
		return result;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {		
		success = super.afterSave(newRecord, success);		
		updateQuickInvoiceLines();
		return success;
	}
			
	private void setLinePrice(MInvoiceLine line, BigDecimal price) {
		line.setPriceActual(price);
		line.setPriceList(price);
		line.setPriceLimit(price);
		line.setPriceEntered(price);
	}
	
	private void setInvoiceLine(MInvoiceLine line, int product_ID, BigDecimal qty, BigDecimal price) {
		line.setM_Product_ID(product_ID, true);
		line.setQty(qty);
		setLinePrice(line, price);
	}
	
	public void updateQuickInvoiceLines() {
		MInvoiceLine invLine = null;
		//Delete empty item lines
		if(is_ValueChanged(COLUMNNAME_Item1_ID) && getItem1_ID() == 0) {			
			invLine = new MInvoiceLine(getCtx(), getItem1_C_InvoiceLine_ID(), get_TrxName());
			if(invLine.get_ID() > 0) {
				invLine.delete(false);
				DB.executeUpdate("UPDATE C_Invoice SET " + COLUMNNAME_Item1_C_InvoiceLine_ID + " = NULL " +
						" WHERE C_Invoice_ID =" + getC_Invoice_ID(), get_TrxName());
			}
		}
		if(is_ValueChanged(COLUMNNAME_Item2_ID) && getItem2_ID() == 0) {
			invLine = new MInvoiceLine(getCtx(), getItem2_C_InvoiceLine_ID(), get_TrxName());
			if(invLine.get_ID() > 0) {
				invLine.delete(false);
				DB.executeUpdate("UPDATE C_Invoice SET " + COLUMNNAME_Item2_C_InvoiceLine_ID + " = NULL " +
						" WHERE C_Invoice_ID =" + getC_Invoice_ID(), get_TrxName());
			}
		} // End Delete
		
		//Update modified item lines.		
		//Item1
		if(getItem1_ID() > 0 && (is_ValueChanged(COLUMNNAME_Item1_ID) || is_ValueChanged(COLUMNNAME_Item1_Qty)
				|| is_ValueChanged(COLUMNNAME_Item1_Price) || getItem1_C_InvoiceLine_ID() == 0)) {
			
			if(getItem1_C_InvoiceLine_ID() > 0) 
				invLine = new MInvoiceLine(getCtx(), getItem1_C_InvoiceLine_ID(), get_TrxName());
			else
				invLine = new MInvoiceLine(this);
			
			setInvoiceLine(invLine, getItem1_ID(), getItem1_Qty(), getItem1_Price());									
			invLine.saveEx();			
			DB.executeUpdate("UPDATE C_Invoice SET " + COLUMNNAME_Item1_C_InvoiceLine_ID + " = "
				+ invLine.getC_InvoiceLine_ID() + " WHERE C_Invoice_ID = " + getC_Invoice_ID(), get_TrxName());	
		}
		//Item 2
		if(getItem2_ID() > 0 && (is_ValueChanged(COLUMNNAME_Item2_ID) || is_ValueChanged(COLUMNNAME_Item2_Qty)
				|| is_ValueChanged(COLUMNNAME_Item2_Price) || getItem2_C_InvoiceLine_ID() == 0)) {
			
			if(getItem2_C_InvoiceLine_ID() > 0)
				invLine = new MInvoiceLine(getCtx(), getItem2_C_InvoiceLine_ID(), get_TrxName());
			else
				invLine = new MInvoiceLine(this);
			
			setInvoiceLine(invLine, getItem2_ID(), getItem2_Qty(), getItem2_Price());									
			invLine.saveEx();			
			DB.executeUpdate("UPDATE C_Invoice SET " + COLUMNNAME_Item2_C_InvoiceLine_ID + " = "
				+ invLine.getC_InvoiceLine_ID() + " WHERE C_Invoice_ID = " + getC_Invoice_ID(), get_TrxName());	
		}
						
	}

	@Override
	public boolean reverseCorrectIt() {
		
		//For Subcontract Invoice
		int C_Project_ID = getC_Project_ID();
		if(C_Project_ID > 0 && getC_Project().getC_BPartner_ID() == getC_BPartner_ID()) {
			//REVERSAL Procedure
			//0. In Boulder Receipts, Reverse Jobwork Variance Journal & Jobwork Invoiced Price
			//1. In Boulder Receipts, Reset Subcon Invoice ID to NULL
			//2. In Subcontract, Reverse the Invoiced Amount & Qty
			//3. 				Reverse Issued Items
			//4. 				Reverse Issued Vehicle Rent
			//5.				Reverse Operator Wage
			//6.				Reverse Diesel Issued to Rented Vehicle
			//7. 				Reverse Additional Charges
			//8.				Reverse Expenses
			TF_MProject proj = new TF_MProject(getCtx(), C_Project_ID, get_TrxName());
			String whereClause;
			for(MInvoiceLine line : getLines()) {
				if(line.getM_Product_ID() == proj.getJobWork_Product_ID()) {
					//0
					whereClause = "Subcon_Invoice_ID = ? ";
					List<MBoulderReceipt> list = new Query(getCtx(), MBoulderReceipt.Table_Name, whereClause, get_TrxName())
						.setParameters(getC_Invoice_ID()).list();
					for(MBoulderReceipt r : list) {
						r.setJobwork_PriceActual(BigDecimal.ZERO);
						if(r.getJobwork_VarJournal_ID() > 0) {
							MJournal j = new MJournal(getCtx(), r.getJobwork_VarJournal_ID(), get_TrxName());
							j.reverseCorrectIt();
							j.saveEx();
							r.setJobwork_VarJournal_ID(0);							
						}
						//1
						r.setSubcon_Invoice_ID(0);
						r.saveEx();
					}

					//2. In Subcontract, Reverse the Invoiced Amount & Qty
					proj.setInvoicedQty(proj.getInvoicedQty().subtract(line.getQtyInvoiced()));
					proj.setInvoicedAmt(proj.getInvoicedAmt().subtract(line.getLineNetAmt()));
					proj.saveEx();
					continue;
				}
				
				//3 or 4 or 6
				if(line.getM_Product_ID() > 0) {
					//3
					whereClause = " C_Project_ID = ?  AND M_Product_ID = ? ";
					List<MJobworkIssuedItems> issuedItems = new Query(getCtx(), MJobworkIssuedItems.Table_Name, whereClause, get_TrxName())
						.setParameters(C_Project_ID, line.getM_Product_ID()).list();
					if(issuedItems.size() > 0) {						
						//Subtract Deducted Qty from Jobwork Issued Item
						MJobworkIssuedItems issuedItem = issuedItems.get(0);						
						issuedItem.setQtyDeducted(issuedItem.getQtyDeducted().add(line.getQtyInvoiced()));
						issuedItem.saveEx();
						//Reset Invoice ID to Issued Items.
						String sql = " Update TF_Jobwork_ItemIssue SET Subcon_Invoice_ID = NULL WHERE Subcon_Invoice_ID = ? " +
								" AND M_Product_ID = ? ";
						ArrayList<Object> params = new ArrayList<Object>();
						params = new ArrayList<Object>();
						params.add(getC_Invoice_ID());
						params.add(issuedItem.getM_Product_ID());
						DB.executeUpdateEx(sql, params.toArray(), get_TrxName());
						continue;
					}
					
					//4 - vehicle rent
					List<MJobworkIssuedResource> resources = new Query(getCtx(), MJobworkIssuedResource.Table_Name, whereClause, get_TrxName())
						.setParameters(C_Project_ID, line.getM_Product_ID()).list();
					if(resources.size()>0) {
						MJobworkIssuedResource res = resources.get(0);
						//Subtract Deducted Qty from Jobwork issued vehicle
						res.setQtyDeducted(res.getQtyDeducted().add(line.getQtyInvoiced()));
						res.setDeductedAmt(res.getDeductedAmt().add(line.getLineNetAmt()));
						res.saveEx();
						continue;
					}
				}
				
				//5-Operator wage or 7 or 8
				if(line.getC_Charge_ID() > 0) {
					//5 - operator wage
					whereClause = " C_Project_ID = ? AND Wage_Charge_ID = ? ";
					List<MJobworkIssuedResource> resources = new Query(getCtx(), MJobworkIssuedResource.Table_Name, whereClause, get_TrxName())
					.setParameters(C_Project_ID, line.getC_Charge_ID()).list();
					if(resources.size()>0) {
						MJobworkIssuedResource res = resources.get(0);
						//Subtract Deducted wage to Jobwork Issued Vehicle
						res.setOperatorDeductedWage(res.getOperatorDeductedWage().add(line.getLineNetAmt()));
						res.saveEx();
					}
					
					//7
					whereClause = " C_Project_ID = ? AND C_Charge_ID = ? ";
					List<MJobworkCharges> charges = new Query(getCtx(), MJobworkCharges.Table_Name, whereClause, get_TrxName())
						.setParameters(C_Project_ID, line.getC_Charge_ID()).list();
					if(charges.size()>0) {						
						//Subtract Deducted Amount from Jobwork Additonal Charge
						MJobworkCharges charge = charges.get(0);
						charge.setDeductedAmt(charge.getDeductedAmt().add(line.getLineNetAmt()));
						charge.saveEx();
						//Reset Invoice ID to Additional Charges and Advance Amount.
						String sql = " Update C_Payment SET Subcon_Invoice_ID = NULL WHERE Subcon_Invoice_ID = ? " +
								" AND C_Charge_ID = ? ";
						ArrayList<Object> params = new ArrayList<Object>();
						params = new ArrayList<Object>();
						params.add(getC_Invoice_ID());
						params.add(charge.getC_Charge_ID());
						DB.executeUpdateEx(sql, params.toArray(), get_TrxName());
						continue;
					}					
					
					//8
					whereClause = " C_Project_ID = ? AND C_ElementValue_ID = (SELECT c.C_ElementValue_ID FROM "
								+ " C_Charge c WHERE c.C_Charge_ID = ? ) ";
					List<MJobworkExpense> expenses = new Query(getCtx(), MJobworkExpense.Table_Name, whereClause, get_TrxName())
						.setParameters(C_Project_ID, line.getC_Charge_ID()).list();
					if(expenses.size()>0) {						
						//Subtract Deducted Amount from Jobwork Expense
						MJobworkExpense exp = expenses.get(0);
						exp.setDeductedAmt(exp.getDeductedAmt().add(line.getLineNetAmt()));
						exp.saveEx();
						//Reset Invoice ID to Expense Entry.
						String sql = " Update TF_Jobwork_Expense_Entry SET Subcon_Invoice_ID = NULL WHERE Subcon_Invoice_ID = ? " +
								" AND C_ElementValue_ID = ? ";
						ArrayList<Object> params = new ArrayList<Object>();
						params = new ArrayList<Object>();
						params.add(getC_Invoice_ID());
						params.add(exp.getC_ElementValue_ID());
						DB.executeUpdateEx(sql, params.toArray(), get_TrxName());
						continue;
					}
				}
				
			}
			
			//Reset Invoice ID to Issued Resource/Vehicle Rent Entry for Monthly Contract Base.
			String sql = " Update TF_Jobwork_ResRentEntry SET Subcon_Invoice_ID = NULL WHERE Subcon_Invoice_ID = ? " ;
			ArrayList<Object> params = new ArrayList<Object>();			
			params.add(getC_Invoice_ID());						
			DB.executeUpdateEx(sql, params.toArray(), get_TrxName());
			
			//Reset Invoice ID to TripSheet Entries.
			sql = " Update TF_TripSheet SET Subcon_Invoice_ID = NULL WHERE Subcon_Invoice_ID = ? " ;
			params = new ArrayList<Object>();
			params.add(getC_Invoice_ID());
			DB.executeUpdateEx(sql, params.toArray(), get_TrxName());
			
			
		}//End C_Project_ID
		
		int TF_Vehicle_Rental_Contract_ID = get_ValueAsInt("TF_Vehicle_Rental_Contract_ID");
		if(TF_Vehicle_Rental_Contract_ID > 0) {
				for(MInvoiceLine invLine : getLines()) {
					if(invLine.getM_Product_ID()>0 && 
							invLine.getM_Product().getProductType().equals(MProduct.PRODUCTTYPE_Resource)) {
						MVehicleRentalContract rc = new MVehicleRentalContract(getCtx(), TF_Vehicle_Rental_Contract_ID, get_TrxName());
						rc.setQtyInvoiced(rc.getQtyInvoiced().subtract(invLine.getQtyInvoiced()));
						//rc.setInvoiced_Amt(rc.getInvoiced_Amt().subtract(invLine.getLineTotalAmt()));
						rc.saveEx();
						break;
					}
				}
		}
		return super.reverseCorrectIt();
	}

}
