package com.portfolio.springboot.dto;

import com.portfolio.springboot.entity.CartEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketDto {
    private long cartNo;
    private String cartName;
    private String cartImgUrl1;
    private String cartPrice;
    private int cartOptionPrice1;
    private int cartOptionPrice2;
    private int cartOptionPrice3;
    private long cartCount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime cartDatetime;

    public static BasketDto toCartDto(CartEntity entity){
        return BasketDto.builder()
                .cartNo(entity.getCartNo())
                .itemName(entity.getItemName())
                .itemImageUrl(entity.getItemImageUrl())
                .itemPrice(entity.getItemPrice())
                .cartitemOptionprice1(entity.itemOptionprice1())
                .cartitemOptionprice2(entity.itemOptionprice2())
                .cartitemOptionprice3(entity.itemOptionprice3())
                .cartDatetime(entity.getCartDatetime())
                .build();
    }
}