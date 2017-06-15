package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MProduct;
import org.compiere.model.MTable;
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
