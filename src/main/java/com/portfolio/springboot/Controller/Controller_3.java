package com.portfolio.springboot.Controller;

import com.portfolio.springboot.dto.*;
import com.portfolio.springboot.entity.MemberEntity;
import com.portfolio.springboot.entity.NoticeEntity;
import com.portfolio.springboot.entity.NoticeRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;


@Controller
public class Controller_3 {

    @Autowired
    private NoticeRepository noticeRepository;


    @GetMapping("/admin_notice_add")
    public String adminNoticeAdd(){


        return "admin_notice_add";
        }

    @PostMapping("/admin_noticeAdd")
    @ResponseBody

    public ResultDto addNotice(@RequestBody NoticeDto noticeAddDto) {
        System.out.println(noticeAddDto.toString());

        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeContent(noticeAddDto.getNoticeContent());
        noticeEntity.setNoticeTitle(noticeAddDto.getNoticeTitle());
        noticeEntity.setNoticeType(noticeAddDto.getNoticeType());
        noticeEntity.setNoticeImageUrl(noticeAddDto.getNoticeImageUrl());
        noticeEntity.setNoticeDatetime(LocalDateTime.now());

        noticeRepository.save(noticeEntity);

        ResultDto resultDto = null;

        resultDto = ResultDto.builder()
                .status("ok")
                .result(1)
                .build();


        return resultDto;
    }

    @PostMapping("/func_notice_addAction")
    @ResponseBody
    public ResultDto func_notice_addAction(@RequestBody NoticeAddDto noticeAddDto){

        // NoticeEdDto 가져온걸 Entity로 바꿔준다.
        NoticeEntity noticeEntity = NoticeEntity.toNoticeEntity(noticeAddDto);

        noticeAddDto.setNoticeImageUrl("./upload/"+noticeAddDto.getNoticeImageUrl());


        // 생성시간을 다시 넣어주기 위해서
        noticeEntity.setNoticeDatetime(LocalDateTime.now());


        // 업데이트
       noticeRepository.save(noticeEntity);

        ResultDto resultDto = null;

        //포인트 수정 성공
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(1)
                    .build();


        return resultDto;
    }

    @PostMapping("/noticeUpload")
    @ResponseBody
    public ResultDto upload(@RequestParam MultipartFile file) throws IOException {

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

//    @PostMapping("/NoticeAddAction")
//    @ResponseBody
//    public ResultDto NoticeAddAction(@RequestBody NoticeAddDto noticeAddDto) {
//
//        noticeAddDto.setNoticeImageUrl("./upload/"+noticeAddDto.getNoticeImageUrl());
//
//        NoticeEntity noticeEntity = NoticeEntity.toNoticeEntity(noticeAddDto);
//
//        NoticeEntity newEntity1 = noticeRepository.save(noticeEntity);
//
//        ResultDto resultDto = null;
//
//        if( newEntity1 != null  ) {
//            //수정 성공
//            resultDto = ResultDto.builder()
//                    .status("ok")
//                    .result(1)
//                    .build();
//        }else{
//            //수정 실패
//            resultDto = ResultDto.builder()
//                    .status("ok")
//                    .result(0)
//                    .build();
//        }
//
//        return resultDto;
//    }

}

