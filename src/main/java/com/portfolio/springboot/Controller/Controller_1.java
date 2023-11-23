package com.portfolio.springboot.Controller;

import com.portfolio.springboot.dto.MemberDto;
import com.portfolio.springboot.entity.MemberEntity;
import com.portfolio.springboot.entity.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class Controller_1 {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/")
    public String loginpage(){
        return "loginpage";
    }
    @GetMapping("/join")
    public String join(){
        return "join";
    }
    @PostMapping("/join")
    public String join2(
                        @RequestParam("inputId") String id,
                        @RequestParam("inputName") String name,
                        @RequestParam("inputPw") String pw,
                        @RequestParam("inputPw2") String pw2,
                        @RequestParam("inputEmail") String email
                        ){

        System.out.println("id : " + id);
        System.out.println("name : " + name);
        System.out.println("pw : " + pw);
        System.out.println("pw2 : " + pw2);
        System.out.println("email : " + email);

        // 각각 입력받은 값을 넣어줌
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberId(id);
        memberEntity.setMemberName(name);
        memberEntity.setMemberPw(pw);
        memberEntity.setMemberEmail(email);
        memberEntity.setMemberStamp(0);
        memberEntity.setMemberJoinDatetime(LocalDateTime.now());
        memberEntity.setMemberRole("ROLE_USER");

        // DB에 추가
        memberRepository.save(memberEntity);

        return "redirect:/";
    }


}
