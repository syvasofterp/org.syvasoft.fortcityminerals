package org.syvasoft.tallyfrontcrusher.process;

import org.compiere.model.MStorageOnHand;
import org.compiere.model.MTransaction;
import org.compiere.model.MWarehouse;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogger;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;

public class ProcessBoulderReceipt extends SvrProcess {

	int m_recordID = 0;
	@Override
	protected void prepare() {
		m_recordID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		String m_processMsg = null;
		MBoulderReceipt br = new MBoulderReceipt(getCtx(), m_recordID, get_TrxName());
		
		MWarehouse warehouse = MWarehouse.get(getCtx(), br.getM_Warehouse_ID());
		int defaultLocatorID = warehouse.getDefaultLocator().getM_Locator_ID(); 
		//Update Storage
		if (!MStorageOnHand.add(getCtx(), br.getM_Warehouse_ID(),
				defaultLocatorID,
				br.getM_Product_ID(),
				0,
				br.getQtyReceived(),br.getDateAcct(),
				get_TrxName()))
			{
				String lastError = CLogger.retrieveErrorString("");
				m_processMsg = "Cannot correct Inventory OnHand (MA) [" + br.getM_Product().getValue() + "] - " + lastError;
				rollback();
				return m_processMsg;
			}
		MTransaction mtrx = new MTransaction (getCtx(), br.getAD_Org_ID(),
			"J+", defaultLocatorID,
			br.getM_Product_ID(), 0,
			br.getQtyReceived(), br.getDateAcct(), get_TrxName());
		mtrx.set_ValueOfColumn(MBoulderReceipt.COLUMNNAME_TF_Boulder_Receipt_ID, br.getTF_Boulder_Receipt_ID());
		if (!mtrx.save())
		{
			m_processMsg = "Could not create Material Transaction (MA) [" + br.getM_Product().getValue() + "]";
			rollback();
			return m_processMsg;
		}
	
		br.setDocStatus("CO");
		br.setProcessed(true);
		br.setM_Transaction_ID(mtrx.getM_Transaction_ID());
		br.saveEx();	
		
		return "Completed Successfully";
	}

}
