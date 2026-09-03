DROP DATABASE IF EXISTS mydb0903;
CREATE DATABASE mydb0903;
use mydb0903;

create table test (
    no INT AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(255),
    writer VARCHAR(255)
)