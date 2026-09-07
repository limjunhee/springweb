package example.practice4.model.dto;

import java.time.LocalDateTime;

import example.practice4.model.entity.EnrollEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor 
@AllArgsConstructor 
@Getter @Setter @ToString @Builder 
public class EnrollDto {
    private Integer enrollId;
    private String status;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // 자바(JPA)에서 entity 로 FK 사용하지만 입력받을 경우 FK번호 받는다.
    private Integer courseId;
    private Integer studentId;

    // 과정명, 학생명 조회 용도
    private String studentName;
    private String courseName;

    // dto -> entity
    // 과정 FK, 학생 FK는 서비스에서 변환할 것.
    public EnrollEntity toEntity(){
        return EnrollEntity.builder()
                            .status(this.status)
                            .build();
    }

    // entity -> dto
    public static EnrollDto from(EnrollEntity enrollEntity){
        return EnrollDto.builder()
                        .enrollId(enrollEntity.getEnrollId())
                        .status(enrollEntity.getStatus())
                        .courseName(enrollEntity.getCourseEntity().getCourseName())
                        .studentName(enrollEntity.getStudentEntity().getStudentName())
                        .build();
    }
}
