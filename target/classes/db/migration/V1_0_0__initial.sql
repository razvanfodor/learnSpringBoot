CREATE TABLE discount(
  id IDENTITY NOT NULL PRIMARY KEY ,
  name VARCHAR(50) NOT NULL ,
  CREATION_DATE DATE NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS DISCOUNT_SEQ START WITH 100
 INCREMENT BY 10;