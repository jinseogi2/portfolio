package com.portfolio.springboot.entity;

import com.portfolio.springboot.dto.BasketDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name="basket")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_no")
    private long cartNo;
    @Column(name = "cart_name")
    private String cartName;
    @Column(name = "cart_image_url_1")
    private String cartImageUrl1;
    @Column(name = "cart_price")
    private int cartPrice;
    @Column(name = "cart_option_price_1")
    private int cartOptionPrice1;
    @Column(name = "cart_option_price_2")
    private int cartOptionPrice2;
    @Column(name = "cart_option_price_3")
    private int cartOptionPrice3;
    @Column(name = "cart_datetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime cartDateTime;

    public static BasketEntity BasketEntity(BasketDto dto) {
        return BasketEntity.builder()
                .cartNo(dto.getCartNo())
                .cartName(dto.getCartName())
                .cartImageUrl1(dto.getCartImgUrl1()) // 수정된 부분
                .cartPrice(dto.getCartPrice())
                .cartOptionPrice1(dto.getCartOptionPrice1())
                .cartOptionPrice2(dto.getCartOptionPrice2())
                .cartOptionPrice3(dto.getCartOptionPrice3())
                .cartDateTime(dto.getCartDatetime())
                .build();
    }

    public static BasketEntity toEntity(BasketDto dto) {
        return BasketEntity.builder()
                .cartNo(dto.getCartNo())
                .cartName(dto.getCartName())
                .cartImageUrl1(dto.getCartImgUrl1()) // 수정된 부분
                .cartPrice(dto.getCartPrice())
                .cartOptionPrice1(dto.getCartOptionPrice1())
                .cartOptionPrice2(dto.getCartOptionPrice2())
                .cartOptionPrice3(dto.getCartOptionPrice3())
                .cartDateTime(dto.getCartDatetime())
                .build();
    }
}
