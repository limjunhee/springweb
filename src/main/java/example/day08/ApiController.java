package example.day08;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
public class ApiController {
    @Autowired private ApiService apiService;
    @GetMapping("/test1")
    public Map<String,Object> Map() {
        return apiService.test1();
    }
    
    @GetMapping("/test2")
    public Map<String, Object> test2() {
        return apiService.test2();
    }

    @GetMapping("/test3")
    public List<Map<String, Object>> test3() {
        return apiService.test3();
    }
    
    @GetMapping("/testJunHee")
    public Map<String, Object> testJunHee() {
        return apiService.testJunHee();
    }
    
}
