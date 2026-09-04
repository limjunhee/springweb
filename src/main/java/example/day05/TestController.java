package example.day05;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // RESTful API
public class TestController {
    @Autowired private TestService testService;

    // 1. 전체 조회
    // http://127.0.0.1:8080/test
    @GetMapping("/test")
    public List<TestDto> 전체조회(){
        return testService.전체조회();
    }
    // 2. 등록
    // http://127.0.0.1:8080/test  /  {"name": "파워에이드", "descri":"방금마심", "price": 800 }
    @PostMapping("/test")
    public boolean 저장( @RequestBody TestDto testDto){
        return testService.저장( testDto );
    }
    // 3. 수정  (무엇을? no -> 어떻게? price, descri)
    // http://127.0.0.1:8080/test  /  {"no": 3, "descri" : "수정테스트", "price": 1800 }
    @PutMapping("/test")
    public boolean 수정 ( @RequestBody TestDto testDto){
        return testService.수정( testDto );
    }
}
