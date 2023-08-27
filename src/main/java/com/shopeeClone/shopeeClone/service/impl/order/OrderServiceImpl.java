package com.shopeeClone.shopeeClone.service.impl.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopeeClone.shopeeClone.converter.order.OrderConvervter;
import com.shopeeClone.shopeeClone.dto.cart.CartDTO;
import com.shopeeClone.shopeeClone.dto.cart.CartRequestForm;
import com.shopeeClone.shopeeClone.dto.order.CreateOrderForm;
import com.shopeeClone.shopeeClone.dto.order.OrderDTO;
import com.shopeeClone.shopeeClone.entity.OrderEntity;
import com.shopeeClone.shopeeClone.entity.OrderProductEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.repository.CartRepository;
import com.shopeeClone.shopeeClone.repository.OrderProductRepository;
import com.shopeeClone.shopeeClone.repository.OrderRepository;
import com.shopeeClone.shopeeClone.repository.ProductRepository;
import com.shopeeClone.shopeeClone.service.CartService;
import com.shopeeClone.shopeeClone.service.order.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderConvervter convervter;
	@Autowired
	private OrderProductRepository orderProductRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CartService cartService;
	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public OrderDTO create(CreateOrderForm form) {
		OrderEntity entity = convervter.toEntity(form);
		orderRepository.save(entity);

		for(int i = 0; i < form.getProductIds().size(); i++) {
			OrderProductEntity orderProductEntity = new OrderProductEntity();
			orderProductEntity.setOrder(entity);
			orderProductEntity.setProduct(productRepository.findById(form.getProductIds().get(i)).orElseThrow(() -> new ValidateException("khong thay product")));
			orderProductEntity.setQuantity(form.getQuantities().get(i));
			orderProductRepository.save(orderProductEntity);
		}
		cartService.multiDelete(form.getCartIds());
		return convervter.toDTO(entity);
	}
	

}
