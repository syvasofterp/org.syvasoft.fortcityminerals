--DROP VIEW M_Transaction_V;

CREATE OR REPLACE VIEW TF_Transaction_V AS

SELECT
	t.M_Transaction_ID,
	t.AD_Client_ID,
	t.AD_Org_ID,
	t.IsActive,
	t.Created,
	t.Createdby,
	t.Updated,
	t.Updatedby,
	t.MovementType,
	t.M_Locator_ID,
	t.M_Product_ID,
	t.MovementDate,
	t.MovementQty,
	t.M_InventoryLine_ID,
	t.M_MovementLine_ID,
	t.M_InOutLine_ID,
	t.M_ProductionLine_ID,
	t.C_ProjectIssue_ID,
	t.M_AttributeSetInstance_ID,
	t.PP_Cost_Collector_ID,
	t.M_Transaction_UU,
	t.TF_Boulder_Receipt_ID,	
	l.value locator_name,
	w.M_Warehouse_ID,
	w.Name warehouse_name,
	mt.Name movementTypeName,
	CASE 
	  WHEN io.DocumentNo IS NOT NULL THEN io.DocumentNo
	  WHEN inv.DocumentNo IS NOT NULL THEN inv.DocumentNo
	  WHEN mov.DocumentNo IS NOT NULL THEN mov.DocumentNo
	  WHEN p.DocumentNo IS NOT NULL THEN p.DocumentNo
	END DocumentNo,
	CASE 
	  WHEN io.DocumentNo IS NOT NULL THEN io.DocStatus
	  WHEN inv.DocumentNo IS NOT NULL THEN inv.DocStatus
	  WHEN mov.DocumentNo IS NOT NULL THEN mov.DocStatus
	  WHEN p.DocumentNo IS NOT NULL THEN p.DocStatus
	  ELSE 'CO'
	END DocStatus,
	CASE 
	  WHEN io.DocumentNo IS NOT NULL THEN COALESCE(io.Description,'') || ', ' || COALESCE(il.Description, ' ')
	  WHEN inv.DocumentNo IS NOT NULL THEN COALESCE(inv.Description,'') || ', ' || COALESCE(invl.Description, ' ')
	  WHEN mov.DocumentNo IS NOT NULL THEN COALESCE(mov.Description,'') || ', ' || COALESCE(movl.Description, ' ')
	  WHEN p.DocumentNo IS NOT NULL THEN COALESCE(p.Description,'') || ', ' || COALESCE(pl.Description, ' ')
	END Description,
	CASE
	  WHEN io.DocumentNo IS NOT NULL THEN (SELECT bp.Name FROM C_BPartner bp WHERE bp.C_BPartner_ID = io.C_BPartner_ID)	  
	END bpName
	
FROM 
   M_Transaction t INNER JOIN M_Locator l
    ON l.M_Locator_ID = t.M_Locator_ID
   INNER JOIN M_Warehouse w 
    ON w.M_Warehouse_ID = l.M_Warehouse_ID
   INNER JOIN AD_Ref_LIST mt
    ON AD_Reference_ID = 189 AND mt.value = t.movementType
   LEFT OUTER JOIN M_InoutLine il
    ON t.M_InoutLine_ID = il.M_InoutLine_ID
   LEFT OUTER JOIN M_InOut io
    ON io.M_InOut_ID = il.M_InOut_ID
   LEFT OUTER JOIN M_InventoryLine invl
    ON invl.M_InventoryLine_ID = t.M_InventoryLine_ID
   LEFT OUTER JOIN M_Inventory inv
    ON inv.M_Inventory_ID = invl.M_Inventory_ID
   LEFT OUTER JOIN M_MovementLine movl
    ON movl.M_MovementLine_ID = t.M_MovementLine_ID
   LEFT OUTER JOIN M_Movement mov
    ON mov.M_Movement_ID = movl.M_Movement_ID
   LEFT OUTER JOIN M_ProductionLine pl
    ON pl.M_ProductionLine_ID = t.M_ProductionLine_ID
   LEFT OUTER JOIN M_Production p
    ON p.M_Production_ID = pl.M_Production_ID

;
