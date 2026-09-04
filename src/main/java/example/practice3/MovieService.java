package example.practice3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class MovieService {
    // private X
    @Autowired MovieRepository movieRepository;

    // [1] 영화 등록
    public boolean addMovie( MovieDto movieDto ){
        // 1 - dto -> 엔티티로 변환하기 
        MovieEntity movieEntity = movieDto.toEntity();

        // 2 - 변환한 엔티티를 저장하기(entity save)
        MovieEntity savedMovieEntity = movieRepository.save(movieEntity);

        // 3 - 엔티티의 id가 1 이상이라면? ('저장하려는 엔티티'가 존재한다면?)
        if (savedMovieEntity.getMovieId() >= 1) {
            return true;
        }else{
            return false;
        }
        
    }

    // [2] 영화 전체 조회
    public List<MovieDto> findAllMovies(){
        // 1. 일단 DB에서 모든 엔티티 뽑아오기
        List<MovieEntity> entities = movieRepository.findAll();

        // 2. 엔티티 출력해야 하니까 dto로 변환
        List<MovieDto> list = new ArrayList<>(); // 빈 dto 리스트 생성(entity 말고)

        // 모든 엔티티를 DTO로 변환하여 새로운 리스트에 저장하기
        for(MovieEntity entity : entities){

            //모든 엔티티에서(entities) 하나씩 뽑아(entity) 변환하기(MovieDto.from())
            MovieDto dto = MovieDto.from(entity);

            list.add(dto);
        }

        return list;
    }
    // [3] 영화 개별 조회(영화번호 기준)
    public MovieDto findMovie(int movieid){
        // 1. findById() - 영화 번호를 기준으로 개별조회 하기
        Optional<MovieEntity> optional = movieRepository.findById(movieid);

        // 2. 조회 결과 확인
        if (optional.isPresent()) {
            // 3. 엔티티 있으면 엔티티를 꺼내기
            MovieEntity entity = optional.get();

            // 4. 꺼낸거 DTO로 변환해서 반환하기
            return MovieDto.from(entity);
        }

        return null; // * 없으면 null이다.
    }
    // [4] 특정 영화 수정(영화번호 기준)
    @Transactional
    public boolean updateMovie( MovieDto movieDto ){
        // 1. ID 기준으로 수정할 대상 찾기
        Optional<MovieEntity> optional = movieRepository.findById(movieDto.getMovieId());

        if (optional.isPresent()) {
            MovieEntity entity = optional.get();

            entity.setDirector(movieDto.getDirector());
            entity.setTitle(movieDto.getTitle());
            entity.setReleaseDate(movieDto.getReleaseDate());
            entity.setRating(movieDto.getRating());

            return true;
        }
        return false;
    }
    // [5] 특정 영화 삭제(영화번호 기준)
    public boolean deleteMovie(int movieid){
        // 1. 영화번호로 삭제할 것 탐색
        Optional<MovieEntity> optional = movieRepository.findById(movieid);

        // 2. 삭제하려는 해당 영화가 존재하면?
        if (optional.isPresent()) {
            movieRepository.deleteById(movieid);

            return true;
        }

        return false;
    }
}