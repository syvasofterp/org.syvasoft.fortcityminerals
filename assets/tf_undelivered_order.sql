-- View: adempiere.tf_undelivered_order

-- DROP VIEW adempiere.tf_undelivered_order;

CREATE OR REPLACE VIEW adempiere.tf_undelivered_order
 AS
 SELECT ol.c_orderline_id,
    o.c_order_id,
    o.ad_client_id,
    o.ad_org_id,
    o.isactive,
    o.created,
    o.createdby,
    o.updated,
    o.updatedby,
    o.documentno,
    o.dateordered,
    o.c_bpartner_id,
    o.c_bpartner_location_id,
    ol.m_product_id,
    ol.qtyordered,
    ol.qtydelivered,
    ol.c_uom_id,
	ol.IsRoyaltyPassInclusive,
	ol.IsRentInclusive,
	ol.IsTaxIncluded,
	ol.PriceEntered Price
   FROM c_order o
     JOIN c_orderline ol ON o.c_order_id = ol.c_order_id
  WHERE (o.docstatus = ANY (ARRAY['CO'::bpchar, 'CL'::bpchar])) AND o.issotrx = 'Y'::bpchar AND ol.qtydelivered < (ol.qtyordered - 5::numeric) AND (o.c_doctype_id IN ( SELECT c_doctype.c_doctype_id
           FROM c_doctype
          WHERE c_doctype.docbasetype = 'SOO'::bpchar AND c_doctype.isactive = 'Y'::bpchar AND c_doctype.ad_client_id = 1000000::numeric AND c_doctype.docsubtypeso = 'SO'::bpchar));

ALTER TABLE adempiere.tf_undelivered_order
    OWNER TO adempiere;

