package example.day11;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Duration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping("/api/member")
@RequiredArgsConstructor 
@CrossOrigin (origins = "http://localhost:5173", allowCredentials = "true") // allowCredentials = true -> 도메인 다른 경우 allowCredentials 이용한 쿠키/세션 유지 가능
public class MemberController {
    private final MemberSerivce memberService;
    private final JwtUtil jwtUtil;

    // [1] 회원가입
    // (기존 유지)
    @PostMapping("/signup")
    public boolean signUp(@RequestBody MemberDto memberDto) {
        return memberService.signUp(memberDto);
    }
    
    
    // [2] 로그인 + 쿠키 (회원 식별(번호) 쿠키에 담아 클라이언트에 전송)
    // 세션 방식에서 쿠키로 변경
    @PostMapping("/login")
    public MemberDto login(@RequestBody MemberDto memberDto , HttpServletResponse response) {
        
        // 1. 서비스에게 인증 확인한다. (기존 유지)
        MemberDto result = memberService.login(memberDto);
        if (result == null) return null; // 로그인 실패

        // 2. 로그인 성공 시 쿠키 생성/발급 *** 쿠키 값을 jwt 안전하게 변경 ***
        // 쿠키는 세션과 다르게 클라이언트에 저장됨
        // -> 회원 번호만 저장하자 (비밀번호 등 민감한 정보는 X)
        // ResponseCookie cookie = ResponseCookie.from("cookieName","cookieValue")
        // 참고 : 정수를 문자 타입으로 변환하는 방법 1) 정수 + ""
        //                                        2) String.valueOf(정수), **쿠키값은 String 타입이다.**

        // 4. 토큰 발급 요청
        String token = jwtUtil.createToken(result.getMno()); // mno --> jwt

        ResponseCookie cookie = ResponseCookie.from("login_member", /*result.getMno()+""*/ token)
                                                .path("/")                      // 쿠키를 사용한 경로, "/"는 도메인 전체를 뜻한다.
                                                // Duration.ofXXX(수) : 쿠키의 유효 가간을 설정 
                                                .maxAge( Duration.ofDays(1))    // 유효기간 1일로 설정
                                                .httpOnly(true)             // JS 이용한 탈취 방지, XSS 공격
                                                .secure(false)                // HTTP에서만 사용, 개발단계 false, 배포단계 true
                                                .sameSite("Lax")            // CSRF 공격을 방지함
                                                .build();

        // 3. 응답 헤더에 쿠키 등록 response.setHeader
        response.setHeader( HttpHeaders.SET_COOKIE, cookie.toString() );
        return result;
    }

    // [3] 내정보조회 + 쿠키
    @GetMapping("/me")
    public MemberDto getMyInfo(
            // @CookieValue( value="쿠키명") ){ // 요청한 브라우저의 쿠키 가져오기
            @CookieValue(value = "login_member", required = false) String token) {
        // 1. 만약에 token 가 없다면 비로그인
        if (token == null)
            return null;
        // ********* 쿠키에 저장된 token 이용하여 회원번호 찾기 ************
        Long loginMno = jwtUtil.getMnoFromToken(token);
        // 2. 로그인 중이면 서비스에게 회원정보 요청
        return memberService.getMyInfo(loginMno);
    }

    // [4] 로그아웃 + 세션을 초기화
    @PostMapping("/logout")
    public boolean logOut(HttpServletResponse response) {
        // 1. 삭제할 쿠키명과 동일한 이름으로 maxAge(0) 하여 재발급
        ResponseCookie cookie = ResponseCookie.from("login_member", "")
                                            .path("/")          // 모든곳에서 로그아웃 가능하도록, 전체
                                            .maxAge(0) // 바로 삭제
                                            .httpOnly(true)
                                            .secure(false)
                                            .build();

        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
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
