package example.day10;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // JPA 사용 시 기본적인 CRUD 메소드 제공, save/findAll/findById/deleteById

    // * 메소드 쿼리(망명규칙) 또는 네이티브 쿼리 추가 가능하다.
    // findByxxx : findById 밖에 없으니, xxx를 기준으로 한 조회 추상 메소드를 만들기 (카멜 표기법 필수)
    MemberEntity findByMid(String mid);      // mid 일치하는 엔티티 조회
    Optional<MemberEntity> findByMname(String mname);  // mname 일치하는 엔티티 조회
}
