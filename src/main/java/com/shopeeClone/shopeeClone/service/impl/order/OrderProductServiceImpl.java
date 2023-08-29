package com.shopeeClone.shopeeClone.service.impl.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopeeClone.shopeeClone.converter.order.OrderProductConverter;
import com.shopeeClone.shopeeClone.dto.order.OrderProductDTO;
import com.shopeeClone.shopeeClone.entity.OrderEntity;
import com.shopeeClone.shopeeClone.entity.OrderProductEntity;
import com.shopeeClone.shopeeClone.repository.OrderProductRepository;
import com.shopeeClone.shopeeClone.repository.OrderRepository;
import com.shopeeClone.shopeeClone.service.order.OrderProductService;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

	@Autowired
	private OrderProductRepository repository;
	@Autowired 
	private OrderRepository orderRepository;
	@Autowired
	private OrderProductConverter converter;
	
	@Override
	public void delete(List<Long> ids) {
		for (Long id : ids) {
			OrderProductEntity entity = repository.findById(id).orElseThrow();
			
			OrderEntity orderEntity = orderRepository.findById(entity.getOrder().getOrderId()).orElseThrow();
			orderEntity.setUser(null);
			orderEntity.setAddress(null);
			entity.setOrder(null);
			entity.setProduct(null);
			
			repository.save(entity);
			repository.delete(entity);

			orderRepository.save(orderEntity);
			orderRepository.delete(orderEntity);
		}
	}

	@Override
	public List<OrderProductDTO> getall() {
		List<OrderProductEntity> entities = repository.findAll();
		List<OrderProductDTO> dtos = new ArrayList<OrderProductDTO>();
		for (OrderProductEntity orderProductEntity : entities) {
			OrderProductDTO dto = new OrderProductDTO();
			dto = converter.toDto(orderProductEntity);
			dtos.add(dto);
		}
		return dtos;
	}

}
