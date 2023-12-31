package com.portfolio.springboot.dto;

import com.portfolio.springboot.entity.NoticeEntity;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDto {
    private Long noticeNo;
    private String noticeType;
    private String noticeTitle;
    private String noticeContent;
    private String noticeImageUrl;
    private LocalDateTime noticeDatetime;

    public static NoticeDto toDto(NoticeEntity entity) {
        return NoticeDto.builder()
                .noticeNo(entity.getNoticeNo())
                .noticeType(entity.getNoticeType())
                .noticeTitle(entity.getNoticeTitle())
                .noticeContent(entity.getNoticeContent())
                .noticeImageUrl(entity.getNoticeImageUrl())
                .noticeDatetime(entity.getNoticeDatetime())
                .build();
    }
    public static NoticeEntity toNoticeEntity(NoticeEdDto edDto) {
        return NoticeEntity.builder()
                .noticeNo(edDto.getNoticeNo())
                .noticeType(edDto.getNoticeType())
                .noticeTitle(edDto.getNoticeTitle())
                .noticeContent(edDto.getNoticeContent())
                .noticeImageUrl(edDto.getNoticeImageUrl())
                .noticeDatetime(LocalDateTime.now())  // 작성시간은 현재 시간으로 설정
                .build();
    }

}
