DROP VIEW TF_PriceListUOM_V ;
CREATE OR REPLACE VIEW TF_PriceListUOM_V 
AS
SELECT	
	pl.TF_PriceListUOM_ID,
	pl.AD_Client_ID,
	pl.AD_Org_ID,
	pl.IsActive,
	pl.Created,
	pl.Createdby,
	pl.Updated,
	pl.Updatedby,
	pl.M_Product_ID,
	pl.C_UOM_ID,
	pl.C_BPartner_ID,
	pl.TF_Destination_ID,
	pl.IsSOTrx,
	CASE WHEN COALESCE(p.GSTRate,0) = 0 THEN COALESCE(pc.GSTRate,0) ELSE COALESCE(p.GSTRate,0) END GSTRate,
	pl.IsTaxIncluded,
	pl.IsRentInclusive,	
	pl.IsRoyaltyPassInclusive,
	CASE WHEN pl.IsTaxIncluded = 'N' THEN pl.Price 
		ELSE ROUND(pl.Price / (1 + CASE WHEN COALESCE(p.GSTRate,0) = 0 THEN COALESCE(pc.GSTRate,0) ELSE COALESCE(p.GSTRate,0) END / 100),2)
	 END Price,
	CASE WHEN pl.IsTaxIncluded = 'N' THEN pl.PriceMin 
		ELSE ROUND(pl.PriceMin / (1 + CASE WHEN COALESCE(p.GSTRate,0) = 0 THEN COALESCE(pc.GSTRate,0) ELSE COALESCE(p.GSTRate,0) END / 100),2)
	 END  PriceMin,
	CASE WHEN pl.IsTaxIncluded = 'N' THEN pl.PriceMax 
		ELSE ROUND(pl.PriceMax / (1 + CASE WHEN COALESCE(p.GSTRate,0) = 0 THEN COALESCE(pc.GSTRate,0) ELSE COALESCE(p.GSTRate,0) END / 100),2)
	 END PriceMax,
	pl.Description,
	pl.TF_PriceListUOM_UU,
	pl.ValidFrom
FROM
	TF_PriceListUOM pl INNER JOIN M_Product p 
	 ON pl.M_Product_ID = p.M_Product_ID
	INNER JOIN M_Product_Category pc
	 ON pc.M_Product_Category_ID = p.M_Product_Category_ID	 