package com.portfolio.springboot.dto;


import com.portfolio.springboot.entity.MemberEntity;
import com.portfolio.springboot.entity.NoticeEntity;
import lombok.*;

import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeAddDto {
    private String noticeType;
    private String noticeTitle;
    private String noticeContent;
    private String noticeImageUrl;
    private LocalDateTime noticeDatetime;

}
