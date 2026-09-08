package example.practice5.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

@Entity 
@Table(name = "comment")
@NoArgsConstructor 
@AllArgsConstructor 
@Getter 
@Setter 
@ToString 
@Builder 
public class CommentEntity extends BaseTime{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column 
    private String author;

    @Column 
    private String password;

    @Column 
    private String content;

    // (단방향) FK = 게시물 번호
    @JoinColumn (name = "board_id")
    @ManyToOne (cascade = CascadeType.REMOVE)
    private BoardEntity boardEntity;
}
