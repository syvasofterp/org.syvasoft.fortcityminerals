-- *** SqlDbx Personal Edition ***
-- !!! Not licensed for commercial use beyound 90 days evaluation period !!!
-- For version limitations please check http://www.sqldbx.com/personal_edition.htm
-- Number of queries executed: 8837, number of rows retrieved: 251820

DROP VIEW IF EXISTS adempiere.tf_minout_v;

CREATE OR REPLACE VIEW adempiere.tf_minout_v AS
 SELECT m.ad_client_id,
    m.ad_org_id,
    m.isactive,
    m.created,
    m.createdby,
    m.updated,
    m.updatedby,
    m.m_inout_id,
    ml.m_inoutline_id,
    m.c_bpartner_id,
    ml.m_product_id,
    m.c_doctype_id,
    m.issotrx,
    m.movementtype,
    mt.name AS movementtypename,
    ml.movementqty,
    m.documentno,
    m.dateacct,
    bp.name AS bpname,
    p.name AS productname,
    m.description AS headerdesc,
    ml.description AS linedesc,
    m.docstatus,
    m.tf_weighmententry_id,
    m.c_order_id,
    o.documentno AS orderno
   
FROM m_inout m
     JOIN m_inoutline ml ON m.m_inout_id = ml.m_inout_id
     JOIN c_bpartner bp ON m.c_bpartner_id = bp.c_bpartner_id
     JOIN m_product p ON ml.m_product_id = p.m_product_id
     JOIN ad_ref_list mt ON mt.ad_reference_id = (189)::numeric AND mt.value = m.movementtype
     LEFT JOIN c_order o ON o.c_order_id = m.c_order_id
  ORDER BY ml.m_inoutline_id;

