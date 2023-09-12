package com.project.shop.controller.admin;

import com.project.shop.entity.Item;
import com.project.shop.entity.ItemImg;
import com.project.shop.repository.ItemRepository;
import com.project.shop.service.ItemImgService;
import com.project.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileStore;

@RequestMapping("/admin")
@RequiredArgsConstructor
@Controller
public class AdminItemsController {

	private final ItemService itemService;

	private final ItemImgService itemImgService;

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
	public String newItemReg(Item item, @RequestPart MultipartFile img_id) throws Exception {
		itemService.itemWrite(item, img_id);

		return "redirect:/admin/ItemList";
	}

	// 상품 삭제
	@GetMapping("/delete")
	public String deleteItem(Long id) {
		itemService.itemDelete(id);
		itemImgService.itemImgDelete(id);

		return "notice/message";
	}
	@GetMapping("/modifyItem/{id}")
	public String modifyItem(@PathVariable ("id")Long id, Model model) {

		return "/admin/modifyItem";
	}
}
