package com.portfolio.springboot.Controller;

import com.portfolio.springboot.dto.JoinDto;
import com.portfolio.springboot.entity.MemberEntity;
import com.portfolio.springboot.entity.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class Controller_3 {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/join")
    public String joinForm(@RequestParam("inputId") String userId,
                           @RequestParam("inputName") String userName,
                           @RequestParam("inputPw") String userPW,
                           @RequestParam("inputPw2") String userPw2,
                           @RequestParam("inputEmail") String userEmail,
                           Model model) {
        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setMemberId(userId);
        memberEntity.setMemberName(userName);
        memberEntity.setMemberEmail(userEmail);
        memberEntity.setMemberPw(userPW);
        memberEntity.setMemberRole("ROLE_USER");
        memberEntity.setMemberStamp(0);
        memberEntity.setMemberJoinDatetime(LocalDateTime.now());
        memberRepository.save(memberEntity);

        System.out.println("inputId--"+userId);
        System.out.println("inputName--"+userName);
        System.out.println("inputPw--"+userPW);
        System.out.println("inputPw2--"+userPw2);
        System.out.println("inputEmail--"+userEmail);

            return "redirect:/";
        }



    @GetMapping("/")
    public String admin() {
        return "admin";
    }

    @GetMapping("notice")
    public String admin_notice() {
        return "admin_notice";
    }

    @GetMapping("member")
    public String admin_member() {
        return "admin_member";
    }

    @GetMapping("menu")
    public String admin_menu() {
        return "admin_menu";
    }

    @GetMapping("order")
    public String admin_order() {
        return "admin_order";
    }

    @GetMapping("memberUpdate")
    public String admin_memberUpdate() {
        return "admin_member_ed";
    }

    @GetMapping("notice_add")
    public String admin_notice_add() {
        return "admin_notice_add";
    }

    @GetMapping("menu_add")
    public String menu_add() {
        return "admin_menu_add";
    }

    @GetMapping("updateMenu")
    public String menuUpdate() {
        return "admin_menu_ed";
    }
}
