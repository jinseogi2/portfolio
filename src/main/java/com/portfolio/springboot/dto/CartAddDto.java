package com.portfolio.springboot.dto;

import jakarta.persistence.Column;
import lombok.*;

// let cartName = document.getElementById("inner-title").innerText;
//         let cartImageUrl = document.getElementById("inputcartImageUrl").src;
//         let cartPrice = document.getElementById("inputPrice").value;
//         let cartCount = document.getElementById("result").innerText;
//         let cart_option1_name = document.getElementById("option").innerText;
//         let cart_option1_price = document.getElementById("option").value;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartAddDto {
    private String cartName;
    private String cartImageUrl;
    private Integer cartPrice;
    private Integer cartCount;
    private String cartOption1Name;
    private Integer cartOption1Price;
}
