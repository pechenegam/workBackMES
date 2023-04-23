INSERT INTO users(id,created_date, updated_date, email, first_name , username,password, second_name)
values(111111111,'2020-01-01','2020-01-01','ivan1@yande.by' ,'Иван',
'User1','$2a$10$F54u0Dsy7nP0fGNXXb7yw.bscVuKs9kJxeddA1wkNGBRZKGrK3tEq', 'Моисеенко');

INSERT INTO users(id,created_date, updated_date, email, first_name , username,password, second_name)
values(111111112,'2020-01-01','2020-01-01','Oleg2@yande.by' ,'Олег',
'User2','$2a$10$F54u0Dsy7nP0fGNXXb7yw.bscVuKs9kJxeddA1wkNGBRZKGrK3tEq', 'Шумилин');

INSERT INTO roles (id,name)
VALUES (1,'ROLE_ADMIN');

INSERT INTO roles (id,name)
VALUES (2,'ROLE_USER');

-- user roles
INSERT INTO users_roles (user_id, roles_id)
VALUES (111111112, 2);

INSERT INTO users_roles (user_id, roles_id)
VALUES (111111111, 1);

INSERT INTO teams(id,created_date, updated_date, unit_name, unit_head_id)
values(1011111,'2020-01-01','2020-01-01','unitName1', 111111111);

INSERT INTO teams(id,created_date, updated_date, unit_name, unit_head_id)
values(1011112,'2020-01-01','2020-01-01','unitName2', 111111112);

INSERT INTO teams_users  (users_id, team_id)
VALUES (111111112, 1011112);

INSERT INTO teams_users (users_id, team_id)
VALUES (111111111, 1011111);
