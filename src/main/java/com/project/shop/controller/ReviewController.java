package com.project.shop.controller;

import com.project.shop.dto.ReviewDto;
import com.project.shop.dto.ReviewItemDto;
import com.project.shop.service.ItemService;
import com.project.shop.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ItemService itemService;
    private final ReviewService reviewService;

    @GetMapping("/review")
    public String review(Model model, @RequestParam("id") Long itemId,
                         Principal principal) {
        // 로그인 된 회원 정보 얻어오기
        String email = principal.getName();
        // 가져온 정보로 주문 정보 찾기
        ReviewItemDto reviewItemDto = reviewService.reviewItem(itemId, email);

        // 해당하는 상품 정보 넘겨주기
        model.addAttribute("item", itemService.itemView(itemId));
        model.addAttribute("order", reviewItemDto);
        return "my/review";
    }

    // 리뷰 입력은 비동기 처리 후 리디렉션 시도해보기
    @PostMapping("/review/write")
    @ResponseBody
    public ResponseEntity newReview(@RequestBody @Valid ReviewDto reviewDto,
                                    BindingResult bindingResult, Principal principal) {
        // BindingResult 이용하여 넘어오는 주문 폼의 값이 검증에 실패했을 경우
        // 예외로 처리하여 ResponseEntity에 담아 응답
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrorList) {
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }
        String email = principal.getName();
        Long reviewId;

        reviewId = reviewService.newReview(reviewDto, email);

        return new ResponseEntity<Long>(reviewId, HttpStatus.OK);
    }
}
