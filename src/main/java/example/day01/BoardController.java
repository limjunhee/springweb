package example.day01;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 1. 해당 컨트롤러에게 HTTP(웹 기술) 적용하기 <- 서블릿 요구(톰캣에 포함(스프링부트에 내장됨))
// 2. 서블릿에게 상속( 해당 클래스로부터 멤버변수/메소드 물려받기 ) , extends HttpServlet
// 3. 물려받은 기능( init, service, destroy )을 재정의 -> 오버라이딩
// 4. HTTP doXXX() 메소드 오버라이딩 하여 기능 구현 --> 컨트롤러 내 비지니스 로직 구현( 컨트롤러 역할 )
// 5. 해당 컨트롤러에 HTTP 주소 등록하기, @WebServlet("/주소정의")

@WebServlet("/Example/day01")
public class BoardController extends HttpServlet {
    
    // [1] init() : 서블릿이 최초 실행된 경우 딱 1번 실행되는 메소드
    @Override
    public void init() throws ServletException {
        // DB 연동, 초기값 지정 등등 추가 가능하다.
        super.init();
    }

    // [2] Service(): 서블릿 생성되고 요청마다 실행되는 메소드
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        // 요청한 사람 IP 조회하여 기능을 제한시키거나, 위치추적 등 다양한 것을 추가할 수 있다.
        super.service(req, res);
    }

    // [3] destroy(): 서블릿이 사라질 때(서버 종료) 1번 실행되는 메소드
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        super.destroy();
    }

    // ************************ HTTP MEHOD CRUD ********************** //

    // [4-1] doGet(): HTTP 요청이 Get이면?
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // DAO를 호출하여 로그인처리(컨트롤러 역할)
    }

    // [4-2] doPost(): HTTP 요청이 Get이면?
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 회원가입 처리
    }

    // [4-3] doPut(): HTTP 요청이 PUT이면?
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 회원정보 수정
    }

    //[4-4] doDelete(): HTTP 요청이 DELETE면?
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 회원탈퇴
    }
}
