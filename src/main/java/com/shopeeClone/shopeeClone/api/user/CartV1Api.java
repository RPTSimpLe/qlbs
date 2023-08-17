package com.shopeeClone.shopeeClone.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopeeClone.shopeeClone.dto.CartDTO;
import com.shopeeClone.shopeeClone.service.CartService;

@RestController
@RequestMapping("api/v1/carts")
public class CartV1Api {
	@Autowired
	private CartService cartService;
	
	@GetMapping
	public CartDTO create(@RequestBody CartDTO dto) {
		return cartService.create(dto);
	}
}
