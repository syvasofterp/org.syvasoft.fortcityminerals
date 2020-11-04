package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MOrder;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.TF_MInOut;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;

public class CreateShipmentForWE extends SvrProcess {
		
	@Override
	protected void prepare() {		
		
	}

	@Override
	protected String doIt() throws Exception {
		String whereClause = " TF_WeighmentEntry.WeighmentEntryType = '1SO' AND TF_WeighmentEntry.Status IN ('CO','CL') AND TF_WeighmentEntry.Processed='N' ";
				
		List<MWeighmentEntry> list = new Query(getCtx(), MWeighmentEntry.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setOrderBy("DocumentNo")
				.list();
		int i=0;
		int j=0;
				
		for(MWeighmentEntry we : list) {
			if(!we.getNetWeightUnit().equals(BigDecimal.ZERO))
			{
				createShipmentDocument(we);
				i=i+1;
			}
			else {
				j=j+1;
				we.setDescription("Net Weighment Qty cannot be zero");
				we.saveEx();
			}
		}
		return i + " weighment entries are procesed!, "+j+" invalid qty weighment entries are not processed!";
	}
	
	public void createShipmentDocument(MWeighmentEntry we) {
		 TF_MOrderLine orderLine = new  TF_MOrderLine(getCtx(), we.getC_OrderLine_ID(), get_TrxName());
		 
		 if(orderLine != null) {	
			 MOrder order = orderLine.getParent();
			 
			 int shipmentDocId = we.getC_DocTypeShipment_ID();
			 
			 //Material Issue
			TF_MInOut inout = new TF_MInOut(getCtx(), 0, get_TrxName());
			inout.setAD_Org_ID(we.getAD_Org_ID());
			inout.setC_BPartner_ID(we.getC_BPartner_ID());
			TF_MBPartner bp = new TF_MBPartner(getCtx(), we.getC_BPartner_ID(), get_TrxName());
			inout.setC_BPartner_Location_ID(bp.getPrimaryC_BPartner_Location_ID());
			inout.setAD_User_ID(bp.getAD_User_ID());
			inout.setDateAcct(we.getGrossWeightTime());
			inout.setC_DocType_ID(shipmentDocId);
			inout.setM_Warehouse_ID(we.getM_Warehouse_ID());
			inout.setDeliveryRule(TF_MInOut.DELIVERYRULE_Availability);
			inout.setDeliveryViaRule(TF_MInOut.DELIVERYVIARULE_Pickup);
			
			inout.setTF_WeighmentEntry_ID(we.getTF_WeighmentEntry_ID());
			inout.setDescription(we.getDescription());
			inout.setMovementType(MInOut.MOVEMENTTYPE_CustomerShipment);
			inout.saveEx(get_TrxName());
			
			//Material Issue Line
			MInOutLine ioLine = new MInOutLine(inout);			
			//ioLine.setOrderLine(orderLine, wh.getDefaultLocator().get_ID(), we.getNetWeightUnit());
			ioLine.setM_Product_ID(we.getM_Product_ID());
			ioLine.setC_UOM_ID(we.getC_UOM_ID());			
			ioLine.setQty(we.getNetWeightUnit());
			ioLine.setM_Locator_ID(we.getNetWeightUnit());
			ioLine.saveEx(get_TrxName());
			
			//Material Issue DocAction
			if (!inout.processIt(DocAction.ACTION_Complete))
				throw new AdempiereException("Failed when processing document - " + order.getProcessMsg());
			
			inout.saveEx();
			//End DocAction
						
		 }
	}
}
