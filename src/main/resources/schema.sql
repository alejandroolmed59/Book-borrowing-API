--SCRIPT JUST FOR H2 DATABASE: Local Spring Profile

DROP TABLE IF EXISTS ROLE;
CREATE TABLE ROLE (
  ROLE_ID int(11) NOT NULL AUTO_INCREMENT,
  ROLE_TYPE varchar(45) NOT NULL,
  PRIMARY KEY (ROLE_ID)
);


DROP TABLE IF EXISTS GENRE;
CREATE TABLE GENRE (
  GENRE_ID int(11) NOT NULL AUTO_INCREMENT,
  GENRE_TYPE varchar(45) NOT NULL,
  PRIMARY KEY (GENRE_ID)
);

DROP TABLE IF EXISTS BOOK;
CREATE TABLE BOOK (
  BOOK_ISBN varchar(13) NOT NULL,
  TITLE varchar(50) NOT NULL,
  YEAR varchar(4) NOT NULL,
  AUTHOR varchar(50) DEFAULT 'UNKNOW',
  AVAILABLE boolean NOT NULL DEFAULT true,
  GENRE_ID int(11) NOT NULL,
  PRIMARY KEY (BOOK_ISBN),
  KEY GENRE_ID_idx (GENRE_ID),
  CONSTRAINT GENRE_ID FOREIGN KEY (GENRE_ID) REFERENCES GENRE (GENRE_ID) ON DELETE NO ACTION ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS USER;
CREATE TABLE USER (
  USER_ID varchar(11) NOT NULL,
  NAME varchar(45) NOT NULL,
  EMAIL varchar(45) NOT NULL,
  PHONE_NUMBER varchar(12) DEFAULT NULL,
  BORROWED_BOOKS int DEFAULT 0,
  ROLE_ID int(11) NOT NULL,
  PRIMARY KEY (USER_ID),
  KEY ROLE_ID_idx (ROLE_ID),
  CONSTRAINT ROLE_ID FOREIGN KEY (ROLE_ID) REFERENCES ROLE (ROLE_ID) ON DELETE NO ACTION ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS BORROWING;
CREATE TABLE BORROWING(
  ORDER_ID int(11) NOT NULL AUTO_INCREMENT,
  USER_ID varchar(11) NOT NULL,
  BOOK_ISBN varchar(13) NOT NULL,
  BORROW_DATE date NOT NULL,
  DUE_DATE date NOT NULL,
  REAL_RETURN_DATE date,
  RENEWAL_FOIS int(11) NOT NULL DEFAULT 0,
  PENALIZATION double NOT NULL DEFAULT 0,
  DELIVERED boolean NOT NULL DEFAULT false,
  PRIMARY KEY (ORDER_ID),
  KEY USER_ID_idx (USER_ID),
  KEY BOOK_ID_idx (BOOK_ISBN),
  CONSTRAINT USER_ID FOREIGN KEY (USER_ID) REFERENCES USER (USER_ID) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT BOOK_ISBN FOREIGN KEY (BOOK_ISBN) REFERENCES BOOK (BOOK_ISBN) ON DELETE NO ACTION ON UPDATE NO ACTION
);