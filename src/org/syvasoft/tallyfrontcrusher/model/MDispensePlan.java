package org.syvasoft.tallyfrontcrusher.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.compiere.process.SvrProcess;

import org.adempiere.exceptions.DBException;
import org.compiere.model.Query;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;

public class MDispensePlan extends X_TF_DispensePlan {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8086375269293054622L;

	public MDispensePlan(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDispensePlan(Properties ctx, int TF_Destination_ID, String trxName) {
		super(ctx, TF_Destination_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if(newRecord) {
			
		}
		return super.afterSave(newRecord, success);
	}

	public void processIt(String docAction) {
		if(MDispensePlan.DOCSTATUS_Completed.equals(docAction)) {
			setDocStatus(DOCSTATUS_Completed);
			setProcessed(true);
		}			
	}
	public void reverseIt() {
		setProcessed(false);
		setDocStatus(DOCSTATUS_Drafted);
	}
}
