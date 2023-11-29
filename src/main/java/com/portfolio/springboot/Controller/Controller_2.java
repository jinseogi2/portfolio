package com.portfolio.springboot.Controller;

import com.portfolio.springboot.dto.*;
import com.portfolio.springboot.entity.ItemEntity;
import com.portfolio.springboot.entity.ItemRepository;
import com.portfolio.springboot.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class Controller_2 {


    @Autowired
    private ItemRepository itemRepository;


    @GetMapping("/itemlist")
    public List<ItemDto> itemList() {
        List<ItemEntity> listEntity = itemRepository.findAll();

        List<ItemDto> listDto = listEntity
                .stream()
                .map(ItemDto::toDto)
                .collect(Collectors.toList());

        return listDto;

    }

    @GetMapping("/itemlistAll")
    public Map<String, List<ItemDto>> itemlistAll() {
        Map<String, List<ItemDto>> map = new HashMap<>();

        List<ItemEntity> listReCommend = itemRepository.findByItemRecommend(1);
        List<ItemDto> listDtoRecommend = listReCommend
                .stream()
                .map(ItemDto::toDto)
                .collect(Collectors.toList());
        map.put("itemlistRecommand", listDtoRecommend);
        List<ItemEntity> listCate1 = itemRepository.findByItemCate("음료");
        List<ItemDto> listDto1 = listCate1
                .stream()
                .map(ItemDto::toDto)
                .collect(Collectors.toList());
        map.put("itemlistDrink", listDto1);

        List<ItemEntity> listCate2 = itemRepository.findByItemCate("티");
        List<ItemDto> listDto2 = listCate2
                .stream()
                .map(ItemDto::toDto)
                .collect(Collectors.toList());
        map.put("itemlistTea", listDto2);

        List<ItemEntity> listCate3 = itemRepository.findByItemCate("디저트");
        List<ItemDto> listDto3 = listCate3
                .stream()
                .map(ItemDto::toDto)
                .collect(Collectors.toList());
        map.put("itemlistDesert", listDto3);

        List<ItemEntity> listCate4 = itemRepository.findByItemCate("etc");
        List<ItemDto> listDto4 = listCate4
                .stream()
                .map(ItemDto::toDto)
                .collect(Collectors.toList());
        map.put("itemlistEtc", listDto4);


        return map; //json 문자열로 리턴이 된다.
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

    @PostMapping("/menuUpdateAction")
    @ResponseBody
    public ResultDto menuEdAction(@RequestBody ItemEdDto itemEdDto){

        //Itemeddto 가져온걸 Entity로 바꿔준다.
        ItemEntity itemEntity = ItemEntity.toItemEntity(itemEdDto);

        // ItemrNo를 레파지토리에 넣어서 그 DB 를 찾아서 iEntity에 가져옴
        ItemEntity tEntity = itemRepository.findById(itemEdDto.getItemNo()).get();

        // ItemEdDto에는 시간을 담고있는 변수가 없어서
        // 생성시간을 다시 넣어주기 위해서
//        LocalDateTime time = tEntity.getMemberJoinDatetime();
//        memberEntity.setMemberJoinDatetime(time);

        // 업데이트
        ItemEntity itEntity = itemRepository.save(itemEntity);

        ResultDto resultDto = null;

        if( itEntity != null  ) {
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


}