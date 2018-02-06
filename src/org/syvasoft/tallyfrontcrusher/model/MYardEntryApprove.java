package org.syvasoft.tallyfrontcrusher.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;

public class MYardEntryApprove extends X_TF_YardEntryApprove {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2995552663424149296L;
	public MYardEntryApprove(Properties ctx, int TF_YardEntryApprove_ID, String trxName) {
		super(ctx, TF_YardEntryApprove_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MYardEntryApprove(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	
	public void createYardEntryLines(boolean reCreate) {		
		if(!reCreate && get_ValueAsBoolean(COLUMNNAME_IsCreated))
			throw new AdempiereException("Yard Entries are already created!");
		
		if(reCreate && get_ValueAsBoolean(COLUMNNAME_IsCreated)) {
			String sqlDelete = "DELETE FROM " + MYardEntryApproveLine.Table_Name + " WHERE TF_YardEntryApprove_ID = " +
					getTF_YardEntryApprove_ID();
			DB.executeUpdate(sqlDelete, get_TrxName());
		}
				
		String sqlSelectLoadEntries = " UPDATE " + MYardLoadEntry.Table_Name + " SET TF_YardEntryApprove_ID " +
				" = ? WHERE TF_YardEntryApprove_ID IS NULL AND DateAcct = ?" ;
		Object[] params = new Object[2];
		params[0] = getTF_YardEntryApprove_ID();
		params[1] = getDateAcct();
		DB.executeUpdateEx(sqlSelectLoadEntries, params, get_TrxName());
		
		String sqlSelectPermitEntries = "UPDATE " + MYardPermitIssueEntry.Table_Name + " SET TF_YardEntryApprove_ID = ? " +
				" WHERE TF_YardEntryApprove_ID IS NULL AND DateAcct = ? ";
		DB.executeUpdateEx(sqlSelectPermitEntries, params, get_TrxName());
		
		String sqlLoad = " SELECT bp.C_BPartner_ID, bp.Name Customer, l.DateAcct, vt.Name VehicleType, vt.TF_VehicleType_ID, COUNT(*)::numeric TotalLoad , "
				+ " SUM(l.Total_Bucket - l.BucketPerLoad - l.Bucket_Discount) xBckQty, "
				+ " SUM(l.Bucket_Discount) discBckQty "
				+ " FROM TF_YardLoadEntry l INNER JOIN TF_VehicleType vt ON l.TF_VehicleType_ID = vt.TF_VehicleType_ID" 
				+ " INNER JOIN C_BPartner bp ON bp.C_BPartner_ID = COALESCE(l.C_BPartner_ID,1000020) "
				+ " WHERE l.DocStatus = 'CO' AND  l.TF_YardEntryApprove_ID = ? GROUP BY l.DateAcct, vt.TF_VehicleType_ID, vt.Name, bp.C_BPartner_ID, bp.Name"
				+ " ORDER BY l.DateAcct, vt.Name, bp.Name ";
		
		String sqlPermitIssue = " SELECT SUM(1::numeric) "
				+ " FROM TF_YardPermitIssue_Entry p WHERE p.DocStatus = 'CO' AND p.TF_YardEntryApprove_ID = ? AND "
				+ " p.TF_VehicleType_ID = ? AND PermitIssue_Type='I' AND COALESCE(p.C_BPartner_ID,1000020) = ? ";
		
		String sqlPermitCancelled = " SELECT SUM(1::numeric) "
				+ " FROM TF_YardPermitIssue_Entry p WHERE p.DocStatus = 'CO' AND p.TF_YardEntryApprove_ID = ? AND "
				+ " p.TF_VehicleType_ID = ? AND PermitIssue_Type='C' AND COALESCE(p.C_BPartner_ID,1000020) = ? ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			pstmt = DB.prepareStatement(sqlLoad, get_TrxName());	
			pstmt.setInt(1,getTF_YardEntryApprove_ID());
			rs = pstmt.executeQuery();
			BigDecimal xBucketQty = BigDecimal.ZERO;
			BigDecimal DiscBucketQty = BigDecimal.ZERO;
			MYardEntryApproveLine xBckLine = null;
			
			while(rs.next())
			{
				int C_BPartner_ID = rs.getInt("C_BPartner_ID");
				int TF_VehicleType_ID = rs.getInt("TF_VehicleType_ID");
				BigDecimal totalload = rs.getBigDecimal("TotalLoad");
				xBucketQty = xBucketQty.add(rs.getBigDecimal("xBckQty"));
				DiscBucketQty = DiscBucketQty.add(rs.getBigDecimal("discBckQty"));
				
				BigDecimal permitIssuedQty = DB.getSQLValueBD(get_TrxName(), sqlPermitIssue, getTF_YardEntryApprove_ID(), TF_VehicleType_ID,
						C_BPartner_ID);
				if(permitIssuedQty == null)
					permitIssuedQty = BigDecimal.ZERO;
				
				BigDecimal permitCancelledQty = DB.getSQLValueBD(get_TrxName(), sqlPermitCancelled, getTF_YardEntryApprove_ID(), TF_VehicleType_ID,
						C_BPartner_ID);
				if(permitCancelledQty == null)
					permitCancelledQty = BigDecimal.ZERO;
				
				BigDecimal permitSalesQty = permitIssuedQty.subtract(permitCancelledQty);
				BigDecimal wpSalesQty = totalload.subtract(permitSalesQty); 
				
				MYardEntryApproveLine line = new MYardEntryApproveLine(getCtx(), 0, get_TrxName());
				line.setAD_Org_ID(getAD_Org_ID());
				line.setTF_YardEntryApprove_ID(getTF_YardEntryApprove_ID());
				line.setTF_VehicleType_ID(TF_VehicleType_ID);
				line.setC_BPartner_ID(C_BPartner_ID);
				line.setDateAcct(getDateAcct());
				line.setTotalLoad(totalload);
				line.setPermitIssuedQty(permitIssuedQty);
				line.setPermitCancelledQty(permitCancelledQty);
				
				line.setPermitSalesQty(permitSalesQty);
				MYardEntryConfig pConfig = MYardEntryConfig.getConfig(getAD_Org_ID(), TF_VehicleType_ID, C_BPartner_ID);				
				if(pConfig == null) {
					DB.close(rs, pstmt);
					throw new AdempiereException("No Price found in Yard Entry Configuration for " + line.getTF_VehicleType().getName());
				}
				line.setPermitPrice(pConfig.getPermitPrice());
				line.setPermitAmount(line.getPermitPrice().multiply(line.getPermitSalesQty()));
				
				line.setWPQty(wpSalesQty);
				line.setWpPrice(pConfig.getWpPrice());
				line.setWPAmount(line.getWpPrice().multiply(line.getWPQty()));
				
				line.saveEx();
				
				if(xBckLine == null)
					xBckLine = line;				
				
			}
			DB.close(rs, pstmt);
			xBckLine.setExtraBucketQty(xBucketQty);			
			MYardEntryConfig xConfig = MYardEntryConfig.getXBckConfig(getAD_Org_ID());			
			if(xConfig != null)
				xBckLine.setExtraBucketPrice(xConfig.getExtraBucketPrice());
			else
				xBckLine.setExtraBucketPrice(BigDecimal.ZERO);
			xBckLine.setExtraBucketAmount(xBckLine.getExtraBucketPrice().multiply(xBckLine.getExtraBucketQty()));
			
			xBckLine.saveEx();
			setIsCreated("Y");
			
		}
		catch(Exception e)
		{
			throw new AdempiereException(e.getMessage(), e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
	}
}
