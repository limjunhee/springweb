package example.practice4.model.entity;

import java.util.ArrayList;

import example.Closet_Practice.BaseTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity 
@Table(name = "student")
@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor 
public class StudentEntity extends BaseTime{
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer studentId;

    private String studentName; 
    
    @OneToMany(mappedBy = "studentEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude // 순환참조 방지
    @Builder.Default // 빌더 패턴 시 기본값 사용
    private ArrayList<EnrollEntity> entities = new ArrayList<>();
}