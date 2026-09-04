package example.day06;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

public class exam {
    public static void main(String[] args) {
        // [1] 리터럴/상수 -> 기본값
        int a = 3; // a 변수가 3을 참조한다.
        int b = 3; // b 변수가 3을 참조한다 -----------------> a,b의 참조 값은 총 1개임
        

        // [2] 참조란? 어떠한 값의 위치, 인스턴스(객체)는 new 한 개당 참조값 1개
        String c = new String("유재석");
        String d = new String("강호동"); // -----------------> c,d의 참조 값은 총 2개임

        Test t = new Test();
        t.name = new String("유재석"); // t 변수가 참조하는 값은 총 1개 (1개 안에 또 다른 1개 들어가있는 형태)

        // 자바 참조
        // 1) [자유] 카테고리 등록
        Category c1 = new Category(1, "자유", new ArrayList<>()); // 1번으로 [자유] 카테고리를 등록
        // 2) [자유] 카테고리를 가진 게시물 작성하기
        Board b1 = new Board(1, "제목1", c1);

            // c1은 몇개를 참조하는가? 1개 , c1 -> category(101번지) 
            // b1은 몇개를 참조하는가? 1개 , b1 -> Board(101번지) -> Category(201번지)
            // String과 기본 타입은 제외하고

            // b1을 통해 c1을 알 수 있을까(참조할 수 있을까)? : 가능함 -> Board에 카테고리 들어있으니까 ㅇㅇ
            // jpa 개발자는 이 구조를 db의 join처럼 사용 가능할 것이라고 생각함(아마도) - 단방향 참조

            // c1을 통해 b1을 참조할 수 있나? : 불가능 -> 카테고리엔 Board가 없으니까 ㅇㅇ
            // 그래서 -> JPA 개발자는 이 구조를 생각하여, 양방향 참조 방식을 개발함

        // 3) category에 게시물1 넣기
        c1.getList().add(b1);
        // c1 -> categoty -> list(board)
        // c1 통해 b1 참조할 수 있는가? : 가능함 -> 이제 카테고리에 Board가 있으니까 ㅇㅇ
        
        // 단, 이런 경우 순환 참조를 조심해야 함
        System.out.println(b1);
        // b1 -> c1 -> b1 -> c1 ->b1 -> c1 ->b1 -> c1 ->b1 -> c1 -> (무한반복)
        // 양방향쪽에 @ToString.Exclude 주입한다.
        // toString() : Object(슈퍼)클래스의 객체 주소값 반환 함수
        // DB가 단방향 참조하는 방법 - FK
        // DB가 양방향 참조하는 방법 - 없음 ㅅㄱ
        // * 오버라이딩: 객체 주소값 대신에 문자열로 반환 함수(주로)

        // 결론: jpa는 양방향 있고, db는 양방향 없다.
        // 실무에선 양방향 비권장, 불필요한 자료까지 불러오니까 ㅇㅇ
    }
}

@Data
@AllArgsConstructor
class Board{
    private int bno;
    private String btitle;
    private Category category; // 참조(FK)
}

@Data 
@AllArgsConstructor
class Category{ // 카테고리가 상위테이블일 때, 하나의 카테고리가 여러 개 Board 참조할 가능성 있음
    private int cno;
    private String cname;
    @ToString.Exclude // toString 사용 금지.
    private List<Board> list = new ArrayList<>();
}


class Test{
    String name;
}