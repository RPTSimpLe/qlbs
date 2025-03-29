package com.shopeeClone.shopeeClone.converter.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.shopeeClone.shopeeClone.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopeeClone.shopeeClone.converter.AddressConverter;
import com.shopeeClone.shopeeClone.dto.order.CreateOrderForm;
import com.shopeeClone.shopeeClone.dto.order.OrderDTO;
import com.shopeeClone.shopeeClone.entity.OrderEntity;
import com.shopeeClone.shopeeClone.entity.OrderProductEntity;
import com.shopeeClone.shopeeClone.entity.ProductEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.repository.OrderProductRepository;
import com.shopeeClone.shopeeClone.repository.ProductRepository;
import com.shopeeClone.shopeeClone.repository.UserRepository;
import com.shopeeClone.shopeeClone.repository.address.AddressRepository;

@Component
public class OrderConvervter {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private AddressConverter addressConverter;
	@Autowired
	private OrderProductConverter orderProductConverter;
	@Autowired
	private UserConverter userConverter;
	public OrderDTO toDTO(OrderEntity entity) {
		OrderDTO dto = new OrderDTO();
		dto.setName(entity.getUser().getFirstName()+" "+entity.getUser().getLastName());
		dto.setAddress(addressConverter.toDto(entity.getAddress()).getAddress());
		dto.setDescription(entity.getDescription());
		dto.setCreateDate(entity.getCreateDate());
		dto.setTotalOrder(entity.getTotalOrder());
		dto.setCreateBy(entity.getCreateBy());
		dto.setOrderProductDTOS(orderProductConverter.toDto(entity.getOrderProduct()));
		dto.setUser(userConverter.toDTO(entity.getUser()));
		return dto;
	}
	public List<OrderDTO> toDTO(List<OrderEntity> orderEntityList) {
		List<OrderDTO> dtos = new ArrayList<>();
		for (OrderEntity orderEntity : orderEntityList) {
			dtos.add(toDTO(orderEntity));
		}
		return dtos;
	}
	
	public OrderEntity toEntity(CreateOrderForm form) {
		OrderEntity entity = new OrderEntity();
		entity.setUser(userRepository.findById(form.getUserId()).orElseThrow(() -> new ValidateException("khong thay user")));
		entity.setAddress(addressRepository.findById(form.getAddressId()).orElseThrow(() -> new ValidateException("khong thay dia chi")));
		entity.setDescription(form.getDescription());
		entity.setCreateDate(form.getCreateDate());
		entity.setTotalOrder(form.getTotalOrder());
		entity.setCreateBy(form.getCreateBy());
		return entity;
		
	
	}
}
