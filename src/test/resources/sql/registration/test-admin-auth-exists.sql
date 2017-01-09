

insert into authorities(username, authority) VALUES ('tuser', 'admin');

drop table company if EXISTS;
CREATE TABLE company
(
  id INTEGER IDENTITY PRIMARY KEY NOT NULL,
  name VARCHAR(255)
);