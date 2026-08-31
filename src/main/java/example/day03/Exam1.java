package example.day03;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class Exam1 {
    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        subClass.method1(); // - 부모 메소드가 아니라 오버라이드된 하위 클래스 메소드 실행
        subClass.method2(); // - 실행은 가능하나 권장하지 않는 메소드. ( The method method2() from the type SubClass is deprecatedJava(67108967) )
                            //   스프링 개발자가 웹 개발자에게 알리는 용도
        
        // 1. 리플렉션(Reflection): 클래스의 정보를 반환
        Class<TestClass> clazz = TestClass.class;

        // 2. 특정 메소드 반환
        try{
            Method method = clazz.getMethod("method3");
            // 3. 특정 메소드의 어노테이션 확인
            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);

            // 4. 특정 어노테이션의 속성 확인
            System.out.println( annotation.value() );
            System.out.println( annotation.data() );

            // 5. 동적 로딩
            TestClass testClass = clazz.getDeclaredConstructor().newInstance();
            method.invoke( testClass );

            
        }
        catch(Exception e){
            System.out.println( e );
        }
    } // void main end
} // class Exam1 end 


// [3] 어노테이션 만들기 : @Interface 어노테이션명 {}
@Retention(RetentionPolicy.RUNTIME) // -> 해당 어노테이션의 생명 주기: 실행 중 유지.
@Target( ElementType.METHOD ) // 해당 어노테이션 사용처: 메소드
@interface MyAnnotation{
    String value(); // 추상 메소드 : 구현부{} 없이 선언부만 존재하는 미완성 메소드 (자식 클래스가 특정 메서드를 반드시 구현하도록 강제하는 역할)
    int data() default 1; // 어노테이션 속성, default 값
} 


// [4] 어노테이션 주입/사용: 사용처 위에 @어노테이션
class TestClass{
    @MyAnnotation( value = "안녕하세요", data = 10 ) // @내가만든어노테이션 ( 매개변수명 )
    public void method3(){ System.out.println( "method3 실행"); }

    @MyAnnotation( value = "안녕하세요2", data = 20)
    public void method4(){ System.out.println( "method4 실행"); }
}
// ★ 비슷한 예시 = @GetMapping(/board/update)


class SuperClass {
    void method1(){}
} // 상위클래스 end

class SubClass extends SuperClass{
    @Override // [1] 어노테이션: (재정의) 컴파일/실행중 해당 메소드 사용하는 방법/주석 명시
    void method1() {
        super.method1();
    }

    @Deprecated // [2] 어노테이션: (더 이상 사용을 권장하지 않음을 명시)
    void method2(){}


} // 하위클래스 end
