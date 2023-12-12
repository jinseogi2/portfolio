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
    private String cartImageUrl;
    @Column(name = "cart_price")
    private long cartPrice;
    @Column(name = "cart_item_amount")
    private long cartItemAmount;
    @Column(name = "cart_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime cartDate;

    public static BasketEntity BasketEntity(BasketDto dto) {
        return BasketEntity.builder()
                .cartNo(dto.getCartNo())
                .itemName(dto.getCartName())
                .cartImageUrl1(dto.getCartImageurl1()) // 수정된 부분
                .itemPrice(dto.getItemPrice())
                .cartItemAmount(dto.getCartItemAmount())
                .cartDate(dto.getCartDate())
                .build();
    }

    public static BasketEntity toEntity(BasketDto dto) {
        return BasketEntity.builder()
                .cartNo(dto.getCartNo())
                .cartCode(dto.getCartCode())
                .itemCode(dto.getItemCode())
                .itemName(dto.getCartName())
                .itemImageUrl(dto.getItemImageUrl()) // 수정된 부분
                .itemPrice(dto.getItemPrice())
                .cartItemAmount(dto.getCartItemAmount())
                .cartDate(dto.getCartDate())
                .build();
    }
}
