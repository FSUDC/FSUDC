DROP TABLE IF EXISTS Profile;

CREATE TABLE Profile (
	id INT UNSIGNED NOT NULL REFERENCES Member (profileID),
	bio TEXT,
	PRIMARY KEY (id)
);