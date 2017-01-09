CREATE TABLE authorities
(
    username VARCHAR(255),
    authority VARCHAR(255)
);
CREATE UNIQUE INDEX authorities_username_authority_key ON authorities (username, authority);
insert into authorities(username,authority) values('tpowell','admin');

CREATE TABLE employee
(
    employeeid SERIAL PRIMARY KEY NOT NULL,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    username VARCHAR(255),
    position VARCHAR(255),
    companyID INTEGER
);
CREATE UNIQUE INDEX employee_username_key ON employee (username);

CREATE TABLE shift_user
(
    userid SERIAL PRIMARY KEY NOT NULL,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(255),
    companyid INTEGER,
    enabled BOOLEAN
);
CREATE TABLE user_role
(
    app_user_id INTEGER NOT NULL,
    role VARCHAR(40)
);

-- CREATE TABLE shift_admin_users
-- (
--     id SERIAL PRIMARY KEY NOT NULL,
--     username VARCHAR(255),
--     password VARCHAR(255),
--     companyid INTEGER,
--     enabled BOOLEAN
-- );
-- CREATE UNIQUE INDEX users_companyID ON shift_admin_users (username, companyid);

CREATE TABLE company
(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255)
);
ALTER TABLE company ADD CONSTRAINT name_companyID UNIQUE (name);

insert into authorities(username,authority) values('tpowell','admin');
insert into shift_user(username,password,companyid, enabled) values('tpowell','$2a$10$iaQgRZJnKV/.i8.wZfOjkeIviNZ1.LpBrUT2zJTV9CVEZ9vg186kq',1,true);
INSERT INTO company (name) VALUES ('powellfull');

create TABLE
    employee_shift(
    id SERIAL PRIMARY KEY,
    work_day DATE,
    start_time TIME,
    end_time TIME,
    employeeID INTEGER
);
