package com.portfolio.springboot.Controller;

import com.portfolio.springboot.dto.*;
import com.portfolio.springboot.entity.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

// 박기현
@Controller
public class Controller_4 {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private ItemRepository itemRepository;


    @PostMapping("/join")
    public String join2(
            @RequestParam("inputId") String id,
            @RequestParam("inputName") String name,
            @RequestParam("inputPw") String pw,
            @RequestParam("inputPw2") String pw2,
            @RequestParam("inputEmail") String email
    ) {

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


    @PostMapping("/loginAction")
    @ResponseBody
    public ResultDto loginAction(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        System.out.println("loginId:" + loginDto.getLoginId());
        System.out.println("loginPw:" + loginDto.getLoginPw());

        //로그인 액션 : 아이디, 암호가 DB에 있으면 로그인 성공, 아니면 실패
        List<MemberEntity> list = memberRepository.findByMemberIdAndMemberPw(
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
        System.out.println("resultDto.result : " + resultDto.getResult());

        return resultDto;
    }

    @GetMapping("/admin_member")
    public String admin_member(Model model) {

        List<MemberEntity> listEntity = memberRepository.findAll();

        List<MemberDto> listDto = listEntity
                .stream()
                .map(MemberDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("count", listDto.size());
        model.addAttribute("list", listDto);


        return "admin_member";
    }

    @GetMapping("/admin_member_ed")
    public String adminMemberUpdate(@RequestParam String memberNo, Model model) {

        MemberEntity memberEntity = memberRepository.findById(Long.valueOf(memberNo)).get();

        MemberDto memberDto = MemberDto.toDto(memberEntity);
        model.addAttribute("member", memberDto);

        return "admin_member_ed";
    }

    @PostMapping("/memberUpdateAction")
    @ResponseBody
    public ResultDto memberEdAction(@RequestBody MemberEdDto memberEdDto) {

        // MemberEdDto 가져온걸 Entity로 바꿔준다.
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberEdDto);

        // MemberNo를 레파지토리에 넣어서 그 DB 를 찾아서 tEntity에 가져옴
        MemberEntity tEntity = memberRepository.findById(memberEdDto.getMemberNo()).get();

        // MemberEdDto에는 시간을 담고있는 변수가 없어서
        // 생성시간을 다시 넣어주기 위해서
        LocalDateTime time = tEntity.getMemberJoinDatetime();
        memberEntity.setMemberJoinDatetime(time);

        // 업데이트
        MemberEntity newEntity = memberRepository.save(memberEntity);

        ResultDto resultDto = null;

        if (newEntity != null) {
            //포인트 수정 성공
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(1)
                    .build();
        } else {
            //포인트 수정 실패
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(0)
                    .build();
        }

        return resultDto;
    }

    @PostMapping("/memberDeleteAction")
    @ResponseBody
    public ResultDto memberDeleteAction(@RequestBody MemberDeleteDto memberDeleteDto) {

        MemberEntity memberEntity = memberRepository.findById(Long.valueOf(memberDeleteDto.getMemberNo())).get();

        memberRepository.delete(memberEntity);

        ResultDto resultDto = null;

        resultDto = ResultDto.builder()
                .status("ok")
                .result(1)
                .build();

        return resultDto;
    }


    @GetMapping("/admin_notice")
    public String showNotice(Model model) {
        List<NoticeEntity> noticeEntities = noticeRepository.findAll();
        List<NoticeEdDto> noticeDtos = noticeEntities.stream()
                .map(NoticeEdDto::fromEntity)
                .collect(Collectors.toList());

        model.addAttribute("count", noticeDtos.size());
        model.addAttribute("list", noticeDtos);

        // 여기서 사용하는 noticeEdDto는 DB에서 가져온 데이터가 아니라 하드코딩된 예시이므로 주석 처리합니다.
        // NoticeEdDto noticeEdDto = NoticeEdDto.builder()
        //         .noticeNo(1L)
        //         .noticeType("BASIC")
        //         .noticeTitle("환영합니다")
        //         .noticeContent("관리자페이지에 오신걸 환영합니다.")
        //         .noticeImageUrl("/img/notice/notice1.jpg")
        //         .noticeDatetime(LocalDateTime.now())
        //         .build();
        //
        // model.addAttribute("notice", noticeEdDto);

        // 템플릿의 경로 반환
        return "admin_notice";
    }

    @GetMapping("/admin_notice_ed")
    public String adminNoticeUpdate(@RequestParam String noticeNo, Model model) {
        NoticeEntity noticeEntity = noticeRepository.findById(Long.valueOf(noticeNo)).get();

        if (noticeEntity != null) {
            NoticeDto noticeDto = NoticeDto.toDto(noticeEntity);
            model.addAttribute("notice", noticeDto);
            return "admin_notice_ed";
        } else {
            // 공지사항이 없을 경우에 대한 처리
            return "redirect:/admin_notice";
        }
    }

    @PostMapping("/noticeUpdateAction")
    @ResponseBody
    public ResultDto noticeUpdateAction(@RequestBody NoticeEdDto noticeEdDto) {
        try {
            // NoticeEdDto를 NoticeEntity로 변환
            NoticeEntity noticeEntity = NoticeEntity.toNoticeEntity(noticeEdDto);

            // NoticeNo를 기반으로 DB에서 해당 공지사항 정보를 찾아옴
            Optional<NoticeEntity> optionalEntity = noticeRepository.findById(noticeEdDto.getNoticeNo());

            if (optionalEntity.isPresent()) {
                NoticeEntity existingEntity = optionalEntity.get();

                // 기존 공지사항의 작성시간을 다시 설정
                LocalDateTime time = existingEntity.getNoticeDatetime();
                noticeEntity.setNoticeDatetime(time);

                // 업데이트
                NoticeEntity newEntity = noticeRepository.save(noticeEntity);

                // 공지사항 수정 성공
                return ResultDto.builder()
                        .status("ok")
                        .result(1)
                        .build();
            } else {
                // 공지사항이 없을 경우에 대한 처리
                return ResultDto.builder()
                        .status("ok")
                        .result(0)
                        .build();
            }
        } catch (Exception e) {
            // 예외 발생 시 실패로 처리
            return ResultDto.builder()
                    .status("error")
                    .result(0)
                    .build();
        }
    }

    @PostMapping("/noticeDeleteAction")
    @ResponseBody
    public ResultDto noticeDeleteAction(@RequestBody NoticeDeleteDto noticeDeleteDto) {

        NoticeEntity noticeEntity = noticeRepository.findById(Long.valueOf(noticeDeleteDto.getNoticeNo())).get();

        noticeRepository.delete(noticeEntity);

        ResultDto resultDto = null;

        resultDto = ResultDto.builder()
                .status("ok")
                .result(1)
                .build();

        return resultDto;
    }

    @GetMapping("/admin_menu")
    public String showMenu(Model model) {
        List<ItemEntity> itemEntities = itemRepository.findAll();
        model.addAttribute("count", itemEntities.size());
        model.addAttribute("list", itemEntities);
        return "admin_menu";
    }

    @GetMapping("/admin_menu_add")
    public String adminMenuAdd() {
        return "admin_menu_add";
    }

    @PostMapping("/admin_menu_add2")
    @ResponseBody
    public ResultDto menuAddAction(@RequestBody ItemAddDto itemAddDto) {

        try {
            ItemEntity newItemEntity = ItemEntity.toEntity(itemAddDto);


            newItemEntity.setItemCode(UUID.randomUUID().toString());
            newItemEntity.setItemUpdateDatetime(LocalDateTime.now());

            itemRepository.save(newItemEntity);


            ResultDto resultDto = ResultDto.builder()
                    .status("ok")
                    .result(1)
                    .build();
            return resultDto;

        } catch (Exception e) {
            e.printStackTrace();
            ResultDto resultDto = ResultDto.builder()
                    .status("error")
                    .result(0)
                    .build();
            return resultDto;
        }
    }
}