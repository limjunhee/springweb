package example.totalpratice1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.totalpratice1.model.dto.CategoryDto;
import example.totalpratice1.model.entity.CategoryEntity;
import example.totalpratice1.model.entity.ProductEntity;
import example.totalpratice1.model.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private ProductEntity productEntity;

    public CategoryDto addCategory( CategoryDto categoryDto ){
        // dto -> entity
        CategoryEntity entity = categoryDto.dtoToEntity();

        // 카테고리에 속한 제품 초기값 -> null
        entity.setProductEntities(null);

        CategoryEntity savedCategoryEntity = categoryRepository.save(entity);

        //pk 여부로 성공 확인
        if (savedCategoryEntity.getCno() >= 1) {
            
            System.out.println("카테고리 등록 성공");
            return categoryDto;
        } else {
            return null;
        }
    }

    public List<CategoryDto> printCategory(){
        List<CategoryEntity> entities = categoryRepository.findAll();

        List<CategoryDto> dtos = new ArrayList<>();

        for(CategoryEntity entity : entities){
            CategoryDto dto = CategoryDto.entityToDto(entity);

            dtos.add(dto);
        }

        return dtos;
    }

    public boolean deleteCategory(Integer cno){
        CategoryEntity entity = categoryRepository.findById(cno).orElse(null);
        if (entity.getCno() >= 1) {
            categoryRepository.deleteById(cno);
            return true;
        }
        return false;
    }
}
