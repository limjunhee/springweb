# day05 / TestEntity sample
# SQL에서는 카멜 표기법 대신 대소문자 앞에 언더바 붙여서 구분하자
insert into test( name, descri, price , create_date, update_date )
    VALUE('코카콜라', '맛있다', 1000 , NOW(), now()),
        ( '사이다', '얘도 맛있다', 1500, NOW(), now() ),
        ( '환타', '얘도 역시 맛있다', 1600, NOW(), now() );