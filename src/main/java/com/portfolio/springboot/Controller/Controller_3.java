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

}
