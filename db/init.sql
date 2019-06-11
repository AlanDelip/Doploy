use doploy;

CREATE TABLE IF NOT EXISTS students (
  name VARCHAR(20),
  city VARCHAR(20)
);
INSERT INTO students
  (name, city)
VALUES
  ('John Doe', 'Los Angeles'),
  ('Denis', 'New York');

GRANT ALL PRIVILEGES ON doploy.* TO 'test'@'%';
FLUSH PRIVILEGES;