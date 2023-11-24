package com.portfolio.springboot.entity;


import com.portfolio.springboot.dto.JoinDto;
import com.portfolio.springboot.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;


//@Entity : DB 테이블과 1:1로 매칭
@Entity
@Table(name="member")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity {
    @Id  //기본키로 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long memberNo;
    @Column(name = "member_id")
    private String memberId;
    @Column(name = "member_pw")
    private String memberPw;
    @Column(name = "member_name")
    private String memberName;
    @Column(name = "member_role")
    private String memberRole; //권한 "admin" "user"
    @Column(name = "member_email")
    private String memberEmail;
    @Column(name = "member_stamp")
    private Integer memberStamp;
    @Column(name = "member_join_datetime")
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime memberJoinDatetime; //가입일 "2023-10-12"

    public static MemberEntity toJoinEntity(JoinDto dto){
        return MemberEntity.builder()
                .memberNo(0L)
                .memberId(dto.getLoginId())
                .memberPw(dto.getLoginPw())
                .memberRole("ROLE_USER")
                .memberStamp(0)
                .memberJoinDatetime(LocalDateTime.now())
                .build();
    }
    public static MemberEntity toMemberEntity(MemberDto dto){
        return MemberEntity.builder()
                .memberNo(dto.getMemberNo())
                .memberId(dto.getMemberId())
                .memberPw(dto.getMemberPw())
                .memberName(dto.getMemberName())
                .memberRole(dto.getMemberRole())
                .memberStamp(dto.getMemberPoint())
                .memberJoinDatetime(dto.getMemberJoinDatetime())
                .build();
    }
}






