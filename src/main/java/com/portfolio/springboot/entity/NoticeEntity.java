package com.portfolio.springboot.entity;

import com.portfolio.springboot.dto.NoticeEdDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="notice")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeNo;

    @Column(name = "notice_type")
    private String noticeType;

    @Column(name = "notice_title")
    private String noticeTitle;

    @Column(name = "notice_content")
    private String noticeContent;

    @Column(name = "notice_image_url")
    private String noticeImageUrl;

    @Column(name = "notice_datetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime noticeDatetime;

    public static NoticeEntity toEntity(NoticeEdDto dto) {
        return NoticeEntity.builder()
                .noticeNo(dto.getNoticeNo())
                .noticeType(dto.getNoticeType())
                .noticeTitle(dto.getNoticeTitle())
                .noticeContent(dto.getNoticeContent())
                .noticeImageUrl(dto.getNoticeImageUrl())
                .noticeDatetime(dto.getNoticeDatetime() != null ? dto.getNoticeDatetime() : LocalDateTime.now())
                .build();
    }
}