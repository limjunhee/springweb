package example.practice4.model.dto;

import java.time.LocalDateTime;

import example.practice4.model.entity.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
@ToString 
@Getter 
@Setter
public class StudentDto {
    private Integer studentId;
    private String studentName;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // dto -> entity : 학생 등록용
    public StudentEntity toEntity(){
        return StudentEntity.builder()
                            .studentName(this.studentName)
                            .build();
    }

    // entity -> dto : 학생명 출력용
    public static StudentDto from(StudentEntity studentEntity){
        return StudentDto.builder()
                        .studentId(studentEntity.getStudentId())
                        .studentName(studentEntity.getStudentName())
                        .createDate(studentEntity.getCreateDate())
                        .updateDate(studentEntity.getUpdateDate())
                        .build();
    }
}
