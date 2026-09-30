package example.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping ("/api/redis")
@RequiredArgsConstructor 
public class RedisController {
    // [1] 레디스를 조작하는 객체를 하나 만들기 (문자열 기반의 자료를 DB 말고 레디스에 CRUD 가능)
    // StringRedisTemplate : 일반 텍스트 문자열 처리 -> 토큰 관리에 최적임
    private final StringRedisTemplate stringRedisTemplate;

    // 1)
    @GetMapping("/test1")
    public Map<String, Object> test1() {
        // [2] 레디스에 자료 삽입 -> .opsForValue().set(key, value); , 문자열타입
        // key 중복 안됨에 주의(당연히 value는 가능)
        stringRedisTemplate.opsForValue().set( "유재석", "90" );
        stringRedisTemplate.opsForValue().set("강호동", "100");
        stringRedisTemplate.opsForValue().set("신동엽", "80");
        
        // [3] 레디스에 자료 조회, .keys("*"), 모든 키에 해당하는 자료 호출
        // 참고: 컬렉션프레임워크 ( List, Map, Set )
        Set<String> keys = stringRedisTemplate.keys("*");
        Map<String, Object> map = new HashMap<>();
        for(String key : keys){ // 모든 키를 하나씩 반복하여
            String data = stringRedisTemplate.opsForValue().get(key); // 키 이용하여 값 호출
            map.put(key, data);
        }
        return map;
    }

    // =============== redis CRUD ====================
    private final ObjectMapper objectMapper = new ObjectMapper(); // 직렬화 객체

    // [1] Redis 저장 http://localhost:8080/api/redis/member
    @PostMapping ("/member")
    public boolean save(@RequestBody MemberDto memberDto) throws JsonProcessingException {
        // 1. 중복 없는 key 구성( 예] 도메인명: 식별키 )
        String key = "member:"+memberDto.getMno(); // 얘시) member:3

        // 2. 문자열템플릿에 DTO/자바객체 대입, DTO -> 문자열(직렬화), 문자열 -> DTO (역직렬화) 변환
        // .writeValueAsString( 자바객체 );, 일반 예외 발생
        String str = objectMapper.writeValueAsString(memberDto); // dto -> 문자열 변환 (직렬화)

        // 3. 레디스에 저장
        stringRedisTemplate.opsForValue().set(key, str); // { member:1 : { mno:1, mid:qwe } }

        return true;
    }

    // [2] redis 전체조회 http://localhost:8080/api/redis/member
    @GetMapping ("/member")
    public List<MemberDto> findAll() throws JsonMappingException, JsonProcessingException{
        // 1. 특정 패턴의 key 조회, memeber:* , member로 시작하는 모든 키 조회
        Set<String> keys = stringRedisTemplate.keys("member:*");

        // 2. 모든 키 반복하여 하나씩 키에 대응하는 dto(값) 호출
        List<MemberDto> list = new ArrayList<>();
        for( String key : keys){
            String value = stringRedisTemplate.opsForValue().get(key);
            // 3. 역직렬화, 문자열 -> 자바 객체
            // objectMapper.readValue(값, 타입명.class);
            MemberDto memberDto = objectMapper.readValue( value, MemberDto.class);

            // 4. 리스트에 담기
            list.add(memberDto);
        }

        return list;
    }
    

    // [3] redis 개별조회 http://localhost:8080/api/redis/member/find?mno=1
    @GetMapping("/member/find")
    public MemberDto find(@RequestParam(name="mno") Long mno) throws JsonMappingException, JsonProcessingException {
        // 1. 조회할 mno 매개변수로 받는다.
        // 2. 레디스에서 특정 mno의 키 조회
        String findKey = "member:" + mno;
        String value = stringRedisTemplate.opsForValue().get(findKey);
        if (value == null) {
            return null;
        }

        // 3. 역직렬화 : string을 자바객체(dto/map/list 등등)로
        MemberDto memberDto = objectMapper.readValue(value, MemberDto.class);

        return memberDto;
    }
    
    // [4] redis 삭제
    @DeleteMapping("/member")
    public boolean delete( @RequestParam(name = "mno") Long mno ){
        // 1. 삭제할 mno 매개변수로 받는다.
        // 2. 삭제할 키를 조합한다.
        String deleteKey = "member:"+mno;
        boolean result = stringRedisTemplate.delete(deleteKey); // redisTemplate.delete(key)
        
        return result;
    }

    // [5] redis 수정
    @PutMapping("/member")
    public boolean update( @RequestBody MemberDto memberDto ){
        // 1. 수정할 자료들을 dto 받고, 수정할 key 조합하여 수정한다.
        String updateKey = "member:"+ memberDto.getMno();
        if (updateKey == null) return false;

        // 2. 동일한 키로 입력받은 dto 직렬화 저장
        try {
            String value = objectMapper.writeValueAsString(memberDto); // 직렬화
            stringRedisTemplate.opsForValue().set(updateKey, value);
            return true;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return false;
    }
}



// - 데이터 저장
//      redisTemplate.opsForValue().set(key, value);
//      redisTemplate.opsForValue().set(key, value, timeout ); // TTL(유효기간) 설정
//      * TTL : 만료 시간 지나면 레디스 엔진이 메모리에서 자동으로 영구 삭제함
//      * key 중복 불가능

// - 데이터 조회
//      redisTemplate.opsForValue().get(key); // 특정 Key의 Value 반환 (없으면 null)
//      redisTemplate.keys("패턴");           // 특정 패턴의 Key 목록 반환 (예시: "RT:*", "student:*")
//      * 실무 네이밍 규칙: "도메인단어:식별번호" 형태 (예: "RT:1", "phone:010-1234-5678").

// 4) 데이터 삭제
//      redisTemplate.delete(key);           // 특정 Key-Value 엔트리 즉시 삭제.

// 5) Key 존재 여부 확인
//      redisTemplate.hasKey(key);           // 존재하면 true, 없으면 false 반환.