CREATE TABLE users(
  id       INT,
  name     VARCHAR(50),
  email    VARCHAR(100),
  PRIMARY  KEY(id)
);

CREATE TABLE subjects(
   code   VARCHAR(20),
   name  VARCHAR(50),
   coordinator VARCHAR(50),
   PRIMARY KEY (code)
);



--INSERT INTO users
--VALUES (001, 'Tom', 'tom@gmail.com');
