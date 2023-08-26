package com.shopeeClone.shopeeClone.converter.cart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopeeClone.shopeeClone.converter.product.ProductConverter;
import com.shopeeClone.shopeeClone.dto.cart.CartDTO;
import com.shopeeClone.shopeeClone.dto.cart.CartRequestForm;
import com.shopeeClone.shopeeClone.entity.CartEntity;
import com.shopeeClone.shopeeClone.entity.ImageEntity;
import com.shopeeClone.shopeeClone.entity.ProductEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.repository.ProductRepository;
import com.shopeeClone.shopeeClone.repository.UserRepository;
import com.shopeeClone.shopeeClone.service.ProductService;

@Component
public class CartConverter {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductConverter productConverter;
	
	public CartDTO toDTO(CartEntity cartEntity) {
		CartDTO cartDTO = new CartDTO();
		cartDTO.setCartId(cartEntity.getCartId());
		cartDTO.setQuantity(cartEntity.getQuantity());
		cartDTO.setCreateDate(cartEntity.getCreateDate());
		cartDTO.setModifiDate(cartEntity.getModifierDate());
		cartDTO.setProduct(productConverter.toDTO(cartEntity.getProduct()));
		return cartDTO;
	}
	
	public CartEntity toEntity(CartRequestForm requestForm) {
		CartEntity cartEntity = new CartEntity();
		cartEntity.setQuantity(requestForm.getQuantity());
		cartEntity.setUser(userRepository.findById(requestForm.getUserId()).orElseThrow(() -> new ValidateException("khong thay user")));
		cartEntity.setProduct(productRepository.findById(requestForm.getProductId()).orElseThrow(() -> new ValidateException("khong thay product")));
		return cartEntity;
	}
	
	public List<CartDTO> toDTO(List<CartEntity> cartEntity){
		List<CartDTO> dtos = new ArrayList<CartDTO>();
		for (CartEntity cartEntity2 : cartEntity) {
			CartDTO dto = toDTO(cartEntity2);
			dtos.add(dto);
		}
		return dtos;
	}
}
