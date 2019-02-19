
-- create DB and users:

CREATE DATABASE IF NOT EXISTS cities DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE USER 'cities'@'localhost' IDENTIFIED BY 'Aa123456';
GRANT ALL PRIVILEGES ON cities. * TO 'cities'@'localhost';
FLUSH PRIVILEGES;
