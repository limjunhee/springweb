package example.totalpratice1.model.dto;

import example.totalpratice1.model.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor 
@ToString @Builder 
public class ReviewDto {
private Integer rno;
    private String reviewer;
    private String content;
    private Integer rating;
    private Integer bno;

    public ReviewEntity dtoToEntity(){
        return ReviewEntity.builder()
        .reviewer(this.reviewer)
        .content(this.content)
        .rating(this.rating)
        .build();
    }

    public static ReviewDto entityToDto( ReviewEntity entity ){
        return ReviewDto.builder()
        .reviewer(entity.getReviewer())
        .content(entity.getContent())
        .rating(entity.getRating())
        .build();
    }
}
