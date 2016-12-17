DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS users;
DROP DATABASE IF EXISTS COMP3095;

CREATE DATABASE IF NOT EXISTS COMP3095;
USE COMP3095;
GRANT all ON COMP3095.* TO 'root'@'localhost' IDENTIFIED BY 'admin'; 


CREATE TABLE users 
( 
	id int(11) AUTO_INCREMENT, 
	firstname varchar(255),
	lastname varchar(255),
	email varchar(255) NOT NULL, 
	phone varchar(10),
	year varchar(10),
	major varchar(10),
	username varchar(20) NOT NULL,
	password varchar(20) NOT NULL,

	CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE posts
(
	id int(11) AUTO_INCREMENT, 
	author_id int(11),
	date_created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	title varchar(255);
	content text,

	CONSTRAINT pk_posts PRIMARY KEY (id),
	CONSTRAINT fk_post_authid FOREIGN KEY (author_id) REFERENCES users (id)
);

CREATE TABLE comments
(
	id int(11) AUTO_INCREMENT,
	post_id int(11), 
	author_id int(11),
	date_created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	content text,

	CONSTRAINT pk_comments PRIMARY KEY (id),
	CONSTRAINT fk_comments_authid FOREIGN KEY (author_id) REFERENCES users (id),
	CONSTRAINT fk_comments_post FOREIGN KEY (post_id) REFERENCES posts (id)
);

INSERT INTO users (id, firstname, lastname, email, phone, year, major, username, password) VALUES
(99, NULL, NULL, 'admin@domain.ca', NULL, NULL, NULL, 'admin', 'admin');