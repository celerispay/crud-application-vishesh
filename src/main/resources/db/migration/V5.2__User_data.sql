INSERT INTO authority VALUES (3, 'UPDATE');
INSERT INTO authority VALUES (4, 'DELETE');

INSERT INTO user VALUES (2, 
						'Lily', 
						'$2a$10$aPu1RcOTV8NrLsmQD0FpUeILLjnOg6vJ4ZudatCpPmud7TFS3CD9G',
						
						'lily@gmail.com');
INSERT INTO user_authorities VALUES (2, 1);
INSERT INTO user_authorities VALUES (2, 2);
INSERT INTO user_authorities VALUES (2, 3);
INSERT INTO user_authorities VALUES (2, 4);

INSERT INTO user VALUES (3, 
						'Jack', 
						'$2a$10$aPu1RcOTV8NrLsmQD0FpUeILLjnOg6vJ4ZudatCpPmud7TFS3CD9G',
						'jack@gmail.com');
INSERT INTO user_authorities VALUES (3, 1);

INSERT INTO user VALUES (4, 
						'James', 
						'$2a$10$aPu1RcOTV8NrLsmQD0FpUeILLjnOg6vJ4ZudatCpPmud7TFS3CD9G',
						'james@gmail.com');
INSERT INTO user_authorities VALUES (4, 1);

INSERT INTO user VALUES (5, 
						'Sam', 
						'$2a$10$aPu1RcOTV8NrLsmQD0FpUeILLjnOg6vJ4ZudatCpPmud7TFS3CD9G',
						'sam@gmail.com');
INSERT INTO user_authorities VALUES (5, 1);
