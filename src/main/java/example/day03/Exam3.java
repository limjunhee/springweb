package example.day03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

public class Exam3 {
    public static void main(String[] args) {
        
    }
}

// [1] 전통 방식 객체 생성, 계속적인 dao인스턴스 생성 가능
class SampleDao {
    void method(){
        System.out.println("메소드실행");
    }
}
class SampleController1 {
    void method(){
        // 1. 다른 클래스 내 메소드 호출하기
        SampleDao sampleDao = new SampleDao(); //인스턴스(주체) 생성
        sampleDao.method(); // 해당 인스턴스(주체)가 메소드를 호출
    }
}

// [2] 전통 방식의 싱글톤 생성, dao 인스턴스 생성 불가능
class SampleDao2 { 
    private SampleDao2(){}
    private static final SampleDao2 instance = new SampleDao2();
    public static SampleDao2 getInstance() { return instance; }
    public void method(){};
}
class SampleController2 {
    SampleDao2 dao2 = SampleDao2.getInstance();
    void method() {
        // 2. 다른 클래스 내 메소드 호출하는 방법
        dao2.method();
    }
}

// [3] 스프링 IOC/DI
@Component
class SampleDao3{
    // 싱글톤 생략 -> @Component 를 이용하여 IOC(자동 객체 관리) 규칙으로 스프링 컨테이너에 자동 빈(객체) 등록
    void method(){}
}
class SampleController3{
    @Autowired // 1. 스프링 컨테이너(메모리)에 등록된 빈(객체) 가져와서 대입
    SampleDao3 dao3;
    void method(){

    }
}
class SampleController4{
    
    // 2. 스프링 권장 방식
    private final SampleDao3 dao3;
    @Autowired
    public SampleController4(SampleDao3 dao3){
        this.dao3 = dao3;
    }
    void method(){
        dao3.method();
    }
}
@RequiredArgsConstructor // final 멤버변수 생성자를 자동 생성
class SampleController5{
    // 3. 롬복 이용한 방식, final 이후 수정 불가능
    private final SampleDao3 dao3;
}



/*
    즉, 다른 클래스의 메소드를 호출하기 위해서는 객체가 필요하다.
    1. [전통] new 클래스명().메소드명();
    2. [미니프로젝트2] 클래스명().getInstance().메소드명();
    3. [스프링] @Conponent 등록, @AutoWired 호출
    -------------------------
    @Component 등록하는 시점 : @SpringBootApplication( @ComponentScan )
    즉, 스프링이 켜지면서 같은 폴더/하위 폴더 내 @Component 다 찾아내서 스프링 컨테이너 등록 구조
    AppStart 주의할 점: 최상위에 위치한다
*/