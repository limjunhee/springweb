package example.practice4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.practice4.model.dto.StudentDto;
import example.practice4.service.StudentService;

@RestController 
@RequestMapping ("/api/student")
public class StudentController {
    @Autowired private StudentService studentService;

    // 새로운 학생 등록
    @PostMapping ("")
    public boolean addStudent(@RequestBody StudentDto studentDto){
        return studentService.addStudent(studentDto);
    }

    //학생 삭제
    @DeleteMapping ("")
    public boolean deleteStudent(@RequestParam (name = "studentId") Integer studentId){
        return studentService.deleteStudent(studentId);
    }
}
