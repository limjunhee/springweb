package example.day02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// spring은 프레임워크이므로 다양한 도구들과 틀을 제공한다. (틀에 맞춘 도구 사용이 중요)
// @어노테이션: 코드에 추가적인 설명과 의미를 부여할 때 사용(라벨, 주석과 비슷)
// 1. @SpringBootApplication: 1) 내장 톰캣 (자동) 세팅 / 2) 서블릿(Controller/컴포넌트) 등록

@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        // 2. spring 실행 , SpringBoot [X] SpringApplication [O]
        // SpringApplication.run(현재클래스명.class);   
            // 클래스.class : 클래스 메타정보(멤버변수/생성자/메소드) 반환
        // SpringApplication.run( Springboot 메타정보 );
        SpringApplication.run(AppStart.class);
        // 3. ctrl+f5 실행, 주의: 2개 이상 실행 안됨
        // 4. 실행 확인 -> http//:localhost:8080 , http//127.0.0.1:8080
    }
}