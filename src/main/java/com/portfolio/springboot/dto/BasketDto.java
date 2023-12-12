package com.portfolio.springboot.dto;

import com.portfolio.springboot.entity.BasketEntity;
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
    private int cartPrice;
    private int cartOptionPrice1;
    private int cartOptionPrice2;
    private int cartOptionPrice3;
    private long cartCount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime cartDatetime;

    public static BasketDto toCartDto(BasketEntity entity){
        return BasketDto.builder()
                .cartNo(entity.getCartNo())
                .cartName(entity.getCartName())
                .cartImgUrl1(entity.getCartImageUrl1())
                .cartPrice(entity.getCartPrice())
                .cartOptionPrice1(entity.getCartOptionPrice1())
                .cartOptionPrice2(entity.getCartOptionPrice2())
                .cartOptionPrice3(entity.getCartOptionPrice3())
                .cartDatetime(entity.getCartDateTime())
                .build();
    }
}