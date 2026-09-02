package example.practice1;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Component //스프링 컨테이너에 빈(객체) 등록
//@Controller // HTTP 서블릿 + @Component -> 주로 *view 반환*
@RestController // @ResponseBody 응답 http content-type:application/json + @Controller -> 주로 *값 반환*

@RequestMapping("/test") // 클래스 내 메소드들의 공통 URL을 정의
// 참고: URL은 원래 중복이 안되나, HTTP METHOD가 다르다면 중복해서 선언 가능하다.

public class TestController {
    
    // 게시물 등록
    // http://127.0.0.1:8080/test/
    @PostMapping("/") // 상위(클래스)에 "/test" 주소 정의했음
    public boolean testWrite( @RequestBody TestDto testDto ){
        System.out.println("TestController.testWrite()");
        return true;
    }

    // 게시물 전체 조회
    // http://127.0.0.1:8080/test/
    @GetMapping("/")
    public ArrayList<TestDto> testPrint(){
        System.out.println("TestController.testPrint()");
        ArrayList<TestDto> list = new ArrayList<>();
        list.add(new TestDto(1,"안녕하세요1","작성자1"));
        list.add(new TestDto(1,"안녕하세요2","작성자2"));
        return list;
    }

    // 게시물 개별 조회
    // http://127.0.0.1:8080/test/detail?no=1
    @GetMapping("/detail")
    public TestDto testDetail( @RequestParam(name = "no") int no ){
        System.out.println("TestController.testDetail()");

        return new TestDto(no,"내용1","작성자1");
    }

    // 게시물 삭제
    // http://127.0.0.1:8080/test/1
    @DeleteMapping("{no}") 
    public boolean testDelete( @PathVariable(name = "no") int no ){
        System.out.println("TestController.testDelete()");
        ArrayList<TestDto> list = new ArrayList<>();
        list.add(new TestDto(1, "안녕하세요1", "작성자1"));
        list.add(new TestDto(1, "안녕하세요2", "작성자2"));

        list.remove(no);
        return true;
    }

    // 게시물 수정
    @PutMapping("/")
    public boolean testUpdate ( @RequestBody TestDto testDto ){
        System.out.println("TestController.testUpdate()");
        return true;
    }
}


@Data // 멤버 변수에 대한 getter setter toString 자동 생성
@AllArgsConstructor
@NoArgsConstructor
@Builder
class TestDto {
    int no;
    String content;
    String writer;
}

/*
    int : 기본타입 +-21억 저장
    Integer : 참조 타입(int 래퍼클래스) + null 저장 가능

    주의점 : HTTP 파싱하는 경우에 int에는 NULL 저장 불가능
    권장1: 기본 타입 대신에 래퍼 타입 사용할 것
*/