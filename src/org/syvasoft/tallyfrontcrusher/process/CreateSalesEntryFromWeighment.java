package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.sql.Savepoint;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MPriceList;
import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;

public class CreateSalesEntryFromWeighment extends SvrProcess {
	private int C_DocType_ID = 0;
	@Override
	protected void prepare() {		
		ProcessInfoParameter[] para = getParameter();		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();
			if(name.equals("C_DocType_ID"))
				C_DocType_ID = para[i].getParameterAsInt();			
		}
	}

	@Override
	protected String doIt() throws Exception {
		String whereClause = "WeighmentEntryType = '1SO' AND Status = 'CO' AND (SELECT OrgType FROM AD_Org WHERE "
				+ "AD_Org.AD_Org_ID = TF_WeighmentEntry.AD_Org_ID) = 'W'"
				+ " AND NOT EXISTS(SELECT C_Order.TF_WeighmentEntry_ID FROM C_Order WHERE "
				+ "C_Order.TF_WeighmentEntry_ID =  TF_WeighmentEntry.TF_WeighmentEntry_ID)";
		List<MWeighmentEntry> wEntries = new Query(getCtx(), MWeighmentEntry.Table_Name, whereClause, get_TrxName())
				.setClient_ID().list();
		for(MWeighmentEntry wEntry : wEntries) {
			Trx trx = Trx.get(get_TrxName(), false);
			Savepoint sp = null;
			try {
				TF_MOrder ord = new TF_MOrder(getCtx(), 0, get_TrxName());
				ord.setAD_Org_ID(wEntry.getAD_Org_ID());
				ord.setC_DocTypeTarget_ID(C_DocType_ID);
				ord.setC_DocType_ID(C_DocType_ID);
				ord.setM_Warehouse_ID(wEntry.getM_Warehouse_ID());
				ord.setDateAcct(wEntry.getGrossWeightTime());
				ord.setDateOrdered(wEntry.getGrossWeightTime());
				int C_BParner_ID = wEntry.getC_BPartner_ID();
				if(C_BParner_ID == 0)
					C_BParner_ID = 1000020;		
				TF_MBPartner bp = new TF_MBPartner(getCtx(), C_BParner_ID, get_TrxName());
				ord.setBPartner(bp);
				ord.setDescription(wEntry.getDescription());
				if(ord.getDescription() != null)
					ord.addDescription("Customer Name : " + wEntry.getPartyName());
				else
					ord.setDescription("Customer Name : " + wEntry.getPartyName());
				ord.setPaymentRule(wEntry.getPaymentRule());		
				//Price List
				int m_M_PriceList_ID = Env.getContextAsInt(getCtx(), "#M_PriceList_ID");
				if(bp.getM_PriceList_ID() > 0)
					m_M_PriceList_ID = bp.getM_PriceList_ID();			
				ord.setM_PriceList_ID(m_M_PriceList_ID);
				ord.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
				ord.setIsSOTrx(true);
				ord.setTF_WeighmentEntry_ID(wEntry.getTF_WeighmentEntry_ID());	
				ord.setTF_Destination_ID(wEntry.getTF_Destination_ID());
				ord.setVehicleNo(wEntry.getVehicleNo());
				ord.setTF_RentedVehicle_ID(wEntry.getTF_RentedVehicle_ID());
				ord.setItem1_BucketQty(null);
				
				
				//Item
				ord.setItem1_IsPermitSales(wEntry.isHasBalance());
				ord.setItem1_VehicleType_ID(wEntry.getTF_VehicleType_ID());
				if(wEntry.isHasBalance())
					ord.setItem1_SandType(TF_MOrder.ITEM1_SANDTYPE_PermitSand);
				else
					ord.setItem1_SandType(TF_MOrder.ITEM1_SANDTYPE_WithoutPermit);
				ord.setItem1_ID(wEntry.getM_Product_ID());
				
				int tonnage_uom_id = MSysConfig.getIntValue("TONNAGE_UOM", 1000069, Env.getAD_Client_ID(getCtx()));
				int uom_id = wEntry.getM_Product().getC_UOM_ID();
				ord.setItem1_UOM_ID(ord.getItem1().getC_UOM_ID());
				ord.setItem1_Tax_ID(1000000);
				BigDecimal qty = wEntry.getNetWeight();
				if(uom_id == tonnage_uom_id)
					qty = qty.divide(new BigDecimal(1000));
				ord.setItem1_TotalLoad(BigDecimal.ONE);
				ord.setItem1_PermitIssued(wEntry.getPermitIssuedQty()); 
				ord.setMDPNo(wEntry.getMDPNo());
				ord.setItem1_Qty(qty);
				BigDecimal price = wEntry.getPrice();
				ord.setItem1_Price(price);
				ord.setItem1_UnitPrice(price);
				ord.setItem1_Amt(ord.getItem1_Qty().multiply(ord.getItem1_Price()));
				
				ord.saveEx();				
				
				sp = trx.setSavepoint(wEntry.getDocumentNo());
				ord.setDocAction(DocAction.ACTION_Complete);
				ord.completeIt();
				ord.setDocStatus(TF_MOrder.DOCSTATUS_Completed);
				ord.saveEx();
				
				String error = DocumentEngine.postImmediate(Env.getCtx(), ord.getAD_Client_ID(), ord.get_Table_ID(), ord.get_ID(), true, ord.get_TrxName());				
				if (! Util.isEmpty(error)) {
						throw new AdempiereException(error);
				}
				trx.releaseSavepoint(sp);
				addLog(ord.get_Table_ID(), ord.getCreated(), null, ord.getDocumentNo() + " is created!", ord.get_Table_ID(), ord.get_ID());
			}
			catch (Exception ex) {
				if(sp != null)
					trx.rollback(sp);
				String desc = wEntry.getDescription();
				if(desc == null)
					desc = "";
				if(!desc.contains("ERROR:")) {
					wEntry.setDescription(desc + 
							" | ERROR: " + ex.getMessage());					
				}					
				wEntry.saveEx();
				addLog(wEntry.get_Table_ID(), wEntry.getUpdated(), null, ex.getMessage(), wEntry.get_Table_ID(), wEntry.get_ID());
			}
		}
		return null;
	}

}
