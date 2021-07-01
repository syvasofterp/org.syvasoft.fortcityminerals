package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MSysConfig;
import org.compiere.model.MUOM;
import org.compiere.model.MUOMConversion;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;


public class MWeighmentEntry extends X_TF_WeighmentEntry {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2613943323993702690L;
	
	private static final CLogger s_log = CLogger.getCLogger(MWeighmentEntry.class);
	
	public MWeighmentEntry(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	public MWeighmentEntry(Properties ctx, int TF_WeighmentEntry_ID, String trxName) {
		super(ctx, TF_WeighmentEntry_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	void CreateBP() {
		if(getPartyName()!=null && getC_BPartner_ID()==0) {
			
			//Check Customer with Phone No
			String whereClause="lower(replace(Phone,' ',''))=lower(replace(?,' ','')) and IsCustomer='Y'";
			TF_MBPartner bp = new Query(getCtx(), TF_MBPartner.Table_Name, whereClause, get_TrxName())
					.setClient_ID().setParameters(getPhone().replace(" ", "")).first();
			if(bp != null)
				setC_BPartner_ID(bp.getC_BPartner_ID());
			else{
				//Get Destination
				String CustomerDest="";
				if(getTF_Destination_ID()>0) {
					MDestination destc=new MDestination(getCtx(), getTF_Destination_ID(), get_TrxName());
					CustomerDest=destc.getName();
				}
				else {
					CustomerDest=getNewDestination();
				}
				TF_MBPartner bpnew=new TF_MBPartner(getCtx(), 0, get_TrxName());
				bpnew.setAD_Org_ID(getAD_Org_ID());
				bpnew.setValue(getPhone() != null ? getPhone() : getPartyName().toUpperCase());
				bpnew.setName(getPartyName());
				bpnew.setC_BP_Group_ID(1000001);
				bpnew.setPhone(getPhone());
				bpnew.setContactName(getPartyName());
				bpnew.setAddress1(CustomerDest); //set Destination
				bpnew.setCity(CustomerDest); // Set Destination
				bpnew.setIsCustomer(true);
				bpnew.setTF_CustomerType_ID(1000000);
				bpnew.setC_Country_ID(208);
				bpnew.saveEx();
				
				setC_BPartner_ID(bpnew.getC_BPartner_ID());
			}
		}
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		//CreateBP();
		
		if(!getStatus().equals(STATUS_Voided)) {
			CreateDestination();
			CreateCustomerVehicle();
			
			if(getTF_RentedVehicle_ID() > 0 && (getVehicleNo() == null || getVehicleNo().length() == 0))
					setVehicleNo(getTF_RentedVehicle().getVehicleNo());
			
			if(getC_BPartner_ID() == 0 && getPaymentRule().equals(PAYMENTRULE_Cash)) {
				TF_MBPartner bp = new Query(getCtx(), TF_MBPartner.Table_Name, "IsPOSCashBP='Y'", get_TrxName())
						.setClient_ID().first();
				if(bp != null) {
					setC_BPartner_ID(bp.getC_BPartner_ID());				
				}
				
			}
			
			if(getPaymentRule().equals(PAYMENTRULE_Cash))
				setPaymentRule(PAYMENTRULE_OnCredit);
			
			if(getTF_RentedVehicle_ID()>0) {
				String rvwhere="COALESCE(Tareweight,0)!=? AND IsTransporter='N' AND TF_RentedVehicle_ID=?";
				MRentedVehicle rv= new Query(getCtx(), MRentedVehicle.Table_Name, rvwhere, get_TrxName())
						.setClient_ID()
						.setParameters(getTareWeight(),getTF_RentedVehicle_ID())
						.first();
				
				if(rv!=null) {
					if(rv.getTareWeight()!=null) {
						rv.setOldTareweight(rv.getTareWeight());
					}
					rv.setTareWeight(getTareWeight());
					rv.saveEx();
				}
						
			}
			
			
			if(!newRecord && getWeighmentEntryType().equals(WEIGHMENTENTRYTYPE_Sales)
					&& MSysConfig.getBooleanValue("WEIGHMENT_REVIEW", false)) {
				if(is_Changed() && getStatus().equals(STATUS_Unbilled) && !is_ValueChanged(COLUMNNAME_Status)) {
					setStatus(STATUS_UnderReview);
				}
			}
			
			if(newRecord) {
				if(isSecondary()) {
					if(getTF_WeighmentEntryPrimary_ID() == 0 && getPrimaryDocumentNo() != null)
						setTF_WeighmentEntryPrimary_ID(getTF_WeighmentEntryPrimary_ID(getPrimaryDocumentNo()));
					//else if(getPrimaryDocumentNo() == null)
					//	throw new AdempiereException("Invalid Secondary Entry without Primary DC Reference");
					
					setInvoiceType(INVOICETYPE_TPWeight);
				}
				else {
					setPrimaryDocumentNo(getDocumentNo());
				}
			}
			
			if(isCreateTwoInvoices() && isSecondary()) {
				throw new AdempiereException("Secondary DC cannot be created as TP and Non TP Invoices!");
			}
		}
		
		if(getStatus().equals(STATUS_Voided)) {
			voidWeighmentEntry();
			setStatus(STATUS_Voided);
			setProcessed(true);
		}
		
		boolean ok = super.beforeSave(newRecord);
		return ok;
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		//self referencing to facilitate easy info search.		
		if(!isSecondary() && getTF_WeighmentEntryPrimary_ID() == 0) {
			String updateSql = "UPDATE TF_WeighmentEntry SET TF_WeighmentEntryPrimary_ID = " + getTF_WeighmentEntry_ID() +
			 " WHERE TF_WeighmentEntry_ID =  " + getTF_WeighmentEntry_ID();
			DB.executeUpdate(updateSql, get_TrxName());
		}
		
		if(getWeighmentEntryType().equals(WEIGHMENTENTRYTYPE_OwnProductionReceipt) || 
				getWeighmentEntryType().equals(WEIGHMENTENTRYTYPE_SubcontractProductionReceipt) ||
				getWeighmentEntryType().equals(WEIGHMENTENTRYTYPE_Input)) {
			CreateQuarry();
		}
		
		return super.afterSave(newRecord, success);
	}
	
	void CreateQuarry() {
		
		if(getMLNo() != null) {
			String where = "Value = '" + getMLNo() + "'";
			
			MQuarry quarry = new Query(getCtx(), MQuarry.Table_Name, where, get_TrxName()).first();
			
			if(quarry == null) {
				quarry = new MQuarry(getCtx(), 0, get_TrxName());
				quarry.setAD_Org_ID(getAD_Org_ID());
				quarry.setName(getMLNo());
				quarry.setValue(getMLNo());
				quarry.setActualQty(getNetWeightUnit());
				quarry.setDeliveredTPQty(getPermitIssuedQty());
				quarry.setBalanceQty(getNetWeightUnit().subtract(getPermitIssuedQty()));
				quarry.saveEx();
				setTF_Quarry_ID(quarry.getTF_Quarry_ID());
				saveEx();
			}
			else{
				String sql = "SELECT SUM(PermitIssuedQty) FROM TF_WeighmentEntry WHERE MLNo  = ? "
						+ " AND Status != 'VO' AND WeighmentEntryType = ?";
				BigDecimal totalTPWeight = DB.getSQLValueBD(get_TrxName(), sql, getMLNo(), getWeighmentEntryType());
				
				sql = "SELECT SUM(NetWeightUnit) FROM TF_WeighmentEntry WHERE MLNo  = ? "
						+ " AND Status != 'VO' AND IsSecondary = 'N' AND WeighmentEntryType = ?";
				BigDecimal netweightUnit = DB.getSQLValueBD(get_TrxName(), sql, getMLNo(), getWeighmentEntryType());
				
				if(!isSecondary()) {
					quarry.setActualQty(netweightUnit);
				}
				
				quarry.setDeliveredTPQty(totalTPWeight);
				quarry.setBalanceQty(netweightUnit.subtract(totalTPWeight));
				quarry.saveEx();
			}
		}
	}
	
	void CreateCustomerVehicle() {
		if(getTF_RentedVehicle_ID() == 0 && getPaymentRule().equals(PAYMENTRULE_OnCredit.toString())) {
			String whereClause="UPPER(replace(vehicleno,' ',''))='"+getVehicleNo().replace(" ","").toUpperCase()+"'";
			
			MRentedVehicle rentedVehicle = new Query(getCtx(), MRentedVehicle.Table_Name, whereClause, get_TrxName())
											.setClient_ID()
											.first();
			
			if(rentedVehicle != null) {
				setTF_RentedVehicle_ID(rentedVehicle.getTF_RentedVehicle_ID());
			}
			else {
				MRentedVehicle rentedVehiclenew = new MRentedVehicle(getCtx(), 0, get_TrxName());
				int C_UOM_ID = MSysConfig.getIntValue("Vehicle_UOM_ID", 100);
				int Product_Category_ID = MSysConfig.getIntValue("Vehicle_Product_Category_ID", 1000055);
				
				rentedVehiclenew.setAD_Org_ID(getAD_Org_ID());
				rentedVehiclenew.setVehicleNo(getVehicleNo().replace(" ","").toUpperCase());

				if(getTransporter_ID() > 0) {
					rentedVehiclenew.setC_BPartner_ID(getTransporter_ID());
					rentedVehiclenew.setIsTransporter(true);
				}
				else {
					rentedVehiclenew.setC_BPartner_ID(getC_BPartner_ID());
				}
				
				rentedVehiclenew.setTareWeight(getTareWeight());
				rentedVehiclenew.setVehicleSOPOType(MRentedVehicle.VEHICLESOPOTYPE_Sales);
				rentedVehiclenew.setTF_VehicleType_ID(getTF_VehicleType_ID());
				rentedVehiclenew.setM_Product_Category_ID(Product_Category_ID);
				rentedVehiclenew.setC_UOM_ID(C_UOM_ID);
				rentedVehiclenew.setIsActive(true);
				rentedVehiclenew.saveEx();
				
				setTF_RentedVehicle_ID(rentedVehiclenew.get_ID());
			}
		}
	}
	
	void CreateDestination() {
		if(getNewDestination()!=null && getTF_Destination_ID()==0) {
			String whereClause="UPPER(replace(Name,' ',''))='"+getNewDestination().replace(" ","").toUpperCase()+"'";
			MDestination dest=new Query(getCtx(),MDestination.Table_Name, whereClause,get_TrxName())
					.setClient_ID()
					.first();
			if(dest!=null) {
				setTF_Destination_ID(dest.getTF_Destination_ID());
			}
			else {
				MDestination destnew=new MDestination(getCtx(), 0, get_TrxName());
				destnew.setAD_Org_ID(getAD_Org_ID());
				destnew.setName(getNewDestination());
				destnew.setDistance(BigDecimal.ZERO);
				destnew.setRate(BigDecimal.ZERO);
				destnew.setIsActive(true);
				destnew.saveEx();
				
				setTF_Destination_ID(destnew.get_ID());
			}
		}
	}
	
	public void close() {		
			if(getStatus().equals(STATUS_Billed) && !isCreateTwoInvoices())
				throw new AdempiereException("Weighment Entry is already processed!");
			setStatus(STATUS_Billed);
			//setProcessed(true);
	}
	public void reverse() {
		setStatus(STATUS_Unbilled);		
		//setProcessed(false);
		//Only Shipment document will set processed as True
		//or false while reversing shipment document.
	}
	
	public BigDecimal getCFTMultiplyRate() {
		if(getNetWeightUnit() == null)
			return null;
		if(getNetWeight().doubleValue() == getNetWeightUnit().doubleValue())
			return BigDecimal.ONE;
		return getNetWeightUnit().divide(getNetWeight()
					.divide(new BigDecimal(1000), 2, RoundingMode.HALF_EVEN)
					, 2, RoundingMode.HALF_EVEN);
	}
	
	public boolean isGST() {
		//BigDecimal royaltyPassQty = getPassQtyIssued();
		//if(royaltyPassQty == null)
		//	royaltyPassQty = BigDecimal.ZERO;
		
		return getGSTAmount().doubleValue() > 0;
	}
	
	/***
	 * Returns Sales Quick Entry Document Type ID
	 * @return
	 */
	public int getC_DocType_ID() {
		if(isGST())
			return TF_MOrder.GSTOrderDocType_ID(getCtx());
		else
			return TF_MOrder.NonGSTOrderDocType_ID(getCtx());
	}
	
	/***
	 * Returns Shipment (Customer) Document Type ID
	 * @return
	 */
	public int getC_DocTypeShipment_ID() {
		MDocType dt = new MDocType(getCtx(), getC_DocType_ID(), get_TrxName());
		return dt.getC_DocTypeShipment_ID();                                                                           
	}
	
	public int getC_DocTypeInvoice_ID() {
		MDocType dt = new MDocType(getCtx(), getC_DocType_ID(), get_TrxName());
		return dt.getC_DocTypeInvoice_ID();
	}
	
	public int getRoyaltyPassProduct_ID() {
		return MSysConfig.getIntValue("ROYALTY_PASS_PRODUCT_ID", 0, getAD_Client_ID(), getAD_Org_ID());
	}
	
	public int getInvoiceSeq_Id() {
		return MSysConfig.getIntValue("INVOICE_SEQ_ID", 0, getAD_Client_ID(), getAD_Org_ID());
	}
	
	public int getC_Tax_ID() {
		TF_MProduct p = new TF_MProduct(getCtx(), getM_Product_ID(), get_TrxName());
		return p.getTax_ID(isGST());
	}
	
	public int getM_InOut_ID() {
		String whereClause = "TF_WeighmentEntry_ID = ? AND DocStatus IN ('CO','CL')";		
		TF_MInOut inout = new Query(getCtx(), TF_MInOut.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getTF_WeighmentEntry_ID())
				.first();
		if(inout != null)
			return inout.get_ID();
		else
			return 0;
	}
	
	public int getM_InOutLine_ID() {
		String whereClause = "TF_WeighmentEntry_ID = ? AND DocStatus IN ('CO','CL')";		
		TF_MInOut inout = new Query(getCtx(), TF_MInOut.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getTF_WeighmentEntry_ID())
				.first();
		
			
		if(inout != null) {
			for(MInOutLine line : inout.getLines()) {
				if(line.getM_Product_ID() == getM_Product_ID()) {
					return line.get_ID();
				}
			}
		}
		
		 return 0;
	}
	
	
	public int getM_InOutLine_ID(int M_Product_ID) {
		String whereClause = "TF_WeighmentEntry_ID = ? AND DocStatus IN ('CO','CL')";		
		TF_MInOut inout = new Query(getCtx(), TF_MInOut.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getTF_WeighmentEntry_ID())
				.first();
		
			
		if(inout != null) {
			for(MInOutLine line : inout.getLines()) {
				if(line.getM_Product_ID() == M_Product_ID) {
					return line.get_ID();
				}
			}
		}
		
		 return 0;
	}
	
	public int getTF_WeighmentEntryPrimary_ID(String documentNo) {
		String whereClause = "DocumentNo = ? ";
		MWeighmentEntry wEntry = new Query(getCtx(), Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(documentNo)
				.first();
		if(wEntry != null)
			return wEntry.get_ID();
		else
			return 0;
	}
	
	public void shipped() {
		setProcessed(true);
	}
	
	public void reverseShipped() {
		setProcessed(false);
	}
	
	public BigDecimal getMT() {
		return getNetWeight().divide(new BigDecimal(1000), RoundingMode.HALF_EVEN);
	}
	
	public int getMT_UOM_ID() {
		return MSysConfig.getIntValue("TONNAGE_UOM", 1000069, getAD_Client_ID());
	}
	
	public int getMTKM_UOM_ID() {
		return MSysConfig.getIntValue("MT_KM_UOM", 1000081, getAD_Client_ID());
	}
			
	public BigDecimal qtyUOMConvert(int C_UOM_From_ID, int C_UOM_To_ID, int M_Product_ID, BigDecimal qty) {
		String whereClause = "IsActive='Y' AND C_UOM_ID=? AND C_UOM_TO_ID=? AND M_Product_ID = ?";
		
		MUOMConversion uomconv = new Query(getCtx(),  MUOMConversion.Table_Name, whereClause, get_TrxName())
									 .setParameters(C_UOM_From_ID, C_UOM_To_ID, M_Product_ID).first();
		
		BigDecimal retValue = null;
		int precision = 2;
		
		if(uomconv != null) {
			retValue = uomconv.getMultiplyRate();
			
			MUOM uomTo = new MUOM(getCtx(), C_UOM_To_ID, get_TrxName());
			
			precision = uomTo.getStdPrecision();
			
			if (retValue == null)
			{
				if (s_log.isLoggable(Level.INFO)) s_log.info ("NOT found - FromUOM=" + C_UOM_From_ID + ", ToUOM=" + C_UOM_To_ID);
				return null;
			}
			
			qty = retValue.multiply(qty);
			if (retValue.scale() > precision)
				qty = retValue.setScale(precision, BigDecimal.ROUND_HALF_UP);			
		}
		
		return qty;
	}
	public BigDecimal getMovementQty() {
		int MT_UOM_ID = MSysConfig.getIntValue("TONNAGE_UOM", 1000069, getAD_Client_ID());
		TF_MProduct prod = new TF_MProduct(getCtx(), getM_Product_ID(), get_TrxName());
		BigDecimal divideRate = prod.getDivideRate(MT_UOM_ID, getC_UOM_ID());
		BigDecimal MT = getNetWeight().divide(new BigDecimal(1000), 2, RoundingMode.HALF_EVEN);		
		
		if(getC_UOM_ID() == 0)
			return MT;
		
		BigDecimal qtyMovement = MT.multiply(divideRate).setScale(2, RoundingMode.HALF_EVEN);
		return qtyMovement;
	}
	
	public BigDecimal getUOMQtyConverted(BigDecimal qty) {
		int tonnage_uom_id = MSysConfig.getIntValue("TONNAGE_UOM", 1000069, Env.getAD_Client_ID(getCtx()));
		
		qty = qtyUOMConvert(tonnage_uom_id, getC_UOM_ID(), getM_Product_ID(), qty);
		
		if(getC_UOM_ID() == MSysConfig.getIntValue("CFT_UOM", 1000076, getAD_Client_ID()))
			qty = qty.setScale(0, BigDecimal.ROUND_UP);
		
		return qty;
	}
	
	public BigDecimal getBilledQty() {
		int tonnage_uom_id = MSysConfig.getIntValue("TONNAGE_UOM", 1000069, Env.getAD_Client_ID(getCtx()));
		BigDecimal qty = getNetWeight();
		if(getC_UOM_ID() == tonnage_uom_id)
			qty = qty.divide(new BigDecimal(1000));
		else
			qty = getNetWeightUnit();
		
		if((!isSecondary() && getInvoiceType().equals(INVOICETYPE_TPWeight)) || isSecondary())
			qty = getUOMQtyConverted(getPermitIssuedQty());		
				
		
		
		return qty;
	}
	
	public BigDecimal getTPBilledQty() {
		return getUOMQtyConverted(getPermitIssuedQty());
	}
	
	public BigDecimal getTotalTPWeight() {		
		String sql = "SELECT SUM(PermitIssuedQty) FROM TF_WeighmentEntry WHERE ? IN (TF_WeighmentEntry_ID, TF_WeighmentEntryPrimary_ID) "
				+ " AND Status != 'VO'";
		BigDecimal totalTPWeight = DB.getSQLValueBD(get_TrxName(), sql, getTF_WeighmentEntry_ID());
		if(totalTPWeight == null)
			totalTPWeight = BigDecimal.ZERO;
		totalTPWeight = getUOMQtyConverted(totalTPWeight);
		return totalTPWeight;
	}
	
	public void validateInvoiceType() {
		if(!getWeighmentEntryType().equals(WEIGHMENTENTRYTYPE_Sales)) 
			return;
		
		BigDecimal totalTPWeight;
		BigDecimal totalActualWeight;
		int count = 0;
		
		if(getC_OrderLine_ID() == 0 && getInvoiceType().equalsIgnoreCase(INVOICETYPE_ActualWeight)) {
			String secondaryDCs = "SELECT COUNT(*) FROM TF_WeighmentEntry WHERE TF_WeighmentEntryPrimary_ID = ? AND IsSecondary = 'Y' AND STATUS IN ('PV','CO','CL')";
			count = DB.getSQLValue(get_TrxName(), secondaryDCs, getTF_WeighmentEntry_ID());
		}
		else if(getC_OrderLine_ID() > 0 && getInvoiceType().equalsIgnoreCase(INVOICETYPE_ActualWeight)){
			String secondaryDCs = "SELECT COUNT(*) FROM TF_WeighmentEntry WHERE C_OrderLine_ID = ? AND IsSecondary = 'Y' AND STATUS IN ('PV','CO','CL')";
			count = DB.getSQLValue(get_TrxName(), secondaryDCs, getC_OrderLine_ID());
		}
		
		if(count > 0)
			throw new AdempiereException("Please create Invoice based on the TP Weight since it has secondary DC");
		
		//Validate Total TP Weight against Primary Weighment Entry without Order
		if(getTF_WeighmentEntryPrimary_ID() > 0 && getC_OrderLine_ID() == 0) {
			int wEntry_ID = getTF_WeighmentEntryPrimary_ID() > 0 ? getTF_WeighmentEntryPrimary_ID() : getTF_WeighmentEntry_ID();
			MWeighmentEntry primary = (MWeighmentEntry) (getTF_WeighmentEntryPrimary() != null ? getTF_WeighmentEntryPrimary() : this);
			
			if(primary.getInvoiceType().equals(INVOICETYPE_ActualWeight) && getInvoiceType().equalsIgnoreCase(INVOICETYPE_TPWeight))
				throw new AdempiereException("Please change Primary DC's Invoice type to TP Weight");
			
			totalActualWeight = primary.getNetWeightUnit();
			
			String sql = "SELECT SUM(PermitIssuedQty) FROM TF_WeighmentEntry WHERE ? IN (TF_WeighmentEntryPrimary_ID, TF_WeighmentEntry_ID) "
					+ " AND STATUS IN ('PV','CO','CL')";
			totalTPWeight = DB.getSQLValueBD(get_TrxName(), sql, wEntry_ID);			
		}		
		//Validate Total TP Weight against Order Line
		//if(getC_OrderLine_ID() > 0)
		else  {
			String sqlTPWeight = "SELECT SUM(PermitIssuedQty) FROM TF_WeighmentEntry WHERE C_OrderLine_ID = ? AND STATUS IN ('PV','CO','CL')";
			totalTPWeight = DB.getSQLValueBD(get_TrxName(), sqlTPWeight, getC_OrderLine_ID());
			
			String sqlActualWeight = "SELECT SUM(NetWeightUnit) FROM TF_WeighmentEntry WHERE C_OrderLine_ID = ? AND STATUS IN ('PV','CO','CL') AND "
					+ " IsSecondary='N'";
			totalActualWeight = DB.getSQLValueBD(get_TrxName(), sqlActualWeight, getC_OrderLine_ID());			
		}
				
		if(totalTPWeight.doubleValue() <= totalActualWeight.doubleValue())
			return ;
		//else
			//throw new AdempiereException("Total TP Weight : "+ totalActualWeight.doubleValue() + " MT is exceeded than Actual Weight : " + totalActualWeight.doubleValue() + " MT");
		
	}
	
	@Override
	public int getC_Order_ID() {
		if(getC_OrderLine_ID() > 0)
			return getC_OrderLine().getC_Order_ID();
		
		return super.getC_Order_ID();
	}
	
	public BigDecimal getMaterialPriceIncludedRent() {
		if(getRent_Amt() == null || getRent_Amt().doubleValue() == 0)
			return getPrice();
		
		BigDecimal unitRent = getRent_Amt().divide(getNetWeightUnit(), 2,RoundingMode.HALF_EVEN);
		
		return getPrice().add(unitRent);
	}
	
	public BigDecimal getMaterialPriceIncludedRoyaltyPass(BigDecimal price) {
		if(getPermitPassAmount() == null || getPermitPassAmount().doubleValue() == 0 || getPassQtyIssued() == null || getPassQtyIssued().doubleValue() == 0)
			return getPrice();
		
		BigDecimal unitPassAmt = getPermitPassAmount().divide(getPassQtyIssued(), 2,RoundingMode.HALF_EVEN);
		
		return price.add(unitPassAmt);
	}
	
	public void voidWeighmentEntry() {
		String oWhereClause = "TF_WeighmentEntry_ID = ? AND C_BPartner_ID = ? AND IsSOTrx = 'Y' AND DocStatus IN ('CO','CL')";
		
		try {
			String msg = null;
			
			
			//Shipment
			MInOut io = new Query(getCtx(), TF_MInOut.Table_Name, oWhereClause, get_TrxName())
					.setClient_ID()
					.setParameters(getTF_WeighmentEntry_ID(), getC_BPartner_ID())
					.first();
			if(io != null) {
				io.setDocAction(DocAction.ACTION_Reverse_Correct);
				io.voidIt();
				io.setDocStatus(TF_MOrder.DOCSTATUS_Reversed);
				io.saveEx();
			}
			
			//Order
			List<TF_MOrder> orders = new Query(getCtx(), TF_MOrder.Table_Name, oWhereClause, get_TrxName())
					.setClient_ID()
					.setParameters(getTF_WeighmentEntry_ID(), getC_BPartner_ID())
					.list();
			for(TF_MOrder sale : orders) {				
				sale.setDocAction(DocAction.ACTION_Void);
				sale.voidIt();
				sale.setDocStatus(TF_MOrder.DOCSTATUS_Voided);
				sale.saveEx();
			}
			//Invoice
			List<TF_MInvoice> invList = new Query(getCtx(), TF_MInvoice.Table_Name, oWhereClause, get_TrxName())
					.setClient_ID()
					.setParameters(getTF_WeighmentEntry_ID(), getC_BPartner_ID())
					.list();
			for(TF_MInvoice inv : invList) {
				
				//Keep the existing invoice no while reversing
				if(!MSysConfig.getBooleanValue(MSysConfig.Invoice_ReverseUseNewNumber, true, getAD_Client_ID()) && invList.size() == 1) {						
					
					String sql = "SELECT COUNT(*) FROM C_Invoice WHERE TF_WeighmentEntry_ID = ?";
					int revCount = DB.getSQLValue(get_TrxName(), sql, getTF_WeighmentEntry_ID());
					revCount = revCount / 2 + 1;
					inv.setDocumentNo(inv.getDocumentNo() + "-"+  revCount);
					inv.saveEx();
				}
				
				inv.setDocAction(DocAction.ACTION_Reverse_Correct);
				inv.voidIt();
				inv.setDocStatus(TF_MOrder.DOCSTATUS_Reversed);
				inv.saveEx();
			}
			
		}
		catch (Exception ex) {
			throw new AdempiereException(ex.getMessage());
		}	
	}				
}
