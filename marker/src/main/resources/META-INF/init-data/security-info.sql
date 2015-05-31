INSERT INTO roles
(name)
VALUES
('ROLE_USER'),
('ROLE_ADMIN'),
('ROLE_DBA');

INSERT INTO users
(email, name, password)
VALUES
('aaaaaaaa@gmail.com', 'aaaa', '123'),
('bbbbbbbb@gmail.com', 'bbbb', '123'),
('cccccccc@gmail.com', 'cccc', '123');

INSERT INTO user_roles
(user_id, role_id)
VALUES
(1, 1),
(2, 2),
(3, 3);