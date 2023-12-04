package com.portfolio.springboot.Controller;


import com.portfolio.springboot.entity.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

//  이강희
@Controller
public class Controller_3 {
    @GetMapping("/admin_notice_add")
    public String adminNoticeAdd(RequestParam String )



}
