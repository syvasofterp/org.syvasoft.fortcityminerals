DROP VIEW IF EXISTS adempiere.tf_dispenseplanline_v;

CREATE OR REPLACE VIEW adempiere.tf_dispenseplanline_v AS
 SELECT dl.tf_dispenseplanline_id,
    dl.scheduledate,
    dl.ad_client_id,
    dl.ad_org_id,
    dl.isactive,
    dl.created,
    dl.createdby,
    dl.updated,
    dl.updatedby,
    dl.documentno,
    dl.dateordered,
    dl.c_orderline_id,
    dl.c_bpartner_id,
    dl.paymentrule,
    dl.m_product_id,
    dl.dispenseqty,
    dl.delivereddpqty,
    dl.balancedpqty,
    dl.c_uom_id,
    dl.tf_destination_id,
    dl.isroyaltypassinclusive,
    dl.isrentinclusive,
    dl.istaxincluded,
    dl.priceentered,
    dl.ispriceconfidential,
    dl.deliverycontact,
    dl.shipmentto,
    dl.shipmentdestination,
    dl.priority,
    dl.type,
    dl.origindate,
    dl.overunitdelivery,
    dl.allowcarryforward,
    dl.docstatus,
        CASE
            WHEN trunc(d.scheduledate) < trunc(getdate()) AND dl.docstatus IN ('RV','IP') THEN 'Red'
            WHEN dl.docstatus = 'CO' THEN 'Green'
            ELSE 'Black'
        END AS color,
    d.tf_dispenseplan_id,
    1000334 AS ad_table_id,
    dl.tf_dispenseplanline_id AS record_id
   
FROM (tf_dispenseplanline dl
     JOIN tf_dispenseplan d ON ((d.tf_dispenseplan_id = dl.tf_dispenseplan_id)))
  WHERE 
  	(dl.docstatus  IN ('IP','RV') AND (dl.allowcarryforward = 'Y' OR trunc(dl.scheduledate) = trunc(getdate()))) OR 
	(dl.docstatus = 'CO' AND (dl.overunitdelivery = 'Y' OR trunc(dl.scheduledate) = trunc(getdate())))