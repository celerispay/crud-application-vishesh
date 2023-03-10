CREATE TABLE IF NOT EXISTS authority (
	id VARCHAR(36) PRIMARY KEY,
	name VARCHAR(45) NOT NULL UNIQUE);
	
CREATE TABLE IF NOT EXISTS user_authorities (
	user_id VARCHAR(36) NOT NULL,
	authorities_id VARCHAR(36) NOT NULL);
	
INSERT INTO user VALUES ("0479c797-0eb5-40b6-96ae-8cb912e5d5ef", 'John', '$2a$10$aPu1RcOTV8NrLsmQD0FpUeILLjnOg6vJ4ZudatCpPmud7TFS3CD9G');
INSERT into authority VALUES ("0479c797-0eb5-40b6-96ae-8cb912e5d5ef", 'READ');
INSERT INTO authority VALUES (2, 'WRITE');
INSERT INTO user_authorities VALUES ("0479c797-0eb5-40b6-96ae-8cb912e5d5ef", 1);
INSERT INTO user_authorities VALUES ("0479c797-0eb5-40b6-96ae-8cb912e5d5ef", 2);