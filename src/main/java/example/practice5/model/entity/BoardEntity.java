package example.practice5.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Table (name = "board")
@Getter @Setter @ToString @Builder 
@NoArgsConstructor @AllArgsConstructor 
public class BoardEntity extends BaseTime{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    private String author;
    @Column 
    private String password;
    @Column
    private String content;

    // 양방향 연결
    @OneToMany (mappedBy = "boardEntity", cascade = CascadeType.ALL) // mappedBy = 자바에서 매핑할 멤버변수명, cascade = 상위 요소 삭제 시의 처리방식
    @Builder.Default  // 빌더 패턴 생성 시 기본값으로 설정함
    @ToString.Exclude // 양방향 참조 시 순환 참조 방지
    private List<CommentEntity> comments = new ArrayList<>();
}