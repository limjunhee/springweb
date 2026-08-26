package example.day02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import example.day02.model.dao.BoardDao;
import example.day02.model.dto.BoardDto;

/*
    1. 컨트롤러에 서블릿( http 프로토콜 사용 가능하게 기능/방법( GET/POST/PUT/DELETE ) 제공하는 클래스 ) 기능 달기
    * 레거시 방식: 상속받아서 서블릿 구현
    * 스프링은 @Controller 내 서블릿 포함
    
    // 1. 웹 기술 포함할 컨트롤러 클래스 위에 @Controller 또는 반환 타입이 json이면 @RestController
    * HTTP Content type: http 통해 데이터 받은 곳에서 데이터 사용하는 타입/규칙/가이드 -> 기술[X] 명칭[O]
        - text/html, application/json(@RestController), form 등등 
        - DTO(자바)는 없다.

    // 2. 해당 메소드 마다의 URL 정의
    //      * URL 정의 시 http://127.0.0.1:8080(도메인) 이후 경로(path, url) 정의, 중복 없이 아무거나(한글, 띄어쓰기는 X)
    //      (1) @PostMapping("URL") : HTTP 메소드중에서 POST 메소드를 매핑/연결/대응하는 어노테이션
*/

@RestController
public class BoardController {

    // controller에서 dao를 호출
    private BoardDao bd = BoardDao.getInstance();

    // [1] 등록 controller
    @PostMapping("/board/save")
    public boolean save( BoardDto boardDto ){
        boolean result = bd.save(boardDto);
        return result;
    }
}
