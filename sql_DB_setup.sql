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

CREATE SEQUENCE public.users_seq
    INCREMENT 1
    START 10
    MINVALUE 1
    MAXVALUE 99999999
    CACHE 1;

alter table users alter column id set default nextval('public.users_seq');

CREATE TABLE subjects(
   code  VARCHAR(20),
   name  VARCHAR(50),
   coordinator_id INT REFERENCES users(id) ,
   version INT,
   PRIMARY KEY (code)
);

CREATE TABLE exams(
   id INT,
   title VARCHAR(50),
   status INT,
   subject_code VARCHAR(20) REFERENCES subjects(code),
   version INT,   
   PRIMARY KEY (id)
);
CREATE SEQUENCE public.exams_seq
    INCREMENT 1
    START 100
    MINVALUE 100
    MAXVALUE 99999999
    CACHE 1;

alter table exams alter column id set default nextval('public.exams_seq');

CREATE TABLE questions(
    id INT,
    question_type INT,
    title VARCHAR(100),
    content VARCHAR(100),
    answer VARCHAR(100),
    mark INT,
    exam_id INT REFERENCES exams(id),
    
    modifiedTime timestamp,
    modifiedBy VARCHAR(100),
    version INT,
    
    PRIMARY KEY(id)
);
CREATE SEQUENCE public.questions_seq
    INCREMENT 1
    START 1000
    MINVALUE 1000
    MAXVALUE 99999999
    CACHE 1;

alter table questions alter column id set default nextval('public.questions_seq');

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
  exam_id INT REFERENCES exams (id) ON UPDATE CASCADE, 
  answer VARCHAR(20),
  mark INT,
  status     INT, 
  CONSTRAINT user_question_pkey PRIMARY KEY (user_id, question_id)  -- explicit pk
);


INSERT INTO users
VALUES (1, 'Admin', '','123',1);
--INSERT INTO users
--VALUES (001, 'Instructor', '','Instructor',1);
INSERT INTO users
VALUES (2, 'Tutor', 'Tutor@gmail.com','123',2);

INSERT INTO users
VALUES (4, 'Instructor2', 'Tutor@gmail.com','123',2);

INSERT INTO users
VALUES (3, 'Student', '','123',3);

INSERT INTO subjects
VALUES ('SWEN90007','SDA',002);

INSERT INTO subjects
VALUES ('SWEN90013','HIS',002);

--INSERT INTO subjects
--VALUES ('SWEN90013','HIS',004);

INSERT INTO users_subjects 
VALUES (003,'SWEN90013',0);

--INSERT INTO users_subjects 
--VALUES (004,'SWEN90013',0);






--Select * FROM users WHERE name = 'test' AND password = 'test';