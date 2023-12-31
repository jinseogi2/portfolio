package com.portfolio.springboot.dto;

import com.portfolio.springboot.entity.MemberEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long memberNo;
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberEmail;
    private String memberRole; //권한 "ADMIN" "ROLE_USER"
    private Integer memberStamp;
    private LocalDateTime memberJoinDatetime; //가입일 "2023-10-12T23:12:23"

    public static MemberDto toDto(MemberEntity entity){
        return MemberDto.builder()
                .memberNo(entity.getMemberNo())
                .memberId(entity.getMemberId())
                .memberPw(entity.getMemberPw())
                .memberName(entity.getMemberName())
                .memberEmail(entity.getMemberEmail())
                .memberRole(entity.getMemberRole())
                .memberStamp(entity.getMemberStamp())
                .memberJoinDatetime(entity.getMemberJoinDatetime())
                .build();
    }
}