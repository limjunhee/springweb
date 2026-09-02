package example.day04;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // 엔티티 객체(빈) 등록
@Table(name = "exam") // 매핑할 테이블의 이름을 정의
@Data @AllArgsConstructor @NoArgsConstructor @Builder // 롬복
public class ExamEntity {
    // 엔티티는 무조건 한 개 이상의 PK를 갖는다.
    @Id // Primary Key 지정함
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Integer eno;
    private String ename;
}
