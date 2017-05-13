drop table authorities if EXISTS;
CREATE TABLE authorities
(
    username VARCHAR(255),
    authority VARCHAR(255)
);
CREATE UNIQUE INDEX authorities_username_authority_key ON authorities (username, authority);

insert into shift_user (username, password,companyid, enabled) VALUES ('username', 'sasa', 1, TRUE );
--CREATE UNIQUE INDEX users_companyID ON shift_user (username, companyid);
drop table company if EXISTS;
CREATE TABLE company
(
    id INTEGER IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(255)
);