package example.day06;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="board")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardEntity {
    @Id
    private Integer bno;
    private String bname;

    //FK - 단방향 참조
    @ManyToOne // M:1 (다대일) - 다수가 하나에게 참조함
    @JoinColumn(name = "cno") // FK이름 지정, 주로 pk와 동일하게.
    private CategoryEntity categoryEntity;

    // 양방향 참조
    @OneToMany(mappedBy = "boardEntity")
    @ToString.Exclude // 순환 참조 방지
    @Builder.Default // 빌더 사용시 초기 값 디폴트로 사용
    private List<ReplyEntity> replyList = new ArrayList<>();
}
