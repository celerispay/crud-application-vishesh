CREATE TABLE IF NOT EXISTS authority (
	id VARCHAR(36) PRIMARY KEY,
	name VARCHAR(45) NOT NULL);
	
CREATE TABLE IF NOT EXISTS user_authority (
	user_id VARCHAR(36) NOT NULL,
	authority_id VARCHAR(36) NOT NULL);
	
INSERT INTO user VALUES (1, "John", "1234");
INSERT into authority VALUES (1, "READ");
INSERT INTO authority VALUES (2, "WRITE");
INSERT INTO user_authority VALUES (1, 1);
INSERT INTO user_authority VALUES (1, 2);