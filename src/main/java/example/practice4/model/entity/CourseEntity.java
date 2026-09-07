package example.practice4.model.entity;

import java.util.ArrayList;
import java.util.List;

import example.Closet_Practice.BaseTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name = "course")
@Getter @Setter @ToString @Builder 
@NoArgsConstructor @AllArgsConstructor 
public class CourseEntity extends BaseTime{
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer courseId;

    @Column
    private String courseName;

    // 양방향 선언
    @OneToMany(mappedBy = "courseEntity" , cascade = CascadeType.ALL, fetch = FetchType.LAZY ) // 일대다
    @ToString.Exclude // 순환참조 방지
    @Builder.Default // 빌더 패턴 사용 시 초기값 사용 선언
    private List<EnrollEntity> entities = new ArrayList<>();
}
