package com.portfolio.springboot.dto;


import com.portfolio.springboot.entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberEdDto {
    private Long memberNo;
    private String memberId;
    private String memberName;
    private String memberPw;
    private String memberEmail;
    private String memberRole; //권한 "ADMIN" "ROLE_USER"
    private Integer memberStamp;
    public static MemberEdDto toDto(MemberEntity entity){
        return MemberEdDto.builder()
                .memberNo(entity.getMemberNo())
                .memberId(entity.getMemberId())
                .memberName(entity.getMemberName())
                .memberPw(entity.getMemberPw())
                .memberEmail(entity.getMemberEmail())
                .memberRole(entity.getMemberRole())
                .memberStamp(entity.getMemberStamp())
               .build();
    }
}