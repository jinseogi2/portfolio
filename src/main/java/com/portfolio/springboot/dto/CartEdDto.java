package com.portfolio.springboot.dto;

import com.portfolio.springboot.entity.ItemEntity;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartEdDto {
    private Long cartNo;
    private Integer cartCount;
}
