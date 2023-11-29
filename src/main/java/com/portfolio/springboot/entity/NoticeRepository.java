package com.portfolio.springboot.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {

    // List<NoticeEntity> findByMemberIdAndMemberPw(String id, String pw);

    // select * form member where member_id = ?
    List<NoticeEntity> findByNoticeType(String noticeType);


}
