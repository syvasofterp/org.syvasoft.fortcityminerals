
package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MPayment;
import org.compiere.model.MUser;
import org.compiere.process.DocAction;
import org.compiere.util.Env;

public class MInvestmentReceipt extends X_TF_InvestmentReceipt {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3277316969251826275L;

	public MInvestmentReceipt(Properties ctx, int TF_InvestmentReceipt_ID, String trxName) {
		super(ctx, TF_InvestmentReceipt_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MInvestmentReceipt(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public void processIt(String docAction) {
		if(DocAction.ACTION_Prepare.equals(docAction)) {
			setDocStatus(DOCSTATUS_InProgress);
		}
		else if(DocAction.ACTION_Complete.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
			
			//Cash Book Receipt
			TF_MPayment payment = new TF_MPayment(getCtx(), 0, get_TrxName());
			payment.setAD_Org_ID(getAD_Org_ID());			
			payment.setDateTrx(getDateAcct());
			payment.setDateAcct(getDateAcct());		
			payment.setC_BankAccount_ID(getC_BankAccount_ID());
			payment.setC_DocType_ID(1000008);
			payment.setIsReceipt(true);
			payment.setC_ElementValue_ID(getTF_Shareholder().getCapitalAcct_ID());
			TF_MCharge charge = TF_MCharge.createChargeFromAccount(getCtx(), payment.getC_ElementValue_ID(), null);
			if(charge != null )
				payment.setC_Charge_ID(charge.get_ID());			
			MUser user = MUser.get(getCtx(), Env.getAD_User_ID(getCtx()));				
			int bPartnerID = user.getC_BPartner_ID();			
			payment.setC_BPartner_ID(bPartnerID);		
			payment.setC_Currency_ID(Env.getContextAsInt(getCtx(), "$C_Currency_ID"));
			payment.setPayAmt(getPayAmt());
			payment.setTenderType(TF_MPayment.TENDERTYPE_Cash);
			payment.setDescription(getDescription());		
			payment.saveEx();
			
			if(!payment.processIt(DocAction.ACTION_Complete))
				throw new AdempiereException("Failed when processing document - " + payment.getProcessMsg());
			payment.saveEx();
			
			setC_PaymentReceipt_ID(payment.getC_Payment_ID());
			
			
			//Cash Book Payment
			payment = new TF_MPayment(getCtx(), 0, get_TrxName());
			payment.setAD_Org_ID(getAD_Org_ID());			
			payment.setDateTrx(getDateAcct());
			payment.setDateAcct(getDateAcct());		
			payment.setC_BankAccount_ID(getC_BankAccount_ID());
			payment.setC_DocType_ID(1000009);
			payment.setIsReceipt(false);
			payment.setC_ElementValue_ID(getC_ElementValue_ID());
			charge = TF_MCharge.createChargeFromAccount(getCtx(), payment.getC_ElementValue_ID(), null);
			if(charge != null )
				payment.setC_Charge_ID(charge.get_ID());			
			payment.setC_BPartner_ID(bPartnerID);		
			payment.setC_Currency_ID(Env.getContextAsInt(getCtx(), "$C_Currency_ID"));
			payment.setPayAmt(getPayAmt());
			payment.setTenderType(TF_MPayment.TENDERTYPE_Cash);
			payment.setDescription(getDescription());
			payment.saveEx();
			
			if(!payment.processIt(DocAction.ACTION_Complete))
				throw new AdempiereException("Failed when processing document - " + payment.getProcessMsg());
			payment.saveEx();
			
			setC_Payment_ID(payment.getC_Payment_ID());
			
			MInvestmentStructure.updateInvestmentPaid(getAD_Org_ID(), get_TrxName());
			
		}
	}
	
	public void reverseIt() {
		if(getC_Payment_ID() > 0) {
			TF_MPayment payment = new TF_MPayment(getCtx(), getC_Payment_ID(), get_TrxName());
			if(payment.getDocStatus().equals(DOCSTATUS_Completed)){
				 if(!payment.reverseCorrectIt())
					 throw new AdempiereException("Cash Book Payment cannot be reversed!");
				 payment.saveEx();
			}
			setC_Payment_ID(0);
		}
		
		if(getC_PaymentReceipt_ID() > 0) {
			TF_MPayment payment = new TF_MPayment(getCtx(), getC_PaymentReceipt_ID(), get_TrxName());
			if(payment.getDocStatus().equals(DOCSTATUS_Completed)){
				 if(!payment.reverseCorrectIt())
					 throw new AdempiereException("Cash Book Receipt cannot be reversed!");
				 payment.saveEx();				 
			}
			setC_PaymentReceipt_ID(0);
		}
		
		setProcessed(false);
		setDocStatus(DOCSTATUS_Drafted);
	}
}

