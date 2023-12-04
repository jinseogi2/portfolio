package com.portfolio.springboot.entity;


import com.portfolio.springboot.dto.NoticeAddDto;
import com.portfolio.springboot.dto.NoticeDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

//      notice_no INT AUTO_INCREMENT NOT NULL PRIMARY KEY, -- 게시글 번호
//      notice_type VARCHAR(255) NOT NULL,  -- 공지사항 종류(이벤트, 배송지연안내 등등)
//      notice_title VARCHAR(255) NOT NULL,  -- 공지사항 제목
//      notice_content TEXT NULL,  -- 공지사항 내용
//      notice_image_url TEXT NULL, -- 공지사항 첨부이미지 url
//      notice_datetime DATETIME DEFAULT NOW() -- 작성시간
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
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime noticeDatetime;

    public static NoticeEntity toNoticeEntity(NoticeDto dto){
        return NoticeEntity.builder()
                .noticeNo(dto.getNoticeNo())
                .noticeType(dto.getNoticeType())
                .noticeTitle(dto.getNoticeTitle())
                .noticeContent(dto.getNoticeContent())
                .noticeImageUrl(dto.getNoticeImageUrl())
                .noticeDatetime(dto.getNoticeDatetime())
                .build();
    }
    public static NoticeEntity toNoticeEntity(NoticeAddDto dto){
        return NoticeEntity.builder()
                .noticeNo(dto.getNoticeNo())
                .noticeType(dto.getNoticeType())
                .noticeTitle(dto.getNoticeTitle())
                .noticeContent(dto.getNoticeContent())
                .noticeImageUrl(dto.getNoticeImageUrl())
                .build();
    }
}
