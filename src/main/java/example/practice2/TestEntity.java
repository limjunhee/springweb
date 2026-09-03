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

/*
    JPA 영속성: 자바의 데이터가 사라지지 않게 데이터베이스에 저장
        -> 자바 프로그램(휘발성/영구저장 불가)
        -> DB ( 비휘발성/영구저장 가능)
*/