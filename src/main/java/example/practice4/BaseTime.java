package example.practice4;

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
    @CreatedDate
    private LocalDateTime createDate;

    // 2. 레코드 수정 시점
    @LastModifiedDate
    private LocalDateTime updateDate;
}
