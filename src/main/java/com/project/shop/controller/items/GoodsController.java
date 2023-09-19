package com.project.shop.controller.items;

import com.project.shop.dto.PageRequestDto;
import com.project.shop.dto.PageResponseDto;
import com.project.shop.entity.Item;
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
    @GetMapping("")
    public String goodsMain(PageRequestDto pageRequestDto, Model model) {
        String keyword = "굿즈";
        String mainCate = "goods";
        model.addAttribute("mainCate", mainCate);
        PageResponseDto<Item> responseDto =
                itemService.mainList(pageRequestDto, keyword);
        model.addAttribute("cateName", keyword);
        model.addAttribute("responseDto", responseDto);

        return "/items/game/root";
    }


    // 각 하위 카테고리별 페이지 컨트롤러
    @GetMapping("/amibo")
    public String goodsAmibo(PageRequestDto pageRequestDto, Model model) {
        String keyword = "아미보";
        String subCate = "amibo";
        String mainCate = "goods";
        PageResponseDto<Item> responseDto =
                itemService.subList(pageRequestDto, keyword);
        model.addAttribute("categoryName", keyword);
        model.addAttribute("subCate", subCate);
        model.addAttribute("mainCate", mainCate);
        model.addAttribute("responseDto", responseDto);
        return "/items/game/subCate";
    }

    @GetMapping("/doll")
    public String goodsDoll(PageRequestDto pageRequestDto, Model model) {
        String keyword = "인형";
        String subCate = "doll";
        String mainCate = "goods";
        PageResponseDto<Item> responseDto =
                itemService.subList(pageRequestDto, keyword);
        model.addAttribute("categoryName", keyword);
        model.addAttribute("subCate", subCate);
        model.addAttribute("mainCate", mainCate);
        model.addAttribute("responseDto", responseDto);
        return "/items/game/subCate";
    }

    @GetMapping("/travel")
    public String goodsTravel(PageRequestDto pageRequestDto, Model model) {
        String keyword = "트래블굿즈";
        String subCate = "travel";
        String mainCate = "goods";
        PageResponseDto<Item> responseDto =
                itemService.subList(pageRequestDto, keyword);
        model.addAttribute("categoryName", keyword);
        model.addAttribute("subCate", subCate);
        model.addAttribute("mainCate", mainCate);
        model.addAttribute("responseDto", responseDto);
        return "/items/game/subCate";
    }

    @GetMapping("/clothes")
    public String gameAction(PageRequestDto pageRequestDto, Model model) {
        String keyword = "옷";
        String subCate = "clothes";
        String mainCate = "goods";
        PageResponseDto<Item> responseDto =
                itemService.subList(pageRequestDto, keyword);
        model.addAttribute("categoryName", keyword);
        model.addAttribute("subCate", subCate);
        model.addAttribute("mainCate", mainCate);
        model.addAttribute("responseDto", responseDto);
        return "/items/game/subCate";
    }

}
