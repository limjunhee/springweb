package example.practice4.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.practice4.model.dto.CourseDto;
import example.practice4.model.dto.StudentDto;
import example.practice4.model.entity.CourseEntity;
import example.practice4.model.repository.CourseRepository;
import example.practice4.model.repository.StudentRepository;

@Service 
public class CourseService {
    @Autowired private CourseRepository courseRepository;
    @Autowired private StudentRepository studentRepository;


    // [1] 과정 등록
    public boolean addCourse(CourseDto courseDto){
        CourseEntity courseEntity = courseDto.toEntity();

        CourseEntity savedCourseEntity = courseRepository.save(courseEntity);

        if (savedCourseEntity.getCourseId() >= 1) {
            return true;
        } else {
            return false;
        }
    }

    //[2] 과정 조회
    public List<CourseDto> printCourse(){
        List<CourseEntity> courseEntities = courseRepository.findAll();

        ArrayList<CourseDto> courseDtos = new ArrayList<>();

        courseEntities.forEach((courseEntity) -> { // 2-1: 하나씩 과정엔티티 꺼내서
            CourseDto courseDto = CourseDto.from(courseEntity); // 2-2: 과정엔티티 -> 과정dto 변환
            // *** 과정DTO에 학생목록 추가!!! ***
            // * 현재 과정(course) ---> 수강기록(enroll)들을 반복하여 --> 수강기록 --> 학생(student)
            courseEntity.getEntities().forEach((enroll) -> {
                StudentDto studentDto = StudentDto.from(enroll.getStudentEntity());
                courseDto.getStudentdto().add(studentDto);
            });
            courseDtos.add(courseDto); // 2-3 변환된 dto 리스트에 저장
        });

        return courseDtos;
    }
}
