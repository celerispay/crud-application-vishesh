DROP TABLE IF EXISTS transaction;
DROP TABLE IF EXISTS authority;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS user_authorities;

CREATE TABLE IF NOT EXISTS authority(
    id VARCHAR(36) NOT NULL,
    name VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
);

create table transaction (
   id varchar(36) not null,
    amount decimal(12,2),
    transaction_reference varchar(36),
    user_id varchar(36),
    primary key (id)
);

CREATE TABLE IF NOT EXISTS user(
    id VARCHAR(36) NOT NULL,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_authorities(
    user_id VARCHAR(36) NOT NULL,
    authorities_id VARCHAR(36) NOT NULL
);
