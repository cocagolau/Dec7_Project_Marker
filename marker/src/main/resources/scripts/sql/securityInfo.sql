INSERT INTO ROLES
(name)
VALUES
('USER'),
('ADMIN'),
('DBA');

INSERT INTO USERS
(email, name, password)
VALUES
('aaaa0000@gmail.com', 'aaaa', '12345678'),
('bbbb1111@gmail.com', 'bbbb', '12345678'),
('cccc2222@gmail.com', 'cccc', '12345678');

INSERT INTO USERS_ROLES
(user_id, role_id)
VALUES
(1, 1),
(2, 2),
(3, 3);