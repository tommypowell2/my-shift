drop table company if EXISTS;
CREATE TABLE company
(
    id INTEGER IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(255)
);

delete from company;


ALTER TABLE company ADD CONSTRAINT name_companyID UNIQUE (name);
-- CREATE UNIQUE INDEX users_companyID ON shift_user (username, companyid);
insert into company (name) VALUES ('My First Company');
insert into company (name) VALUES ('My Second Company');
