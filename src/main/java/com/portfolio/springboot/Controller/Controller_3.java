package com.portfolio.springboot.Controller;

import com.portfolio.springboot.dto.NoticeAddDto;

import com.portfolio.springboot.dto.ResultDto;
import com.portfolio.springboot.entity.NoticeEntity;
import com.portfolio.springboot.entity.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class Controller_3 {

    @Autowired
    private NoticeRepository noticeRepository;


    @GetMapping("/admin_notice")
    public String admin_notice(Model model){

        List<NoticeEntity> noticeEntity = noticeRepository.findAll();

        model.addAttribute("count",noticeEntity.size());
        model.addAttribute("list",noticeEntity);

        return "admin_notice";
    }
    @PostMapping("/admin_notice2")
    @ResponseBody
    public ResultDto addNotice(@RequestBody NoticeAddDto noticeAddDto) {

        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeContent(noticeAddDto.getNoticeContent());
        noticeEntity.setNoticeTitle(noticeAddDto.getNoticeTitle());
        noticeEntity.setNoticeType(noticeAddDto.getNoticeType());
        noticeEntity.setNoticeImageUrl(noticeAddDto.getNoticeImageUrl());
        noticeEntity.setNoticeDatetime(LocalDateTime.now());

        noticeRepository.save(noticeEntity);

        ResultDto resultDto = null;

        resultDto = ResultDto.builder()
                .status("ok")
                .result(1)
                .build();


        return resultDto;
    }
    @GetMapping("/admin_notice_add")
    public String addNoticeForm() {
        return "admin_notice_add";
    }
}
