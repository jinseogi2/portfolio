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
    private String itemPrice;
    private String itemImageUrl;
    private String itemExplanation;

    // itemUpdateDatetime은 추가할 때는 자동으로 현재 시간으로 설정하도록 하면 됩니다.
    // setter가 없으므로, 자동으로 현재 시간으로 설정됩니다.

    public static ItemAddDto toDto(ItemEntity entity) {
        return ItemAddDto.builder()

                .itemName(entity.getItemName())
                .itemCate(entity.getItemCate())
                .itemPrice(entity.getItemPrice())
                .itemImageUrl(entity.getItemImageUrl())
                .itemExplanation(entity.getItemExplanation())
                .build();
    }
}
