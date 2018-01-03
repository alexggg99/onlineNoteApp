DROP TABLE IF EXISTS users ;

CREATE TABLE IF NOT EXISTS users (
  id NUMBER NOT NULL AUTO_INCREMENT,
  username VARCHAR(64) NOT NULL,
  password VARCHAR(64) NOT NULL,
  fullname VARCHAR(75) NOT NULL,
  email VARCHAR(145) NULL,
  avatar VARCHAR(500) NULL,
  state INT NOT NULL,
  role INT NOT NULL,
  activation_code VARCHAR(52) NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX username_UNIQUE (username ASC)
);

CREATE TABLE IF NOT EXISTS notes (
  id NUMBER NOT NULL AUTO_INCREMENT,
  user_id NUMBER NOT NULL,
  note VARCHAR(1000) NULL,
  created_at timestamp not null,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id)
    REFERENCES users(id)
);