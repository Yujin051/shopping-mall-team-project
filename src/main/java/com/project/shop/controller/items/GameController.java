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
@RequestMapping("/items/game")
public class GameController {

	private final ItemService itemService;

	// 첫 정렬 키워드를 프론트에서 파라미터로 받아서 검색할 수 있게
	// 개선할 수 있지 않을까? 일단은 편법으로 각 페이지별로 파라미터를
	// 일일이 하드코딩한 방식으로 첫 정렬 페이지 구현
	@GetMapping("")
	public String gameMain(PageRequestDto pageRequestDto, Model model) {
		String keyword = "게임";
		String mainCate = "game";
		PageResponseDto<Item> responseDto =
				itemService.mainList(pageRequestDto, keyword);
		model.addAttribute("cateName", keyword);
		model.addAttribute("mainCate", mainCate);
		model.addAttribute("responseDto", responseDto);

		return "/items/game/root";
	}

	@GetMapping("/action")
	public String gameAction(PageRequestDto pageRequestDto, Model model) {
		String keyword = "액션";
		String subCate = "action";
		String mainCate = "game";
		PageResponseDto<Item> responseDto =
				itemService.subList(pageRequestDto, keyword);
		model.addAttribute("categoryName", keyword);
		model.addAttribute("subCate", subCate);
		model.addAttribute("mainCate", mainCate);
		model.addAttribute("responseDto", responseDto);
		return "/items/game/subCate";
	}
	@GetMapping("/adventure")
	public String gameAdventure(PageRequestDto pageRequestDto, Model model) {
		String keyword = "어드벤쳐";
		String subCate = "adventure";
		String mainCate = "game";
		PageResponseDto<Item> responseDto =
				itemService.subList(pageRequestDto, keyword);
		model.addAttribute("categoryName", keyword);
		model.addAttribute("subCate", subCate);
		model.addAttribute("mainCate", mainCate);
		model.addAttribute("responseDto", responseDto);
		return "/items/game/subCate";
	}

	@GetMapping("/racing")
	public String gameRacing(PageRequestDto pageRequestDto, Model model) {
		String keyword = "레이싱";
		String subCate = "racing";
		String mainCate = "game";
		PageResponseDto<Item> responseDto =
				itemService.subList(pageRequestDto, keyword);
		model.addAttribute("categoryName", keyword);
		model.addAttribute("subCate", subCate);
		model.addAttribute("mainCate", mainCate);
		model.addAttribute("responseDto", responseDto);
		return "/items/game/subCate";
	}
	@GetMapping("/sport")
	public String gameSports(PageRequestDto pageRequestDto, Model model) {
		String keyword = "스포츠";
		String subCate = "sport";
		String mainCate = "game";
		PageResponseDto<Item> responseDto =
				itemService.subList(pageRequestDto, keyword);
		model.addAttribute("categoryName", keyword);
		model.addAttribute("subCate", subCate);
		model.addAttribute("mainCate", mainCate);
		model.addAttribute("responseDto", responseDto);
		return "/items/game/subCate";
	}


	@GetMapping("/rpg")
	public String gameRpg(PageRequestDto pageRequestDto, Model model) {
		String keyword = "RPG/롤플레잉";
		String subCate = "rpg";
		String mainCate = "game";
		PageResponseDto<Item> responseDto =
				itemService.subList(pageRequestDto, keyword);
		model.addAttribute("categoryName", keyword);
		model.addAttribute("subCate", subCate);
		model.addAttribute("mainCate", mainCate);
		model.addAttribute("responseDto", responseDto);
		return "/items/game/subCate";
	}

	@GetMapping("/fps")
	public String gameFPS(PageRequestDto pageRequestDto, Model model) {
		String keyword = "FPS/슈팅";
		String subCate = "fps";
		String mainCate = "game";
		PageResponseDto<Item> responseDto =
				itemService.subList(pageRequestDto, keyword);
		model.addAttribute("categoryName", keyword);
		model.addAttribute("subCate", subCate);
		model.addAttribute("mainCate", mainCate);
		model.addAttribute("responseDto", responseDto);
		return "/items/game/subCate";
	}

	@GetMapping("/party")
	public String gameParty(PageRequestDto pageRequestDto, Model model) {
		String keyword = "파티";
		String subCate = "party";
		String mainCate = "game";
		PageResponseDto<Item> responseDto =
				itemService.subList(pageRequestDto, keyword);
		model.addAttribute("categoryName", keyword);
		model.addAttribute("subCate", subCate);
		model.addAttribute("mainCate", mainCate);
		model.addAttribute("responseDto", responseDto);
		return "/items/game/subCate";
	}
	@GetMapping("/rts")
	public String gameStrategy(PageRequestDto pageRequestDto, Model model) {
		String keyword = "전략";
		String subCate = "rts";
		String mainCate = "game";
		PageResponseDto<Item> responseDto =
				itemService.subList(pageRequestDto, keyword);
		model.addAttribute("categoryName", keyword);
		model.addAttribute("subCate", subCate);
		model.addAttribute("mainCate", mainCate);
		model.addAttribute("responseDto", responseDto);
		return "/items/game/subCate";
	}
	@GetMapping("/simulation")
	public String gameSimulation(PageRequestDto pageRequestDto, Model model) {
		String keyword = "시뮬레이션";
		String subCate = "simulation";
		String mainCate = "game";
		PageResponseDto<Item> responseDto =
				itemService.subList(pageRequestDto, keyword);
		model.addAttribute("categoryName", keyword);
		model.addAttribute("subCate", subCate);
		model.addAttribute("mainCate", mainCate);
		model.addAttribute("responseDto", responseDto);
		return "/items/game/subCate";
	}
	@GetMapping("/arcade")
	public String gameArcade(PageRequestDto pageRequestDto, Model model) {
		String keyword = "아케이드/퍼즐";
		String subCate = "arcade";
		String mainCate = "game";
		PageResponseDto<Item> responseDto =
				itemService.subList(pageRequestDto, keyword);
		model.addAttribute("categoryName", keyword);
		model.addAttribute("subCate", subCate);
		model.addAttribute("mainCate", mainCate);
		model.addAttribute("responseDto", responseDto);
		return "/items/game/subCate";
	}
	@GetMapping("/music")
	public String gameMusic(PageRequestDto pageRequestDto, Model model) {
		String keyword = "음악";
		String subCate = "music";
		String mainCate = "game";
		PageResponseDto<Item> responseDto =
				itemService.subList(pageRequestDto, keyword);
		model.addAttribute("categoryName", keyword);
		model.addAttribute("subCate", subCate);
		model.addAttribute("mainCate", mainCate);
		model.addAttribute("responseDto", responseDto);
		return "/items/game/subCate";
	}
	@GetMapping("/etc")
	public String gameEtc(PageRequestDto pageRequestDto, Model model) {
		String keyword = "기타";
		String subCate = "etc";
		String mainCate = "game";
		PageResponseDto<Item> responseDto =
				itemService.subList(pageRequestDto, keyword);
		model.addAttribute("categoryName", keyword);
		model.addAttribute("subCate", subCate);
		model.addAttribute("mainCate", mainCate);
		model.addAttribute("responseDto", responseDto);
		return "/items/game/subCate";
	}

}
