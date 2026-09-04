package example.day06;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "category")
@NoArgsConstructor @AllArgsConstructor @Builder @Data
public class CategoryEntity {
    @Id
    private Integer cno;
    private String cname;

    // 양방향 참조(하나가 다수에게 참조)
    // @OneToMany( mappedBy = "매핑할 멤버변수") 
    @OneToMany( mappedBy = "categoryEntity") // 자바(JPA)에서만 매핑한다.
    @ToString.Exclude // 양방향 참조 - 순환 참조를 방지
    @Builder.Default // 만일 해당 엔티티가 빌더 패턴 사용 시 초기값 그대로 사용한다!
    private List<BoardEntity> boardList = new ArrayList<>();
}