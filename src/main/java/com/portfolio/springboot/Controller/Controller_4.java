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
    @GetMapping("/basket")
    public String carts(Model model){
        List<BasketEntity> listEntity = BasketRepository.findAll();

        List<BasketDto> listDto = listEntity
                .stream()
                .map(BasketDto::toCartDto)
                .collect(Collectors.toList());



        model.addAttribute("count", listDto.size());
        model.addAttribute("list", listDto);

        return "/basket";
    }
}