package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.security.auth.SubjectDomainCombiner;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalLine;
import org.compiere.model.MPeriod;
import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
import org.compiere.model.MQuery;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
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
	
	/** Column name VehicleNo */
    public static final String COLUMNNAME_VehicleNo = "VehicleNo";
    /** Set Vehicle No.
	@param VehicleNo Vehicle No	  */
	public void setVehicleNo (String VehicleNo)
	{
		set_Value (COLUMNNAME_VehicleNo, VehicleNo);
	}
	
	/** Get Vehicle No.
		@return Vehicle No	  */
	public String getVehicleNo () 
	{
		return (String)get_Value(COLUMNNAME_VehicleNo);
	}
	
	/** Column name GL_Journal_ID */
    public static final String COLUMNNAME_GL_Journal_ID = "GL_Journal_ID";
    public org.compiere.model.I_GL_Journal getGL_Journal() throws RuntimeException
    {
		return (org.compiere.model.I_GL_Journal)MTable.get(getCtx(), org.compiere.model.I_GL_Journal.Table_Name)
			.getPO(getGL_Journal_ID(), get_TrxName());	}

	/** Set Journal.
		@param GL_Journal_ID 
		General Ledger Journal
	  */
	public void setGL_Journal_ID (int GL_Journal_ID)
	{
		if (GL_Journal_ID < 1) 
			set_Value (COLUMNNAME_GL_Journal_ID, null);
		else 
			set_Value (COLUMNNAME_GL_Journal_ID, Integer.valueOf(GL_Journal_ID));
	}

	/** Get Journal.
		@return General Ledger Journal
	  */
	public int getGL_Journal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GL_Journal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
	public String completeIt() {		
		String msg = super.completeIt();
		createCounterProjectSalesInvoice();
		return msg;
	}

	@Override
	public boolean reverseCorrectIt() {
		boolean ok = super.reverseCorrectIt();
		if(getRef_Invoice_ID() > 0) {			
			MInvoice inv = new MInvoice(getCtx(), getRef_Invoice_ID(), get_TrxName());
			if(inv.getDocStatus().equals(DOCSTATUS_Completed)) {
				inv.reverseCorrectIt();
				inv.saveEx();
			}
			int GL_Journal_ID = inv.get_ValueAsInt(COLUMNNAME_GL_Journal_ID);
			if(GL_Journal_ID > 0) {
				TF_MJournal j = new TF_MJournal(getCtx(), GL_Journal_ID, get_TrxName());
				if(j.getDocStatus().equals(TF_MInvoice.DOCSTATUS_Completed)) {
					if (!j.processIt(DocAction.ACTION_Reverse_Correct))
						throw new AdempiereException("Failed when processing document - " + j.getProcessMsg());
					j.saveEx();
				}
			}
			if(inv.getC_Project_ID() > 0  && inv.isSOTrx()) {
				TF_MProject proj = new TF_MProject(getCtx(), inv.getC_Project_ID(), get_TrxName());
				if(proj.getSubcontractType().equals(TF_MProject.SUBCONTRACTTYPE_KatingProject)) {
					proj.updateQtyBilled();
					proj.saveEx();
				}
			}
		}	
		
		return ok;
	}
	
	public void createCounterProjectSalesInvoice() {
		if(isSOTrx() || getC_Project_ID() == 0)
			return;
		
		TF_MProject counterProj = TF_MProject.getLinkedProject(getC_Project_ID());
		if(counterProj == null || !counterProj.isCreateSalesInvoice() || 
				counterProj.getC_DocTypeLink_ID() != getC_DocTypeTarget_ID())
			return;
		
		
		int Customer_ID = counterProj.getC_BPartner_ID();
		int SalesInvoiceDocType_ID = counterProj.getC_DocTypeSalesInvoice_ID();
		int Product_ID = counterProj.getJobWork_Product_ID();
		
				
		//Crusher Production Subcontract Purchase		
		BigDecimal purchasePrice = MJobworkProductPrice.getPrice(getCtx(), counterProj.getC_Project_ID(), Product_ID, getDateAcct()) ;
		
		TF_MBPartner bp = new TF_MBPartner(getCtx(), Customer_ID, get_TrxName());
		
		//Invoice Header
		TF_MInvoice invoice = new TF_MInvoice(getCtx(), 0, get_TrxName());
		invoice.setClientOrg(getAD_Client_ID(), counterProj.getAD_Org_ID());
		invoice.setC_DocTypeTarget_ID(SalesInvoiceDocType_ID);	// AP Invoice
		invoice.setIsSOTrx(true);
		invoice.setDateInvoiced(getDateInvoiced());
		invoice.setDateAcct(getDateAcct());
		//
		invoice.setSalesRep_ID(Env.getAD_User_ID(getCtx()));		
		//
		
		invoice.setBPartner(bp);				
		invoice.setVehicleNo(getVehicleNo());		
		invoice.setDescription("(" + getDocumentNo() + ")");
		
		//Price List
		int m_M_PriceList_ID = Env.getContextAsInt(getCtx(), "#M_PriceList_ID");
		if(bp.getPO_PriceList_ID() > 0)
			m_M_PriceList_ID = bp.getPO_PriceList_ID();			
		invoice.setM_PriceList_ID(m_M_PriceList_ID);
		invoice.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
		
		//Financial Dimension - Profit Center		
		invoice.setC_Project_ID(counterProj.getC_Project_ID());
		invoice.setRef_Invoice_ID(getC_Invoice_ID());
		invoice.saveEx();
		//End Invoice Header
		
		
		// Reverse Unbilled JObwork amount		
		MSubcontractType subconType = new MSubcontractType(getCtx(), counterProj.getTF_SubcontractType_ID(), get_TrxName());
		int unbilledJobworkAcct = subconType.getUnbilledKatingJobworkAcct_ID();
		int unbilledJobworkReceivableAcct = subconType.getUnbillKatingReceivableAcct_ID();
		
		int m_C_DocTypeTarget_ID = 1000000;						
		TF_MJournal j = new TF_MJournal(getCtx(), 0, get_TrxName());		
		j.setDescription("Unbilled Kating Jobwork Reversed from Sales - " + invoice.getDocumentNo());
		j.setAD_Org_ID(invoice.getAD_Org_ID());
		j.setC_AcctSchema_ID(Env.getContextAsInt(getCtx(), "$C_AcctSchema_ID"));
		j.setC_Currency_ID(Env.getContextAsInt(getCtx(), "$C_Currency_ID"));
		j.setPostingType(TF_MJournal.POSTINGTYPE_Actual);
		j.setC_DocType_ID(m_C_DocTypeTarget_ID);
		j.setDateDoc(getDateAcct());
		j.setDateAcct(getDateAcct());
		j.setDocStatus(TF_MJournal.DOCSTATUS_Drafted);
		MPeriod period = MPeriod.get(getCtx(), getDateAcct());
		j.setC_Period_ID(period.getC_Period_ID());
		j.setGL_Category_ID(1000000);
		j.setC_ConversionType_ID(114);
		j.setC_Project_ID(counterProj.getC_Project_ID());
		j.saveEx();
		// End journal
				
		
		int line = 10;
		BigDecimal totalAmt = BigDecimal.ZERO;
		BigDecimal totalQty = BigDecimal.ZERO;		
		//Invoice Line - Item1		
		for(MInvoiceLine srcLine : getLines()) {
			//Create Invoice Line
			MInvoiceLine invLine = new MInvoiceLine(invoice);
			invLine.setM_Product_ID(Product_ID , true);
			invLine.setQty(srcLine.getQtyInvoiced());					
			invLine.setPriceActual(purchasePrice);
			invLine.setPriceList(purchasePrice);
			invLine.setPriceLimit(purchasePrice);
			invLine.setPriceEntered(purchasePrice);		
			invLine.setC_Tax_ID(1000000);
			invLine.setDescription(srcLine.getDescription());
			invLine.setC_Project_ID(counterProj.getC_Project_ID());
			invLine.saveEx();
			
			//Create Unbilled JObwork reversal line
			//Debit Unbilled Jobwork				
			String desc = "Reversed from " + invoice.getDocumentNo() +  " | Tonnage: " + invLine.getQtyInvoiced() + ", Price: " + invLine.getPriceEntered();			
			MJournalLine jl = new MJournalLine(j);
			jl.setLine(line);			
			jl.setAccount_ID(unbilledJobworkAcct);
			jl.setM_Product_ID(invLine.getM_Product_ID());
			jl.setC_BPartner_ID(invoice.getC_BPartner_ID());
			jl.setQty(invLine.getQtyInvoiced());
			jl.setC_UOM_ID(invLine.getC_UOM_ID());
			jl.setDescription(desc);
			jl.setAmtSourceDr(invLine.getLineNetAmt());
			jl.setAmtAcctDr(invLine.getLineNetAmt());
			jl.setIsGenerated(true);
			jl.saveEx();
			
			line = line + 10;
			totalAmt = totalAmt.add(invLine.getLineNetAmt());
			totalQty = totalQty.add(invLine.getQtyInvoiced());
		}
		
		//Credit Unbilled Jobwork Receivable
		String desc = "Reversed from " + invoice.getDocumentNo() +  " | Tonnage: " + totalQty + ", Price: " + purchasePrice;
		MJournalLine jl;			
		jl = new MJournalLine(j);
		jl.setLine(10);			
		jl.setAccount_ID(unbilledJobworkReceivableAcct);
		jl.setM_Product_ID(counterProj.getJobWork_Product_ID());
		jl.setC_BPartner_ID(invoice.getC_BPartner_ID());
		jl.setC_UOM_ID(counterProj.getC_UOM_ID());
		jl.setQty(totalQty);
		jl.setDescription("Reversed from " + invoice.getDocumentNo());
		jl.setAmtSourceCr(totalAmt);
		jl.setAmtAcctCr(totalAmt);
		jl.setIsGenerated(true);
		jl.saveEx();
				
		
		//DocAction
		if (!j.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + j.getProcessMsg());
		j.saveEx();

		invoice.setGL_Journal_ID(j.getGL_Journal_ID());
		//Invoice DocAction
		if (!invoice.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + invoice.getProcessMsg());
		invoice.saveEx();
		
		counterProj.set_TrxName(get_TrxName());		
		counterProj.updateQtyBilled();
		counterProj.saveEx(get_TrxName());
		setRef_Invoice_ID(invoice.getC_Invoice_ID());
	}

}
