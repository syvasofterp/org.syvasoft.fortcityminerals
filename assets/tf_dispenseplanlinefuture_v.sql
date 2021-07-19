-- *** SqlDbx Personal Edition ***
-- !!! Not licensed for commercial use beyound 90 days evaluation period !!!
-- For version limitations please check http://www.sqldbx.com/personal_edition.htm
-- Number of queries executed: 8845, number of rows retrieved: 251820

DROP VIEW IF EXISTS adempiere.tf_dispenseplanlinefuture_v;

CREATE OR REPLACE VIEW adempiere.tf_dispenseplanlinefuture_v AS
 SELECT dl.tf_dispenseplanline_id,
    date(dl.scheduledate) AS scheduledate,
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
    dl.tf_vehicletype_id,
    dl.freightuom_id,
        CASE
            WHEN ((trunc(d.scheduledate) < trunc(getdate())) AND ((dl.docstatus)::text = ANY (ARRAY[('RV'::character varying)::text, ('IP'::character varying)::text]))) THEN 'Red'::text
            WHEN ((dl.docstatus)::text = 'CO'::text) THEN 'Green'::text
            ELSE 'Black'::text
        END AS color,
    d.tf_dispenseplan_id,
    1000334 AS ad_table_id,
    dl.tf_dispenseplanline_id AS record_id
   
FROM (tf_dispenseplanline dl
     JOIN tf_dispenseplan d ON ((d.tf_dispenseplan_id = dl.tf_dispenseplan_id)))
  WHERE (((dl.docstatus)::text = ANY (ARRAY[('DR'::character varying)::text, ('IP'::character varying)::text, ('RV'::character varying)::text])) AND (trunc(dl.scheduledate) > trunc(getdate())));

