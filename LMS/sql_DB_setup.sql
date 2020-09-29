DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TABLE users(
  id       INT,
  name     VARCHAR(50),
  email    VARCHAR(100),
  password VARCHAR(50),
  user_type INT,
  PRIMARY  KEY(id)
);

CREATE TABLE subjects(
   code  VARCHAR(20),
   name  VARCHAR(50),
   coordinator_id INT REFERENCES users(id) ,
   PRIMARY KEY (code)
);

CREATE TABLE exams(
   id INT,
   title VARCHAR(50),
   status INT,
   subject_code VARCHAR(20) REFERENCES subjects(code),
   PRIMARY KEY (id)
);

CREATE TABLE questions(
    id INT,
    question_type INT,
    title VARCHAR(100),
    content VARCHAR(100),
    answer VARCHAR(100),
    mark INT,
    exam_id INT REFERENCES exams(id),
    PRIMARY KEY(id)
);

CREATE TABLE users_subjects (
  user_id    INT REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE, 
  subject_code   VARCHAR(20) REFERENCES subjects (code) ON UPDATE CASCADE, 
  status     INT, 
  CONSTRAINT user_subject_pkey PRIMARY KEY (user_id, subject_code)  -- explicit pk
);

CREATE TABLE users_questions (
  user_id    INT REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE, 
  question_id    INT REFERENCES questions (id) ON UPDATE CASCADE, 
  answer VARCHAR(20),
  mark INT,
  status     INT, 
  CONSTRAINT user_question_pkey PRIMARY KEY (user_id, question_id)  -- explicit pk
);



INSERT INTO users
VALUES (000, 'Admin', '','123',0);
--INSERT INTO users
--VALUES (001, 'Instructor', '','Instructor',1);
INSERT INTO users
VALUES (001, 'test', 'test@gmail.com','123',1);

INSERT INTO users
VALUES (002, 'Student', '','123',2);


INSERT INTO subjects
VALUES ('SWEN90007','SDA',001);

INSERT INTO users_subjects 
VALUES (002,'SWEN90007',0);

INSERT INTO subjects
VALUES ('SWEN90013','SDA',001);

INSERT INTO exams
VALUES(001,'exam1','0','SWEN90013');

INSERT INTO questions
VALUES(001,1,'Answer the fllowing questions','Are you ok?','',0,001);

--Select * FROM users WHERE name = 'test' AND password = 'test';