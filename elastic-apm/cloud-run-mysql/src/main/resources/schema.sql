DROP TABLE IF EXISTS attendance;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS courses;

CREATE TABLE students (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(255),
  last_name VARCHAR(255));


CREATE TABLE courses (
  course_name VARCHAR(255),
  course_code VARCHAR(4) PRIMARY KEY);


CREATE TABLE attendance (
  date_attended DATE NOT NULL,
  id INT,
  course_code VARCHAR(4),
  status ENUM('PRESENT','ABSENT'),
  FOREIGN KEY (id) REFERENCES students(id),
  FOREIGN KEY (course_code) REFERENCES courses(course_code));