package example.day04;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // 비지니스로직 담당하는 객체(빈) 등록
public class ExamService {
    private final ExamRepository examRepository;
    // 리포지토리 호출
    // 리포지토리 객체.findAll() : (구현체) select 지원
    // [1] 전체 조회
    public List<ExamEntity> findAll(){
        return examRepository.findAll();
    }

    // [2] 저장
    public boolean 저장 (ExamEntity entity){
        // 리포지토리 호출
        // 리포지토리객체.save( 저장할entity) : insert 지원
        // save 반환값은 영속(매핑/저장)된 엔티티 반환
        ExamEntity saved = examRepository.save(entity);

        // save된 엔티티의 pk가 존재하면 저장 성공
        if (saved.getEno() >= 1) {
            return true;
        }
        // pk 없으면 실패
        return false;
    }

    // [3] 삭제
    public boolean 삭제( int no ){
        // 리포지토리 호출
        // 리포지토리객체.deleteById( 삭제할PK번호 )
        // 반환타입 : 없음, 삭제 여부 findXXX
        examRepository.deleteById(no);

        return true;
    }
    
    // [4] 수정 
    @Transactional // 트랜잭션 : 여러 개의 SQL을 하나의 (논리)단위로 묶음
    // SQL문 중 만약 하나라도 오류가 난다면 ROLLBACK, 모두 정상이면 COMMIT
    // 활용처(계좌이체 -> 출금/입금) , 회원가입시포인트지급(회원가입, 최초포인트지급)
    public boolean 수정(ExamEntity examEntity){
        // 1. 영속된 엔티티 조회(PK == 수정할 번호)
        // 입력받은 엔티티의 영속받은 *진짜 엔티티*를 가져온다.
        // 리포지토리객체.findById(조회할PK번호) : select SQL 지원
        // 반환 타입: Optional<엔티티>
        // Optional 클래스란? 본문(객체) 감싼 클래스(왜? null 예외 안전하게 사용하기 위함)
        //  -> 만약 조회 결과 엔티티가 없을 때 .getEno() 오류가 발생 -> 자바에서 예외 1등 : nullPointerException
        //  Optional<객체타입> 변수명: 객체 래핑하여 null 검사 지원
        Optional<ExamEntity> optional
        = examRepository.findById(examEntity.getEno());

        // 2. 조회된 결과 엔티티 여부 확인
        if(optional.isPresent()){ // 객체가 있으면 true, 없다면 false
            ExamEntity savedEntity = optional.get(); // 래핑된 Optional에서 엔티티 꺼낸다

            //3. 만약 엔티티가 존재하면 수정 -> update SQL 대신에 setter
            savedEntity.setEname(examEntity.getEname());

            return true;
        }

        return false;
    }
}
