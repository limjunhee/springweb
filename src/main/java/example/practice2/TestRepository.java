package example.practice2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 리포지토리 (DB 조작) 담당하는 객체 등록(Bean)
public interface TestRepository extends JpaRepository< TestEntity, Integer > {
    // <TestEntity, Integer> -> <조작할엔티티명, 엔티티PK타입> 
}
