INSERT INTO wallet_type (wty_code, wty_iden,wty_labe) VALUES
	 (1,'1','CUSTOMER'),
	 (2,'2','INTERNE'),
	 (3,'3','MERCHANT');

INSERT INTO wallet_status (wst_code, wst_iden,wst_labe) VALUES
	 (1,'1','ACTIVE'),
	 (2,'2','INACTIVE'),
	 (3,'3','PENDING');

INSERT INTO doc_type (dty_code, dty_iden,dty_labe) VALUES
	 (1,'1','image'),
	 (2,'2','pdf');

INSERT INTO customer_status (cts_code, cts_iden,cts_labe) VALUES
	 (1,'1','ACTIVE'),
	 (2,'2','INACTIVE'),
	 (3,'3','PENDING');

INSERT INTO customer_identity_type (cit_code, cit_iden,cit_labe) VALUES
	 (1,'1','CIN'),
	 (2,'2','PASSPORT');

INSERT INTO customer_doc_liste (cdl_code, cdl_iden,cdl_labe) VALUES
	 (2,'2','PASSPORT'),
	 (1,'1','CIN');

INSERT INTO country (ctr_code, ctr_iden,ctr_labe) VALUES
	 (3,'fr','France'),
	 (2,'us','USA'),
	 (1,'tn','Tunisia');

INSERT INTO city (cty_code, cty_iden,cty_labe,cty_ctr_code) VALUES
	 (3,'3','Paris',3),
	 (2,'2','New York City',2),
	 (1, '1','Tunis',1);

INSERT INTO "role" (id, name) VALUES
	 (4,'ADMIN'),
	 (3,'CUSTOMER'),
	 (2,'INTERNE'),
	 (1,'MERCHANT');
