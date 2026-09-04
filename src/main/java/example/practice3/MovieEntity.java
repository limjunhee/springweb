package example.practice3;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity // 데이터베이스와 매핑된 관계
@Table(name = "movie") // 테이블명을 지정
@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor // 롬복
public class MovieEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId; // 영화번호(PK)

    @Column(name = "title", unique = true, nullable = false, length = 100)
    private String title; // 영화제목

    @Column(name = "director", nullable = false, length = 100)
    private String director; // 감독

    @Column(name = "releasedate", nullable = false )
    private String releaseDate; // 개봉일

    @Column(name = "rating", columnDefinition = "int default 0 not null")
    private Integer rating;

    // 생성일과 수정일은 BaseTime 상속
}
