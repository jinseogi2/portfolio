package com.portfolio.springboot.entity;


// CREATE TABLE megacoffee.cart (
// 	cart_no INT AUTO_INCREMENT NOT NULL PRIMARY KEY, -- 카트 번호
// 	cart_name VARCHAR(255) NOT NULL, -- 상품 이름
// 	cart_image_url TEXT NULL, -- 카트에 들어오는 이미지 url ( 아이탬 )
// 	cart_price INT NOT NULL, -- 상품 금액
// 	cart_count TINYINT NOT NULL, -- 상품 개수

//    cart_option1_name VARCHAR(255) NULL, -- 옵션1 이름
//    cart_option1_price INT NULL, -- 옵션1 금액
//    cart_option2_name VARCHAR(255) NULL, -- 옵션2 이름
//    cart_option2_price INT NULL, -- 옵션2 금액
//    cart_option3_name VARCHAR(255) NULL, -- 옵션3 이름
//    cart_option3_price INT NULL, -- 옵션3 금액
// 	cart_datetime DATETIME DEFAULT NOW() -- 작성시간
// );
import com.portfolio.springboot.dto.CartAddDto;
import com.portfolio.springboot.dto.CartEdDto;
import com.portfolio.springboot.dto.NoticeEdDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Entity
@Table(name = "cart")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_no")
    private Long cartNo;
    @Column(name = "cart_name")
    private String cartName;
    @Column(name = "cart_image_url", nullable = false)
    private String cartImageUrl;
    @Column(name = "cart_price", nullable = false)
    private Integer cartPrice;
    @Column(name = "cart_count", nullable = false)
    private Integer cartCount;

    @Column(name = "cart_option1_name")
    private String cartOption1Name;
    @Column(name = "cart_option1_price")
    private Integer cartOption1Price;

    @Column(name = "cart_option2_name")
    private String cartOption2Name;
    @Column(name = "cart_option2_price")
    private Integer cartOption2Price;

    @Column(name = "cart_option3_name")
    private String cartOption3Name;
    @Column(name = "cart_option3_price")
    private Integer cartOption3Price;


    @Column(name = "cart_datetime")
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime cartDatetime;

    public static CartEntity toEntity(CartAddDto dto) {
        return CartEntity.builder()
                .cartName(dto.getCartName())
                .cartImageUrl(dto.getCartImageUrl())
                .cartPrice(dto.getCartPrice())
                .cartCount(dto.getCartCount())
                .cartOption1Name(dto.getCartOption1Name())
                .cartOption1Price(dto.getCartOption1Price())
                .build();
    }
    public static CartEntity toEntity(CartEdDto dto) {
        return CartEntity.builder()
                .cartNo(dto.getCartNo())
                .cartCount(dto.getCartCount())
                .build();
    }
}



