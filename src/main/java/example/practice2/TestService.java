package example.practice2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

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
    public ArrayList<TestEntity> testPrint(){
        return testRepository.testPrint();
    }
    // 3 - 게시물 개별 조회

    // 4 - 게시물 삭제

    // 5 - 게시물 수정
}
