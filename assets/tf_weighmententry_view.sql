-- *** SqlDbx Personal Edition ***
-- !!! Not licensed for commercial use beyound 90 days evaluation period !!!
-- For version limitations please check http://www.sqldbx.com/personal_edition.htm
-- Number of queries executed: 8843, number of rows retrieved: 251820

DROP VIEW IF EXISTS adempiere.tf_weighmententry_view;

CREATE OR REPLACE VIEW adempiere.tf_weighmententry_view AS
 SELECT tf_weighmententry.tf_weighmententry_id,
    tf_weighmententry.ad_client_id,
    tf_weighmententry.ad_org_id,
    tf_weighmententry.isactive,
    tf_weighmententry.created,
    tf_weighmententry.createdby,
    tf_weighmententry.updated,
    tf_weighmententry.updatedby,
    tf_weighmententry.documentno,
    tf_weighmententry.processed,
    tf_weighmententry.status,
    tf_weighmententry.weighmententrytype,
    tf_weighmententry.c_bpartner_id,
    tf_weighmententry.tf_quarry_id,
    tf_weighmententry.c_project_id,
    tf_weighmententry.vehicleno,
    tf_weighmententry.tf_rentedvehicle_id,
    tf_weighmententry.m_product_id,
    tf_weighmententry.tareweight,
    tf_weighmententry.tareweighttime,
    tf_weighmententry.grossweight,
    tf_weighmententry.grossweighttime,
    tf_weighmententry.netweight,
    tf_weighmententry.username,
    tf_weighmententry.tf_weighmententry_uu,
    tf_weighmententry.c_order_id,
    tf_weighmententry.tf_boulder_receipt_id,
    tf_weighmententry.description,
    tf_weighmententry.tf_destination_id,
    tf_weighmententry.paymentrule,
    tf_weighmententry.m_warehouse_id,
    tf_weighmententry.ismanual,
    tf_weighmententry.tf_vehicletype_id,
    tf_weighmententry.hasbalance,
    tf_weighmententry.price,
    tf_weighmententry.mdpno,
    tf_weighmententry.permitissuedqty,
    tf_weighmententry.amount,
    tf_weighmententry.partyname,
    tf_weighmententry.phone,
    tf_weighmententry.tf_send_to,
    tf_weighmententry.tf_productionplant_id,
    tf_weighmententry.tf_bluemetal_type,
    tf_weighmententry.netweightunit,
    tf_weighmententry.c_uom_id,
    tf_weighmententry.c_orderline_id,
    tf_weighmententry.rent_amt,
    tf_weighmententry.drivertips,
    tf_weighmententry.totalamt,
    tf_weighmententry.invoiceno,
    tf_weighmententry.permitpassamount,
    tf_weighmententry.passqtyissued,
    tf_weighmententry.passpriceperunit,
    tf_weighmententry.tenderamount,
    tf_weighmententry.changeamt,
    tf_weighmententry.newdestination,
    tf_weighmententry.m_product2_id,
    tf_weighmententry.ispermitsales,
    tf_weighmententry.billingname,
    tf_weighmententry.drivername,
    tf_weighmententry.roundingoff,
    tf_weighmententry.discountamount,
    tf_weighmententry.completedby,
    tf_weighmententry.gstamount,
    tf_weighmententry.newproduct,
    tf_weighmententry.tpno,
        CASE
            WHEN (tf_weighmententry.issecondary = 'N'::bpchar) THEN NULL::numeric
            ELSE tf_weighmententry.tf_weighmententryprimary_id
        END AS tf_weighmententryprimary_id,
    tf_weighmententry.issecondary,
    tf_weighmententry.invoicetype,
    tf_weighmententry.royaltyno,
    tf_weighmententry.ewaybillno,
    tf_weighmententry.primarydocumentno,
    tf_weighmententry.tf_pricelistuom_id,
    tf_weighmententry.isroyaltypassinclusive,
    tf_weighmententry.isrentinclusive,
    tf_weighmententry.istaxincluded,
    tf_weighmententry.ispriceconfidential,
    tf_weighmententry.c_bpartnerwb_id,
    tf_weighmententry.freightrule_id,
    tf_weighmententry.freightprice,
    tf_weighmententry.drivercontact,
    tf_weighmententry.invoiceno2,
    tf_weighmententry.createtwoinvoices,
    tf_weighmententry.tf_dispenseplanline_id,
    tf_weighmententry.shipmentto,
        CASE
            WHEN ((tf_weighmententry.status)::text = 'CO'::text) THEN 1
            ELSE 2
        END AS statusorder,
    ( SELECT string_agg((pay_1.documentno)::text, ','::text ORDER BY (pay_1.documentno)::text) AS string_agg
           
FROM c_payment pay_1
          WHERE ((tf_weighmententry.tf_weighmententry_id = pay_1.tf_weighmententry_id) AND (pay_1.docstatus = ANY (ARRAY['CO'::bpchar, 'CL'::bpchar])))) AS cashbookentryno,
    ( SELECT string_agg(to_char(pay_1.datetrx, 'dd/mm/yyyy'::text), ','::text ORDER BY pay_1.documentno) AS string_agg
           
FROM c_payment pay_1
          WHERE ((tf_weighmententry.tf_weighmententry_id = pay_1.tf_weighmententry_id) AND (pay_1.docstatus = ANY (ARRAY['CO'::bpchar, 'CL'::bpchar])))) AS cashbookreceiptdate,
    ( SELECT sum(pay_1.payamt) AS sum
           
FROM c_payment pay_1
          WHERE ((tf_weighmententry.tf_weighmententry_id = pay_1.tf_weighmententry_id) AND (pay_1.docstatus = ANY (ARRAY['CO'::bpchar, 'CL'::bpchar])))) AS cashreceived,
        CASE
            WHEN (doc.tf_docattachment_id IS NOT NULL) THEN 'Yes'::text
            ELSE 'No'::text
        END AS docattachment,
    NULL::unknown AS generate_salesentry,
        CASE
            WHEN (( SELECT count(*) AS count
               
FROM ad_note
              WHERE ((ad_note.record_id = tf_weighmententry.tf_weighmententry_id) AND (ad_note.ad_table_id = (1000212)::numeric))) = 0) THEN 'Black'::text
            WHEN (( SELECT count(*) AS count
               
FROM ad_note
              WHERE ((ad_note.record_id = tf_weighmententry.tf_weighmententry_id) AND (ad_note.ad_table_id = (1000212)::numeric) AND (ad_note.processed = 'N'::bpchar))) > 0) THEN 'Red'::text
            WHEN (( SELECT count(*) AS count
               
FROM ad_note
              WHERE ((ad_note.record_id = tf_weighmententry.tf_weighmententry_id) AND (ad_note.ad_table_id = (1000212)::numeric) AND (ad_note.processed = 'Y'::bpchar))) > 0) THEN 'Green'::text
            ELSE NULL::text
        END AS color,
        CASE
            WHEN ((tf_rentedvehicle.istransporter = 'Y'::bpchar) AND (tf_weighmententry.customertransporter = 'N'::bpchar)) THEN ( SELECT c_bpartner.name
               
FROM c_bpartner
              WHERE (c_bpartner.c_bpartner_id = tf_rentedvehicle.c_bpartner_id))
            WHEN (tf_rentedvehicle.isownvehicle = 'Y'::bpchar) THEN 'Own Vehicle'::character varying
            ELSE 'Customer Vehicle'::character varying
        END AS transporter,
        CASE
            WHEN ((tf_rentedvehicle.istransporter = 'Y'::bpchar) AND (tf_weighmententry.customertransporter = 'N'::bpchar)) THEN tf_rentedvehicle.c_bpartner_id
            ELSE NULL::numeric
        END AS transporter_id,
        CASE
            WHEN ((tf_rentedvehicle.istransporter = 'Y'::bpchar) AND (tf_weighmententry.customertransporter = 'N'::bpchar)) THEN 'T'::character varying
            WHEN (tf_rentedvehicle.isownvehicle = 'Y'::bpchar) THEN 'O'::character varying
            ELSE 'C'::character varying
        END AS vehiclecategory,
    NULL::unknown AS print_challan,
    1000212 AS ad_table_id,
    tf_weighmententry.tf_weighmententry_id AS record_id
   
FROM tf_weighmententry
     LEFT JOIN c_payment pay ON tf_weighmententry.tf_weighmententry_id = pay.tf_weighmententry_id AND pay.docstatus = ANY (ARRAY['CO'::bpchar, 'CL'::bpchar])
     LEFT JOIN tf_docattachment doc ON doc.tf_weighmententry_id = tf_weighmententry.tf_weighmententry_id
     LEFT JOIN tf_rentedvehicle ON tf_rentedvehicle.tf_rentedvehicle_id = tf_weighmententry.tf_rentedvehicle_id;

