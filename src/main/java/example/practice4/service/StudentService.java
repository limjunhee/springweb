package example.practice4.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.practice4.model.dto.StudentDto;
import example.practice4.model.entity.StudentEntity;
import example.practice4.model.repository.StudentRepository;

@Service 
public class StudentService {
    @Autowired private StudentRepository studentRepository;

    // 학생 등록
    public boolean addStudent(StudentDto studentDto){
        StudentEntity studentEntity = studentDto.toEntity();

        StudentEntity savedStudentEntity = studentRepository.save(studentEntity);

        if (savedStudentEntity.getStudentId() >= 1) {
            return true;
        } else {
            return false;
        }
    }

    // 학생 삭제
    // PK가 삭제될 때 연관된 FK가 존재하면 제약 조건을 확인할 것!
    // cascade = CascadeType.REMOVE / SQL : on delete cascade
    public boolean deleteStudent(Integer studentId){
        Optional<StudentEntity> optional = studentRepository.findById(studentId);

        if (optional.isPresent()) {
            studentRepository.deleteById(studentId);
            return true;
        } else {
            return false;
        }
    }
}
