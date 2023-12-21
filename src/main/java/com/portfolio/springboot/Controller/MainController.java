package com.portfolio.springboot.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/search_pw_form")
    public String search_pw(){  return "search_pw";   }
    @GetMapping("/search_id_form")
    public String search_id(){   return "search_id";   }
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/join")
    public String join(){
        return "join";
    }


    @GetMapping("/payCard")
    public String payCard(){
        return "PayCard";
    }




    @GetMapping("/admin_menu_add")
    public String admin_menu_add(){
        return "admin_menu_add";
    }


    @GetMapping("/admin_notice_add")
    public String admin_notice_add(){
        return "admin_notice_add";
    }

}
