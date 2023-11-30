package com.portfolio.springboot.Controller;

import com.portfolio.springboot.entity.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FrontController {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private ItemRepository itemRepository;


    // *************
    // 메인 페이지
    // *************
    @GetMapping("/main")
    public String main(Model model, HttpServletRequest request) {

        // 세션에서 로그인한 사용자의 아이디와 비밀번호를 가져옵니다.
        String loginId = (String) request.getSession().getAttribute("loginId");
        String loginPw = (String) request.getSession().getAttribute("loginPw");

        // 로그인한 사용자의 아이디와 비밀번호로 회원 정보를 데이터베이스에서 조회합니다.
        List<MemberEntity> mlist = memberRepository.findByMemberIdAndMemberPw(loginId, loginPw);

        // 조회된 회원 정보 중 첫 번째 회원을 가져옵니다.
        MemberEntity user = mlist.get(0);

        // HTML에 사용자 정보를 전달합니다.
        model.addAttribute("user", user);

        // 모든 공지사항을 가져옵니다.
        List<NoticeEntity> ntlist = noticeRepository.findAll();

        // 이벤트와 일반 공지사항을 구분하여 가져옵니다.
        List<NoticeEntity> ntElist = noticeRepository.findByNoticeType("EVENT");
        List<NoticeEntity> ntBlist = noticeRepository.findByNoticeType("BASIC");

        // 가져온 공지사항 정보를 HTML에 전달합니다.
        if (!ntlist.isEmpty()) {
            model.addAttribute("notice", ntlist);
        }
        if (!ntElist.isEmpty()) {
            model.addAttribute("event", ntElist);
        }
        if (!ntBlist.isEmpty()) {
            model.addAttribute("basic", ntBlist);
        }

        // 아이템 관련 정보를 데이터베이스에서 조회합니다.
        List<ItemEntity> ilist = itemRepository.findAll();
        List<ItemEntity> rlist = itemRepository.findByItemRecommend(1);
        List<ItemEntity> nlist = itemRepository.findByItemRecommend(2);

        // 조회된 아이템 정보를 HTML에 전달합니다.
        if (!ilist.isEmpty()) {
            model.addAttribute("ilist", ilist);
        }
        if (!rlist.isEmpty()) {
            model.addAttribute("rlist", rlist);
        }
        if (!nlist.isEmpty()) {
            model.addAttribute("nlist", nlist);
        }

        // "Main"이라는 뷰를 반환합니다.
        return "Main";
    }
    // *************
    // 더보기 페이지
    // *************
    @GetMapping("/lastPage")
    public String lastPage(Model model, HttpServletRequest request){

        // 세션에서 로그인한 사용자의 아이디와 비밀번호를 가져옵니다.
        String loginId = (String) request.getSession().getAttribute("loginId");
        String loginPw = (String) request.getSession().getAttribute("loginPw");

        // 로그인한 사용자의 아이디와 비밀번호로 회원 정보를 데이터베이스에서 조회합니다.
        List<MemberEntity> mlist = memberRepository.findByMemberIdAndMemberPw(loginId, loginPw);

        // 조회된 회원 정보 중 첫 번째 회원을 가져옵니다.
        MemberEntity user = mlist.get(0);

        // HTML에 사용자 정보를 전달합니다.
        model.addAttribute("user", user);

        // 이벤트와 일반 공지사항을 구분하여 가져옵니다.
        List<NoticeEntity> ntElist = noticeRepository.findByNoticeType("EVENT");
        List<NoticeEntity> ntBlist = noticeRepository.findByNoticeType("BASIC");

        // 가져온 공지사항 정보를 HTML에 전달합니다.
        if (!ntElist.isEmpty()) {
            model.addAttribute("event", ntElist);
        }
        if (!ntBlist.isEmpty()) {
            model.addAttribute("basic", ntBlist);
        }

        return "Lastpage";
    }
}

