package com.portfolio.springboot.dto;


import com.portfolio.springboot.entity.ItemEntity;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemAddDto {

    private Long itemNo;
    private String itemCode;
    private String itemName;
    private String itemCate;
    private Integer itemRecommend;
    private Integer itemPrice;
    private String itemImageUrl;
    private String itemExplanation;


    public static ItemAddDto toDto(ItemEntity entity){
        return ItemAddDto.builder()
                .itemNo(entity.getItemNo())
                .itemCode(entity.getItemCode())
                .itemName(entity.getItemName())
                .itemCate(entity.getItemCate())
                .itemRecommend(entity.getItemRecommend())
                .itemPrice(entity.getItemPrice())
                .itemImageUrl(entity.getItemImageUrl())
                .itemExplanation(entity.getItemExplanation())
                .build();
    }
}
