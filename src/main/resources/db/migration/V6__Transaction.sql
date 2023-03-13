CREATE TABLE IF NOT EXISTS transaction (
	id VARCHAR(36) PRIMARY KEY,
	transaction_reference VARCHAR(36) NOT NULL,
	amount DECIMAL(12, 2) NOT NULL,
	user_id VARCHAR(36) NOT NULL);