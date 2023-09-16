package com.project.shop.controller.items;

import com.project.shop.dto.PageRequestDto;
import com.project.shop.dto.PageResponseDto;
import com.project.shop.dto.ReviewDto;
import com.project.shop.repository.ReviewRepository;
import com.project.shop.entity.Item;
import com.project.shop.service.ItemService;
import com.project.shop.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemsController {

    private final ItemService itemService;
    private final ReviewRepository reviewRepository;

    @GetMapping(value = "")
    public String items(Model model, Long id) {
        // 하나의 아이템(상품) 정보 조회하는 메소드
        model.addAttribute("items", itemService.itemView(id));
        model.addAttribute("reviews", reviewRepository.findReviewDtoList(id));
        return "items/root";
    }

    @GetMapping(value = "/search")
    public String itemsSearch(PageRequestDto pageRequestDto, Model model) {
        // 검색된 엔티티 값을 응답 객체로 받아 모델에 넣어 프론트로 넘겨주는 메소드
        PageResponseDto<Item> responseDto = itemService.list(pageRequestDto);
        model.addAttribute("responseDto", responseDto);
		return "items/search";
	}

    @GetMapping("/hardware/root")
    public String hardwareMain(PageRequestDto pageRequestDto, Model model) {
        String keyword = "하드웨어";
        PageResponseDto<ItemDto> responseDto =
                itemService.mainList(pageRequestDto, keyword);
        model.addAttribute("cateName", keyword);
        model.addAttribute("responseDto", responseDto);

        return "/items/game/root";
    }

    @GetMapping("/goods/root")
    public String goodsMain(PageRequestDto pageRequestDto, Model model) {
        String keyword = "굿즈";
        PageResponseDto<ItemDto> responseDto =
                itemService.mainList(pageRequestDto, keyword);
        model.addAttribute("cateName", keyword);
        model.addAttribute("responseDto", responseDto);

        return "/items/game/root";
    }

    @GetMapping("/accessory/root")
    public String accessoryMain(PageRequestDto pageRequestDto, Model model) {
        String keyword = "악세서리";
        PageResponseDto<ItemDto> responseDto =
                itemService.mainList(pageRequestDto, keyword);
        model.addAttribute("cateName", keyword);
        model.addAttribute("responseDto", responseDto);

        return "/items/game/root";
    }
}
