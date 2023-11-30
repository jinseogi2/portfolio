package com.portfolio.springboot.dto;

import com.portfolio.springboot.entity.NoticeEntity;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeEdDto {
    private Long noticeNo;
    private String noticeType;
    private String noticeTitle;
    private String noticeContent;
    private String noticeImageUrl;
    private LocalDateTime noticeDatetime;

    // NoticeEdDto에서 NoticeEntity로 변환하는 메서드
    public NoticeEntity toEntity() {
        return NoticeEntity.builder()
                .noticeNo(this.noticeNo)
                .noticeType(this.noticeType)
                .noticeTitle(this.noticeTitle)
                .noticeContent(this.noticeContent)
                .noticeImageUrl(this.noticeImageUrl)
                .noticeDatetime(this.noticeDatetime)
                .build();
    }

    // NoticeEntity에서 NoticeEdDto로 변환하는 메서드
    public static NoticeEdDto fromEntity(NoticeEntity entity) {
        return NoticeEdDto.builder()
                .noticeNo(entity.getNoticeNo())
                .noticeType(entity.getNoticeType())
                .noticeTitle(entity.getNoticeTitle())
                .noticeContent(entity.getNoticeContent())
                .noticeImageUrl(entity.getNoticeImageUrl())
                .noticeDatetime(entity.getNoticeDatetime())
                .build();
    }
}