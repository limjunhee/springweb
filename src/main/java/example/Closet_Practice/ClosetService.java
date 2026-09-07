package example.Closet_Practice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ClosetService {
    @Autowired private ClosetRepository closetRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private CategoryRepository categoryRepository;


    // 의류 등록
    public boolean clothesAdd(ClothesDto clothesDto, int mno){
        // 1. dto를 entity로 바꾸기
        ClothesEntity clothesEntity = clothesDto.toEntity(); // fk = null 인 엔티티 가져옴

        UserEntity userEntity = userRepository.findById(mno)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다: " + mno)); //fk = 회원번호
        CategoryEntity categoryEntity = categoryRepository.findById(clothesDto.getCno())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다: " + clothesDto.getCno())); //fk = 의류카테고리(니트, 청바지 등등...)

        // null인 fk 대입
        clothesEntity.setUserEntity(userEntity);
        clothesEntity.setCategoryEntity(categoryEntity);

        // 2. 변환한 엔티티를 저장하기
        ClothesEntity savedClothesEntity = closetRepository.save(clothesEntity);


        // 3. 엔티티의 clno가 1 이상이라면?
        if (savedClothesEntity.getClno() >= 1) {
            return true;
        } else {
            return false;
        }
    }

    // 의류 전체조회
    public ArrayList<ClothesDto>clothesPrintAll(){
        
        // DB의 모든 의류 조회
        List<ClothesEntity> entityList = closetRepository.findAll();

        // 반환할 DTO 리스트 생성
        ArrayList<ClothesDto>dtoList = new ArrayList<>();

        // Entity -> DTO 변환 
        for(ClothesEntity entity : entityList ){

            ClothesDto dto = ClothesDto.builder()
            .clno(entity.getClno())
            .mno(entity.getUserEntity().getMno())
            .cno(entity.getCategoryEntity().getCno())
            .clname(entity.getClname())
            .clcolor(entity.getClcolor())
            .retype(entity.getRetype())
            .build();
        
            dtoList.add(dto);
        }

        // DTO 리스트 반환
        return dtoList;
    }
}
