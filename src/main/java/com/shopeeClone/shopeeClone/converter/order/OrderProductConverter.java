package com.shopeeClone.shopeeClone.converter.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopeeClone.shopeeClone.converter.AddressConverter;
import com.shopeeClone.shopeeClone.converter.product.ProductConverter;
import com.shopeeClone.shopeeClone.dto.order.OrderProductDTO;
import com.shopeeClone.shopeeClone.entity.OrderProductEntity;
import com.shopeeClone.shopeeClone.repository.OrderProductRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderProductConverter {
	@Autowired
	private OrderProductRepository orderProductRepository;
	@Autowired
	private ProductConverter converter;

	public OrderProductDTO toDto(OrderProductEntity entity) {
		OrderProductDTO dto = new OrderProductDTO();
		dto.setQuantity(entity.getQuantity());
		dto.setOrderProductId(entity.getOrderProductId());
		dto.setDto(converter.toDTO(entity.getProduct()));
		return dto;
	}
	public List<OrderProductDTO> toDto(List<OrderProductEntity> entityList) {
		List<OrderProductDTO> dtoList = new ArrayList<>();
		for (OrderProductEntity entity : entityList) {
			dtoList.add(toDto(entity));
		}
		return dtoList;
	}
}
