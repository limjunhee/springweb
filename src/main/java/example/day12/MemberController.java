package example.day12;

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
    
    private final RedisTokenService redisTokenService;
    
    // [2] 로그인
    @PostMapping("/login")
    public MemberDto login(@RequestBody MemberDto memberDto , HttpServletResponse response) {
        
        // 1. 서비스에게 인증 확인한다. (기존 유지)
        MemberDto result = memberService.login(memberDto);
        if (result == null) return null; // 로그인 실패

        // 2. 토큰 **2개** 발급 요청
        String accessToken = jwtUtil.createAccessToken( result.getMno() );
        String refreshToken = jwtUtil.createRefreshToken( result.getMno() );

        // 3. refreshToken만 레디스에 저장(대조용으로 사용)
        redisTokenService.setRefreshToken(result.getMno(), refreshToken);

        // 4. 로그인 성공 시 쿠키 2개 생성/발급 , 쿠키만료기간 == 토큰만료기간 동일권장
        ResponseCookie cookie1 = ResponseCookie.from("accessToken", accessToken)
                                                .path("/").maxAge(Duration.ofMinutes(30) )
                                                .httpOnly(true).secure(false).sameSite("Lax")
                                                .build(); // 30분짜리 accessToken 쿠키 완성
        
        ResponseCookie cookie2 = ResponseCookie.from("refreshToken", refreshToken)
                                                .path("/").maxAge(Duration.ofDays(7))
                                                .httpOnly(true).secure(false).sameSite("Lax")
                                                .build(); // 7일짜리 refreshToken 쿠키 완성
        
        // 5. 응답 헤더에 쿠키 2개 등록 response.addHeader -> cookie1, cookie2
        response.addHeader( HttpHeaders.SET_COOKIE, cookie1.toString() );
        response.addHeader( HttpHeaders.SET_COOKIE, cookie2.toString() );
        
        return result;
    }

    // [3] 내정보조회 + 쿠키
    @GetMapping("/me")
    public MemberDto getMyInfo(
            // @CookieValue( value="쿠키명") ){ // 요청한 브라우저의 쿠키 가져오기
            @CookieValue(value = "accessToken", required = false) String token) {
        // 1. 만약에 token 가 없다면 비로그인
        if (token == null)
            return null;
        // ********* 쿠키에 저장된 token 이용하여 회원번호 찾기 ************
        Long loginMno = jwtUtil.getMnoFromToken(token);
        // 2. 로그인 중이면 서비스에게 회원정보 요청
        return memberService.getMyInfo( loginMno );
    }

    // [4] 로그아웃 + 쿠키
    @PostMapping("/logout")
    public boolean logOut( @CookieValue(value = "accessToken", required = false ) String accessToken, HttpServletResponse response) {
        // 1. 만약 accessToken 있으면 회원 번호를 조회
        if (accessToken != null) {
            Long mno = jwtUtil.getMnoFromToken(accessToken);
            // 2. 만약 회원이 조회된다면? 레디스 안의 refreshToken을 삭제하기.
            redisTokenService.deleteRefreshToken(mno);

        }

        // 3. 쿠키 2개도 삭제하기
        ResponseCookie cookie1 = ResponseCookie.from("accessToken", "")
                                            .path("/")          // 모든곳에서 로그아웃 가능하도록, 전체
                                            .maxAge(0) // 바로 삭제
                                            .httpOnly(true)
                                            .secure(false)
                                            .build();
        ResponseCookie cookie2 = ResponseCookie.from("refreshToken", "")
                                            .path("/") // 모든곳에서 로그아웃 가능하도록, 전체
                                            .maxAge(0) // 바로 삭제
                                            .httpOnly(true)
                                            .secure(false)
                                            .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie1.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, cookie2.toString());
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
