INSERT INTO users (username, password) VALUES
	('admin', 'password'),
	('chris', 'password'),
	('steve', 'password'),
	('silvia', 'password'),
	('donni', 'password');

INSERT INTO projects (prj_id, prj_name, prj_desc) VALUES
	(1, 'Project A', 'Description of project A'),
	(2, 'Project B', 'Description of project B'),
	(3, 'Project C', 'Description of project C'),
	(4, 'Project D', 'Description of project D');

INSERT INTO proj_assignments (username, prj_id) VALUES
	('chris', 2),
	('steve', 4),
	('silvia', 4),
	('donni', 1);

INSERT INTO freezers (f_descr) VALUES
	('Freezer 1'),
	('Freezer 2'),
	('Freezer 3'),
	('Freezer 4'),
	('Freezer 5');