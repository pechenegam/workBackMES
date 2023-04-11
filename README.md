
## To start app
**Run this in the project**
```
docker-compose up
```
This will launch the database in PostgreSQL
____
After that, run
```
DemoApplication.java
```
____
## Users credentials in migration 
| User | Password |
|:----------------|:----------------|
| User1 | 41241241 |
| User2 | 41241241 |

МИГРАЦИЯ РУЧНАЯ

INSERT INTO users(id,created_date, updated_date, email, first_name , username,password, second_name)
values(1,'2020-01-01','2020-01-01','ivan1@yande.by' ,'firstName1',
'User1','$2a$10$F54u0Dsy7nP0fGNXXb7yw.bscVuKs9kJxeddA1wkNGBRZKGrK3tEq', 'secondName1');

INSERT INTO users(id,created_date, updated_date, email, first_name , username,password, second_name)
values(2,'2020-01-01','2020-01-01','ivan2@yande.by' ,'firstName2',
'User2','$2a$10$F54u0Dsy7nP0fGNXXb7yw.bscVuKs9kJxeddA1wkNGBRZKGrK3tEq', 'secondName2');

INSERT INTO roles (id,name)
VALUES (1,'ROLE_ADMIN');

INSERT INTO roles (id,name)
VALUES (2,'ROLE_USER');

-- user roles
INSERT INTO users_roles (user_id, roles_id)
VALUES (2, 2);

INSERT INTO users_roles (user_id, roles_id)
VALUES (1, 1);

INSERT INTO teams(id,created_date, updated_date, unit_name, unit_head_id)
values(1,'2020-01-01','2020-01-01','unitName1', 1);

INSERT INTO teams(id,created_date, updated_date, unit_name, unit_head_id)
values(2,'2020-01-01','2020-01-01','unitName2', 2);