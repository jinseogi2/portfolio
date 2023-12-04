package com.portfolio.springboot.Controller;

import com.portfolio.springboot.dto.NoticeDto;
import com.portfolio.springboot.dto.ResultDto;
import com.portfolio.springboot.entity.NoticeEntity;
import com.portfolio.springboot.entity.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Controller
public class Controller_3 {

    @Autowired
    private NoticeRepository noticeRepository;


    @GetMapping("/admin_notice")
    public String admin_notice(Model model){

        List<NoticeEntity> noticeEntity = noticeRepository.findAll();

        model.addAttribute("count",noticeEntity.size());
        model.addAttribute("list",noticeEntity);

        return "admin_notice";
    }
    @PostMapping("/admin_notice2")
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
    @PostMapping("/upload")
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


}

