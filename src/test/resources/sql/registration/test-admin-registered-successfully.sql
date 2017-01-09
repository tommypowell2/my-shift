drop INDEX users_companyID IF EXISTS;
drop INDEX authorities_username_authority_key IF EXISTS;

drop table authorities if EXISTS;
CREATE TABLE authorities
(
    username VARCHAR(255),
    authority VARCHAR(255)
);
CREATE UNIQUE INDEX authorities_username_authority_key ON authorities (username, authority);

drop table company if EXISTS;
CREATE TABLE company
(
    id INTEGER IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(255)
);

ALTER TABLE company ADD CONSTRAINT name_companyID UNIQUE (name);
insert into company (name) VALUES ('My First Company');
CREATE UNIQUE INDEX users_companyID ON shift_user (username, companyid);