package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MProduction;
import org.compiere.model.MProductionLine;
import org.compiere.model.Query;

public class MCrusherProduction extends X_TF_Crusher_Production {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3035302864622713550L;

	public MCrusherProduction(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCrusherProduction(Properties ctx, int TF_Crusher_Production_ID,	String trxName) {
		super(ctx, TF_Crusher_Production_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public List<MProduction> getProductions() {
		String where = " TF_Crusher_Production_ID = ?";
		List<MProduction> productions = new Query(getCtx(), MProduction.Table_Name, where, get_TrxName())
		.setClient_ID().setParameters(getTF_Crusher_Production_ID()).list();
		return productions;
	}
	
	public void deleteProductions() {		
		List<MProduction> productions = getProductions();
		for(MProduction prod : productions) {
			prod.deleteLines(get_TrxName());
			prod.deleteEx(true);
		}
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {		
		//if(newRecord || is_ValueChanged(COLUMNNAME_TF_BlueMetal_Type) || is_ValueChanged(COLUMNNAME_QtyUsed)) {
		//	createProduction(true);
		//}		
		return super.afterSave(newRecord, success);
	}

	public void createProduction(boolean reCreate){
		if(!reCreate && getIsCreated().equals("Y"))
			return;
		
		// Delete if there is any productions...
		deleteProductions();
		//RM - Raw Material, BM - Blue Metal
		double RMQtyUsed = getQtyUsed().doubleValue();		
		MCrusherProductionConfig[] configs = MCrusherProductionConfig.getMCrusherProductionConfig(getCtx(), getTF_BlueMetal_Type());
		//Creating Production Headers
		for(MCrusherProductionConfig config : configs) {
			double BMPercent = config.getPercent().doubleValue();			
			double BMUnitDivisor = config.getUnit_Divisor().doubleValue();
			double RMQtyUsedPerBlueMetal = RMQtyUsed * BMPercent / 100;
			double BMProductionQtyInUnit = RMQtyUsedPerBlueMetal / BMUnitDivisor;
			
			// Production Header
			MProduction prod = new MProduction(getCtx(), 0, get_TrxName());
			prod.set_ValueOfColumn("TF_Crusher_Production_ID", getTF_Crusher_Production_ID());
			prod.setMovementDate(getMovementDate());
			prod.set_ValueOfColumn("ProductionType", "P+");
			prod.setM_Product_ID(config.getM_Product_ID());
			prod.setM_Locator_ID(getM_Locator_ID());
			prod.setProductionQty(new BigDecimal(BMProductionQtyInUnit).setScale(3, BigDecimal.ROUND_CEILING));
			prod.setIsCreated("Y");
			prod.set_ValueOfColumn("RM_Product_ID", getRM_Product_ID());
			prod.set_ValueOfColumn("QtyUsed", new BigDecimal(RMQtyUsedPerBlueMetal));
			prod.setDocStatus(MProduction.DOCSTATUS_Drafted);
			prod.saveEx();
			
			//Create End Product Production Line
			MProductionLine  line = new MProductionLine(prod);
			line.setLine(10);
			line.setM_Product_ID(config.getM_Product_ID());
			line.setM_Locator_ID(getM_Locator_ID());
			//line.setQtyUsed(prod.getProductionQty());
			line.setMovementQty(prod.getProductionQty());
			line.setPlannedQty(prod.getProductionQty());			
			line.saveEx();
			
			//Create Raw material (Boulder) Production Line
			MProductionLine  bomLine = new MProductionLine(prod);
			bomLine.setLine(20);
			bomLine.setM_Product_ID(config.getRM_Product_ID());
			bomLine.setM_Locator_ID(getM_Locator_ID());
			bomLine.setQtyUsed(new BigDecimal(RMQtyUsedPerBlueMetal));			
			bomLine.setMovementQty(new BigDecimal(RMQtyUsedPerBlueMetal));
			bomLine.setPlannedQty(bomLine.getQtyUsed());			
			bomLine.saveEx();
			
		}		
		setIsCreated("Y");		
	}
	
	public String validateCrusherProduction() {		
		if(getProductions().size() == 0)
			return "Create Production lines!";
		
		return null;
			
	}
	
	public String processIt(String DocAction) {
		String m_processMsg = validateCrusherProduction();
		
		if(m_processMsg != null)
			return m_processMsg;
		
		if(MProduction.DOCACTION_Prepare.equals(DocAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(MProduction.DOCACTION_Complete.equals(DocAction)) {
			List<MProduction> productions = getProductions();
			
			for(MProduction prod : productions) {
				boolean result = prod.processIt(DocAction);
				prod.saveEx();
				if(!result) {
					m_processMsg = " Not able to Complete the Production! ";
					break;
				}
			}
			
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			
		}
		return m_processMsg;
	}
	
	public void reverseIt() {
		if(isProcessed() && getDocStatus().equals(DOCSTATUS_Completed)) {
			List<MProduction> productions = getProductions();			
			for(MProduction prod : productions) {
				prod.reverseCorrectIt();
				prod.saveEx();
			}
			setDocStatus(DOCSTATUS_Reversed);			
		}
	}
}
