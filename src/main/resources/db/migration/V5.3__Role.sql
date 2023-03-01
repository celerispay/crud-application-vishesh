INSERT INTO authority VALUES (5, 'ROLE_USER');
INSERT INTO authority VALUES (6, 'ROLE_MANAGER');
INSERT INTO authority VALUES (7, 'ROLE_ADMIN');

INSERT INTO user_authority VALUES(3, 5);
INSERT INTO user_authority VALUES(4, 5);
INSERT INTO user_authority VALUES(5, 5);
INSERT INTO user_authority VALUES(2, 7);
INSERT INTO user_authority VALUES(1, 6);