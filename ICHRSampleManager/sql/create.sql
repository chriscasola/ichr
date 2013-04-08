CREATE TABLE users (
	username VARCHAR(15),
	password VARCHAR(15),
	PRIMARY KEY (username)
);

CREATE TABLE projects (
	prj_id INT NOT NULL AUTO_INCREMENT,
	prj_name VARCHAR(255),
	prj_desc TEXT,
	PRIMARY KEY (prj_id)
);

CREATE TABLE proj_assignments (
	username VARCHAR(15),
	prj_id INT,
	date_assigned TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (username, prj_id),
	FOREIGN KEY (prj_id) REFERENCES projects(prj_id),
	FOREIGN KEY (username) REFERENCES users(username)
);

CREATE TABLE boxes (
	box_barcode VARCHAR(255),
	PRIMARY KEY (box_barcode)
);

CREATE TABLE samples (
	sample_barcode VARCHAR(255),
	sample_name VARCHAR(255),
	box_barcode VARCHAR(255),
	PRIMARY KEY (sample_barcode),
	FOREIGN KEY (box_barcode) REFERENCES boxes(box_barcode)
);

CREATE TABLE thaw_dates (
	sample_barcode VARCHAR(255),
	username VARCHAR(15),
	thaw_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (sample_barcode, thaw_date),
	FOREIGN KEY (sample_barcode) REFERENCES samples(sample_barcode),
	FOREIGN KEY (username) REFERENCES users(username)
);

CREATE TABLE sample_uses (
	sample_barcode VARCHAR(255),
	username VARCHAR(15),
	time_out TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	time_in TIMESTAMP,
	PRIMARY KEY (sample_barcode, time_out),
	FOREIGN KEY (sample_barcode) REFERENCES samples(sample_barcode),
	FOREIGN KEY (username) REFERENCES users(username)
);

CREATE TABLE freezers (
	f_id INT NOT NULL AUTO_INCREMENT,
	f_descr VARCHAR(255),
	PRIMARY KEY (f_id)
);