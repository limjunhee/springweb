package example.day10;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController 
@RequestMapping("/api/member")
public class MemberController {
    @GetMapping("")
    public String test( HttpServletRequest request ) {

        // 1) HttpServletRequest : HTTP 요청 정보를 담고 있는 서블릿 객체
        System.out.println(request.getRemoteAddr() );                // 요청한 클라이언트의 IP( 로그, 위치추적, 조회수 )
        System.out.println(request.getHeader("User-Agent"));   // 요청한 클라이언트 브라우저 정보
        System.out.println(request.getSession() ) ;                 // 요청한 클라이언트의 세션 객체 정보


        // 2) 세션 객체란? 톰캣 서버내 브라우저마다 독립적인 저장소
        // 주로: 로그인 성공 정보, 인증 번호, 비회원제 장바구니 등등 일시적인 휘발성 저장소가 필요할 때
        HttpSession session = request.getSession();             // 세션객체내 여러 개 정보 저장 가능
        System.out.println(session.getId());                    // 세션 식별번호
        System.out.println(session.getCreationTime());          // 세션 생성시간
        System.out.println(session.getLastAccessedTime());      // 세션 마지막 접근 시간
        System.out.println(session.getMaxInactiveInterval());   // 세션 생명주기( 기본값 : 30분 )
        

        // 3) 세션 정보 저장=로그인/호출=마이페이지/삭제=로그아웃
        session.setAttribute("data", "사과"); // map 구조 -> (key, value)
        session.getAttribute("data");                // 키를 이용한 속성값 호출

        session.invalidate(); // 세션 초기화
        
        return session.getId();
    }
    
}
