package example.practice4.model.entity;

import example.Closet_Practice.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity 
@Table(name = "enroll")
@Data 
@ToString 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class EnrollEntity extends BaseTime{
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer enrollId;

    @Column
    private String status;


    // FK 단방향 연결 설정
    // 참고: 카멜 표기법이 아니라, _로 대신한다. 예) courseId -> course_id
    // FK: 과정 번호
    @JoinColumn (name = "course_id")
    @ManyToOne 
    private CourseEntity courseEntity;

    // FK: 학생 번호
    @JoinColumn (name = "student_id")
    @ManyToOne 
    private StudentEntity studentEntity;
}
