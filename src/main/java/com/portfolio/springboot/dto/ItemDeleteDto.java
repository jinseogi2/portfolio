package com.portfolio.springboot.dto;

import com.portfolio.springboot.entity.ItemEntity;
import com.portfolio.springboot.entity.MemberEntity;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDeleteDto {
    private Long itemNo;

    public static ItemDeleteDto toDto(ItemEntity entity){
        return ItemDeleteDto.builder()
                .itemNo(entity.getItemNo())
                .build();

}


}
