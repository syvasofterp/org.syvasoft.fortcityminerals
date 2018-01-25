DROP VIEW TF_KatingEntry_Summary;
CREATE OR REPLACE VIEW TF_KatingEntry_Summary
AS
SELECT 
	MAX(v.TF_RentedVehicle_ID) TF_KatingEntry_Summary_ID, k.AD_Client_ID, k.AD_Org_ID, k.DateAcct, k.Createdby, 
	now() created, k.Createdby updatedby, now() updated, 'Y'::CHARACTER(1) isactive,
	k.C_Project_ID, 
	v.VehicleNo as name, 'Total Load: ' || COUNT(*) Description 
FROM 
	TF_KatingEntry k INNER JOIN TF_RentedVehicle v
	 ON k.TF_RentedVehicle_ID = v.TF_RentedVehicle_ID
WHERE
	k.DocStatus = 'CO' AND DATE_TRUNC('day',k.DateAcct)=DATE_TRUNC('day',now())

GROUP BY 
	k.AD_Client_ID, k.AD_Org_ID, k.DateAcct, k.Createdby, k.C_Project_ID, v.VehicleNo

;