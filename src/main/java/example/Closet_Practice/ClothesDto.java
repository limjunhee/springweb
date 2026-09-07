package example.Closet_Practice;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ClothesDto {
    private Integer mno; // 의류를 가진 회원 번호(FK)
    private Integer cno; // 의류 종류(카테고리) 번호(FK)

    private Integer clno; // 옷 번호( clothes의 PK)
    private String clcolor; // 옷 색깔
    private String clname; // 옷 이름
    private String retype; // 보유 및 처리상태
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // 
    // dto를 entity로
    public ClothesEntity toEntity(){
        return ClothesEntity.builder()
                            .clno(this.clno)
                            .clcolor(this.clcolor)
                            .clname(this.clname)
                            .retype(this.retype)
                            .build();
    }

    // entity를 dto로
    public ClothesDto from(ClothesEntity clothesEntity){
        return ClothesDto.builder()
                        .clno(clothesEntity.getClno())
                        .clcolor(clothesEntity.getClcolor())
                        .clname(clothesEntity.getClname())
                        .retype(clothesEntity.getRetype())
                        .createDate(clothesEntity.getCreateDate())
                        .updateDate(clothesEntity.getUpdateDate())
                        .build();
    }
}
