package example.practice2;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // 비지니스 로직 담당하는 객체 등록(Bean)
public class TestService {
    private final TestRepository testRepository; //리포지토리 호출

    // 1 - 게시물 등록
    public boolean testWrite(TestEntity testEntity){
        TestEntity saved = testRepository.save(testEntity); //insert 지원

        // save된 엔티티의 pk가 존재하면? -> 저장 성공
        if (saved.getNo() >= 1) {
            return true;
        }

        // 없다면 실패
        return false;
    }

    // 2 - 게시물 전체 조회
    public List<TestEntity> testPrint(){
        return testRepository.findAll();
    }

    // 3 - 게시물 개별 조회
    public TestEntity testDetail(int no){
        // Optional로 래핑하지 않는 법 -> findById(no) 뒤에 예외객체 던지는 메서드인 .orElseThrow() 사용
        // 어째서 가능한가? -> .orElseThrow()는 Optional<T>이고, 변환 타입은 Optional<T>가 아닌 T이기 때문이다. (자동으로 제네릭에 들어간 타입으로 반환)
        
        // 리포지토리객체.findById(*번호*).orElseThrow(() -> new IllegalArgumentException( *콘솔에 출력할 문장 또는 변수* ));
        TestEntity savedEntity = testRepository.findById(no)
                                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. no=" + no));

                                // 없는 번호를 매개변수로 전달했을 경우 콘솔 출력 결과 : java.lang.IllegalArgumentException: 해당 게시물이 없습니다. no=23
        return savedEntity;
    }
    // 4 - 게시물 삭제
    public boolean testDelete(int no){
        // 리포지토리 호출
        testRepository.deleteById(no);
        
        return true;
    }
    // 5 - 게시물 수정
    @Transactional // 여러 개의 SQL을 하나의 논리적 단위로 묶는 트랜잭션
    public boolean testUpdate( TestEntity testEntity ){
        // 1. PK 기반으로 영속된 엔티티를 조회하기(Null 값(=레코드 없음)도 안정적으로 처리하기 위해)
        Optional<TestEntity> optional = testRepository.findById(testEntity.getNo());

        // 2. 조회된 결과, 즉 엔티티의 여부를 확인하기
        if (optional.isPresent()) {
            TestEntity savedEntity = optional.get(); // 래핑된 Optional에서 엔티티 꺼내기

            //3. 엔티티가 존재하니 수정한다 -> sql update 말고 setter로
            savedEntity.setContent(testEntity.getContent());
            savedEntity.setWriter(testEntity.getWriter());

            return true;
        }
        return false;
    }
    // @Transactional이 없다면?
    // 1. testRepository.findById(testEntity.getNo());를 호출하는 순간 하나짜리 트랜잭션이 열렸다가 바로 끝나버림
    //      (Spring data JPA가 각 리포지토리 메소드에 기본으로 트랜잭션을 걸어주기 때문)
    // 2. findById()가 끝나자마자 영속성 컨텍스트가 닫혀버리고, 반환된 savedEntity는 준영속(detached) 상태가 됨
    // 3. 그 이후 savedEntity.setContent(testEntity.getContent()); 를 해도 자바 객체의 필드값만 바뀌고, 아무도 그 변경을 감지하거나 DB에 반영하지 않는다.
    // 4. 결국 true로 반환하지만 DB에 변화는 하나도 없게 된다.
}