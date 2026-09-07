package example.practice4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.practice4.model.dto.CourseDto;
import example.practice4.service.CourseService;

@RestController 
@RequestMapping("/api/course")
public class CourseController {
    @Autowired private CourseService courseService;

    // [1] 과정 등록
    @PostMapping("")
    public boolean addCourse(@RequestBody CourseDto courseDto){
        return courseService.addCourse(courseDto);
    }

    // [2] 과정 조회
    @GetMapping("")
    public List<CourseDto> printCourse(){
        return courseService.printCourse();
    }
}
