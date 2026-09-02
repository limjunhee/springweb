package example.day04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 1. 내장 톰캣 지원(@EnableAutoConfiguration)
// 2. IOC/DI 컴포넌트 등록(@ComponentScan)
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }
}
