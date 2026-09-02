package example.practice2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor // final 멤버변수에 대한 생성자를 자동 생성
public class TestController {

    private final TestService testService;

    // 1 - 게시물 등록
    // http://127.0.0.1:8080/practice2/test / { "content": "안녕하세요new" , "writer":"임준희" }
    @PostMapping("practice2/test")
    public boolean testWrite( @RequestBody TestEntity testEntity ){
        System.out.println("TestController.testWrite()");
        return testService.testWrite(testEntity);
    }

    // 2 - 게시물 전체 조회
    // http://127.0.0.1:8080/practice2/test
    @GetMapping("practice2/test")
    public List<TestEntity> testPrint(){
        System.out.println("TestController.testPrint()");
        // 서비스 호출
        return testService.testPrint();
    }

    // 3 - 게시물 개별 조회
    // http://127.0.0.1:8080/practice2/test/detail?no=1
    @GetMapping("practice2/test/detail")
    public TestEntity testDetail( @RequestParam(name = "no") int no ){
        System.out.println("TestController.testDetail()");

        return testService.testDetail(no);
    }

    // 4 - 게시물 삭제
    // http://127.0.0.1:8080/practice2/test/detail?no=4
    @DeleteMapping("practice2/test")
    public boolean testDelete(@RequestParam(name = "no") int no) {
        System.out.println("TestController.testDelete()");
        // 서비스 호출
        return testService.testDelete(no);
    }
    // 5 - 게시물 수정
    // http://127.0.0.1:8080/practice2/test  /  { "no" : 3 ,"content": "안녕하세요new" , "writer":"임준희" }
    @PutMapping("practice2/test")
    public boolean testUpdate( @RequestBody TestEntity testEntity ){
        System.out.println("TestController.testUpdate()");
        //서비스 호출
        boolean result = testService.testUpdate(testEntity);
        return result;
    }
}
