-- *** SqlDbx Personal Edition ***
-- !!! Not licensed for commercial use beyound 90 days evaluation period !!!
-- For version limitations please check http://www.sqldbx.com/personal_edition.htm
-- Number of queries executed: 2622, number of rows retrieved: 98720

DROP VIEW IF EXISTS adempiere.tf_uom_v;

CREATE OR REPLACE VIEW adempiere.tf_uom_v AS
 SELECT row_number() OVER (ORDER BY u.c_uom_id) AS tf_uom_v_id,
    o.ad_client_id,
    o.ad_org_id,
    u.isactive,
    u.created,
    u.updated,
    u.createdby,
    u.updatedby,
    u.c_uom_id,
    u.x12de355,
    u.uomsymbol,
    u.name,
    c.m_product_id,
    ( SELECT p.name
           
FROM m_product p
          WHERE (c.m_product_id = p.m_product_id)) AS productname,
    c.c_uom_to_id,
    ( SELECT u1.name
           
FROM c_uom u1
          WHERE (u1.c_uom_id = c.c_uom_to_id)) AS to_uom,
    c.dividerate
   
FROM (c_uom u LEFT JOIN c_uom_conversion c ON u.c_uom_id = c.c_uom_id )     CROSS JOIN ad_org o
  WHERE u.isactive = 'Y' AND u.ad_client_id IN (0,1000000) AND o.ad_client_id = 1000000 AND o.ad_org_id > 0 AND u.uomtype='WE';

