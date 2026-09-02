package example.practice2;

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
@Table(name = "test") // 매핑할 테이블의 이름
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // 롬복
public class TestEntity {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Integer no;
    
    private String writer;
    private String content;
}
