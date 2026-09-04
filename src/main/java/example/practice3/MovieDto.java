package example.practice3;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MovieDto {
    private Integer movieId;
    private String title;
    private String director;
    private String releaseDate;
    private Integer rating;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // dto -> entity 변환 함수 (save 목적, 엔티티를 외부에 노출시키지 않기 위해 필요한 부분만 변환)
    public MovieEntity toEntity(){
        return MovieEntity.builder()
                          .title(this.title)
                          .director(this.director)
                          .releaseDate(this.releaseDate)
                          .rating(this.rating)
                          .build();
    }

    public static MovieDto from(MovieEntity movieEntity){
        return MovieDto.builder()
                        .movieId(movieEntity.getMovieId())
                        .title(movieEntity.getTitle())
                        .director(movieEntity.getDirector())
                        .releaseDate(movieEntity.getReleaseDate())
                        .rating(movieEntity.getRating())
                        .createDate(movieEntity.getCreateDate())
                        .updateDate(movieEntity.getUpDateTime())
                        .build();
    }
}
