package example.day08;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Service 
public class ApiService {

    // 서비스 키를 안전하게 application.properties에서 관리 
    // 즉) 프로젝트간 api키는 github push 하지 말것.
    @Value ("${api.public-data.service-key}")
    private String serviceKey;
    // WebClient 객체 빌더 패턴 생성
    private WebClient webClient = WebClient.builder().build();


    // [1] 대구광역시 중구 맛집 현황 JSON
    public  Map<String, Object> test1(){
        // 1. API 주소
        String url = "https://api.odcloud.kr/api/15052602/v1/uddi:855807e2-fe8a-4e47-8a5a-ce1894e410d7_201909031553";
        url += "?page=" + 1;
        url += "&perPage=" + 10;
        url += "&serviceKey="+serviceKey;
        // 2. WebClient 객체 이용한 api 요청 하고 응답받기
        Map<String, Object> response = webClient.get()
                                        .uri(url)
                                        .retrieve()
                                        .bodyToMono(Map.class)
                                        .block();
        return response;
    }

    // React_Practice5
    public Map<String, Object> testJunHee() {
        // 1. API 주소
        String url = "https://api.odcloud.kr/api/15052602/v1/uddi:855807e2-fe8a-4e47-8a5a-ce1894e410d7_201909031553";
        url += "?page=" + 1;
        url += "&perPage=" + 10;
        url += "&serviceKey=" + serviceKey;
        // 2. WebClient 객체 이용한 api 요청 하고 응답받기
        Map<String, Object> response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
        return response;
    }

    // [2] 국립중앙의료원_전국 약국 정보 조회 서비스
    public Map<String, Object> test2(){
        // 1. api 주소 (공공데이터 신청한 api 요청 url)
        String url = "https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown";
        url += "?serviceKey=" + serviceKey;
        url += "&pageNo=" + 1;
        url += "&numOfRows=" + 10;

        // 3. [주의] : webClient에서 xml 타입을 String 타입으로 가져오기
        String response = webClient.get()
                                        .uri(url)
                                        .retrieve()
                                        .bodyToMono(String.class) // XML 타입 --String--> Map 직렬화/변환
                                        .block();

        // 4. String -> xml 변환
        XmlMapper xmlMapper = new XmlMapper(); // XmlMapper 객체 생성
        // Map<String,Object> map = xmlMapper.readValue( xml문자열, 타입명.class); // + 일반예외
        try {
            Map<String, Object> map = xmlMapper.readValue(response, Map.class);    
            return map;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    // [3] CSV : API 연결이 아니라 csv 파일 다운받는 방식 -> 업데이트 안됨(내가 최신 파일을 다운로드 해야 함)
    //           -> 자동화되는 환경에선 비권장
    // 프로젝트 내 resources/static 폴더에 다운받은 엑셀 파일을 넣는다.
    public List<Map<String,Object>> test3(){
        List<Map<String, Object>> list = new ArrayList<>();

        // 1. .csv 파일 경로, resources 이하 폴더
        String flieName = "static/중소벤처기업부_벤처기업명단_20260521.csv";

        // 2. ClassPathResource 객체를 이용하여 해당 경로 내 파일 가져오기 [파일객체]
        ClassPathResource resource = new ClassPathResource(flieName);

        // 3. (대용량)파일들을 바이트로 읽어와서 바이트 배열에 저장
        // -> resource.getInputStream().readAllBytes(); + 일반예외
        try {
            byte[] bytes = resource.getInputStream().readAllBytes();    

            // 4. 한글 인코딩 -> EUC-KR, UTF-8, CP949 등등
            InputStreamReader reader = new InputStreamReader(new java.io.ByteArrayInputStream(bytes), 
                                                            Charset.forName("UTF-8"));
            
            // 5. OpenCSV 라이브러리를 이용해 바이트들을 대입함
            CSVReader csvReader = new CSVReaderBuilder(reader).build();

            // 6. 주로 첫 행은 제목(행) 가져오기 (key/속성명 사용할 예정)
            String[] headers = csvReader.readNext(); // 한 줄 읽어오기
            
            // 7. 나머지 행들을 반복문을 이용하여 가져오기.
            String[] values;
            
            while (true) { // 읽어온 행의 값이 없을 때까지
                // 8. 한 줄씩 읽어오기
                values = csvReader.readNext(); // 한 줄 읽어오기
                if (values == null) break; // 읽어온 데이터가 없으면 반복문 종료
                
                // 9. 반복문을 이용해 map 만들기
                Map<String, Object> row = new LinkedHashMap<>();
                for(int index = 0; index < headers.length; index++){
                    row.put(headers[index], values[index]);
                }

                // 10. list에 생성한 map 추가
                list.add(row);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return new ArrayList<>();
    }

}

/*
 *      JSON vs xml vs csv
 *          - JSON(자바스크립트 객체) : {key:value, key:value, key:value} ->  implementation 'org.springframework.boot:spring-boot-starter-webflux'
 *          - xml(마크업 형식) : <속성명>속성값</속성명> -> implementation 'com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.22.2'
 *          - csv(쉼표 구분 형식) : 값,값,값,값 ->  implementation 'com.opencsv:opencsv:5.12.0'
 * 
 *      컬렉션프레임워크: List , Set , Map
 *          - List: 여러개 자료들을 인덱스로 구분하여 하나의 자료에 저장
 *              -> [ 값1, 값2, 값3 ]
 *          - Set : 여러개 자료들을 인덱스(중복값)없이 하나의 자료에 저장
 *              -> ( 값1, 값2, 값3 )
 *          - Map : key와value 한쌍(entry)으로 여러쌍을 하나의 자료에 저장
 *              -> { 속성명:값1 , 속성명:값2, 속성명:값3 }
 * 
 *          * List<Map<String,Object>> == [{ }, { }, { }]
 * 
 *      WebClient 객체 : 스프링에서 외부 API 요청 라이브러리
 *          1.설치: implementation 'org.springframework.boot:spring-boot-starter-webflux'
 *          2.객체: WebClient webClient = WebClient.builder().build();
 *      클래스명.class: 리플렉션( 특정/해당 클래스정보 반환 )
 */