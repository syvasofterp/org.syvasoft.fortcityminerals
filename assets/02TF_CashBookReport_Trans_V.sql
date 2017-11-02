--DROP VIEW TF_CashBookReport_Trans_V;
--Cash Book Report Transaction
--SELECT * FROM TF_CashBookReport_Trans_V;
CREATE OR REPLACE VIEW TF_CashBookReport_Trans_V AS

SELECT
	p.AD_Client_ID, p.AD_Org_ID, p.IsActive, p.Created,p.CreatedBy,p.Updated,p.Updatedby,
	p.C_Payment_ID,
	p.C_BankAccount_ID,
	p.DateAcct,
	p.DocumentNo,
		
	CASE
	 WHEN p.C_ElementValue_ID IS NOT NULL THEN c.Name
	 WHEN bp.IsPOSCashBP='Y' AND p.IsReceipt='Y' THEN 'Cash Sales'
	 WHEN bp.IsPOSCashBP='Y' AND p.IsReceipt='N' THEN 'Cash Purchase'
	 ELSE bp.Name
	END AccountHead,
	
	CASE
WHEN p.CashType = 'V' AND p.C_Invoice_ID IS NULL THEN
		(CASE WHEN p.C_BPartner_ID=1000020 THEN '' ELSE 'Vendor : ' || bp.Name END)
		|| COALESCE(' - info : ' || i.Description, '')
	 WHEN p.CashType = 'V' AND p.Description IS NULL THEN 'Purchase Invoice : ' || i.DocumentNo ||
		(CASE WHEN p.C_BPartner_ID=1000020 THEN '' ELSE  ' - Vendor : ' || bp.Name END)
		|| COALESCE(' - info : ' || i.Description, '')
WHEN p.CashType = 'C' AND p.C_Invoice_ID IS NULL THEN
		(CASE WHEN p.C_BPartner_ID=1000020 THEN '' ELSE 'Customer : ' || bp.Name END)
		|| COALESCE(' - info : ' || i.Description, '')
	 WHEN p.CashType = 'C' AND p.Description IS NULL THEN 'Sales Invoice : ' || i.DocumentNo ||
		(CASE WHEN p.C_BPartner_ID=1000020 THEN '' ELSE ' - Customer : ' || bp.Name END)
		|| COALESCE(' - info : ' || i.Description, '')
	 WHEN p.CashType ='Y' THEN p.Description || ' - Employee : ' || bp.Name
	 ELSE p.Description
	End || CASE WHEN p.CashType IN ('C','V') AND p.C_Charge_ID IS NOT NULL THEN ' - ' || c.Name ELSE '' END
	|| COALESCE(' (' || pr.Name || ')', '') || COALESCE(' [For  ' || e.Name || ']','')
	Description,
	CASE
	 WHEN p.IsReceipt = 'Y' THEN PayAmt
	 ELSE NULL
	END Receipt,
	CASE
	 WHEN p.IsReceipt = 'N' THEN PayAmt
	 ELSE NULL
	END Payment
	
FROM
	C_Payment p 
	INNER JOIN C_BPartner bp
	 ON bp.C_BPartner_ID = p.C_BPartner_ID
	LEFT OUTER JOIN C_Charge c
	 ON c.C_Charge_ID = p.C_Charge_ID
	LEFT OUTER JOIN C_Invoice i
	 ON i.C_Invoice_ID = p.C_Invoice_ID
	LEFT OUTER JOIN C_Project pr
	 ON pr.C_Project_ID = p.C_Project_ID
	LEFT OUTER JOIN C_ElementValue e
	 ON e.C_ElementValue_ID = p.User1_ID
WHERE
	p.DocStatus IN ('CO','CL')
ORDER BY
	p.AD_Org_ID, p.C_BankAccount_ID, p.DateTrx;