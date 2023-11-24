package com.portfolio.springboot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/main")
    public String main(){
        return "Main";
    }
    @GetMapping("/")
    public String loginpage(){
        return "loginpage";
    }

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

    @GetMapping("/admin_notice")
    public String admin_notice(){   return "admin_notice";    }

    @GetMapping("/admin_member")
    public String admin_member(){   return "admin_member";  }

    @GetMapping("/admin_menu")
    public String admin_menu(){   return "admin_menu";  }

    @GetMapping("/admin_order")
    public String admin_order(){   return "admin_order";  }

    @GetMapping("/admin_member_ed")
    public String admin_member_ed(){
        return "admin_member_ed";
    }
    @GetMapping("/admin_menu_add")
    public String admin_menu_add(){
        return "admin_menu_add";
    }
    @GetMapping("/admin_menu_ed")
    public String admin_menu_ed(){
        return "admin_menu_ed";
    }
    @GetMapping("/admin_notice_add")
    public String admin_notice_add(){
        return "admin_notice_add";
    }
    @GetMapping("/admin_notice_ed")
    public String admin_notice_ed(){
        return "admin_notice_ed";
    }
}
