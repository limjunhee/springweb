package example.React_Practice5;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@CrossOrigin ("http://localhost:5173")
public class ApiController {
    @Autowired private ApiService apiService;
    
    // React_Practice5: 임준희의 안양 흡연구역 불러오기
    @GetMapping("/api/testJunHee")
    public Map<String, Object> testJunHee() {
        return apiService.testJunHee();
    }
    
    @GetMapping(value = "/api/testJin", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> testJin() {
        return apiService.testJin();
    }

    @GetMapping(value = "/api/test4", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> test4() {
        return apiService.test4();
    }

    @GetMapping("/practice6/restaurants")
    public Map<String, Object> getRestaurants() {
        return apiService.getRestaurants();
    }
}
