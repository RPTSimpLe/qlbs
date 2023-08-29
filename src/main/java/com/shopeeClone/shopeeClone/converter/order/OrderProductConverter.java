package com.shopeeClone.shopeeClone.converter.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopeeClone.shopeeClone.converter.AddressConverter;
import com.shopeeClone.shopeeClone.converter.product.ProductConverter;
import com.shopeeClone.shopeeClone.dto.ProductDTO;
import com.shopeeClone.shopeeClone.dto.order.OrderProductDTO;
import com.shopeeClone.shopeeClone.entity.OrderProductEntity;
import com.shopeeClone.shopeeClone.repository.OrderProductRepository;

@Component
public class OrderProductConverter {
	@Autowired
	private OrderProductRepository orderProductRepository;
	@Autowired
	private AddressConverter addressConverter;
	@Autowired
	private ProductConverter converter;

	public OrderProductDTO toDto(OrderProductEntity entity) {
		OrderProductDTO dto = new OrderProductDTO();
		dto.setAddress(addressConverter.toDto(entity.getOrder().getAddress()).getAddress());
		dto.setQuantity(entity.getQuantity());
		dto.setOrderProductId(entity.getOrderProductId());
		dto.setDto(converter.toDTO(entity.getProduct()));
		return dto;
	}
}
