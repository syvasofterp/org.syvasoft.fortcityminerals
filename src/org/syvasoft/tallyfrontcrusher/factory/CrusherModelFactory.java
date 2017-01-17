package org.syvasoft.tallyfrontcrusher.factory;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.syvasoft.tallyfrontcrusher.model.MBoulderReceipt;
import org.syvasoft.tallyfrontcrusher.model.MQuarry;

public class CrusherModelFactory implements IModelFactory {

	@Override
	public Class<?> getClass(String tableName) {
		if (MBoulderReceipt.Table_Name.equals(tableName))
			return MBoulderReceipt.class;
		else if (MQuarry.Table_Name.equals(tableName))
			return MQuarry.class;
		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		Properties ctx = Env.getCtx();
		if (MBoulderReceipt.Table_Name.equals(tableName))
			return new MBoulderReceipt(ctx, Record_ID, trxName);
		else if (MQuarry.Table_Name.equals(tableName))
			return new MQuarry(ctx, Record_ID, trxName);
		return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		Properties ctx = Env.getCtx();
		if (MBoulderReceipt.Table_Name.equals(tableName))
			return new MBoulderReceipt(ctx, rs, trxName);
		else if (MQuarry.Table_Name.equals(tableName))
			return new MQuarry(ctx, rs, trxName);
		return null;
	}

}
