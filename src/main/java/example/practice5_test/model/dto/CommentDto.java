package example.practice5_test.model.dto;

import java.time.LocalDateTime;

import example.practice5_test.model.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor 
@ToString @Builder 
public class CommentDto {
    private Integer id;
    private String author;
    private String password;
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Integer boardId;

    public CommentEntity toEntity(){
        return CommentEntity.builder()
                            .author(this.author)
                            .password(this.password)
                            .content(this.content)
                            .build();
    }

    public static CommentDto from(CommentEntity commentEntity){
        return CommentDto.builder()
                        .id(commentEntity.getId())
                        .author(commentEntity.getAuthor())
                        .password(commentEntity.getPassword())
                        .content(commentEntity.getContent())
                        .createdAt(commentEntity.getCreatedAt())
                        .updatedAt(commentEntity.getUpdatedAt())
                        .build();
    }
}
