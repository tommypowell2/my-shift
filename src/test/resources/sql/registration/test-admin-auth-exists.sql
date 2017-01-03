

insert into authorities(username, authority) VALUES ('tuser', 'admin');

-- CREATE TABLE shift_admin_users
-- (
--     id INTEGER IDENTITY PRIMARY KEY NOT NULL,
--     username VARCHAR(255),
--     password VARCHAR(255),
--     companyid INTEGER,
--     enabled BOOLEAN
-- );

drop table company if EXISTS;
CREATE TABLE company
(
  id INTEGER IDENTITY PRIMARY KEY NOT NULL,
  name VARCHAR(255)
);