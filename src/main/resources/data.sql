--SCRIPT JUST FOR H2 DATABASE: Local Spring Profile
--ROLE
INSERT INTO ROLE (ROLE_TYPE) VALUES ('Student'), ('Teacher'), ('Other');

INSERT INTO USER (USER_ID, NAME, EMAIL, PHONE_NUMBER, ROLE_ID) VALUES ('00097017','Alejandro Olmedo', '00097017@uca.edu.sv', '79325260', 1);
INSERT INTO USER (USER_ID, NAME, EMAIL, PHONE_NUMBER, ROLE_ID) VALUES ('00032104','Daniel Sosa', '00032104@uca.edu.sv', '74782679', 2);

INSERT INTO GENRE (GENRE_TYPE) VALUES ('Action and adventure'), ('Fantasy'), ('History'), ('Classic'), ('Mystery'), ('Science'), ('Horror'), ('Fiction');