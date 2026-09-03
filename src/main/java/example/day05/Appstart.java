package example.day05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import example.day02_2.AppStart;

@SpringBootApplication
@EnableJpaAuditing // JPA Entity 등록
public class Appstart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }
    
}