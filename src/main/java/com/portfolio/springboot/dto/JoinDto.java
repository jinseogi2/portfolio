package com.portfolio.springboot.dto;


import com.portfolio.springboot.entity.MemberEntity;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JoinDto {
    private String loginId;
    private String loginPw;

    public static JoinDto toDto(MemberEntity entity){
        return JoinDto.builder()
                .loginId(entity.getMemberId())
                .loginPw(entity.getMemberPw())
                .build();
    }
}