package example.day05;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TestDto { // 서로 계층간 이동 객체( Controller에서는 Entity 사용 금지 )
    // 엔티티와 동일하게 멤버변수 구성: 기능별로 DTO 구성 
    // 예) 등록DTO, 조회DTO, 수정DTO -> 실제로는 기능 별로 모두 다르게 만들어야 한다.
    private Integer no;
    private String name;
    private String descri;
    private Integer price;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // DTO ----> ENTITY 변환 함수 : 컨트롤러에서 서비스로 이동할 때 DTO를 ENTITY로
    // toEntity() -> 주로 save 목적임
    // 엔티티를 외부에 노출시키지 않기 위해, 필요한 부분만 변환시키자 (현재 코드는 통합된 버전이라 체감되지 않을 것)
    
    public TestEntity toEntity(){
        return TestEntity.builder() // 빌더 패턴 : new 객체() 대신에 객체 생성을 메소드하는 방식
                         .name(this.name)
                         .descri(this.descri)
                         .price(this.price)
                         .build(); // 빌더패턴 end
    } 
    
    // ENTITY --> DTO 함수 : 서비스에서 컨트롤러로 이동할 때 ENTITY를 DTO로, from( Entity entity ), find 목적으로 사용한다
    // static : 인스턴스 없이 호출 가능한 메소드 또는 멤버 변수(상수)
    public static TestDto from( TestEntity testEntity ){
        return TestDto.builder() // 빌더 시작, 순서/개수 상관없이 자유롭게 객체 생성
                        .no(testEntity.getNo())
                        .name(testEntity.getName())
                        .descri(testEntity.getDescri())
                        .price(testEntity.getPrice())
                        .createDate(testEntity.getCreateDate())
                        .updateDate(testEntity.getUpdateDate())
                        .build();
    }
}

// 위에는 this 썼는데 아래에서는 testEntity 불러와서 하는 이유?
// this : 해당 메소드를 호출한 인스턴스를 가리킴
// static은 인스턴스가 없으므로 this 아예 불가능