package com.project.shop.controller.items;

import com.project.shop.dto.ItemDto;
import com.project.shop.dto.PageRequestDto;
import com.project.shop.dto.PageResponseDto;
import com.project.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("items/goods")
public class GoodsController {
    // 검색/페이징을 위한 서비스 호출
    private final ItemService itemService;

    // 각 하위 카테고리별 페이지 컨트롤러
    @GetMapping("/amibo")
    public String goodsAmibo(PageRequestDto pageRequestDto, Model model) {
        String keyword = "아미보";
        PageResponseDto<ItemDto> responseDto =
                itemService.subList(pageRequestDto, keyword);
        model.addAttribute("categoryName", keyword);
        model.addAttribute("responseDto", responseDto);
        return "/items/game/subCate";
    }

    @GetMapping("/doll")
    public String goodsDoll(PageRequestDto pageRequestDto, Model model) {
        String keyword = "아미보";
        PageResponseDto<ItemDto> responseDto =
                itemService.subList(pageRequestDto, keyword);
        model.addAttribute("categoryName", keyword);
        model.addAttribute("responseDto", responseDto);
        return "/items/game/subCate";
    }

    @GetMapping("/travel")
    public String goodsTravel(PageRequestDto pageRequestDto, Model model) {
        String keyword = "트래블굿즈";
        PageResponseDto<ItemDto> responseDto =
                itemService.subList(pageRequestDto, keyword);
        model.addAttribute("categoryName", keyword);
        model.addAttribute("responseDto", responseDto);
        return "/items/game/subCate";
    }

    @GetMapping("/homeparty")
    public String gameAction(PageRequestDto pageRequestDto, Model model) {
        String keyword = "홈파티굿즈";
        PageResponseDto<ItemDto> responseDto =
                itemService.subList(pageRequestDto, keyword);
        model.addAttribute("categoryName", keyword);
        model.addAttribute("responseDto", responseDto);
        return "/items/game/subCate";
    }

}
