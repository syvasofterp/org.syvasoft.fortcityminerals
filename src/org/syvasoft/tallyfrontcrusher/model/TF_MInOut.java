package org.syvasoft.tallyfrontcrusher.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.MInOut;
import org.compiere.model.MOrder;


public class TF_MInOut extends MInOut {

	/**
	 * 
	 */
	private static final long serialVersionUID = -662134145363709054L;

	public TF_MInOut(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public TF_MInOut(Properties ctx, int S_Resource_ID, String trxName) {
		super(ctx, S_Resource_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	

	public TF_MInOut (MOrder order, int C_DocTypeShipment_ID, Timestamp movementDate)
	{
		super(order, C_DocTypeShipment_ID, movementDate);
	}
	
	/** Column name TF_WeighmentEntry_ID */
    public static final String COLUMNNAME_TF_WeighmentEntry_ID = "TF_WeighmentEntry_ID";
    
	/** Set TF_WeighmentEntry_ID.
		@param TF_WeighmentEntry_ID 
		Weighment Entry
	*/
	public void setTF_WeighmentEntry_ID (int TF_WeighmentEntry_ID)
	{
		if (TF_WeighmentEntry_ID < 1) 
			set_Value (COLUMNNAME_TF_WeighmentEntry_ID, null);
		else 
			set_Value (COLUMNNAME_TF_WeighmentEntry_ID, Integer.valueOf(TF_WeighmentEntry_ID));
	}

	/** Get TF_WeighmentEntry_ID.
		@return TF_WeighmentEntry_ID
	  */
	public int getTF_WeighmentEntry_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TF_WeighmentEntry_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	
	@Override
	public String completeIt() {
		if(getTF_WeighmentEntry_ID() > 0) {
			MWeighmentEntry we = new MWeighmentEntry(getCtx(), getTF_WeighmentEntry_ID(), get_TrxName());
			we.close();
			we.saveEx();
		}
		// TODO Auto-generated method stub
		return super.completeIt();
	}
	
	@Override
	public boolean reverseCorrectIt() {
		if(getTF_WeighmentEntry_ID() >0) {
			MWeighmentEntry we = new MWeighmentEntry(getCtx(), getTF_WeighmentEntry_ID(), get_TrxName());
			we.reverse();
			we.saveEx();
		}
		// TODO Auto-generated method stub
		return super.reverseCorrectIt();
	}
}
