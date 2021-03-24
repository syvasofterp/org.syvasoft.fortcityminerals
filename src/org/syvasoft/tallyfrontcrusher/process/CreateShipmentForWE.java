package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MOrder;
import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.TF_MInOut;
import org.syvasoft.tallyfrontcrusher.model.TF_MInOutLine;
import org.syvasoft.tallyfrontcrusher.model.MDestination;
import org.syvasoft.tallyfrontcrusher.model.MLumpSumRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MRentedVehicle;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;

public class CreateShipmentForWE extends SvrProcess {
		
	@Override
	protected void prepare() {		
		
	}

	@Override
	protected String doIt() throws Exception {
		String whereClause = " TF_WeighmentEntry.WeighmentEntryType = '1SO' AND TF_WeighmentEntry.Status IN ('CL') AND TF_WeighmentEntry.Processed='N' AND IsSecondary='N' ";
				
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
			inout.setMovementDate(we.getGrossWeightTime());
			inout.setC_DocType_ID(shipmentDocId);
			inout.setM_Warehouse_ID(we.getM_Warehouse_ID());
			inout.setDeliveryRule(TF_MInOut.DELIVERYRULE_Availability);
			inout.setDeliveryViaRule(TF_MInOut.DELIVERYVIARULE_Pickup);
			inout.setIsSOTrx(true); 
			
			inout.setTF_WeighmentEntry_ID(we.getTF_WeighmentEntry_ID());
			inout.setDescription(we.getDescription());
			inout.setMovementType(TF_MInOut.MOVEMENTTYPE_CustomerShipment);
			if(we.getC_OrderLine_ID() > 0)
				inout.setC_Order_ID(order.getC_Order_ID());
			inout.saveEx(get_TrxName());
			
			//Material Issue Line
			TF_MInOutLine ioLine = new TF_MInOutLine(inout);			
			//ioLine.setOrderLine(orderLine, wh.getDefaultLocator().get_ID(), we.getNetWeightUnit());
			ioLine.setM_Product_ID(we.getM_Product_ID());
			ioLine.setC_UOM_ID(we.getC_UOM_ID());			
			ioLine.setQty(we.getNetWeightUnit());
			ioLine.setM_Locator_ID(we.getNetWeightUnit());
			ioLine.setC_OrderLine_ID(we.getC_OrderLine_ID());
			ioLine.saveEx(get_TrxName());
			
			
			//Royalty Pass Issue Line
			//it is applicable even for Non GST
			if(we.getPassQtyIssued().doubleValue() != 0) {
				ioLine = new TF_MInOutLine(inout);
				ioLine.setM_Product_ID(we.getM_Product2_ID());
				ioLine.setC_UOM_ID(we.getC_UOM_ID());
				ioLine.setQty(we.getPassQtyIssued());
				ioLine.setM_Locator_ID(we.getPassQtyIssued());
				ioLine.saveEx(get_TrxName());
			}
			
			
			//Create Vehicle Rent Line for the Hired and Owned Vehicle
			if(we.getTF_RentedVehicle() != null) {
				MRentedVehicle rv = (MRentedVehicle) we.getTF_RentedVehicle();
				if(rv.isOwnVehicle() || (rv.isTransporter() && rv.getC_BPartner_ID() != we.getC_BPartner_ID() )) {
					int Vendor_ID = rv.getC_BPartner_ID();
					MDestination dest = new MDestination(getCtx(), we.getTF_Destination_ID(), get_TrxName());
					BigDecimal RateMT = MLumpSumRentConfig.getRateMT(getCtx(), we.getAD_Org_ID(), Vendor_ID, we.getC_BPartner_ID(), we.getM_Product_ID(), 
							we.getTF_Destination_ID(), we.getTF_VehicleType_ID(), dest.getDistance(), get_TrxName());
					BigDecimal RateKM = MLumpSumRentConfig.getRateKm(getCtx(), we.getAD_Org_ID(), Vendor_ID, we.getC_BPartner_ID(), we.getM_Product_ID(), 
							we.getTF_Destination_ID(), we.getTF_VehicleType_ID(), dest.getDistance(), get_TrxName());
					BigDecimal RateMTKM = MLumpSumRentConfig.getRateMTKm(getCtx(), we.getAD_Org_ID(), Vendor_ID, we.getC_BPartner_ID(), we.getM_Product_ID(), 
							we.getTF_Destination_ID(), we.getTF_VehicleType_ID(), dest.getDistance(), get_TrxName());
					
					
					BigDecimal distance = dest.getDistance();
					int Rent_UOM_ID = 0;
					BigDecimal qty = BigDecimal.ZERO;
					BigDecimal price = BigDecimal.ZERO;
					if(we.getRent_Amt().doubleValue() > 0) {
						Rent_UOM_ID = MSysConfig.getIntValue("LOAD_UOM", 1000072, we.getAD_Client_ID());
						qty = BigDecimal.ONE;
						price = we.getRent_Amt();
					}
					else if(RateMT.doubleValue() > 0) {						
						Rent_UOM_ID = MSysConfig.getIntValue("TONNAGE_UOM", 1000069, we.getAD_Client_ID());
						qty = we.getMT();
						price = RateMT;
					}
					else if(RateKM.doubleValue() > 0) {						
						Rent_UOM_ID = MSysConfig.getIntValue("KM_UOM", 1000071, we.getAD_Client_ID());
						qty = dest.getDistance();
						price = RateKM;
					}
					else if(RateMTKM.doubleValue() > 0) {
						//Currently the price is converted from RateMTKM to RateKM
						//since two measurement cannot be shown as quantity. 
						//ioLine.setTF_Destination_ID(we.getTF_Destination_ID());
						//ioLine.setDistance(distance);
						//ioLine.setRateMTKM(RateMTKM);
						//Creating consolidated Transporter Invoice should be reworked based on this condition.
						Rent_UOM_ID = MSysConfig.getIntValue("MT_KM_UOM", 1000081, we.getAD_Client_ID());
						qty = dest.getDistance();
						price = RateMTKM.multiply(we.getNetWeightUnit());
					}
					else {
						Rent_UOM_ID = MSysConfig.getIntValue("LOAD_UOM", 1000072, we.getAD_Client_ID());
						qty = BigDecimal.ONE;
						price = MLumpSumRentConfig.getLumpSumRent(getCtx(),we.getAD_Org_ID(),Vendor_ID, we.getC_BPartner_ID(), we.getM_Product_ID(), 
								we.getTF_Destination_ID(), we.getTF_VehicleType_ID(), dest.getDistance(), get_TrxName());
					}
					
					ioLine = new TF_MInOutLine(inout);
					ioLine.setM_Product_ID(rv.getM_Product_ID());
					ioLine.setC_UOM_ID(Rent_UOM_ID);
					ioLine.setTF_Destination_ID(we.getTF_Destination_ID());
					ioLine.setDistance(distance);
					ioLine.setRateMTKM(RateMTKM);
					ioLine.setQty(qty);
					ioLine.set_ValueOfColumn("Price", price);
					ioLine.setM_Locator_ID(qty);
					ioLine.setDescription("Destination : " + dest.getName());
					ioLine.saveEx(get_TrxName());
				}
			}
			
			//Material Issue DocAction
			if (!inout.processIt(DocAction.ACTION_Complete))
				throw new AdempiereException("Failed when processing document - " + order.getProcessMsg());
			
			inout.saveEx();
			//End DocAction
						
		 }
	}
}
