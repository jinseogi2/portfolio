package com.portfolio.springboot.Controller;

import com.portfolio.springboot.dto.*;
import com.portfolio.springboot.entity.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
public class ApiController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartRepository cartRepository;
    // 접속한 사용자를 담아놓기 위해서
    @PostMapping("/loginAction")
    public ResultDto loginAction(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        System.out.println("loginId:"+loginDto.getLoginId());
        System.out.println("loginPw:"+loginDto.getLoginPw());

        //로그인 액션 : 아이디, 암호가 DB에 있으면 로그인 성공, 아니면 실패
        List<MemberEntity> list = memberRepository.findByMemberIdAndMemberPw(
                loginDto.getLoginId(),
                loginDto.getLoginPw()
        );

        ResultDto resultDto = null;

        if( list.size() > 0 ) {
            //로그인 성공
            //관리자로 로그인하면
            if( loginDto.getLoginId().equals("admin") ){
                resultDto = ResultDto.builder()
                        .status("ok")
                        .result(2)
                        .build();
            }else{
                resultDto = ResultDto.builder()
                        .status("ok")
                        .result(1)
                        .build();
            }

            // ID, PW 를 가상의 공간에 저장해줌
            request.getSession().setAttribute("loginId", loginDto.getLoginId());
            request.getSession().setAttribute("loginPw", loginDto.getLoginPw());

            // 얻어올때
            // String n = (String)request.getSession().getAttribute("loginId");

            // request.getSession().invalidate(); //로그아웃처리하기 전까지
        }else{
            //로그인 실패
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(0)
                    .build();
        }
        System.out.println("resultDto.result : " + resultDto.getResult());

        return resultDto;
    }

    @PostMapping("/memberUpdateAction")
    public ResultDto memberEdAction(@RequestBody MemberEdDto memberEdDto){

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

        if( newEntity != null  ) {
            //포인트 수정 성공
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(1)
                    .build();
        }else{
            //포인트 수정 실패
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(0)
                    .build();
        }

        return resultDto;
    }


    @PostMapping("/memberDeleteAction")
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

    ////// 매뉴 추가
    ////// 매뉴 추가
    ////// 매뉴 추가
    ////// 매뉴 추가

    @PostMapping("/menu_Add_upload")
    public ResultDto m_a_upload(@RequestParam MultipartFile file) throws IOException {

        String newFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        if( !file.isEmpty() ){
            File newFile = new File(newFileName);
            file.transferTo( newFile );
        }else {
            ResultDto resultDto = ResultDto.builder()
                    .status("ok")
                    .result(0)
                    .build();

            return resultDto;
        }

        ResultDto resultDto = ResultDto.builder()
                .status("ok")
                .result(1)
                .uploadFileName(newFileName)
                .build();

        return resultDto;
    }
    @PostMapping("/menuAddAction")
    public ResultDto menuAddAction(@RequestBody ItemAddDto itemAddDto){



        itemAddDto.setItemImageUrl("./upload/"+itemAddDto.getItemImageUrl());
        
        ItemEntity newEntity = ItemEntity.toAddEntity(itemAddDto);


        newEntity.setItemCode(UUID.randomUUID().toString());
        System.out.println("itemCode : : " + newEntity.getItemCode());
        newEntity.setItemUpdateDatetime(LocalDateTime.now());
        System.out.println("dateTime : : " + newEntity.getItemUpdateDatetime());

        itemRepository.save(newEntity);

        ResultDto resultDto = null;

        resultDto = ResultDto.builder()
                .status("ok")
                .result(1)
                .build();

        return resultDto;
    }

    //////// 매뉴 업데이트
    //////// 매뉴 업데이트
    //////// 매뉴 업데이트
    //////// 매뉴 업데이트

    @PostMapping("/menu_Update_upload")
    public ResultDto m_upload(@RequestParam MultipartFile file) throws IOException {

        String newFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        if( !file.isEmpty() ){
            File newFile = new File(newFileName);
            file.transferTo( newFile );
        }else {
            ResultDto resultDto = ResultDto.builder()
                    .status("ok")
                    .result(0)
                    .build();

            return resultDto;
        }

        ResultDto resultDto = ResultDto.builder()
                .status("ok")
                .result(1)
                .uploadFileName(newFileName)
                .build();

        return resultDto;
    }
    @PostMapping("/menuUpdateAction")
    public ResultDto menuUpdateAction(@RequestBody ItemEdDto itemEdDto) {

        itemEdDto.setItemImageUrl("./upload/"+itemEdDto.getItemImageUrl());

        ItemEntity itemEntity = ItemEntity.toEntity(itemEdDto);

        ItemEntity newEntity = itemRepository.save(itemEntity);

        ResultDto resultDto = null;

        if( newEntity != null  ) {
            //수정 성공
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(1)
                    .build();
        }else{
            //수정 실패
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(0)
                    .build();
        }

        return resultDto;
    }

    @PostMapping("/menuUpdateAction_none")
    public ResultDto menuUpdateAction2(@RequestBody ItemEdDto itemEdDto) {

        ItemEntity itemEntity = ItemEntity.toEntity(itemEdDto);

        ItemEntity newEntity = itemRepository.save(itemEntity);

        ResultDto resultDto = null;

        if( newEntity != null  ) {
            //수정 성공
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(1)
                    .build();
        }else{
            //수정 실패
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(0)
                    .build();
        }

        return resultDto;
    }



    @PostMapping("/menuDeleteAction")
    public ResultDto menuDeleteAction(@RequestBody ItemDeleteDto itemDeleteDto) {

        ItemEntity itemEntity = itemRepository.findById(Long.valueOf(itemDeleteDto.getItemNo())).get();

        itemRepository.delete(itemEntity);

        ResultDto resultDto = null;

        resultDto = ResultDto.builder()
                .status("ok")
                .result(1)
                .build();

        return resultDto;
    }





    ////// 공지사항 올리기
    ////// 공지사항 올리기
    ////// 공지사항 올리기
    ////// 공지사항 올리기

    @PostMapping("/notice_Add_upload")
    public ResultDto n_a_pload(@RequestParam MultipartFile file) throws IOException {

        String newFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        if( !file.isEmpty() ){
            File newFile = new File(newFileName);
            file.transferTo( newFile );
        }else {
            ResultDto resultDto = ResultDto.builder()
                    .status("ok")
                    .result(0)
                    .build();

            return resultDto;
        }

        ResultDto resultDto = ResultDto.builder()
                .status("ok")
                .result(1)
                .uploadFileName(newFileName)
                .build();

        return resultDto;
    }
    @PostMapping("/noticeAddAction")
    public ResultDto noticeAddAction(@RequestBody NoticeAddDto noticeAddDto) {

        noticeAddDto.setNoticeImageUrl("./upload/"+noticeAddDto.getNoticeImageUrl());

        NoticeEntity noticeEntity = NoticeEntity.toEntity(noticeAddDto);

        noticeRepository.save(noticeEntity);

        ResultDto resultDto = null;

        resultDto = ResultDto.builder()
                .status("ok")
                .result(1)
                .build();

        return resultDto;
    }

    ///// 공지사항 업데이트
    ///// 공지사항 업데이트
    ///// 공지사항 업데이트

    @PostMapping("/notice_Update_upload")
    public ResultDto n_u_pload(@RequestParam MultipartFile file) throws IOException {

        String newFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        if( !file.isEmpty() ){
            File newFile = new File(newFileName);
            file.transferTo( newFile );
        }else {
            ResultDto resultDto = ResultDto.builder()
                    .status("ok")
                    .result(0)
                    .build();

            return resultDto;
        }

        ResultDto resultDto = ResultDto.builder()
                .status("ok")
                .result(1)
                .uploadFileName(newFileName)
                .build();

        return resultDto;
    }
    @PostMapping("/noticeUpdateAction_none")
    public ResultDto noticeUpdateAction2(@RequestBody NoticeEdDto noticeEdDto) {

        NoticeEntity noticeEntity = NoticeEntity.toEntity(noticeEdDto);

        NoticeEntity newEntity = noticeRepository.save(noticeEntity);

        ResultDto resultDto = null;

        if( newEntity != null  ) {
            //수정 성공
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(1)
                    .build();
        }else{
            //수정 실패
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(0)
                    .build();
        }

        return resultDto;
    }
    @PostMapping("/noticeUpdateAction")
    public ResultDto noticeUpdateAction(@RequestBody NoticeEdDto noticeEdDto) {

        noticeEdDto.setNoticeImageUrl("./upload/"+noticeEdDto.getNoticeImageUrl());

        NoticeEntity noticeEntity = NoticeEntity.toEntity(noticeEdDto);

        NoticeEntity newEntity = noticeRepository.save(noticeEntity);

        ResultDto resultDto = null;

        if( newEntity != null  ) {
            //수정 성공
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(1)
                    .build();
        }else{
            //수정 실패
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(0)
                    .build();
        }

        return resultDto;
    }

    @PostMapping("/noticeDeleteAction")
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

    @PostMapping("/cartAddAction")
    public ResultDto cartAdd_Action(@RequestBody CartAddDto cartAddDto){

        CartEntity cartEntity = CartEntity.toEntity(cartAddDto);
        cartEntity.setCartDatetime(LocalDateTime.now());

        cartRepository.save(cartEntity);

        ResultDto resultDto = null;

        resultDto = ResultDto.builder()
                .status("ok")
                .result(1)
                .build();
        return resultDto;
    }

    @PostMapping("/cartDeleteAction")
    public ResultDto cartDeleteAction(@RequestBody CartDeleteDto cartDeleteDto) {

        CartEntity cartEntity = cartRepository.findById(Long.valueOf(cartDeleteDto.getCartNo())).get();

        cartRepository.delete(cartEntity);

        ResultDto resultDto = null;

        resultDto = ResultDto.builder()
                .status("ok")
                .result(1)
                .build();

        return resultDto;
    }

}
