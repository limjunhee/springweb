use mydb0902;

create table test (
    no INT AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(255),
    writer VARCHAR(255)
)

INSERT INTO test (content, writer) VALUES ("안녕하세요", "유재석");
INSERT INTO test (content, writer) VALUES ("안녕하세요2", "강호동");
INSERT INTO test (content, writer) VALUES ("안녕하세요3", "신동엽");