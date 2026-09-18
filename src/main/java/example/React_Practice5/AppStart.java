// 외부 API 연결 실습
package example.React_Practice5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication 
@EnableJpaAuditing 
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }
}