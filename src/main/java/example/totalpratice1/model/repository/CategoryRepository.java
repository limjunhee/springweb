package example.totalpratice1.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.totalpratice1.model.dto.CategoryDto;

@Repository 
public interface CategoryRepository extends JpaRepository<CategoryDto, Integer> {
    
}
