package com.portfolio.springboot.dto;

import com.portfolio.springboot.entity.ItemEntity;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemAddDto {
    private String itemName;
    private String itemCate;
    private Integer itemPrice;
    private String itemImageUrl;
    private String itemExplanation;
    private Integer itemRecommend;
    public static ItemAddDto toDto(ItemEntity entity) {
        return ItemAddDto.builder()

                .itemName(entity.getItemName())
                .itemCate(entity.getItemCate())
                .itemPrice(entity.getItemPrice())
                .itemRecommend(entity.getItemRecommend())
                .itemImageUrl(entity.getItemImageUrl())
                .itemExplanation(entity.getItemExplanation())
                .build();
    }
}
