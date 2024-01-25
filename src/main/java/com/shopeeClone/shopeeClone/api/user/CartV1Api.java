package com.shopeeClone.shopeeClone.api.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopeeClone.shopeeClone.dto.cart.CartDTO;
import com.shopeeClone.shopeeClone.dto.cart.CartRequestForm;
import com.shopeeClone.shopeeClone.dto.cart.UpdateCart;
import com.shopeeClone.shopeeClone.service.CartService;

@RestController
@RequestMapping("api/v1/carts")
public class CartV1Api {
	@Autowired
	private CartService cartService;
	
	@PostMapping
	public CartDTO create(@RequestBody CartRequestForm requestForm) {
		return cartService.create(requestForm);
	}
	@GetMapping("/countCart")
	public Long countCart(@Param(value = "uId") Long uId){
		return cartService.countCart(uId);
	}
	@GetMapping("/{uId}")
	public List<CartDTO> getAllByUId(@PathVariable Long uId) {
		return cartService.getAllByUId(uId);
	}
	
	@DeleteMapping("{cartIds}")
	public void delete(@PathVariable List<Long> cartIds) {
		cartService.multiDelete(cartIds);
	}
	
	@PutMapping("{id}")
	public CartDTO update(@PathVariable Long id,@RequestBody UpdateCart cart ) {
		return cartService.update(id, cart);
	}
}
