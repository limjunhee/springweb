package example.practice3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //RESTful API
public class MovieController {
    @Autowired private MovieService movieService;
    // [1] 영화 등록
    @PostMapping("/movie")
    public boolean addMovie(@RequestBody MovieDto movieDto){
        return movieService.addMovie(movieDto);
    }
    // [2] 영화 전체 조회
    @GetMapping("/movie")
    public List<MovieDto> findAllMovies(){
        return movieService.findAllMovies();
    }
    // [3] 영화 개별 조회

    // [4] 특정 영화 수정

    // [5] 특정 영화 삭제(영화번호 기준)
}
