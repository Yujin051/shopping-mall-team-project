package com.project.shop.controller.items;

import com.project.shop.dto.PageRequestDto;
import com.project.shop.dto.PageResponseDto;
import com.project.shop.entity.Item;
import com.project.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "")
    public String items(Model model, Long id) {
        // 하나의 아이템(상품) 정보 조회하는 메소드
        model.addAttribute("items", itemService.itemView(id));
        return "items/root";
    }

    @GetMapping(value = "/search")
    public String itemsSearch(PageRequestDto pageRequestDto, Model model) {
        // 검색된 엔티티 값을 응답 객체로 받아 모델에 넣어 프론트로 넘겨주는 메소드
        PageResponseDto<Item> responseDto = itemService.list(pageRequestDto);
        model.addAttribute("responseDto", responseDto);
		return "items/search";
	}
}
