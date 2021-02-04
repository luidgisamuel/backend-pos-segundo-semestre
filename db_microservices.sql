CREATE DATABASE microservices;

USE microservices;

CREATE TABLE institution (
	cnpj CHAR(18) NOT NULL,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL,
    telephone CHAR(14) NOT NULL,
    PRIMARY KEY (cnpj)
);

CREATE TABLE user (
	id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(60) NOT NULL UNIQUE,
    password CHAR(60) NOT NULL,
    type VARCHAR(15) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE administrator (
	cpf CHAR(14) NOT NULL,
    name VARCHAR(50) NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (cpf),
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE professor (
	cpf CHAR(14) NOT NULL,
    name VARCHAR(50) NOT NULL,
    education VARCHAR(50) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    cellphone CHAR(15) NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (cpf),
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE student (
	cpf CHAR(14) NOT NULL,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL,
    cellphone CHAR(15) NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (cpf),
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE course (
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    time_load INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE student_course (
	student_cpf CHAR(14) NOT NULL,
    course_id INT NOT NULL,
    FOREIGN KEY (student_cpf) REFERENCES student (cpf) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE subject (
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    time_load INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE course_subject (
	course_id INT NOT NULL,
    subject_id INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subject (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE grade (
	id INT NOT NULL AUTO_INCREMENT,
    value DECIMAL(5, 2) NOT NULL,
    description VARCHAR(100) NOT NULL,    
    PRIMARY KEY (id)   
);


CREATE TABLE grade_student (
	grade_id INT NOT NULL,
   student_cpf CHAR(14) NOT NULL,
    FOREIGN KEY (grade_id) REFERENCES grade (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (student_cpf) REFERENCES student (cpf) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE grade_subject (
	grade_id INT NOT NULL,
     subject_id INT NOT NULL,
     FOREIGN KEY (grade_id) REFERENCES grade (id) ON DELETE CASCADE ON UPDATE CASCADE,
     FOREIGN KEY (subject_id) REFERENCES subject (id) ON DELETE CASCADE ON UPDATE CASCADE
);