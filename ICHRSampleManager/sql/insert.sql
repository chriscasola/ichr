INSERT INTO contacts (first, last, addr1, city, state, zipcode, email, phone) VALUES
	('chris', 'casola', '7 Schussler Rd', 'worcester', 'ma', '01609', 'ccasola@wpi.edu', '555-555-5555'),
	('steve', 'breselli', '100 Institute Rd', 'worcester', 'ma', '01609', 'sjberselli@wpi.edu', '222-222-2222'),
	('dongni', 'zhang', '100 Institute Rd', 'worcester', 'ma', '01609', 'dzhang@wpi.edu', '333-333-3333'),
	('silvia', 'zamora-palacios', '100 Institute Rd', 'worcester', 'ma', '01609', 'szamora@wpi.edu', '777-777-7777');

INSERT INTO users (username, password, role, contact_id) VALUES
	('ccasola', 'password', 'tech', 1),
	('sberselli', 'password', 'admin', 2);

INSERT INTO suppliers (supplier_name, supplier_contact) VALUES
	('Acme', 3),
	('Biotech', 4);

INSERT INTO sample_types (sample_type_desc) VALUES
	('serum'),
	('plasma');

INSERT INTO boxes (box_id, supplier_id, sample_type) VALUES
	('393kdndl3', 2, 'serum'),
	('39jfl13k3', 1, 'plasma');

INSERT INTO samples (sample_id, census_num, planned_use, volume, box_id) VALUES
	('393kdndl3_1', 'abd1', 'To be used in Steve\'s experiment', '10 mL', '393kdndl3'),
	('39jfl13k3_1', 'abd1', 'To be used in Steve\'s experiment', '20 mL', '39jfl13k3');

INSERT INTO sample_uses (sample_id, username, time_out, time_in, comment_in, comment_out) VALUES
	('393kdndl3_1', 'ccasola', '2013-04-15 10:14:00', NULL, NULL, 'Enough left for 1 more test'),
	('393kdndl3_1', 'sberselli', '2013-03-10 13:01:12', '2013-03-10 14:23:45', 'Still enough left for two tests', 'Brand new sample - first check out');

INSERT INTO freezers (freezer_desc) VALUES
	('freezer A'),
	('freezer B');

INSERT INTO freezer_shelves (freezer_id, row, col, box_id) VALUES
	(1, 3, 5, '393kdndl3'),
	(2, 2, 4, '39jfl13k3');
