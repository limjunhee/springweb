package example.practice2;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor // final 멤버변수에 대한 생성자를 자동 생성
public class TestController {
    private final TestService testService;
    // 1 - 게시물 등록
    @PostMapping("practice2/test")
    public boolean testWrite( @RequestBody TestEntity testEntity){
        return testService.testWrite(testEntity);
    }

    // 2 - 게시물 전체 조회
    @GetMapping("practice2/test")
    public ArrayList<TestEntity> testPrint(){
        // 서비스 호출
        return testService.testPrint();
    }

    // 3 - 게시물 개별 조회

    // 4 - 게시물 삭제

    // 5 - 게시물 수정
}
