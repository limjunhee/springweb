package example.practice5_test.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import example.practice5_test.model.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data @Builder 
@NoArgsConstructor @AllArgsConstructor 
public class BoardDto {
    private Integer id;
    private String author;
    private String password;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    
    // 게시물의 댓글들
    @Builder.Default
    private List<CommentDto> comments = new ArrayList<>();

    public BoardEntity toEntity(){
        return BoardEntity.builder()
                        .id(this.id)
                        .author(this.author)
                        .password(this.password)
                        .content(this.content)
                        .build();
    }

    public static BoardDto from(BoardEntity boardEntity){
        return BoardDto.builder()
                        .id(boardEntity.getId())
                        .author(boardEntity.getAuthor())
                        .password(boardEntity.getPassword())
                        .content(boardEntity.getContent())
                        .createdAt(boardEntity.getCreatedAt())
                        .updateAt(boardEntity.getUpdatedAt())
                        .build();
    }
}
