package example.practice5_test.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Table (name="comment")
@NoArgsConstructor @AllArgsConstructor 
@Getter @Setter @ToString @Builder 
public class CommentEntity extends BaseTime{
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String author;
    private String password;
    private String content;

    // FK - 게시글 엔티티
    @JoinColumn (name = "board_id")
    @ManyToOne 
    private BoardEntity boardEntity;
}
