package example.day06;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "reply")
@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class ReplyEntity {
    @Id
    private Integer rno;
    private String rname;

    //단방향 참조
    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY) // 다대일()
    @JoinColumn(name = "bno")
    private BoardEntity boardEntity;

}
/*
    * 영속성: 프로그램이 꺼지거나 해도 영구적으로 데이터를 저장하는 속성(자바는 휘발성이다.)
    - Entity entity = new entity(); // 객체
    - repository.save(), repository.findAll(), repository.findById() 등등 결과 영속된
    - 즉) Entity 영속된 entity = repository.save( 비영속 entity )

    @ManyToOne( cascade = 영속성 제약 조건, fetch = 불러오는시기) 
        CascadeType.REMOVE : 만일 부모 엔티티가 삭제되면, 자식 엔티티도 같이 삭제된다.
        CascadeType.MERGE : 만일 부모 엔티티가 수정되면 자식 엔티티도 같이 수정사항이 반영된다.
        CascadeType.DETACH : 만일 부모 엔티티가 영속(연결) 해제하면 자힉 엔티티도 같이 해제한다. (DB에는 없고 자바에 있는 기능)
        CascadeType.REFRESH : 만일 부모 엔티티가 재호출(갱신)된디먄 자식 엔티티도 같이 갱신한다.
        CascadeType.PERSIST : 만약 부모 엔티티가 저장하면 자식 엔티티 같이 저장된다.
        CascadeType.ALL : [위 조건들을 모두 사용]

        FetchType.LAZY : 해당 엔티티 조회시 자식(참조)엔티티 불러오지 않는다.
            - 초기 로딩 빠르다, 재사용성은 느리다, 필요한 정보만 불러온다. <지연로딩>
        FetchType.EAGER : 해당 엔티티 조회시 자식(참조) 엔티티 불러온다.
            - 기본값, 초기 로딩 느리다, 재사용성 빠르다, 불필요한 정보까지 불러온다. <성능 저하>
*/