package com.portfolio.springboot.dto;


import com.portfolio.springboot.entity.MemberEntity;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDeleteDto {
    private Long memberNo;

    public static MemberDeleteDto toDto(MemberEntity entity){
        return MemberDeleteDto.builder()
                .memberNo(entity.getMemberNo())
                .build();
    }
}
