package com.project.shop.controller.admin;

import com.project.shop.entity.Item;
import com.project.shop.entity.Notice;
import com.project.shop.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.Errors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
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
	public String adminItemsList(Model model, @PageableDefault(size = 15) Pageable pageable) {

		Page<Item> itemListPages = itemService.itemListPaging(pageable);

		int startPage = Math.max(1, itemListPages.getPageable().getPageNumber() - 2);
		int endPage = Math.min(itemListPages.getPageable().getPageNumber() + 2, itemListPages.getTotalPages());

		// model.addAttribute("list", itemService.itemList());
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("list", itemListPages);
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
	public String newItemReg(@Valid Item item, Errors error, @RequestPart MultipartFile file) throws Exception {
		itemService.itemWrite(item, file);

		return "redirect:/admin/ItemList";
	}

	// 상품 삭제
	@GetMapping("/deleteItem")
	public String deleteItem(Long id, Model model) {
		itemService.itemDelete(id);

		model.addAttribute("message", "상품이 삭제되었습니다.");
		model.addAttribute("SearchUrl", "/admin/ItemList");

		return "/admin/Message";
	}


	@GetMapping("/modifyItem/{id}")
	public String modifyItem(@PathVariable ("id")Long id, Model model) {
		model.addAttribute("item", itemService.itemView(id));

		return "/admin/modifyItem";
	}


	@PostMapping("/updateItem/{id}")
	public String noticeUpdate(@PathVariable("id")Long id, Item item, Model model, MultipartFile file) throws Exception {
		Item itemUpdate = itemService.itemView(id);
		itemUpdate.setItemName(item.getItemName());
		itemUpdate.setItemContent(item.getItemContent());
		itemUpdate.setItemPrice(item.getItemPrice());
		itemUpdate.setItemQty(item.getItemQty());
		itemUpdate.setImgOriginal(item.getImgOriginal());
		itemUpdate.setImgSaved(item.getImgSaved());
		itemUpdate.setMainCate(item.getMainCate());
		itemUpdate.setSubCate(item.getSubCate());
		model.addAttribute("message", "게시글이 수정되었습니다.");
		model.addAttribute("SearchUrl", "/admin/ItemList");

		itemService.itemWrite(itemUpdate, file);

		return "notice/Message";


	}
}
