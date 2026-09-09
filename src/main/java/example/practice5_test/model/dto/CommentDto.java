package example.practice5_test.model.dto;

import java.time.LocalDateTime;

import example.practice5_test.model.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder 
@NoArgsConstructor @AllArgsConstructor 
public class CommentDto {
    private Integer id; //
    private String author;
    private String password;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    // 이 댓글이 달린 게시물 ID
    private Integer boardId;

    public CommentEntity toEntity(){
        return CommentEntity.builder()
                            .id(this.id)
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
                        .createAt(commentEntity.getCreatedAt())
                        .updateAt(commentEntity.getUpdatedAt())
                        .build();
    }

}
