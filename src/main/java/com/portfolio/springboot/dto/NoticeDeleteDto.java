package com.portfolio.springboot.dto;


import com.portfolio.springboot.entity.MemberEntity;
import com.portfolio.springboot.entity.NoticeEntity;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDeleteDto {
    private Long noticeNo;

    public static NoticeDeleteDto toDto(NoticeEntity entity){
        return NoticeDeleteDto.builder()
                .noticeNo(entity.getNoticeNo())
                .build();
    }

}
