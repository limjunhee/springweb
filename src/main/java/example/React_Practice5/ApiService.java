package example.React_Practice5;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;

import tools.jackson.databind.ObjectMapper;



@Service 
public class ApiService {

    // 서비스 키를 안전하게 application.properties에서 관리 
    // 즉) 프로젝트간 api키는 github push 하지 말것.
    @Value ("${api.public-data.service-key}")
    private String serviceKey;
    // WebClient 객체 빌더 패턴 생성
    private WebClient webClient = WebClient.builder().build();

    // React_Practice5: 임준희의 안양 흡연구역 불러오기
    public Map<String, Object> testJunHee() {
        // 1. API 주소
        String url = "http://opendata.anyang.go.kr:8082/openApi/3830000/getAnyangSmkngZoneSttus";
        url += "?numOfRows=" + 10;
        url += "&pageNo=" + 1;
        // 2. WebClient 객체 이용한 api 요청 하고 응답받기
        Map<String, Object> response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
        return response;
    }

    @Value("${api.public.data.service-Key2}")
    private String serviceKey2;

    // [1]. 정형진 살고 싶은 우리동네카테고리
    public Map<String, Object> testJin() {
        // 1. API 주소 (공공데이터 신청한 api 요청 url)
        String url = "https://api.odcloud.kr/api/15144308/v1/uddi:6b4313c0-ceef-428f-b56f-e8d3337b2395";
        url += "?page=" + 1;
        url += "&perPage=" + 10;
        url += "&serviceKey=" + serviceKey2;
        // 2. WebClient 객체 빌더패턴 생성
        WebClient webClient = WebClient.builder().build();
        // 3. WebClient 객체 이용한 api 요청하고 응답받기
        Map<String, Object> response = webClient.get() // .http 메소드명 , http.GET메소드
                .uri(url) // url은 http 주소상에 지원(쿼리스트링) 까지 포함
                .retrieve() // 요청 결과 반환 결과 수신
                .bodyToMono(Map.class) // 응답 결과 content-type
                .block(); // 동기화
        return response;
    }

    public Map<String, Object> test4() {
        String url = "https://apis.data.go.kr/6260000/FoodService/getFoodKr?serviceKey=133a87bf0560d1f71d28b921f55209465a418318bfbc48a90409ef98072c41ab&pageNo=1&numOfRows=10&resultType=json";
        String raw = webClient.get().uri(url).retrieve()
                .bodyToMono(String.class)
                .block();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(raw, Map.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Value("${api.public.data.service-Key3}")
    private String serviceKey3;
    public Map<String, Object> getRestaurants() {
        String url = "https://apis.data.go.kr/1741000/general_restaurants/info";
        url += "?serviceKey=" + serviceKey3;
        url += "&pageNo=" + 1;
        url += "&numOfRows=" + 10;
        url += "&returnType=json";

        Map<String, Object> response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        return response;
    }
}