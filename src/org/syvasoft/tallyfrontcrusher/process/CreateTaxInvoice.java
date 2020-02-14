package org.syvasoft.tallyfrontcrusher.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MOrg;
import org.compiere.model.MPriceList;
import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MDestination;
import org.syvasoft.tallyfrontcrusher.model.MGenerateTaxInvoice;
import org.syvasoft.tallyfrontcrusher.model.MLumpSumRentConfig;
import org.syvasoft.tallyfrontcrusher.model.MPriceListUOM;
import org.syvasoft.tallyfrontcrusher.model.MRentedVehicle;
import org.syvasoft.tallyfrontcrusher.model.MTRTaxInvoice;
import org.syvasoft.tallyfrontcrusher.model.MTRTaxInvoiceLine;
import org.syvasoft.tallyfrontcrusher.model.MWeighmentEntry;
import org.syvasoft.tallyfrontcrusher.model.TF_MBPartner;
import org.syvasoft.tallyfrontcrusher.model.TF_MBankAccount;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrder;
import org.syvasoft.tallyfrontcrusher.model.TF_MOrderLine;
import org.syvasoft.tallyfrontcrusher.model.TF_MProduct;
import org.syvasoft.tallyfrontcrusher.model.MGenerateTaxInvoice;

public class CreateTaxInvoice extends SvrProcess {
	private String docAction="CO";
	MGenerateTaxInvoice genTaxInvoice = null;
	protected void prepare() {
		genTaxInvoice = new MGenerateTaxInvoice(getCtx(), getRecord_ID(), get_TrxName());
		
		ProcessInfoParameter[] para = getParameter();		
		for (int i = 0; i < para.length; i++)
		{						
			String name = para[i].getParameterName();
			if(name.equals("DocAction"))
				docAction =  para[i].getParameterAsString();
		}
	}

	@Override
	protected String doIt() throws Exception {
		if(!genTaxInvoice.isProcessed())
		{				
			genTaxInvoice.processIt(DocAction.ACTION_Complete);	
			genTaxInvoice.saveEx();
			
			String whereClause = MGenerateTaxInvoice.COLUMNNAME_TF_Generate_Taxinvoice_ID + " = ? AND DocStatus = 'CO'"; 
			MTRTaxInvoice taxInvoice = new Query(getCtx(), MTRTaxInvoice.Table_Name, whereClause, get_TrxName())
					.setClient_ID()
					.setParameters(genTaxInvoice.getTF_Generate_Taxinvoice_ID())
					.first();
			if(taxInvoice != null) {
				//addLog(taxInvoice.get_Table_ID(), taxInvoice.getCreated(), null, taxInvoice.getDocumentNo() + " is created!", taxInvoice.get_Table_ID(), taxInvoice.get_ID());
				addLog(1, null, null, "Sales Tax Invoice: " + taxInvoice.getDocumentNo(),
						MTRTaxInvoice.Table_ID, taxInvoice.getTF_TRTaxInvoice_ID());
			}
			
		}
		else if(genTaxInvoice.isProcessed() && genTaxInvoice.getDocStatus().equals(MGenerateTaxInvoice.DOCSTATUS_Completed)) {
			genTaxInvoice.reverseIt();
			genTaxInvoice.saveEx();
		}
			
		return null;
	}


}
