package com.portfolio.springboot.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//  이강희
@Controller
public class Controller_3 {
    @GetMapping("/select")
    public String select(){
        return ("select");
    }
}
