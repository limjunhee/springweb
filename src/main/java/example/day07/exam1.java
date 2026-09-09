package example.day07;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class exam1 {
    public static void main(String[] args) {
        // 서로 다른 메소드의 메소드 호출 방법
        // 메소드란? 상호작용 ( 2개 이상의 개체 주고 (인수M)받는 (리턴1) )
        // 1. 인스턴스 생성하여 메소드 호출
        TestService testService = new TestService();
        int result = testService.plus(3, 5);
        System.out.println(result);
        
        // 2. 싱글톤
        // TestService testService2 = TestService.getInstance();
        // int result2 = testService2.plus(3, 2);
        
        // 3. 메소드가 static이면?
        int result2 = testService.plus2(10, 5);
        
        
        // 4. 스프링 방식  
        // @Service(자동 인스턴스 생성), @Autowired(인스턴스 호출)
        // // @Autowired private TestService testService;
        // int result4 = testService.plus(10, 5);

        // 인스턴스(주체p) vs static(주체x)
        TestService t1 = new TestService();
        t1.달리기();
        TestService t2 = new TestService();
        t2.달리기();

        // TestService.달리기2();

        // 5. 사칙연산
        int x = 10 + 2 + 5 ; // 17

        TestService t3 = new TestService();
        t3.개별호출().밥먹기();
    }
}

@Component  
class TestService{
    // private TestService(){}
    // private static final TestService instance = new TestService();
    // public  static TestService.getInstance() { return  instance }

    int plus( int x, int y ){
        return x + y;
    }

    static int plus2(int x, int y){return  x + y;}

    void 달리기(){
        System.out.println(this);
    }

    // static void 달리기2(){
    //     System.out.println(this); // Cannot use this in a static context
    // }

    List<Student> list = new ArrayList<>();
    TestService(){
        list.add( new Student("강호동"));
        list.add(new Student("신동엽"));
    }
    Student 개별호출(){ return list.get(1);}
}

class Student {
    String name;

    void 밥먹기() {
        System.out.println(this.name + " 밥 먹음");
    }

    Student (String name) { this.name = name; }
}
