package com.portfolio.springboot.Controller;

import com.portfolio.springboot.dto.ItemDto;
import com.portfolio.springboot.dto.MemberDto;
import com.portfolio.springboot.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class AdminController {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private ItemRepository itemRepository;


    ///admin_member(관리자 페이지 회원목록 출력)
    @GetMapping("/admin_member")
    public String admin_member(Model model){

        List<MemberEntity> listEntity = memberRepository.findAll();

        List<MemberDto> listDto = listEntity
                .stream()
                .map(MemberDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("count", listDto.size());
        model.addAttribute("list", listDto);


        return "admin_member";
    }

    //관리자 페이지 회원목록 에서 회원정보 수정
    @GetMapping("/admin_member_ed")
    public String adminMemberUpdate(@RequestParam String memberNo, Model model){

        MemberEntity memberEntity = memberRepository.findById(Long.valueOf(memberNo)).get();

        MemberDto memberDto = MemberDto.toDto(memberEntity);
        model.addAttribute("member", memberDto);

        return "admin_member_ed";
    }


    @GetMapping("/admin_notice")
    public String admin_notice(Model model){

        List<NoticeEntity> noticeEntity = noticeRepository.findAll();

        model.addAttribute("count",noticeEntity.size());
        model.addAttribute("list",noticeEntity);

        return "admin_notice";
    }

    @GetMapping("/admin_menu")
    public String admin_menu(Model model) {
        List<ItemEntity> listentity = itemRepository.findAll();
        List<ItemDto> listDto = listentity
                .stream()
                .map(ItemDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("count", listDto.size());
        model.addAttribute("list", listDto);

        return "admin_menu";
    }

    @GetMapping("/admin_menu_ed")
    public String adminmenuUpdate(@RequestParam String itemNo, Model model){

        ItemEntity itemEntity = itemRepository.findById(Long.valueOf(itemNo)).get();

        ItemDto itemDto = ItemDto.toDto(itemEntity);
        model.addAttribute("item", itemDto);

        return "admin_menu_ed";
    }


}
