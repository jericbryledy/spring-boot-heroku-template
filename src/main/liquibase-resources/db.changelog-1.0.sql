--liquibase formatted sql

--changeset jeric:1
--comment: create user table
CREATE TABLE user_account (
	id SERIAL PRIMARY KEY ,
	username character varying(45) UNIQUE NOT NULL,
	password character varying(45) NOT NULL,
	first_name character varying(45) NOT NULL,
	last_name character varying(45) NOT NULL,
	created_timestamp timestamp without time zone NOT NULL
);
