package example.day10;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping("/api/member")
@RequiredArgsConstructor 
public class MemberController {
    private final MemberSerivce memberSerivce;

    // [1] 회원가입
    @PostMapping("/signup")
    public boolean signUp(@RequestBody MemberDto memberDto) {
        return memberSerivce.signUp(memberDto);
    }
    
    
    // [2] 로그인 + 세션 (인증 성공 시 성공한 회원 정보 저장 -> 로그인 성공한 회원이 글쓰기/제품등록 등등 FK 용도로 써야하기 때문)
    @PostMapping("/login")
    public MemberDto login(@RequestBody MemberDto memberDto , HttpSession session) {
        
        // 1. 서비스에게 인증 확인한다.
        MemberDto result = memberSerivce.login(memberDto);
        if (result == null) return null; // 로그인 실패

        // 2. 인증 성공했다면 세선에 인증한 회원정보 담아준다.
        // 매개변수에 HttpSession 객체 정의
        session.setAttribute("login_member", result); // login_member라는 키(이름)로 memberDto value(로그인 성공한)를 저장한다. *Object로 자동 업개스팅됨에 주의*

        return result;
    }

    // [3] 내정보조회 + 세션(이미 로그인된 회원이 자기 정보를 요청)
    @GetMapping ("/me")
    public MemberDto getMyInfo( HttpSession session ){
        // 사용자에게 추가적으로 입력받을 것은 없으므로, 세션 객체만 매개변수로 넣는다.
        // 1. 세션에서 특정한 정보를 꺼내기 
        //      -> 특정한 정보? == 세션 인증하여 로그인한 회원 == login_member
        Object obj = session.getAttribute("login_member");
        if (obj == null) {
            return null;
        }

        // 2. 존재한다면 다시 obj를 MemberDto로 다운캐스팅
        MemberDto memberDto = (MemberDto)obj;
        
        // 3. 서비스에게 회원번호(mno)를 전달하며 추가 정보 요청하여 반환받음
        return memberSerivce.getMyInfo(memberDto.getMno());
    }

    // [4] 로그아웃 + 세션을 초기화
    @PostMapping("/logout")
    public boolean logOut(HttpSession session) {
        // 사용자에게 추가로 입력받을 값은 없음 -> 매개변수에 세션 객체만
        // 1. 세션 초기화
        session.invalidate(); // 세션 내 모든 정보 초기화 (선택 1)
        // session.removeAttribute("login_member"); // 세션 내 특정 정보만 삭제 (선택 2)

        return true;
    }
    


    // * 세션 관련 실습
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
