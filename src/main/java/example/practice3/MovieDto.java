package example.practice3;

import java.time.LocalDate;
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
    private LocalDate releasedate;
    private Double rating;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // dto -> entity 변환 함수 (save 목적, 엔티티를 외부에 노출시키지 않기 위해 필요한 부분만 변환)
    // static 안쓴 이유 : 해당 메소드 호출하는 대상이 dto 인스턴스라서
    public MovieEntity toEntity(){
        // 생성자 방식
        // return new MovieEntity(null, this.title, this.director, this.releaseDate, this.rating);
        // [VS]
        // 빌더 패턴 방식(순서 섞여도 노상관)
        return MovieEntity.builder()
                          .title(this.title)
                          .director(this.director)
                          .releasedate(this.releasedate)
                          .rating(this.rating)
                          .build();
    }

    // entity -> dto 변환 함수 (find 목적)
    // static이기 때문에 인스턴스가 없음 -> this 사용이 불가능하다.
    // static 포함 이유 : 해당 메소드 호출하는 대상의 dto 인스턴스가 없는 경우이기 때문임
    public static MovieDto from(MovieEntity movieEntity){
        return MovieDto.builder()
                        .movieId(movieEntity.getMovieId())
                        .title(movieEntity.getTitle())
                        .director(movieEntity.getDirector())
                        .releasedate(movieEntity.getReleasedate())
                        .rating(movieEntity.getRating())
                        .createDate(movieEntity.getCreateDate())
                        .updateDate(movieEntity.getUpdateDate())
                        .build();
    }
}
