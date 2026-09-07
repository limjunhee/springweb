package example.practice4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class EnrollController {
    @Autowired private EnrollService enrollService;

    // 새로운 수강 정보 등록
}
