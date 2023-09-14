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
@RequestMapping("/items")
public class GameController {

	private final ItemService itemService;

	// 첫 정렬 키워드를 프론트에서 파라미터로 받아서 검색할 수 있게
	// 개선할 수 있지 않을까? 일단은 편법으로 각 페이지별로 파라미터를
	// 일일이 하드코딩한 방식으로 첫 정렬 페이지 구현
	@GetMapping("/game")
	public String gameMain(PageRequestDto pageRequestDto, Model model) {
		String keyword = "게임";
		PageResponseDto<Item> responseDto =
				itemService.mainList(pageRequestDto, keyword);
		model.addAttribute("responseDto", responseDto);

		return "/items/game/root";
	}

	@GetMapping("/game/rpg")
	public String gameRpg(PageRequestDto pageRequestDto, Model model) {
		String keyword = "RPG";
		PageResponseDto<Item> responseDto =
				itemService.subList(pageRequestDto, keyword);
		model.addAttribute("responseDto", responseDto);
		return "/items/game/rpg";
	}

}
