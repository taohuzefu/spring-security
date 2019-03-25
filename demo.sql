CREATE DATABASE demo;

CREATE TABLE agent (
  id BIGSERIAL UNIQUE PRIMARY KEY,
  username varchar(255)  DEFAULT NULL,
  password varchar(255)  DEFAULT NULL ,
  nickname varchar(255)  DEFAULT '' ,
  roles varchar(255) DEFAULT NULL
);


INSERT INTO `user` VALUES ('1', 'anoy', 'pwd', 'anoy', 'ROLE_USER'), ('2', 'admin', 'pwd', 'admin', 'ROLE_USER,ROLE_ADMIN');