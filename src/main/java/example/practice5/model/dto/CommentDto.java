package example.practice5.model.dto;

import java.time.LocalDateTime;

import example.practice5.model.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @Builder 
@NoArgsConstructor @AllArgsConstructor 
public class CommentDto {
    private Integer Id;
    private String author;
    private String password;
    private String content;

    // BaseTime
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // FK: 게시물번호 불러오기
    private Integer boardId;

    // dto -> entity
    public CommentEntity toEntity(){
        return CommentEntity.builder()
                            .id(this.Id)
                            .author(this.author)
                            .password(this.password)
                            .content(this.content)
                            .build();
    }

    public static CommentDto from(CommentEntity commentEntity){
        return CommentDto.builder()
                        .Id(commentEntity.getId())
                        .author(commentEntity.getAuthor())
                        .password(commentEntity.getPassword())
                        .content(commentEntity.getContent())
                        .createdAt(commentEntity.getCreatedAt())
                        .updatedAt(commentEntity.getUpdatedAt())
                        .boardId(commentEntity.getBoardEntity().getId())
                        .build();
    }
}
