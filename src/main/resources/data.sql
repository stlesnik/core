DROP TABLE IF EXISTS atm_table;

	CREATE TABLE atm_table (
	  id INT AUTO_INCREMENT  PRIMARY KEY,
	  name VARCHAR(255) NOT NULL,
	  description VARCHAR(255) NOT NULL,
	  code INT
	);

	INSERT INTO atm_table (name, description, code) VALUES
	  ('Underground atm', 'Located on mayakovskaya metro station', '20031'),
	  ('Bank office atm', 'Lokated on Tverskaya street, h. 31', '42203'),
	  ('Shop atm', 'Located in the Crocus moll on the second floor', '24502');