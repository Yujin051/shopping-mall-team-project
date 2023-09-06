package com.project.shop.controller.support;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class faqController {

    @GetMapping("/support/faq")
    public String faq() {
        return "faq";
    }
}
