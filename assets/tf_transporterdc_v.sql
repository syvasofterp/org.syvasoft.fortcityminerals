-- *** SqlDbx Personal Edition ***
-- !!! Not licensed for commercial use beyound 90 days evaluation period !!!
-- For version limitations please check http://www.sqldbx.com/personal_edition.htm
-- Number of queries executed: 8845, number of rows retrieved: 251820

DROP VIEW IF EXISTS adempiere.tf_transporterdc_v;

CREATE OR REPLACE VIEW adempiere.tf_transporterdc_v AS
 SELECT io.ad_client_id,
    io.ad_org_id,
    io.m_inout_id,
    iol.m_inoutline_id,
    io.documentno AS shipmentno,
    io.docstatus,
    iol.docstatus AS receiptlinestatus,
    date(io.movementdate) AS movementdate,
    w.documentno AS dcno,
    io.c_bpartner_id AS vendor_id,
    iol.tf_lumpsumrent_config_id,
    iol.tf_destination_id,
    iol.distance,
    iol.qtyentered,
    w.weighmententrytype,
    w.c_bpartner_id,
    w.m_product_id,
    w.tf_rentedvehicle_id,
    iol.c_uom_id,
    u.name AS uomname,
    iol.price,
        CASE
            WHEN (iol.c_uom_id = (1000081)::numeric) THEN ((iol.distance * iol.qtyentered) * iol.price)
            ELSE (iol.qtyentered * iol.price)
        END AS totalamt,
    rent.rentmargin,
    io.created,
    io.createdby,
    iol.updated,
    iol.updatedby,
    io.isactive,
    319 AS ad_table_id,
    io.m_inout_id AS record_id
   
FROM ((((m_inout io
     JOIN m_inoutline iol ON ((iol.m_inout_id = io.m_inout_id)))
     JOIN tf_weighmententry w ON ((w.tf_weighmententry_id = io.tf_weighmententry_id)))
     LEFT JOIN tf_lumpsumrent_config rent ON ((rent.tf_lumpsumrent_config_id = iol.tf_lumpsumrent_config_id)))
     LEFT JOIN c_uom u ON ((u.c_uom_id = iol.c_uom_id)))
  WHERE ((io.issotrx = 'N'::bpchar) AND (io.docstatus <> 'RE'::bpchar));

