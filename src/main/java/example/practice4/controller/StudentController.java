package example.practice4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class StudentController {
    @Autowired private StudentService studentService;

    // 새로운 학생 등록
}
