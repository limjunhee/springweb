package example.day07;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

interface Calculator{ int plus(int x, int y); }
public class exam2 {
    public static void main(String[] args) {
        // 1. 구현체: 추상메소드(인터페이스) 구현한 객체

        // 2. 익명구현체: 추상메소드 구현한 클래스 없이 직접 구현한 구현체(일회성)
        // Calculator calc = new Calculator() { //추상메서드 구현 };
        Calculator calc = new Calculator() {
            @Override public int plus(int x, int y){return x+y;}
        };
        int result1 = calc.plus(5, 8);
        System.out.println(result1);

        // 3. 람다 표현식 -> 익명함수를 간단하게 표현하는 방법
        // 매개변수 타입 추론 가능 시 타입 생략 가능함 (a,b) -> a + b
        // 매개변수가 1개인 경우 소괄호 생략 가능
        // 실행문이 단일 표현식인 경우 중괄호, return 생략 가능
        Calculator calc2 = ( x, y ) -> { return x + y; }; // Calculator calc = new Calculator() { @Override public int plus(int x, int y) {return x+y;} };
        int result2 = calc2.plus(5, 3);
        
        // 3-1. 람다표현식 사용하는 인터페이스들
        // 참고) 제네릭이란? 클래스/인터페이스 안에서 사용할 타입을 정하는 타입
        // 
        // Function<인수타입, 반환타입> / apply( 인수 )   -> 매개변수 O, 반환 O 일 경우
        Function<Integer, Integer> function = x -> { return  x * 2; };
        System.out.println( function.apply(3)); // 6

        // Supplier<반환타입> , get() -> 매개변수 X, 반환 O일 경우
        Supplier<Integer> supplier = ( ) -> { return 2; };
        System.out.println(supplier.get());

        // Consumer< 인수타입 > , accept( 인수 ), 매개변수o / 반환 x
        Consumer< String > consumer = (str) -> {System.out.println( str ); };
        consumer.accept("유재석");

        // Predicate< 인수타입 > , test( 인수 ), 매개변수o/ 반환o ( true or false )
        // 필터링, 유효성 검증시 많이 사용 
        Predicate<Integer> predicate = ( x ) -> {return x % 2 == 0;};
        boolean result4 = predicate.test(3);
        System.out.println(result4);

        // - 활용처 - 
        // 위 4가지 직접적인 사용이 아닌, API(남이 만든 클래스/메소드) 에서 활용 가능
        // forEach -> comsumer, map -> function , filter -> Predicate
        // 즉) 스트림 API 주로 사용된다.

        // 스트림 API란? 
    }
}
