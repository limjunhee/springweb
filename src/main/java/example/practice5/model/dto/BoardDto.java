package example.practice5.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import example.practice5.model.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@ToString  
@Builder 
@AllArgsConstructor
public class BoardDto {
    private Integer id;
    private String author;
    private String password;
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // 게시물에 달린 댓글 불러오기
    @Builder.Default
    private List<CommentDto> comments = new ArrayList<>();
    
    // dto -> entity
    public BoardEntity toEntity(){
        return BoardEntity.builder()
                            .author(this.author)
                            .password(this.password)
                            .content(this.content)
                            .build();
    }
    // entity -> dto
    public static BoardDto from (BoardEntity boardEntity){
        return BoardDto.builder()
                        .id(boardEntity.getId())
                        .author(boardEntity.getAuthor())
                        .password(boardEntity.getPassword())
                        .content(boardEntity.getContent())
                        .createdAt(boardEntity.getCreatedAt())
                        .updatedAt(boardEntity.getUpdatedAt())
                        .build();
    }
}
