package example.day14;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// 1. 어노테이션
@Configuration      // 스프링 컨테이너에 [설정 클래스] 빈 등록
@EnableWebSocket    // STORM 프로토콜 브로커 기능을 사용하는 컴포넌트
// [복습]
// @Controller     // 스프링 컨테이너에 [컨트롤러 클래스] 빈 등록
// @Service        // 스프링 컨테이너에 [서비스 클래스] 빈 등록
// @Repository     // 스프링 컨테이너에 [리포지토리 클래스] 빈 등록
// @RestController // 스프링 컨테이너에 [컨트롤러 클래스 + ResponseBody(응답객체 자동 직렬화)] 빈 등록 (API를 쉽게 만들 수 있도록 제공)
// @Component      // 빈 등록(위에 있는 걸 모두 포함) -> 스프링 아키텍처와 관계 없는 클래스 등록할 때 사용
// -----------> 스프링에서 해당 클래스들을 확인하여 빈(객체) 생성하여 컨테이너 메모리 저장 -> 컴파일 할 때 객체 등록되고, @SpringBootApplication 시점에서 빈을 컨테이너에 모두 등록

public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
    // WebSocketMessageBrokerConfigurer : 인터페이스

    // 2.
    @Override // 추상 메소드 구현
    public void configureMessageBroker(MessageBrokerRegistry registry){
        // 1. 구독(양방향 연결) 요청하는 방법/주소 정의
        // registry.enableSimpleBroker("구독주소");
        registry.enableSimpleBroker("/sub");

        // 2. 구독(양방향 연결)된 상태에서 메시지 주고 받는 방법/주소/엔드포인트 등록
        // registry.setApplicationDestinationPrefixes("발행주소");
        registry.setApplicationDestinationPrefixes("/pub");
    }


    // 3.
    @Override // 추상 메소드 구현
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // registry.addEndpoint("/소켓주소");
        registry.addEndpoint("/ws-chat") // 소켓 주소
                .setAllowedOriginPatterns("*"); // 모든 도메인을 허용
    }
}


/*
    HTTP는 단방향 통신을 하면서 무상태(Stateless -> 클라이언트 요청 하나당 한개의 응답(여러번이 안된다.)
        - CRUD에 사용
    WebSocket : 양방향 통신 지원, 상태를 유지하며(Stateful) 한 번 연결 후 그 상태에서 양방향 통신
        - 채팅, 알림 등의 실시간 통신이 필요한 경우 사용

    WebSocket 의존성
    -> implementation 'org.springframework.boot:spring-boot-starter-websocket'

    [브로커 설정 클래스]
        - 구독 주소 : ws:localhost:8080/sub     , 특정 방/경로 등록
        - 발행 주소 : ws:localhost:8080/pub     , 특정 방/경로 메시지 발행
        - 소켓 주소 : ws:localhost:8080/ws-chat , 백엔드-프론트 연결

    
*/