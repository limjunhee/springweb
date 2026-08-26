package example.day02_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 내장 톰캣 자동 세팅, 서블릿 자동 등록 등등....
@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        // 1. spring 실행 , SpringBoot [X] SpringApplication [O]
        // SpringApplication.run(현재클래스명.class);
        // 클래스.class : 클래스 메타정보(멤버변수/생성자/메소드) 반환
        // SpringApplication.run( Springboot 메타정보 );
        SpringApplication.run(AppStart.class);
        // 2. 실행 확인 -> http//:localhost:8080 , http//127.0.0.1:8080
    }
}