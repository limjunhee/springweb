package example.day05;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@MappedSuperclass
@EntityListeners( AuditingEntityListener.class)
public class BaseTime {
    // 1. 레코드 생성 시점
    // @CreatedDate : 엔티티가 영속화(INSERT)될 때의 현재 일시를 자동으로 기록
    @CreatedDate
    private LocalDateTime createDate;
    
    // 2. 레코드 변경 시점
    // @LastModifiedDate : 엔티티의 데이터가 변경(UPDATE)될 때의 변경 일시를 자동으로 갱신
    @LastModifiedDate
    private LocalDateTime upDateDate;

}
