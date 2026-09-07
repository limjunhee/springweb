package example.practice4.model.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor // 1. 자식 클래스의 호출이 가능하도록 선언
@MappedSuperclass // 테이블이 아닌, 클래스에 상속하는 용도임을 선언
@EntityListeners ( AuditingEntityListener.class ) // 감시 기능
public class BaseTime {
    @CreatedDate private LocalDateTime createdAt;
    @LastModifiedDate private LocalDateTime updatedAt;
}
