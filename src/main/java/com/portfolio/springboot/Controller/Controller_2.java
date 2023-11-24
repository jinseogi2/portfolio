package com.portfolio.springboot.Controller;

import com.portfolio.springboot.dto.JoinDto;
import com.portfolio.springboot.dto.LoginDto;
import com.portfolio.springboot.dto.ResultDto;
import com.portfolio.springboot.entity.MemberEntity;
import com.portfolio.springboot.entity.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class Controller_2 {

    @Autowired
    private MemberRepository memberRepositoy;

    @GetMapping("/")
    public String loginpage() {

        return "loginpage";
    }

    @GetMapping("/loginpage")
    public String loginpage2(){
        return "redirect:/loginpage";
    }
    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @GetMapping("search_id")
    public String search_id() {
        return "search_id";
    }

    @GetMapping("search_pw")
    public String search_pw() {
        return "search_pw";
    }


    @PostMapping("/loginAction")
    public ResultDto loginAction(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        System.out.println("loginId:" + loginDto.getLoginId());
        System.out.println("loginPw:" + loginDto.getLoginPw());

        //로그인 액션 : 아이디, 암호가 DB에 있으면 로그인 성공, 아니면 실패
        List<MemberEntity> list = memberRepositoy.findByMemberIdAndMemberPw(
                loginDto.getLoginId(),
                loginDto.getLoginPw()
        );

        ResultDto resultDto = null;

        if (list.size() > 0) {
            //로그인 성공
            //관리자로 로그인하면
            if (loginDto.getLoginId().equals("admin")) {
                resultDto = ResultDto.builder()
                        .status("ok")
                        .result(2)
                        .build();
            } else {
                resultDto = ResultDto.builder()
                        .status("ok")
                        .result(1)
                        .build();
            }

            request.getSession().setAttribute("loginId", loginDto.getLoginId());
            //request.getSession().invalidate(); //로그아웃처리
        } else {
            //로그인 실패
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(0)
                    .build();
        }

        return resultDto;
    }

    @PostMapping("/joinAction")
    @ResponseBody
    public ResultDto loginAction(@RequestBody JoinDto joinDto) {
        System.out.println("loginId:" + joinDto.getLoginId());
        System.out.println("loginPw:" + joinDto.getLoginPw());
        System.out.println("loginName:" + joinDto.getLoginName());
        System.out.println("loginEmail:" + joinDto.getLoginEmail());


        MemberEntity memberJoinEntity = MemberEntity.toJoinEntity(joinDto);

        memberRepositoy.save(memberJoinEntity);
        //로그인 액션 : 아이디, 암호가 DB에 있으면 로그인 성공, 아니면 실패
//        MemberEntity newEntity = memberRepositoy.save(memberJoinEntity);

        ResultDto resultDto = null;
        resultDto = ResultDto.builder()
                .status("ok")
                .result(1)
                .build();
//        if (newEntity != null) {
//            //회원가입 성공
//            resultDto = ResultDto.builder()
//                    .status("ok")
//                    .result(1)
//                    .build();
//        } else {
//            //회원가입 실패
//            resultDto = ResultDto.builder()
//                    .status("ok")
//                    .result(0)
//                    .build();
//        }

        return resultDto;
    }
}