package com.portfolio.springboot.dto;

import com.portfolio.springboot.entity.ItemEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Long itemNo;
    private String itemCode;
    private String itemName;
    private String itemCate;
    private Integer itemRecommend;
    private String itemPrice;
    private String itemImageUrl;
    private String itemExplanation;
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime itemUpdateDatetime;

    public static ItemDto toDto(ItemEntity entity){
        return ItemDto.builder()
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