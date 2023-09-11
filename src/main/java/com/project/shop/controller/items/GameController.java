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
@RequestMapping("/items")
public class GameController {

	private final ItemService itemService;

	@GetMapping("/game")
	public String gameMain(PageRequestDto pageRequestDto, Model model) {

		PageResponseDto<ItemDto> responseDto = itemService.list(pageRequestDto);
		model.addAttribute("responseDto", responseDto);

		return "/items/game/root";
	}

	@GetMapping("/game/rpg")
	public String gameRpg(PageRequestDto pageRequestDto, Model model) {
		String keyword = "RPG";
		PageResponseDto<ItemDto> responseDto =
				itemService.subList(pageRequestDto, keyword);
		model.addAttribute("responseDto", responseDto);
		return "/items/game/rpg";
	}

}
