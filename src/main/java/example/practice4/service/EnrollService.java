package example.practice4.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.practice4.model.dto.EnrollDto;
import example.practice4.model.entity.CourseEntity;
import example.practice4.model.entity.EnrollEntity;
import example.practice4.model.entity.StudentEntity;
import example.practice4.model.repository.CourseRepository;
import example.practice4.model.repository.EnrollRepository;
import example.practice4.model.repository.StudentRepository;

@Service
public class EnrollService {
    @Autowired private EnrollRepository enrollRepository;
    @Autowired private StudentRepository studentRepository;
    @Autowired private CourseRepository courseRepository;

    public boolean enrollAdd( EnrollDto enrollDto){
        // DTO 내 FK 값을 ENTITY로 변환함
        EnrollEntity enrollEntity = enrollDto.toEntity();

        Optional<StudentEntity> optional1 = studentRepository.findById(enrollDto.getStudentId());
        Optional<CourseEntity> optional2 = courseRepository.findById(enrollDto.getCourseId());
        
        if (optional1.isPresent() && optional2.isPresent()) {

            // 학생 엔티티 꺼내서 enroll 엔티티에 대입
            StudentEntity studentEntity = optional1.get();
            // 과정 엔티티 꺼내서 enroll 엔티티에 대입
            CourseEntity courseEntity = optional2.get();

            enrollEntity.setStudentEntity(studentEntity);
            enrollEntity.setCourseEntity(courseEntity);
        }
        EnrollEntity savedEnrollEntity = enrollRepository.save(enrollEntity);

        if (savedEnrollEntity.getEnrollId() >= 1) {
            // DTO 내 FK 값을 엔티티로 변환
            return true;
        } else {
            return false;
        }
    }

    public EnrollDto enrollPrint( Integer enrollId ){
        // Optional<> 클래스는 null 예외검사 메소드 제공, isPresent() / .orElse()
        EnrollEntity enrollEntity = enrollRepository.findById(enrollId).orElse(null);
        return EnrollDto.from(enrollEntity);
    }
}
