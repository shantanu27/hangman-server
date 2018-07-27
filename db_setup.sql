CREATE DATABASE IF NOT EXISTS hangman DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE hangman;

DROP TABLE IF EXISTS phrases;

CREATE TABLE phrases (
  phrase_id MEDIUMINT NOT NULL AUTO_INCREMENT,
  phrase VARCHAR(100) NOT NULL,
  PRIMARY KEY(phrase_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci;

DROP TABLE IF EXISTS tokens;

CREATE TABLE tokens (
  token_id VARCHAR(20) NOT NULL,
  phrase_id MEDIUMINT NOT NULL,
  state VARCHAR(100) NOT NULL,
  PRIMARY KEY(token_id),
  CONSTRAINT phraseid_phrases_fk  FOREIGN KEY (phrase_id)  REFERENCES phrases (phrase_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci;
