package org.syvasoft.tallyfrontcrusher.process;


import org.compiere.process.DocAction;
import org.compiere.process.SvrProcess;
import org.syvasoft.tallyfrontcrusher.model.MTripSheet;
import org.syvasoft.tallyfrontcrusher.model.MVehicleRentalContract;

public class ProcessTripSheet extends SvrProcess {

	@Override
	protected void prepare() {
		
	}

	@Override
	protected String doIt() throws Exception {
		MTripSheet tripSheet = new MTripSheet(getCtx(), getRecord_ID(), get_TrxName());
		tripSheet.processIt(DocAction.ACTION_Complete);
		tripSheet.saveEx();
		
		//Rental Contract Updates
		int rentalContractID = tripSheet.getTF_Vehicle_Rental_Contract_ID(); 
		if( rentalContractID > 0) {
			MVehicleRentalContract rentalContract = new MVehicleRentalContract(getCtx(), rentalContractID, get_TrxName());
			rentalContract.updateTripSheetBasedFields();
			rentalContract.saveEx();
		}
		return null;
	}

}
