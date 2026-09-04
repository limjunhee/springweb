package example.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class TestService {
    @Autowired private TestRepository testRepository;

    // 1. 전체조회
    public List<TestDto> 전체조회(){
        // 1. 모든 엔티티 조회한다.
        List<TestEntity> entities = testRepository.findAll();

        // 2. 모든 엔티티를 DTO로 변환하기
        // 빈 리스트 생성
        List<TestDto> list = new ArrayList<>();

        // 모든 엔티티 반복하여 DTO로 변환하여 새로운 리스트 저장
        // 리스트 객체.foreach( (반복변수) -> {} )
        entities.forEach((entity) -> {
            //리스트 내 하나씩 entity(반복변수)에 대입 반복
            // TestDto 내 entity -> dto변환함수 : from
            TestDto dto = TestDto.from(entity);
            // 변환 결과를 새로운 리스트에 담기
            list.add(dto);
        } );

        // 3. 반환
        return list;
    }

    // 2. 저장
    public boolean 저장( TestDto testDto ){
        // 1. dto를 entity로 바꿔야한다.
        TestEntity testEntity = testDto.toEntity();
        // 2. entity save저장
        TestEntity savedEntity = testRepository.save(testEntity);
        // 3. 
        if (savedEntity.getNo() >= 1) {
            return true;
        }
        return false;
    }

    // 3. 수정
    @Transactional //트랜잭션
    public boolean 수정(TestDto testDto){
        // 1. 수정하려는 엔티티의 PK 찾기
        Optional<TestEntity> optional = testRepository.findById(testDto.getNo());

        // 2. 찾으려는 엔티티가 존재한다면?
        if (optional.isPresent()) {
            // 3. 엔티티 꺼낸다.
            TestEntity entity = optional.get();

            // 4. setter 메소드 이용한 수정
            entity.setPrice(testDto.getPrice());
            entity.setDescri(testDto.getDescri());
            return true;
        }
        return false;
    }
}
