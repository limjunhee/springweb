package example.practice4.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import example.practice4.model.entity.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor 
@NoArgsConstructor 
@Getter 
@Setter 
@ToString 
@Builder 
public class CourseDto {
    private Integer courseId;
    private String courseName;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // 학생 목록
    private List<StudentDto> studentdtos = new ArrayList<>();

    // dto -> entity : 과정등록 용도로 사용(save)
    public CourseEntity toEntity(){
        return CourseEntity.builder()
                            .courseName(this.courseName)
                            .build();
    }

    // entity -> dto : 과정정보 조회용
    public static CourseDto from(CourseEntity courseEntity){
        return CourseDto.builder()
                        .courseId(courseEntity.getCourseId())
                        .courseName(courseEntity.getCourseName())
                        .createDate(courseEntity.getCreateDate())
                        .updateDate(courseEntity.getUpdateDate())
                        .build();
    }
}
