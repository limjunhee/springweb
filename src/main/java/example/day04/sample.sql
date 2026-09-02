drop DATABASE if EXISTS mydb0902;
create database mydb0902;

use mydb0902;

create table exam(
    eno INT AUTO_INCREMENT PRIMARY KEY,
    ename VARCHAR(255)
)

INSERT INTO exam (ename) VALUES ("유재석");
INSERT INTO exam (ename) VALUES ("강호동");
INSERT INTO exam (ename) VALUES ("신동엽");