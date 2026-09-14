package example.totalpratice1.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import example.totalpratice1.model.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor 
@ToString @Builder 
public class CategoryDto {
    private Integer no;
    private String name;
    
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    // // 제품 목록
    // @Builder.Default
    // private List<ProductEntity> products;

    public CategoryEntity toEntity(){
        return CategoryEntity.builder()

                            .build();
    }

    public static CategoryDto from( CategoryEntity categoryEntity){
        return CategoryDto.builder()
                            
                            .build();
    }
}