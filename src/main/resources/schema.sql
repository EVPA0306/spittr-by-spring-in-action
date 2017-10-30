DROP TABLE IF EXISTS spitter;
CREATE TABLE spitter (id BIGINT PRIMARY KEY , username VARCHAR(255), firstname VARCHAR(255)
, lastname VARCHAR(255), password VARCHAR(255));

DROP TABLE IF EXISTS spittle;
CREATE TABLE spittle (id BIGINT PRIMARY KEY , message VARCHAR(255), time TIMESTAMP
  , latitude DECIMAL, longitude DECIMAL);