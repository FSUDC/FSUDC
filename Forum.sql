DROP TABLE IF EXISTS Forum;

CREATE TABLE Forum (
	id INT UNSIGNED NOT NULL,
	name VARCHAR (50) NOT NULL, 
	PRIMARY KEY (id)
);

INSERT INTO Forum (id, name)
VALUES 	(1, 'General Programming'),
		(2, 'FSU Computer Science Courses'),
		(3, 'FSU Computer Sciene Events'),
		(4, 'Computer Science Careers'),
		(5, 'Computer Science News');
