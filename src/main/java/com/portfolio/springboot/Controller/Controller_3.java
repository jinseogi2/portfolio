package com.portfolio.springboot.Controller;


import com.portfolio.springboot.dto.NoticeDto;
import com.portfolio.springboot.entity.ItemEntity;
import com.portfolio.springboot.entity.ItemRepository;
import com.portfolio.springboot.entity.NoticeEntity;
import com.portfolio.springboot.entity.NoticeRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.events.Event;

import java.util.List;

//  이강희
@Controller
public class Controller_3 {
    @Autowired
    private NoticeRepository noticeRepository;

    @GetMapping("/event")
    public String eventPage(@RequestParam String noticeNo, Model model) {

        NoticeEntity noticeEntity = noticeRepository.findById(Long.valueOf(noticeNo)).get();

        NoticeDto noticeDto = NoticeDto.toDto(noticeEntity);
        model.addAttribute("notice", noticeDto);

        return "event";

    }

    @GetMapping("/noticeLink")
    public String noticeLink(@RequestParam String noticeImageUrl, Model model) {
        List<NoticeEntity> noticeEntity = noticeRepository.findByNoticeImageUrl(noticeImageUrl);
        NoticeEntity notice = noticeEntity.get(0);
        model.addAttribute("noticeLink", notice);

        System.out.println("noticeImageUrl :: " + noticeImageUrl);
        return "noticeLink";
    }
}
