package com.portfolio.springboot.entity;

import com.portfolio.springboot.dto.ItemAddDto;
import com.portfolio.springboot.dto.ItemDto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_no")
    private Long itemNo;

    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "item_cate", nullable = false)
    private String itemCate;

    @Column(name = "item_recommend", nullable = false)
    private Integer itemRecommend;

    @Column(name = "item_price", nullable = false)
    private String itemPrice;

    @Column(name = "item_image_url", nullable = false)
    private String itemImageUrl;

    @Column(name = "item_explanation", nullable = false)
    private String itemExplanation;

    @Column(name = "item_update_datetime")
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime itemUpdateDatetime;

    public static ItemEntity toEntity(ItemDto dto) {
        return ItemEntity.builder()
                .itemNo(dto.getItemNo())
                .itemCode(dto.getItemCode())
                .itemName(dto.getItemName())
                .itemCate(dto.getItemCate())
                .itemRecommend(dto.getItemRecommend())
                .itemPrice(dto.getItemPrice())
                .itemImageUrl(dto.getItemImageUrl())
                .itemExplanation(dto.getItemExplanation())
                .itemUpdateDatetime(dto.getItemUpdateDatetime())
                .build();
    }

    public static ItemEntity toEntity(ItemAddDto dto) {
        return ItemEntity.builder()
                .itemName(dto.getItemName())
                .itemCate(dto.getItemCate())
                .itemPrice(dto.getItemPrice())
                .itemImageUrl(dto.getItemImageUrl())
                .itemExplanation(dto.getItemExplanation())
                .build();
    }
}
