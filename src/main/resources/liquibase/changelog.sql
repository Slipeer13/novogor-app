--liquibase formatted sql

--changeset author:Popov Sergey

CREATE TABLE id_members (
id BIGINT NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE station (
station_id BIGSERIAL PRIMARY KEY,
name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE status (
status_id BIGSERIAL PRIMARY KEY,
name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE pump (
pump_id BIGSERIAL PRIMARY KEY,
name VARCHAR(255) NOT NULL,
note VARCHAR(512) NOT NULL,
date_vibro_diagnostic DATE NOT NULL,
station_id BIGINT,
status_id BIGINT,
CONSTRAINT fk_station FOREIGN KEY (station_id) REFERENCES station(station_id),
CONSTRAINT fk_status FOREIGN KEY (status_id) REFERENCES status(status_id)
);


