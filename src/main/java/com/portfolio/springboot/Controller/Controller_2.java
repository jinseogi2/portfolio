package com.portfolio.springboot.Controller;

import com.portfolio.springboot.dto.*;
import com.portfolio.springboot.entity.ItemEntity;
import com.portfolio.springboot.entity.ItemRepository;
import com.portfolio.springboot.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


// 최백호
@Controller
public class Controller_2 {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/select")
    public String order(@RequestParam String itemNo, Model model) {

        System.out.println("itemNo ::: " + itemNo);

        ItemEntity itemEntity = itemRepository.findById(Long.valueOf(itemNo)).get();

        model.addAttribute("item", itemEntity);
        return "select";
    }
}



/*
    @GetMapping("/select1")
    public String select()
    {
        return "select";
    }
*/






