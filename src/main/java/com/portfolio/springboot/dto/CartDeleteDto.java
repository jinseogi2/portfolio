package com.portfolio.springboot.dto;

import com.portfolio.springboot.entity.CartEntity;
import com.portfolio.springboot.entity.ItemEntity;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDeleteDto {
    private Long cartNo;
}