package example.practice5_test.model.entity;

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
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity 
@Table (name = "comment")
@Data @Builder @ToString 
@NoArgsConstructor @AllArgsConstructor 
public class CommentEntity extends BaseTime{
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column 
    private String author;

    @Column 
    private String password;
    
    @Column 
    private String content;

    @ManyToOne
    @JoinColumn (name = "board_id")
    private BoardEntity boardEntity;
}
