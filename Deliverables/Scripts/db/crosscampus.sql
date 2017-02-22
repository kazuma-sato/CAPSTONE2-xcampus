/*

# Remove "/*" above if database already exists

USE  crosscampus;

DROP TIGGER IF EXISTS user_before_insert;
DROP TIGGER IF EXISTS file_before_insert;
DROP TIGGER IF EXISTS entry_before_insert;
DROP TIGGER IF EXISTS entry_before_update;

DROP TABLE IF EXISTS favourite;
DROP TABLE IF EXISTS notification;
DROP TABLE IF EXISTS rating;
DROP TABLE IF EXISTS file;
DROP TABLE IF EXISTS entry;
DROP TABLE IF EXISTS entryType;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS student_program;
DROP TABLE IF EXISTS program;
DROP TABLE IF EXISTS institution;
DROP TABLE IF EXISTS users;

DROP DATABASE IF EXISTS crosscampus;
*/

CREATE DATABASE IF NOT EXISTS crosscampus;
USE crosscampus;
CREATE USER IF NOT EXISTS 
xcampus;
GRANT all ON crosscampus.* TO 'xcampus'@'localhost' IDENTIFIED BY 'GBCxcamp'; 

CREATE TABLE users
( 
  id int(16) NOT NULL AUTO_INCREMENT,
  username varchar(128) NOT NULL,
  password varchar(256),
  firstname varchar(32) NOT NULL,
  lastname varchar(32) NOT NULL,
  email varchar(128) NOT NULL,
  dateCreated timestamp DEFAULT CURRENT_TIMESTAMP,
  google varchar(256),
  twitter varchar(256),
  facebook varchar(256),

  CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE institution
(
	id int(16) NOT NULL AUTO_INCREMENT,
	name varchar(256) NOT NULL,
	domain varchar(256) NOT NULL,

	CONSTRAINT pk_institution PRIMARY KEY (id)
);

CREATE TABLE program
(
	id varchar(16) NOT NULL,
	startSemester date NOT NULL, #needs revision
	institution int(16) NOT NULL,

	CONSTRAINT pk_program PRIMARY KEY (id, startSemester, institution)
);

CREATE TABLE student_program
(
	studentID int(16) NOT NULL,
	programID varchar(16) NOT NULL,
	startSemester date NOT NULL,  
	institution int(16) NOT NULL,

	CONSTRAINT pk_student_program PRIMARY KEY (studentID, programID, startSemester, institution),
	CONSTRAINT fk_student_program_users FOREIGN KEY (studentID) REFERENCES users (id),
	CONSTRAINT fk_student_program_program FOREIGN KEY (programID, startSemester, institution) 
		REFERENCES program (id, startSemester, institution)
);

CREATE TABLE course
(
	id varchar(16) NOT NULL,
	name varchar(255),
	description varchar(1024),
	programCode varchar(16) NOT NULL,
	institution int(16) NOT NULL,
	startSemester date NOT NULL, 

	CONSTRAINT pk_course PRIMARY KEY (id, programCode, institution, startSemester),
	CONSTRAINT fk_course_programCode FOREIGN KEY (programCode, startSemester, institution) 
		REFERENCES program(id, startSemester, institution)
);

CREATE TABLE entryType
(
	id int(16) NOT NULL AUTO_INCREMENT,
	name varchar(32) NOT NULL,

	CONSTRAINT pk_entryType PRIMARY KEY (id)
);

CREATE TABLE entry
(
  id int(16) NOT NULL AUTO_INCREMENT,
  author int(16) NOT NULL,
  parentID int(16) NULL,
  name varchar(128) NOT NULL,
  entryType int(16) NOT NULL,
  description varchar(1024) NOT NULL,
  dateCreated timestamp DEFAULT CURRENT_TIMESTAMP,
  dateModified timestamp NULL ON UPDATE CURRENT_TIMESTAMP,
  courseID varchar(16) NOT NULL,
  programCode varchar(16) NOT NULL,
  institution int(16) NOT NULL,
  startSemester date NOT NULL,
  flaggedBy int(16),
  dateFlagged date,

  CONSTRAINT pk_entry PRIMARY KEY (id),
  CONSTRAINT fk_entry_parentID FOREIGN KEY (parentID) REFERENCES entry(id),
  CONSTRAINT fk_entry_user FOREIGN KEY (author) REFERENCES users (id),
  CONSTRAINT fk_entry_type FOREIGN KEY (entryType) REFERENCES entryType (id),
  CONSTRAINT fk_entry_course FOREIGN KEY (courseID, programCode, institution, startSemester)
  	REFERENCES course(id, programCode, institution, startSemester),
  CONSTRAINT fk_entry_flaggedBy FOREIGN KEY (flaggedBy) REFERENCES users (id)
);
DELIMITER //

CREATE TRIGGER entry_before_update
BEFORE UPDATE
	ON entry FOR EACH ROW
		IF NEW.flaggedBy<=>OLD.flaggedBy THEN
			SET NEW.dateFlagged = CURRENT_TIMESTAMP();
		END IF;//

DELIMITER ;

CREATE TABLE file
(
	id int(16) NOT NULL AUTO_INCREMENT,
	fileName varchar(255) NOT NULL,
	dateAdded timestamp DEFAULT CURRENT_TIMESTAMP,
	dateArchived date,
	approvedBy int(16),
	directoryLocation varchar(1024) NOT NULL,
	entryID int(16) NOT NULL,

	CONSTRAINT pk_file PRIMARY KEY (id),
	CONSTRAINT fk_file_entry FOREIGN KEY (entryID) REFERENCES entry(id)
);
DELIMITER //

CREATE TRIGGER file_before_update
BEFORE UPDATE
		ON file FOR EACH ROW
		IF NEW.approvedBy<=>OLD.approvedBy THEN
			SET NEW.dateArchived = CURRENT_TIMESTAMP();
		END IF;//

DELIMITER ;

CREATE TABLE rating
(
	entryID int(16) NOT NULL,
	userID int(16) NOT NULL,

	CONSTRAINT pk_rating PRIMARY KEY (entryID, userID),
	CONSTRAINT fk_rating_entry FOREIGN KEY (entryID) REFERENCES entry(id),
	CONSTRAINT fk_rating_user FOREIGN KEY (userID) REFERENCES users(id)
);

CREATE TABLE notification
(
	entryID int(16) NOT NULL,
	userID int(16) NOT NULL,

	CONSTRAINT pk_notification PRIMARY KEY (entryID, userID),
	CONSTRAINT fk_notification_entry FOREIGN KEY (entryID) REFERENCES entry(id),
	CONSTRAINT fk_notification_user FOREIGN KEY (userID) REFERENCES users(id)
);

CREATE TABLE favourite
(
	entryID int(16) NOT NULL,
	userID int(16) NOT NULL,

	CONSTRAINT pk_favourite PRIMARY KEY (entryID, userID),
	CONSTRAINT fk_favourite_entry FOREIGN KEY (entryID) REFERENCES entry(id),
	CONSTRAINT fk_favourite_user FOREIGN KEY (userID) REFERENCES users(id)
);