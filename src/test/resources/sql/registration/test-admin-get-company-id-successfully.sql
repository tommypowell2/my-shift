-- CREATE TABLE authorities
-- (
--     username VARCHAR(255),
--     authority VARCHAR(255)
-- );
CREATE UNIQUE INDEX authorities_username_authority_key ON authorities (username, authority);

-- DROP TABLE USERS IF EXISTS;

-- CREATE TABLE shift_admin_users
-- (
--     id INTEGER IDENTITY PRIMARY KEY NOT NULL,
--     username VARCHAR(255),
--     password VARCHAR(255),
--     companyid INTEGER,
--     enabled BOOLEAN
-- );
insert into shift_admin_users (username, password,companyid, enabled) VALUES ('username', 'sasa', 1, TRUE );
CREATE UNIQUE INDEX users_companyID ON shift_admin_users (username, companyid);

CREATE TABLE company
(
    id INTEGER IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(255)
);