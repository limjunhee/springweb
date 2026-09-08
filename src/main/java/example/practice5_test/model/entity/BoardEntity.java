package example.practice5_test.model.entity;

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
@AllArgsConstructor @NoArgsConstructor 
public class BoardEntity extends BaseTime{
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column 
    private String author;
    @Column
    private String password;
    @Column
    private String content;

    // 양방향 참조 - 댓글목록
    @OneToMany (mappedBy = "boardEntity", cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    private List<CommentEntity> comments = new ArrayList<>();
}
