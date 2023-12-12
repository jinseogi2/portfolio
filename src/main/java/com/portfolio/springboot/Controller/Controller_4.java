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
    public String getBasket(Model model) {
        // 여기서 필요한 데이터를 가져와 BasketDto로 변환하여 모델에 추가합니다.
        List<BasketDto> basketItems = // 서비스 호출이나 다른 방법으로 데이터를 가져와서 BasketDto로 변환

                model.addAttribute("basketItems", basketItems);

        return "basket";
    }
}