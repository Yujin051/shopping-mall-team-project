package com.project.shop.controller;

import com.project.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    ItemService itemService;

    @GetMapping(value = "/")
    // 메인 페이지 상품 목록은 임시로 상위 1~12번 데이터 출력하도록 설정
    public String main(Model model) {
        model.addAttribute("list", itemService.itemList());
        return "main";
    }
}