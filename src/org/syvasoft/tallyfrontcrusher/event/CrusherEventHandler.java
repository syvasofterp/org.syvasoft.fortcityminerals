package org.syvasoft.tallyfrontcrusher.event;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventTopics;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MClient;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.model.MProduct;
import org.compiere.model.MProduction;
import org.compiere.model.MSysConfig;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.RollUpCosts;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.osgi.service.event.Event;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MGLPostingConfig;


public class CrusherEventHandler extends AbstractEventHandler {

	CLogger log = CLogger.getCLogger(CrusherEventHandler.class);
	@Override
	protected void initialize() {
		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, MInvoice.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_PREPARE, MProduction.Table_Name);

	}

	@Override
	protected void doHandleEvent(Event event) {
		PO po = getPO(event);
		if(po instanceof MInvoice) {
			MInvoice inv = (MInvoice) po;
			MInvoiceLine[] lines = inv.getLines();
			//Post Jobwork Expense Variance Journal for Subcontractor Invoice
			MGLPostingConfig glConfig = MGLPostingConfig.getMGLPostingConfig(inv.getCtx());
			for(MInvoiceLine line : lines) {
				if(line.getM_Product_ID() == glConfig.getJobWork_Product_ID()) {
					MBoulderReceipt.postJobworkExpenseVarianceJournal(inv.getCtx(), inv, line.getPriceEntered(), inv.get_TrxName());
					break;
				}
			}
		}
		else if (po instanceof MProduction) {
			MProduction prod = (MProduction) po;
			//Before Prepare		
			//1. Create Costing Record for the End Product if there is no cost.
			//2. Update Cost using Rollup BOM Cost Process.
			int product_ID 			= 	prod.getM_Product_ID();
			MAcctSchema as 			= 	MClient.get(prod.getCtx()).getAcctSchema();
			MProduct product		=	MProduct.get(prod.getCtx(), product_ID);
			String costingMethod 	= 	product.getCostingMethod(as) ;
			int costElement_ID 		= 	MCostElement.getMaterialCostElement(prod.getCtx(), costingMethod).get_ID();
			
			String localTrxName		=	Trx.createTrxName();
			Trx trx					=	Trx.get(localTrxName, true);
			try {
				MCost cost				=	MCost.get(product, 0, as, 0, costElement_ID, localTrxName);
				boolean alwaysUpdateStdCost = MSysConfig.getBooleanValue("MFG_UpdateCostsOnCreate", false, prod.getAD_Client_ID());
				if(cost.getCurrentCostPrice().doubleValue() != 0 && !alwaysUpdateStdCost) {
					return;
				}
				cost.saveEx();
				trx.commit();
							
					
				// Call Rollup BOM Cost process
				
				// Create instance parameters. I e the parameters you want to send to the process.
				ProcessInfoParameter pi1 = new ProcessInfoParameter("M_Product_Category_ID", 0,"","","");
				ProcessInfoParameter pi2 = new ProcessInfoParameter("M_Product_ID", product_ID, "","","");
				ProcessInfoParameter pi3 = new ProcessInfoParameter("M_CostElement_ID", costElement_ID, "","","");
	
				// Lookup process in the AD, in this case by value
				MProcess pr = new Query(Env.getCtx(), MProcess.Table_Name, "value=?", null)
				                        .setParameters("M_Product_BOM_Rollup")
				                        .first();
				if (pr==null) {
				      log.warning("Process [RollUp BOM Cost] does not exist. ");
				      return;
				}
	
				// Create a process info instance. This is a composite class containing the parameters.
				ProcessInfo pi = new ProcessInfo("", pr.get_ID(),0,0);
				pi.setParameter(new ProcessInfoParameter[] {pi1, pi2, pi3});
				
				// Create an instance of the actual process class.
				RollUpCosts process = new RollUpCosts();				
				
				// Create process instance (mainly for logging/sync purpose)
				MPInstance mpi = new MPInstance(Env.getCtx(), 0, null);				
				mpi.setAD_Process_ID(pr.get_ID());				
				mpi.setRecord_ID(0);
				mpi.save();
				mpi.createParameter(10, pi1.getParameterName(), pi1.getParameterAsInt());
				mpi.createParameter(20, pi2.getParameterName(), pi2.getParameterAsInt());
				mpi.createParameter(30, pi3.getParameterName(), pi3.getParameterAsInt());
				mpi.save();
	
				// Connect the process to the process instance.
				pi.setAD_PInstance_ID(mpi.get_ID());
	
				log.info("Starting process " + pr.getName());
				boolean result = process.startProcess(prod.getCtx(), pi, trx);
				trx.commit();
			}
			catch(Exception ex) {
				trx.rollback();
				throw ex;
			}
			finally {
				trx.close();
			}
			
			// End Call Rollup BOM Cost process
			
		}
	}

}
