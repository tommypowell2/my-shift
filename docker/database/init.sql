CREATE TABLE authorities
(
    username VARCHAR(255),
    authority VARCHAR(255)
);
CREATE UNIQUE INDEX authorities_username_authority_key ON authorities (username, authority);

CREATE TABLE employee
(
    employeeid SERIAL PRIMARY KEY NOT NULL,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    username VARCHAR(255),
    position VARCHAR(255)
);
CREATE UNIQUE INDEX employee_username_key ON employee (username);

CREATE TABLE shift_user
(
    userid INTEGER PRIMARY KEY NOT NULL,
    username VARCHAR(255),
    password VARCHAR(8)
);
CREATE TABLE user_role
(
    app_user_id INTEGER NOT NULL,
    role VARCHAR(40)
);
CREATE TABLE users
(
    id SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(255),
    password VARCHAR(255),
    enabled BOOLEAN
);
insert into authorities(username,authority) values('tpowell','admin');
insert into users(username,password,enabled) values('tpowell','$2a$10$iaQgRZJnKV/.i8.wZfOjkeIviNZ1.LpBrUT2zJTV9CVEZ9vg186kq',true);
-- password = test
