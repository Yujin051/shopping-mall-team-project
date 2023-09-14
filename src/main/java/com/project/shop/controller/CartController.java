package com.project.shop.controller;

import com.project.shop.dto.CartDto;
import com.project.shop.dto.CartItemDto;
import com.project.shop.repository.CartItemRepository;
import com.project.shop.service.CartService;
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
public class CartController {

	private final CartService cartService;
	private final CartItemRepository cartItemRepository;

	@GetMapping("/cart")
	public String cart(Principal principal, Model model) {
		// principal 객체로 회원 정보 얻어온 후 장바구니 탐색
		// 장바구니 상품 정보 모델 통해 프론트로 넘겨주기
		String email = principal.getName();
		List<CartDto> cartDtoList = cartService.findCart(email);
		model.addAttribute("cartItems", cartDtoList);
		return "my/cart";
	}

	// post 요청은 장바구니 상품 추가 요청
	@PostMapping("/cart")
	@ResponseBody
	public ResponseEntity cart(@RequestBody @Valid CartItemDto cartItemDto,
							   BindingResult bindingResult, Principal principal) {

		// CartItemDto 는 프론트에서 넘어오는 값(상품 id, 개수)

		// BindingResult 이용하여 넘어오는 값이 검증에 실패했을 경우
		// 예외로 처리하여 ResponseEntity에 담아 응답
		if (bindingResult.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for (FieldError fieldError : fieldErrorList) {
				sb.append(fieldError.getDefaultMessage());
			}
			return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
		}

		// principal 객체에 담긴 현재 로그인 된 유저의 이름 가져오기
		String email = principal.getName();
		// 장바구니 상품의 번호를 담기 위한 Long형 변수 선언
		Long cartItemId;

		// 장바구니 상품 DTO 객체의 값과 회원 email을 파라미터로 새로운
		// 장바구니 상품 생성
		try {
			cartItemId = cartService.addCart(cartItemDto, email);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
	}

	// 장바구니 삭제 요청 처리 컨트롤러
	@DeleteMapping("/cart/{cartItemId}")
	@ResponseBody
	public ResponseEntity deleteCartItem(@PathVariable("cartItemId") Long cartItemId,
										 Principal principal) {
		// 장바구니가 해당 사용자의 장바구니인지 검증
		if(!cartService.validateCartItem(cartItemId, principal.getName())) {
			return  new ResponseEntity<String>("잘못된 접근입니다.", HttpStatus.FORBIDDEN);
		}

		// 해당하는 아이템 장바구니에서 삭제
		cartService.deleteCartItem(cartItemId);
		return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
	}
}
