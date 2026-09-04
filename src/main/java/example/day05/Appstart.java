package example.day05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // JPA Entity 등록
public class Appstart {
    public static void main(String[] args) {
        SpringApplication.run(Appstart.class);
    }
    
}