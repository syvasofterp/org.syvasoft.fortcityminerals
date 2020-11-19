package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.sql.Savepoint;
import java.sql.Timestamp;
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
import org.syvasoft.tallyfrontcrusher.model.MDestination;
import org.syvasoft.tallyfrontcrusher.model.MLumpSumRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.MRentedVehicle;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;

public class CreatePurchaseEntryFromWeighment extends SvrProcess {

	private int C_DocType_ID = 1000050;
	
	@Override
	protected void prepare() {		

	}
	
	@Override
	protected String doIt() throws Exception {
		//String whereClause = " AD_Org_ID = ? AND TRUNC(GrossWeightTime) >= ? AND TRUNC(GrossWeightTime) <= ? AND "
		String whereClause = " WeighmentEntryType = '2PO' AND Status = 'CO' AND (SELECT OrgType FROM AD_Org WHERE "				
				+ "AD_Org.AD_Org_ID = TF_WeighmentEntry.AD_Org_ID) = 'C'"
				+ " AND (SELECT M_Product_Category_ID FROM M_Product WHERE M_Product.M_Product_ID=TF_WeighmentEntry.M_Product_ID)=1000050"
				+ " AND NOT EXISTS(SELECT C_Order.TF_WeighmentEntry_ID FROM C_Order WHERE "
				+ "C_Order.TF_WeighmentEntry_ID =  TF_WeighmentEntry.TF_WeighmentEntry_ID)";
		int i = 0;
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
				ord.setPaymentRule(wEntry.getPaymentRule());		
				//Price List
				int m_M_PriceList_ID = Env.getContextAsInt(getCtx(), "#M_PriceList_ID");
				if(bp.getM_PriceList_ID() > 0)
					m_M_PriceList_ID = bp.getM_PriceList_ID();			
				ord.setM_PriceList_ID(m_M_PriceList_ID);
				ord.setC_Currency_ID(MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName()).getC_Currency_ID());
				ord.setIsSOTrx(false);
				ord.setTF_WeighmentEntry_ID(wEntry.getTF_WeighmentEntry_ID());	
				ord.setTF_Destination_ID(wEntry.getTF_Destination_ID());
				ord.setVehicleNo(wEntry.getVehicleNo());
				ord.setTF_RentedVehicle_ID(wEntry.getTF_RentedVehicle_ID());
				ord.setItem1_BucketQty(null);
				ord.setTF_Send_To(wEntry.getTF_Send_To());
				ord.setTF_ProductionPlant_ID(wEntry.getTF_ProductionPlant_ID());
				ord.setTF_BlueMetal_Type(wEntry.getTF_BlueMetal_Type());
				
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
				
				//Get price from Purchase Price List by UOM
				BigDecimal price =BigDecimal.ZERO;
				
				MPriceListUOM pprice = MPriceListUOM.getPriceListUOM(getCtx(), wEntry.getM_Product_ID(),
						ord.getItem1_UOM_ID(), bp.getC_BPartner_ID(), false, wEntry.getGrossWeightTime());
				if( pprice == null)
					throw new AdempiereException("Please configure the Purchase Price!");
				
				price = pprice.getPrice();
				ord.setItem1_Price(price);
				ord.setItem1_UnitPrice(price);
				ord.setItem1_Amt(ord.getItem1_Qty().multiply(ord.getItem1_Price()));
				ord.setCreateTransporterInvoice(!pprice.isRentInclusive());
				
				//Setting Transporter Charge
				if(!pprice.isRentInclusive() && ord.getTF_RentedVehicle_ID() > 0) {
					MDestination dest = new MDestination(getCtx(), ord.getTF_Destination_ID(), get_TrxName());
					BigDecimal RateKm=MLumpSumRentConfig.getRateKm(getCtx(), ord.getAD_Org_ID(), ord.getTF_Destination_ID(), 
							ord.getItem1_VehicleType_ID(), dest.getDistance(), null);
					ord.setRate(RateKm);
					MRentedVehicle rv = new MRentedVehicle(getCtx(), ord.getTF_RentedVehicle_ID(), get_TrxName());
					int Vendor_ID = rv.getC_BPartner_ID();		
					if(RateKm.equals(BigDecimal.ZERO)) {
						ord.setIsLumpSumRent(true);
					}
					else {
						ord.setIsLumpSumRent(false);
					}
					BigDecimal RentAmt=MLumpSumRentConfig.getLumpSumRent(getCtx(),ord.getAD_Org_ID(),Vendor_ID, ord.getC_BPartner_ID(), 
							ord.getItem1_ID(), ord.getTF_Destination_ID(), ord.getItem1_VehicleType_ID(), dest.getDistance(), get_TrxName());
					ord.setRent_Amt(RentAmt);
					ord.setRentMargin(BigDecimal.ZERO);
					ord.setRentPayable(RentAmt);
					
				}
				
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
				addLog(wEntry.get_Table_ID(), wEntry.getGrossWeightTime(), null, ex.getMessage(), wEntry.get_Table_ID(), wEntry.get_ID());
			}
			i++;
		}
		return i + " Weighment Entries are processed!";

	}
}