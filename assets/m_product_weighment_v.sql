﻿-- View: m_product_weighment_v

-- DROP VIEW m_product_weighment_v;

CREATE OR REPLACE VIEW m_product_weighment_v AS 
 SELECT p.m_product_id,
    p.ad_client_id,
    p.ad_org_id,
    p.isactive,
    p.created,
    p.createdby,
    p.updated,
    p.updatedby,
    p.value,
    p.name,
    p.description,
    p.documentnote,
    p.help,
    p.upc,
    p.sku,
    p.c_uom_id,
    p.salesrep_id,
    p.issummary,
    p.isstocked,
    p.ispurchased,
    p.issold,
    p.isbom,
    p.isinvoiceprintdetails,
    p.ispicklistprintdetails,
    p.isverified,
    p.c_revenuerecognition_id,
    p.m_product_category_id,
    p.classification,
    p.volume,
    p.weight,
    p.shelfwidth,
    p.shelfheight,
    p.shelfdepth,
    p.unitsperpallet,
    p.c_taxcategory_id,
    p.s_resource_id,
    p.discontinued,
    p.discontinuedby,
    p.processing,
    p.s_expensetype_id,
    p.producttype,
    p.imageurl,
    p.descriptionurl,
    p.guaranteedays,
    p.r_mailtext_id,
    p.versionno,
    p.m_attributeset_id,
    p.m_attributesetinstance_id,
    p.downloadurl,
    p.m_freightcategory_id,
    p.m_locator_id,
    p.guaranteedaysmin,
    p.iswebstorefeatured,
    p.isselfservice,
    p.c_subscriptiontype_id,
    p.isdropship,
    p.isexcludeautodelivery,
    p.group1,
    p.group2,
    p.istoformule,
    p.lowlevel,
    p.unitsperpack,
    p.discontinuedat,
    p.copyfrom,
    p.m_product_uu,
    p.m_parttype_id,
    p.iskanban,
    p.ismanufactured,
    p.isphantom,
    p.isownbox,
    p.m_sernoctl_id,
    p.tf_vehicletype_id,
    p.std_load,
    p.setopeningstock,
    p.m_inventory_id,
    u.name AS uom_name
   FROM m_product p
     JOIN c_uom u ON p.c_uom_id = u.c_uom_id
  WHERE p.isactive = 'Y'::bpchar AND u.uomtype::text = 'WE'::text;
