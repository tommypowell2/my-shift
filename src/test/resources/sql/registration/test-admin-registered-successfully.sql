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

-- drop table shift_admin_users if EXISTS;
-- CREATE TABLE shift_admin_users
-- (
--     id INTEGER IDENTITY PRIMARY KEY NOT NULL,
--     username VARCHAR(255),
--     password VARCHAR(255),
--     companyid INTEGER,
--     enabled BOOLEAN
-- );
CREATE UNIQUE INDEX users_companyID ON shift_admin_users (username, companyid);
-- ALTER TABLE shift_admin_users ADD CONSTRAINT IF NOT EXIST comeon UNIQUE (username, companyid);