package org.syvasoft.tallyfrontcrusher.event;

import java.util.List;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventTopics;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.osgi.service.event.Event;
import org.syvasoft.tallyfrontcrusher.model.MNotification;

public class NotificationEventHandler extends AbstractEventHandler {
	
	@Override
	protected void initialize() {
		String whereClause =  "AD_Table_ID IN (SELECT AD_Table_ID FROM TF_SmsNotification WHERE AD_Table_ID IS NOT NULL AND IsActive = 'Y')";
		List<MTable> list = new Query(Env.getCtx(), MTable.Table_Name, whereClause, null)				
				.setOnlyActiveRecords(true)
				.list();
		for(MTable table : list) {
			registerTableEvent(IEventTopics.PO_AFTER_CHANGE, table.getTableName());
			registerTableEvent(IEventTopics.PO_AFTER_NEW, table.getTableName());
		}
	}
	
	@Override
	protected void doHandleEvent(Event event) {
		PO po = getPO(event);
		if(event.getTopic().equals(IEventTopics.PO_AFTER_CHANGE) || event.getTopic().equals(IEventTopics.PO_AFTER_NEW)) {
			String whereClause =  "AD_Table_ID = ? AND IsScheduled='N'";
			List<MNotification> list = new Query(Env.getCtx(), MNotification.Table_Name, whereClause, po.get_TrxName())
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(po.get_Table_ID())
					.list();
			
			for(MNotification msg : list) {
				String sql = "SELECT COUNT(*) FROM " + po.get_TableName() + " WHERE " + po.get_TableName() + "_ID = " + po.get_ID(); 
				
				if(!Util.isEmpty(msg.getWhereClause())) {
					sql = sql + " AND (" + msg.getWhereClause() + ")";
				}
				
				int count = DB.getSQLValue(po.get_TrxName(), sql);
				if(count > 0)
					msg.notifyMessage(po.get_ID() + "");
			}		
		}
	}
}
