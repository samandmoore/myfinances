DROP TABLE IF EXISTS users;
CREATE TABLE users (
   id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
   username VARCHAR(255) NOT NULL,
   hashedPassword VARCHAR(255) NOT NULL,
   emailAddress VARCHAR(255) NOT NULL,
   unconfirmedEmailAddress VARCHAR(255) NULL,
	createdAt TIMESTAMP DEFAULT 0,
	modifiedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   UNIQUE INDEX users_idx (username),
   UNIQUE INDEX users_email_idx (emailAddress)
) ENGINE = INNODB;