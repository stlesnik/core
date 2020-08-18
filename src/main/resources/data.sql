DROP TABLE IF EXISTS COUNTERS;
DROP TABLE IF EXISTS CASSETTES;

CREATE TABLE CASSETTES (
  ID INT,
  NAME VARCHAR(255) NOT NULL,
  TYPE ENUM('Recycling','In','RetractReject'),
  VALUEe INT,
  PRIMARY KEY (ID)
);
CREATE TABLE COUNTERS (
  ID INT AUTO_INCREMENT,
  VALUEe INT NOT NULL,
  NUMBER INT NOT NULL,
  CASSETTE_ID INT NOT NULL,
  PRIMARY KEY (ID),
  CONSTRAINT cassette_id FOREIGN KEY (CASSETTE_ID) REFERENCES CASSETTES(ID)
);
INSERT INTO CASSETTES (ID, NAME, TYPE) VALUES
  (4301, 'In cassette', 'In');
INSERT INTO CASSETTES (ID, NAME, TYPE, VALUEe) VALUES
  (4560, 'Recycling cassette', 'Recycling', 5000),
  (4561, 'Recycling cassette', 'Recycling', 2000),
  (4562, 'Recycling cassette', 'Recycling', 1000),
  (4563, 'Recycling cassette', 'Recycling', 500),
  (4564, 'Recycling cassette', 'Recycling', 200),
  (4565, 'Recycling cassette', 'Recycling', 100);
INSERT INTO COUNTERS (VALUEe, NUMBER, CASSETTE_ID) VALUES
   (5000, 5, 4301),
   (5000, 100, 4560),
   (2000, 100, 4561),
   (1000, 100, 4562),
   (500, 100, 4563),
   (200, 100, 4564),
   (100, 100, 4565);