package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MWarehouse;
import org.compiere.process.DocAction;

public class MJobworkItemIssue extends X_TF_Jobwork_ItemIssue {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4455822570297383854L;

	public MJobworkItemIssue(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MJobworkItemIssue(Properties ctx, int TF_Jobwork_ItemIssue_ID,
			String trxName) {
		super(ctx, TF_Jobwork_ItemIssue_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public void processIt(String docAction) {
		if(DocAction.ACTION_Prepare.equals(docAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(DocAction.ACTION_Complete.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			
			//Post Inventory Use Inventory for Fuel Expense.
			MWarehouse wh = (MWarehouse) getM_Warehouse();
			//Inventory Use Header
			MInventory inv = new MInventory(wh, get_TrxName());
			inv.setC_DocType_ID(1000026);
			String desc = getM_Product().getName() + " Issued to (Subcontract) " +  getC_Project().getName(); 
			inv.setDescription(desc);
			inv.setMovementDate(getDateAcct());
			inv.setUser1_ID(getC_ElementValue_ID());			
			inv.setDocStatus(DOCSTATUS_Drafted);
			inv.setC_Project_ID(getC_Project_ID());
			inv.saveEx();
			
			//Inventory Use Line
			MInventoryLine line = new MInventoryLine(inv, wh.getDefaultLocator().get_ID(), getM_Product_ID(), 0, null, null, getQty());
			line.setC_Charge_ID(MGLPostingConfig.getMGLPostingConfig(getCtx()).getItemIssue_Charge_ID());
			line.setDescription(desc);
			//line.setCurrentCostPrice(getRate());
			line.saveEx();
			
			//Complete Inventory Use Document
			inv.processIt(docAction);
			inv.saveEx();
			
			//Update Inventory Use ID back to Fuel Issue Entry.
			setM_Inventory_ID(inv.getM_Inventory_ID());		
			
			// Added to Jobwork Issued Items
			MJobworkIssuedItems.addIssuedItem(getCtx(), getC_Project_ID(), getM_Product_ID(), getC_UOM_ID(), getQty(), get_TrxName());
			
		}
		
	}
	
	public void reverseIt() {
		if(getM_Inventory_ID() > 0) {
			
			// Reversed from Jobwork Issued Items
			MJobworkIssuedItems.addIssuedItem(getCtx(), getC_Project_ID(), getM_Product_ID(), getC_UOM_ID(), getQty().negate(), get_TrxName());
			
			MInventory inv = new MInventory(getCtx(), getM_Inventory_ID(), get_TrxName());
			inv.reverseCorrectIt();
			inv.saveEx();
			
			
			setM_Inventory_ID(0);
			setProcessed(false);
			setDocStatus(DOCSTATUS_Drafted);
		}
	}
}
