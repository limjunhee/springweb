package example.day14;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/sse")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") 
public class NoticeController {
    // 알림 서비스 호출
    private final NoticeService noticeService;

    // 1. 알림 구독 매핑
    // 응답 content-type : JSON 아니고 스트림 타입
    // 스트림 쓰는 이유 : 알림을 만들기 위해?
    @GetMapping (value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(){
        return noticeService.subscribe();
    }

    // 2. 알림 메시지 전송 (테스트용)
    @GetMapping ("/message")
    public void onMessage( @RequestParam ( name = "msg") String msg ){
        noticeService.onMessage(msg);
    }
}
