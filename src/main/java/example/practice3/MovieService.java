package example.practice3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired private MovieRepository movieRepository;

    // [1] 영화 등록
    public boolean addMovie( MovieDto movieDto){
        // 1 - dto를 엔티티로 변환하기 
        MovieEntity movieEntity = movieDto.toEntity();

        // 2 - 변환한 엔티티를 저장하기
        MovieEntity savedMovieEntity = movieRepository.save(movieEntity);

        // 3 - 엔티티의 id가 1 이상이라면? (엔티티가 존재한다면?)
        if (savedMovieEntity.getMovieId() >= 1) {
            return true;
        }
        return false;
    }

    // [2] 영화 전체 조회
    public List<MovieDto> findAllMovies(){
        // 1. 일단 DB에서 모든 엔티티 뽑아오기
        List<MovieEntity> entities = movieRepository.findAll();

        // 2. 엔티티 출력해야 하니까 dto로 변환
        List<MovieDto> list = new ArrayList<>(); // 빈 리스트 생성

        // 모든 엔티티를 DTO로 변환하여 새로운 리스트에 저장하기
        for(MovieEntity entity : entities){

            //모든 엔티티에서(entities) 하나씩 뽑아(entity) 변환하기(MovieDto.from())
            MovieDto dto = MovieDto.from(entity);

            list.add(dto);
        }

        return list;
    }
    // [3] 영화 개별 조회(영화번호 기준)

    // [4] 특정 영화 수정(영화번호 기준)

    // [5] 특정 영화 삭제(영화번호 기준)
}