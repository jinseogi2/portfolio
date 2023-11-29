package com.portfolio.springboot.dto;


import com.portfolio.springboot.entity.ItemEntity;
import com.portfolio.springboot.entity.MemberEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemEdDto {
    private Long itemNo;
    private String itemCode;
    private String itemName;
    private String itemCate;
    private Integer itemRecommend;
    private Integer itemPrice;
    private String itemImageUrl;
    private String itemExplanation;
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime itemUpdateDatetime;

    public static ItemEdDto toDto(ItemEntity entity){
        return ItemEdDto.builder()
                .itemNo(entity.getItemNo())
                .itemCode(entity.getItemCode())
                .itemName(entity.getItemName())
                .itemCate(entity.getItemCate())
                .itemRecommend(entity.getItemRecommend())
                .itemPrice(entity.getItemPrice())
                .itemImageUrl(entity.getItemImageUrl())
                .itemExplanation(entity.getItemExplanation())
                .itemUpdateDatetime(entity.getItemUpdateDatetime())
                .build();
    }
}
