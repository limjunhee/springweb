package example.totalpratice1.model.dto;

import example.totalpratice1.model.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor 
@ToString @Builder 
public class ProductDto {
    private Integer bno;

    private String name;
    private Integer price;

    private Integer cno;

    public ProductEntity dtoToEntity(){
        return ProductEntity.builder()
                .name(this.name)
                .price(this.price).build();
    }

    public static ProductDto entityToDto(ProductEntity productEntity){
        return ProductDto.builder()
                .bno(productEntity.getBno())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .cno(productEntity.getCategoryEntity().getCno()).build();
    }
}
