package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MPriceList;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Trx;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MInOut;
import org.syvasoft.tallyfrontcrusher.model.TF_MInOutLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;

public class CreateTransporterInvoice extends SvrProcess {

	int C_Tax_ID = 0;
	boolean reverseCharge = false;
	int M_InoutLine_ID = 0;
	Savepoint sp = null;
	Timestamp dateInvoiced = null;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();	
		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();
			if(name.equals("C_Tax_ID"))
				C_Tax_ID = para[i].getParameterAsInt();
			else if (name.equals("ReverseCharge"))
				reverseCharge = para[i].getParameterAsBoolean();
			else if (name.equals("DateInvoiced"))
				dateInvoiced  = para[i].getParameterAsTimestamp();
		}
		
		M_InoutLine_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		String whereClause = " DocStatus = 'CO' AND (EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE " +
				" T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID = M_InOutLIne.M_InOutLIne_ID) OR M_InOutLine_ID = ?) "
				+ "  ";
		List<TF_MInOutLine> list = new Query(getCtx(), TF_MInOutLine.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getAD_PInstance_ID(), M_InoutLine_ID)
				.list();
		int i = 0;
		for(TF_MInOutLine ioLine : list) {
			TF_MInOut io = new TF_MInOut(getCtx(), ioLine.getM_InOut_ID(), get_TrxName());
			if(!io.getDocStatus().equals(TF_MInOut.STATUS_Completed)) {
				addLog(0, null, BigDecimal.ONE, "Invalid Material Receipt Document Status : " + io.getDocStatusName(), io.get_Table_ID(), io.get_ID());
				continue;
			}		
			
			if(ioLine.getPrice().doubleValue() == 0) {
				addLog(0, null, null, "Doc No: " + io.getDocumentNo() + " - Invalid Price!" , io.get_Table_ID(), io.get_ID());
				continue;
			}
			
			Trx trx = Trx.get(get_TrxName(), false);
			
			try {
				sp = trx.setSavepoint(io.getDocumentNo());
				TF_MOrder ord = new TF_MOrder(getCtx(), 0, get_TrxName());
				ord.setIsSOTrx(false);
				ord.setC_DocTypeTarget_ID(ord.getC_VendorInvoiceDocType_ID());
				ord.setC_DocType_ID(ord.getC_VendorInvoiceDocType_ID());
				ord.setDateAcct(dateInvoiced);
				ord.setDateOrdered(dateInvoiced);
				ord.setC_BPartner_ID(io.getC_BPartner_ID());
				ord.setM_Warehouse_ID(io.getM_Warehouse_ID());
				ord.setPaymentRule(TF_MOrder.PAYMENTRULE_OnCredit);
				//Price List
				int m_M_PriceList_ID = MPriceList.getDefault(getCtx(), true).getM_PriceList_ID();							
				ord.setM_PriceList_ID(m_M_PriceList_ID);
				ord.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
				
				ord.setTF_Destination_ID(ioLine.getTF_Destination_ID());
				ord.setDistance(ioLine.getDistance());				
				
				ord.saveEx();
											
				//create lines
				TF_MOrderLine ordLine = new TF_MOrderLine(ord);
				BigDecimal rateMTKM = ioLine.getRateMTKM();
				BigDecimal price = ioLine.getPrice();
				BigDecimal distance = ioLine.getDistance();
				MWeighmentEntry wEntry = new MWeighmentEntry(getCtx(), io.getTF_WeighmentEntry_ID(), get_TrxName());
				ordLine.setDescription("Customer DC#: " + wEntry.getDocumentNo());
				if(rateMTKM != null && rateMTKM.doubleValue() >  0) {
					price = price.multiply(ioLine.getDistance());
					ordLine.addDescription("Rate MT/km : " + rateMTKM.doubleValue() + ", Distance (km): " + distance);
				}				
				ordLine.setM_Product_ID(ioLine.getM_Product_ID(), ioLine.getC_UOM_ID());
				ordLine.setC_UOM_ID(ioLine.getC_UOM_ID());
				ordLine.setPriceActual(price);
				ordLine.setPriceList(price);
				ordLine.setPriceLimit(price);
				ordLine.setPriceEntered(price);
				ordLine.setQty(ioLine.getMovementQty());
				ordLine.setC_Tax_ID(C_Tax_ID);
				ordLine.saveEx();
				
				io.setC_Order_ID(ord.getC_Order_ID());
				io.saveEx();
				
				ioLine.setC_OrderLine_ID(ordLine.getC_OrderLine_ID());
				ioLine.set_ValueOfColumn("DocStatus", MWeighmentEntry.STATUS_Billed);
				ioLine.saveEx();
				
				//complete document
				if (!ord.processIt(DocAction.ACTION_Complete))
					throw new AdempiereException("Failed when processing document - " + ord.getProcessMsg());
				ord.setDocStatus(ord.DOCSTATUS_Completed);
				ord.setProcessed(true);
				ord.saveEx();
												
				trx.releaseSavepoint(sp);
				addLog(ord.get_Table_ID(), null, null, " Transporter PO Entry : " +  ord.getDocumentNo() + " is created!", ord.get_Table_ID(), ord.get_ID());
				
			}
			catch (Exception ex) {
				if(sp != null)
					trx.rollback(sp);				
				addLog(0, null, null, ex.getMessage(), ioLine.get_Table_ID(), ioLine.get_ID());
			}
			i++;
		}
		
		return i + "  Entries are processed!";
	}

}
