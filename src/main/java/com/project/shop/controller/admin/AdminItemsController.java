package com.project.shop.controller.admin;

import com.project.shop.entity.Item;
import com.project.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/admin")
@RequiredArgsConstructor
@Controller
public class AdminItemsController {

	private final ItemService itemService;

	// 상품 등록 리스트
	@GetMapping(value = "/ItemList")
	public String adminItemsList(Model model) {
		model.addAttribute("list", itemService.itemList());
		model.addAttribute("modify", "수정");
		model.addAttribute("delete", "삭제");

		return "/admin/admin_ItemList";
	}

	// 상품 등록 페이지
	@GetMapping("/newItem")
	public String newItem() {
		return "/admin/newItem";
	}

	// 상품 등록 처리(db로 전송)
	@PostMapping("/newItemReg")
	public String newItemReg(Item item, @RequestPart MultipartFile file) throws Exception {
		itemService.itemWrite(item, file);

		return "redirect:/admin/ItemList";
	}

	// 상품 삭제
	@PostMapping("/delete/{id}")
	public String deleteItem(@PathVariable ("id") Long id) {

		itemService.itemDelete(id);

		return "redirect:/admin/ItemList";
	}

	@GetMapping("/viewItem")
	public String viewItem(Model model, Long id) {
		model.addAttribute("item", itemService.itemView(id));
		return "/admin/modifyItem";
	}


	@GetMapping("/modifyItem/{id}")
	public String modifyItem(@PathVariable ("id")Long id, Model model) {

		return "/admin/modifyItem";
	}
}
