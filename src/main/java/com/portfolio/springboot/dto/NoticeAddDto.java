package com.portfolio.springboot.dto;


import com.portfolio.springboot.entity.NoticeEntity;
import lombok.*;

//      notice_no INT AUTO_INCREMENT NOT NULL PRIMARY KEY, -- 게시글 번호
//      notice_type VARCHAR(255) NOT NULL,  -- 공지사항 종류(이벤트, 배송지연안내 등등)
//      notice_title VARCHAR(255) NOT NULL,  -- 공지사항 제목
//      notice_content TEXT NULL,  -- 공지사항 내용
//      notice_image_url TEXT NULL, -- 공지사항 첨부이미지 url
//      notice_datetime DATETIME DEFAULT NOW() -- 작성시간

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeAddDto {
    private Long noticeNo;
    private String noticeType;
    private String noticeTitle;
    private String noticeContent;
    private String noticeImageUrl;


    public static NoticeDto toDto(NoticeEntity entity){
        return NoticeDto.builder()
                .noticeNo(entity.getNoticeNo())
                .noticeType(entity.getNoticeType())
                .noticeTitle(entity.getNoticeTitle())
                .noticeContent(entity.getNoticeContent())
                .noticeImageUrl(entity.getNoticeImageUrl())

                .build();
    }
}