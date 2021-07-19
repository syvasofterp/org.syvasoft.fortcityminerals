-- *** SqlDbx Personal Edition ***
-- !!! Not licensed for commercial use beyound 90 days evaluation period !!!
-- For version limitations please check http://www.sqldbx.com/personal_edition.htm
-- Number of queries executed: 8843, number of rows retrieved: 251820

DROP VIEW IF EXISTS adempiere.tf_torder_v;

CREATE OR REPLACE VIEW adempiere.tf_torder_v AS
 SELECT tf_torder.ad_client_id,
    tf_torder.ad_org_id,
    tf_torder.isactive,
    tf_torder.created,
    tf_torder.createdby,
    tf_torder.updated,
    tf_torder.updatedby,
    date(tf_torder.dateacct) AS dateacct,
    tf_torder.docstatus,
    tf_torder.c_bpartner_id,
    tf_torder.documentno,
    tf_torderline.tf_torderline_id,
    tf_torderline.tf_vehicletype_id,
    tf_torderline.tf_destination_id,
    tf_torderline.freightprice,
    tf_torderline.c_uom_id,
    1000358 AS ad_table_id,
    tf_torder.tf_torder_id,
    tf_torder.tf_torder_id AS record_id,
    NULL::unknown AS printtorder
   
FROM tf_torder JOIN tf_torderline ON tf_torderline.tf_torder_id = tf_torder.tf_torder_id;

