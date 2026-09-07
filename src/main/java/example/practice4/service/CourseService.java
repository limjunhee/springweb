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
        List<CourseEntity> entityList = courseRepository.findAll();

        ArrayList<CourseDto> dtoList = new ArrayList<>();

        for (CourseEntity entity : entityList){
            CourseDto dto = CourseDto.from(entity);

            // 과정 나올 때 학생 목록도 같이 나오게 한다.
            // 현재 과정 내 학생 엔티티를 찾는다
            // * 현재 과정(course) ---> 수강기록(enroll)들을 반복하여 탐색

        }

        return dtoList;
    }
}
