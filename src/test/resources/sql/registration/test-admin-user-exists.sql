-- alter TABLE shift_user ADD COLUMN firstname VARCHAR(255);
-- alter TABLE shift_user ADD COLUMN lastname VARCHAR(255);
-- alter TABLE shift_user ADD COLUMN companyid INTEGER;
-- alter TABLE shift_user ADD COLUMN enabled BOOLEAN;
-- alter TABLE shift_user ADD COLUMN position VARCHAR(255);
INSERT INTO shift_user (username, password, companyid, enabled) VALUES ('tuser', 'xyz', 0, TRUE);