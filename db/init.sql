CREATE DATABASE userdb;
use userdb;

CREATE TABLE students (
  name VARCHAR(20),
  city VARCHAR(20)
);

INSERT INTO students
  (name, city)
VALUES
  ('John Doe', 'Los Angeles'),
  ('Denis', 'New York');
