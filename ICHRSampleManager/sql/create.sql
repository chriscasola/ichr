CREATE DATABASE IF NOT EXISTS ichr;

CREATE TABLE ichr.contacts (
	contact_id INT NOT NULL AUTO_INCREMENT,
	first VARCHAR(30),
	last VARCHAR(30),
	addr1 VARCHAR(255),
	addr2 VARCHAR(255),
	city VARCHAR(255),
	state CHAR(2),
	zipcode VARCHAR(10),
	email VARCHAR(255),
	phone VARCHAR(20),
	PRIMARY KEY (contact_id)
);

CREATE TABLE ichr.users (
	username VARCHAR(15),
	password VARCHAR(15),
	role VARCHAR(15),
	contact_id INT,
	PRIMARY KEY (username),
	FOREIGN KEY (contact_id) REFERENCES contacts(contact_id)
);

CREATE TABLE ichr.suppliers (
	supplier_id INT NOT NULL AUTO_INCREMENT,
	supplier_name VARCHAR(255),
	supplier_contact INT,
	PRIMARY KEY (supplier_id),
	FOREIGN KEY (supplier_contact) REFERENCES contacts(contact_id)
);

CREATE TABLE ichr.sample_types (
	sample_type_desc VARCHAR(255),
	PRIMARY KEY (sample_type_desc)
);

CREATE TABLE ichr.boxes (
	box_id VARCHAR(255),
	supplier_id INT,
	sample_type VARCHAR(255),
	PRIMARY KEY (box_id),
	FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id),
	FOREIGN KEY (sample_type) REFERENCES sample_types(sample_type_desc)
);

CREATE TABLE ichr.samples (
	sample_id VARCHAR(255),
	census_num VARCHAR(255),
	planned_use VARCHAR(255),
	volume VARCHAR(255),
	box_id VARCHAR(255),
	thaw_count INT DEFAULT 0,
	is_empty BOOLEAN DEFAULT 0,
	PRIMARY KEY (sample_id),
	FOREIGN KEY (box_id) REFERENCES boxes(box_id)
);

CREATE TABLE ichr.sample_uses (
	sample_id VARCHAR(255),
	username VARCHAR(15),
	time_out TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	time_in TIMESTAMP,
	comment_in VARCHAR(255),
	comment_out VARCHAR(255),
	PRIMARY KEY (sample_id, time_out),
	FOREIGN KEY (sample_id) REFERENCES samples(sample_id),
	FOREIGN KEY (username) REFERENCES users(username)
);

CREATE TABLE ichr.freezers (
	freezer_id INT NOT NULL AUTO_INCREMENT,
	freezer_desc VARCHAR(255),
	PRIMARY KEY (freezer_id)
);

CREATE TABLE ichr.freezer_shelves (
	freezer_id INT,
	row INT,
	col INT,
	box_id VARCHAR(255),
	PRIMARY KEY (freezer_id, row, col),
	FOREIGN KEY (freezer_id) REFERENCES freezers(freezer_id),
	FOREIGN KEY (box_id) REFERENCES boxes(box_id)
);
