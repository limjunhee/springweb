package example.day05;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "test")
@NoArgsConstructor @AllArgsConstructor @Builder @Getter @Setter
public class TestEntity extends BaseTime {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer no;
    @Column(name = "name", nullable = false, length = 100, unique = true) // 다양한 제약 조건 추가 가능, 이름 설정
    private String name; // 이름

    @Column( columnDefinition = "varchar(100) default '제품설명' not null ")
    private String descri; // 설명

    @Column( insertable =  true, updatable = true )
    private Integer price; // 가격

    // 제품 등록일, 제품 수정일 --> basetime 상속
}

/*
 * @Column( name = "필드명") : 생략시 자동으로 멤버변수명으로 지정한다.
 * 
 * @Column( nullabke = true/false ) : not null
 * 
 * @Column( length = "문자열길이~255" )
 * 
 * @Column( unique = true ) : 중복 불가능/가능
 * 
 * @Column( columnDefinition = "SQL DDL 구문")
 * 
 * @Column( insertable = insert여부, updatable = update여부 )
 */