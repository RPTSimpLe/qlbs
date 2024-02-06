package com.shopeeClone.shopeeClone.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopeeClone.shopeeClone.converter.cart.CartConverter;
import com.shopeeClone.shopeeClone.dto.cart.CartDTO;
import com.shopeeClone.shopeeClone.dto.cart.CartRequestForm;
import com.shopeeClone.shopeeClone.dto.cart.UpdateCart;
import com.shopeeClone.shopeeClone.entity.CartEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.repository.CartRepository;
import com.shopeeClone.shopeeClone.service.CartService;


@Service
@Transactional
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartConverter cartConverter;
	
	@Override
	public CartDTO create(CartRequestForm requestForm) {
		CartEntity cartEntity = cartConverter.toEntity(requestForm);
		List<CartEntity> cartEntities = cartRepository.findAll();
		int count=0;
		for (CartEntity cart : cartEntities){
			UpdateCart updateCart = new UpdateCart(cartEntity.getQuantity());
			if (cart.getProduct()==cartEntity.getProduct()){
				update(cart.getCartId(),updateCart);
				count++;
			}
		}
		if (count==0){
			cartRepository.save(cartEntity);
		}
		return cartConverter.toDTO(cartEntity);
	}

	@Override
	public List<CartDTO> getAll() {
		List<CartEntity>entities = cartRepository.findAll();
		return cartConverter.toDTO(entities);
	}

	@Override
	public void delete(Long cartId) {
		CartEntity cartEntity = cartRepository.findById(cartId).orElseThrow(() -> new ValidateException("không tìm thấy cart"));
		cartRepository.delete(cartEntity);
	}

	@Override
	public void deleteAll(Long uId) {
		List<CartEntity>cartEntities = cartRepository.findAllByUId(uId);
		for (CartEntity cartEntity: cartEntities){
			cartRepository.delete(cartEntity);
		}
	}

	@Override
	public void multiDelete(List<Long> cartIds) {
		for (Long long1 : cartIds) {
			delete(long1);
		}
		
	}
	@Override
	public List<CartDTO> getAllByUId(Long uId) {
		List<CartEntity>cartEntities = cartRepository.findAllByUId(uId);
		List<CartDTO>cartDTOS = cartConverter.toDTO(cartEntities);
		return cartDTOS;
	}
	@Override
	public CartDTO update(Long id, UpdateCart cart) {
		CartEntity cartEntity = cartRepository.findById(id).orElseThrow(() -> new ValidateException("không tìm thấy cart"));
		Integer newQuantity =cart.getQuantity()+cartEntity.getQuantity();

		cartEntity.setQuantity(newQuantity);
		cartRepository.save(cartEntity);
		return cartConverter.toDTO(cartEntity);
	}

	@Override
	public Long countCart(Long uId) {
		return cartRepository.countCart(uId);
	}
}
