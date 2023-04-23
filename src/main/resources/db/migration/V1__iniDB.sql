CREATE TABLE refreshtoken
  (
     id          INT8 NOT NULL,
     expiry_date TIMESTAMP NOT NULL,
     token       VARCHAR(255) NOT NULL,
     user_id     INT8,
     PRIMARY KEY (id)
  );

CREATE TABLE roles
  (
     id   BIGSERIAL NOT NULL,
     NAME VARCHAR(255),
     PRIMARY KEY (id)
  );

CREATE TABLE teams
  (
     id           BIGSERIAL NOT NULL,
     created_date TIMESTAMP,
     delete_date  TIMESTAMP,
     updated_date TIMESTAMP,
     unit_name    VARCHAR(255),
     unit_head_id INT8,
     PRIMARY KEY (id)
  );

CREATE TABLE teams_users
  (
     team_id  INT8 NOT NULL,
     users_id INT8 NOT NULL
  );

CREATE TABLE users
  (
     id           BIGSERIAL NOT NULL,
     created_date TIMESTAMP,
     delete_date  TIMESTAMP,
     updated_date TIMESTAMP,
     email        VARCHAR(255),
     first_name   VARCHAR(255),
     password     VARCHAR(255),
     second_name  VARCHAR(255),
     username     VARCHAR(255),
     team_id      INT8,
     PRIMARY KEY (id)
  );

CREATE TABLE users_roles
  (
     user_id  INT8 NOT NULL,
     roles_id INT8 NOT NULL,
     PRIMARY KEY (user_id, roles_id)
  ) 