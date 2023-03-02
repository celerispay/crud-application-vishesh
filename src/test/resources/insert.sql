INSERT INTO user(id, username, password, email) VALUES ('e983d0a6-d9fe-43bc-a494-8f103087760b', 'foo', '$2a$10$aPu1RcOTV8NrLsmQD0FpUeILLjnOg6vJ4ZudatCpPmud7TFS3CD9G', 'foo@abc.com');
INSERT into authority(id, name) VALUES ('ccaf4000-e8c1-4435-aa8b-f0bd98aa60f3', 'alpha');
INSERT INTO authority(id, name) VALUES ('dd18101d-241f-43d1-bb2a-0116a7f5a548', 'beta');
INSERT INTO user_authority(user_id, authority_id) VALUES ('e983d0a6-d9fe-43bc-a494-8f103087760b', 'ccaf4000-e8c1-4435-aa8b-f0bd98aa60f3');
INSERT INTO user_authority(user_id, authority_id) VALUES ('e983d0a6-d9fe-43bc-a494-8f103087760b', 'dd18101d-241f-43d1-bb2a-0116a7f5a548');
