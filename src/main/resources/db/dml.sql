INSERT INTO role (name) VALUES ('admin');
INSERT INTO role (name) VALUES ('user');

INSERT INTO person (role_id, first_name, last_name, login, dob, password, email)
VALUES (1, 'Sophia', 'Denisovich', 'admin', '1987-05-28 00:00:00', 'admin', 'sophia@gmail.com');

INSERT INTO person (role_id, first_name, last_name, login, dob, password, email)
VALUES (2, 'Olesya', 'Petrova', 'user', '1987-01-04 00:00:00', 'user', 'olesya@gmail.com');