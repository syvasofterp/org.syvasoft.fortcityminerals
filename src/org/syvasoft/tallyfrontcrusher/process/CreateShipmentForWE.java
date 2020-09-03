package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MOrder;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTax;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.TF_MInOut;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;

public class CreateShipmentForWE extends SvrProcess {
		
	@Override
	protected void prepare() {		
		ProcessInfoParameter[] para = getParameter();		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();
		}
	}

	@Override
	protected String doIt() throws Exception {
		String whereClause = " TF_WeighmentEntry.WeighmentEntryType = '1SO' AND TF_WeighmentEntry.Status = 'CO' AND TF_WeighmentEntry.C_OrderLine_ID IS NOT NULL";
				
		List<MWeighmentEntry> list = new Query(getCtx(), MWeighmentEntry.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setOrderBy("DocumentNo")
				.list();
		for(MWeighmentEntry we : list) {
			createShipmentDocument(we);
		}
		return list.size() + " weighment entries are procesed!";
	}
	
	public void createShipmentDocument(MWeighmentEntry we) {
		 TF_MOrderLine orderLine = new  TF_MOrderLine(getCtx(), we.getC_OrderLine_ID(), get_TrxName());
		 
		 if(orderLine != null) {	
			 MOrder order = orderLine.getParent();
			 
			 int shipmentDocId = order.getC_DocType().getC_DocTypeShipment_ID();
			 
			 //Material Issue
			TF_MInOut inout = new TF_MInOut(order, shipmentDocId, we.getGrossWeightTime());
			inout.setTF_WeighmentEntry_ID(we.getTF_WeighmentEntry_ID());
			inout.setDescription(we.getDescription());
			inout.setMovementType(MInOut.MOVEMENTTYPE_CustomerShipment);
			inout.saveEx(get_TrxName());
			
			//Material Issue Line
			MInOutLine ioLine = new MInOutLine(inout);
			MWarehouse wh = (MWarehouse) we.getM_Warehouse();
			ioLine.setOrderLine(orderLine, wh.getDefaultLocator().get_ID(), we.getNetWeightUnit());
			ioLine.setQty(we.getNetWeightUnit());
			ioLine.saveEx(get_TrxName());
			
			//Material Issue DocAction
			if (!inout.processIt(DocAction.ACTION_Complete))
				throw new AdempiereException("Failed when processing document - " + order.getProcessMsg());
			
			inout.saveEx();
			//End DocAction
		 }
	}
}
