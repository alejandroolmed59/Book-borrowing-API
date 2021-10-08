--SCRIPT JUST FOR H2 DATABASE: Local Spring Profile
--ROLE
INSERT INTO ROLE (ROLE_TYPE) VALUES ('Student'), ('Teacher'), ('Other');

INSERT INTO USER (USER_ID, NAME, EMAIL, PHONE_NUMBER, ROLE_ID) VALUES ('00097017','Alejandro Olmedo', '00097017@uca.edu.sv', '79325260', 1);
INSERT INTO USER (USER_ID, NAME, EMAIL, PHONE_NUMBER, ROLE_ID) VALUES ('00032104','Daniel Sosa', '00032104@uca.edu.sv', '74782679', 2);

INSERT INTO GENRE (GENRE_TYPE) VALUES ('Action and adventure'), ('Fantasy'), ('History'), ('Classic'), ('Mystery'), ('Science'), ('Horror'), ('Fiction');

INSERT INTO BOOK (BOOK_ISBN, TITLE, YEAR, AUTHOR, GENRE_ID) VALUES ('9783140464079', 'Le petit prince', '1943', 'Saint-Exupéry', 4);
INSERT INTO BOOK (BOOK_ISBN, TITLE, YEAR, AUTHOR, GENRE_ID) VALUES ('0192861891', 'Hyperspace', '1994', 'Michio Kaku', 6);
INSERT INTO BOOK (BOOK_ISBN, TITLE, YEAR, GENRE_ID) VALUES ('3124412342', 'Anonymous ', '1994', 5);