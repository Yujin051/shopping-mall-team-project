//package com.project.shop.web;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import jakarta.servlet.http.*;
//import com.project.shop.auth.dto.SessionUser;
//
//@Controller
//public class IndexController {
//	HttpSession httpSession;
//	
//	@GetMapping("/")
//	public String index(Model model) {
//	    
//		model.addAttribute(model.getAttribute(null));
//	    SessionUser user = (SessionUser) httpSession.getAttribute("user");
//	    
//	    if (user != null) {
//	        model.addAttribute("userName", user.getName());
//	    }
//	    return "index";
//	}
//}
