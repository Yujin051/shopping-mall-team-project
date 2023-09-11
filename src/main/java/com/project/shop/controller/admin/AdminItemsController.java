package com.project.shop.controller.admin;

import com.project.shop.entity.Item;
import com.project.shop.entity.ItemImg;
import com.project.shop.service.ItemImgService;
import com.project.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileStore;

@RequestMapping("/admin")
@Controller
public class AdminItemsController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemImgService itemImgService;

	@GetMapping(value = "/ItemList")
	public String adminItemsList() {
		return "/admin/admin_ItemList";
	}

	@GetMapping("/newItem")
	public String newItem() {
		return "/admin/newItem";
	}

	@PostMapping("/newItemReg")
	public String newItemReg(Item item, MultipartFile files) {


		//itemService.newItem(item, files);

		return "redirect:/admin/ItemList";
	}
	
	@GetMapping("/modifyItem")
	public String modifyItem() {
		return "/admin/modifyItem";
	}
}
