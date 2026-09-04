package example.practice3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //RESTful API
public class MovieController {
    // private O
    @Autowired private MovieService movieService;
    // [1] 영화 등록
    // http://127.0.0.1:8080/api/movie  / { "title":"영화4","director":"감독new","releasedate":"2026-09-02","rating":10 }
    @PostMapping("/api/movie")
    public boolean addMovie(@RequestBody MovieDto movieDto){
        return movieService.addMovie(movieDto);
    }
    // [2] 영화 전체 조회
    // http://127.0.0.1:8080/api/movie  
    @GetMapping("/api/movie")
    public List<MovieDto> findAllMovies(){
        return movieService.findAllMovies();
    }
    // [3] 영화 개별 조회
    @GetMapping("/api/movie/detail")
    public MovieDto findMovie(@RequestParam(name = "movieid") int movieid){
        return movieService.findMovie(movieid);
    }
    // [4] 특정 영화 수정
    // http://127.0.0.1:8080/api/movie?movieid=1  /  {"title":"영화1수정","director" : "감독1new","rating":5,"releasedate":"2026-09-04"}
    @PutMapping("/api/movie")
    public boolean updateMovie( @RequestBody MovieDto movieDto){
        return movieService.updateMovie(movieDto);
    }
    // [5] 특정 영화 삭제(영화번호 기준)
    @DeleteMapping("api/movie/{movieid}")
    public boolean deleteMovie(@PathVariable(name = "movieid") int movieid){
        return movieService.deleteMovie(movieid);
    }
}
