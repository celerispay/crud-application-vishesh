ALTER TABLE user
	ADD COLUMN email varchar(254) UNIQUE AFTER password;
	
UPDATE user 
	SET email='john@gmail.com' 
	WHERE username='John';