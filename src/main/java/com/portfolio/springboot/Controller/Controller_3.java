package com.portfolio.springboot.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controller_3 {
    @GetMapping("/")
        public String admin(){
            return"admin";
    }
    @GetMapping("notice")
    public String admin_notice(){
        return "admin_notice";

    }
    @GetMapping("member")
    public String admin_member(){
        return "admin_member";
    }
    @GetMapping("menu")
    public String admin_menu(){
        return "admin_menu";
    }
    @GetMapping("order")
    public String admin_order(){
        return "admin_order";
    }
    @GetMapping("memberUpdate")
    public String admin_memberUpdate(){
        return "admin_member_ed";
    }
    @GetMapping("notice_add")
    public String admin_notice_add(){
        return "admin_notice_add";
    }
    @GetMapping("menu_add")
    public String menu_add(){
        return "admin_menu_add";
    }
    @GetMapping("updateMenu")
        public String menuUpdate(){
        return "admin_menu_ed";
    }

}
