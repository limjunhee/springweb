-- day02/sample.sql에서 생성된 DB 이용 -> mydb0826
USE mydb0826;
drop table waiting;
CREATE TABLE waiting( 
    no int AUTO_INCREMENT , 
    phonenumber VARCHAR(255) ,
    count INT,
    constraint PRIMARY KEY( no ) 
);
insert into waiting( phonenumber , count )values( "010-1234-5678", 3 ),( "010-1111-2222", 3),("010-2222-3333",4); -- 샘플 데이터 3개 