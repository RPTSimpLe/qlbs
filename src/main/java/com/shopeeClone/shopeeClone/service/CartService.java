package com.shopeeClone.shopeeClone.service;

import java.util.List;

import com.shopeeClone.shopeeClone.dto.cart.CartDTO;
import com.shopeeClone.shopeeClone.dto.cart.CartRequestForm;
import com.shopeeClone.shopeeClone.dto.cart.UpdateCart;

public interface CartService {

	CartDTO create(CartRequestForm requestForm);

	List<CartDTO> getAll();

	void delete(Long cartId);
	void deleteAll(Long uId);

	void multiDelete(List<Long> cartIds);

	CartDTO update(Long id, UpdateCart cart);

    List<CartDTO> getAllByUId(Long uId);

    Long countCart(Long uId);
}
