INSERT INTO user(id, username, password, email) VALUES('b39dd5b7-f3ae-4115-82c8-87a1fbbe3ee1', 'Jack', '1234', 'jack@gmail.com'); 
INSERT INTO authority(id, name) VALUES('f5949f93-dbcb-4d90-bdde-29ca009d15a8', 'read');
INSERT INTO user_authorities(user_id, authorities_id) VALUES('b39dd5b7-f3ae-4115-82c8-87a1fbbe3ee1', 'f5949f93-dbcb-4d90-bdde-29ca009d15a8');
INSERT INTO transaction(id, amount, transaction_reference, user_id) VALUES('f45d2eed-1a6b-4ed4-b1a8-ab1091b19c0a', '999.99', 'c501f4cd-b662-47ab-b999-95a2b655fa88', 'b39dd5b7-f3ae-4115-82c8-87a1fbbe3ee1');
