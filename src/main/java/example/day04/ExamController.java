package example.day04;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController // 싱글톤
@RequiredArgsConstructor // final 멤버변수에 대한 생성자 자동 생성
public class ExamController {
    // 서비스객체(주입)불러오기
    private final ExamService examService;

    // [1] 전체 조회  
    // http://127.0.0.1:8080/day04/exam
    @GetMapping("day04/exam")
    public List<ExamEntity> findAll(){
        // 서비스 호출
        return examService.findAll();
    }

    // [2] 저장  
    // http://127.0.0.1:8080/day04/exam / { "ename":"홍길동" }
    @PostMapping("day04/exam")
    public boolean 저장( @RequestBody ExamEntity examEntity){
        //서비스 호출
        return examService.저장(examEntity);
    }
    
    // [3] 삭제
    // http://127.0.0.1:8080/day04/exam?no=4
    @DeleteMapping("day04/exam")
    public boolean 삭제 ( @RequestParam (name = "no") int no){
        //서비스호출
        return examService.삭제(no);
    }

    // [4] 수정
    // http://127.0.0.1:8080/day04/exam  /  { "eno": 6, "ename":"임준희" }
    @PutMapping("day04/exam")
    public boolean 수정( @RequestBody ExamEntity examEntity){
        // 서비스 호출
        return examService.수정( examEntity );
    }
}
