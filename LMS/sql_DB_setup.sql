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
CREATE TABLE users_exams (
  user_id    INT REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE, 
  exam_id    INT REFERENCES exams(id) ON UPDATE CASCADE, 
  mark       INT,
  status     INT,
  CONSTRAINT user_exam_pkey PRIMARY KEY (user_id, exam_id)  -- explicit pk
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
VALUES (001, 'Admin', '','123',1);
--INSERT INTO users
--VALUES (001, 'Instructor', '','Instructor',1);
INSERT INTO users
VALUES (002, 'Tutor', 'Tutor@gmail.com','123',2);

INSERT INTO users
VALUES (003, 'Student', '','123',3);

INSERT INTO subjects
VALUES ('SWEN90007','SDA',002);

INSERT INTO subjects
VALUES ('SWEN90013','HIS',002);

INSERT INTO users_subjects 
VALUES (003,'SWEN90013',0);





INSERT INTO exams
VALUES(1301,'exam1','0','SWEN90013');

INSERT INTO exams
VALUES(1302,'exam2','0','SWEN90013');

INSERT INTO questions
VALUES(130101,1,'Answer the fllowing questions','Are you ok?','',10,1301);
INSERT INTO questions
VALUES(130102,2,'How many apples do you have?','A.1#B.2#C.3#D.4','A',20,1301);
INSERT INTO questions
VALUES(130103,2,'Which one is not the offical expression?','A.Hello#B.Goodbye#C.See ya#D.see you','C',15,1301);

--Select * FROM users WHERE name = 'test' AND password = 'test';