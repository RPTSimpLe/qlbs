package com.shopeeClone.shopeeClone.api.user;

import java.util.List;
import java.util.Map;

import com.shopeeClone.shopeeClone.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import com.shopeeClone.shopeeClone.dto.cart.CartDTO;
import com.shopeeClone.shopeeClone.dto.order.CreateOrderForm;
import com.shopeeClone.shopeeClone.dto.order.OrderDTO;
import com.shopeeClone.shopeeClone.dto.order.OrderProductDTO;
import com.shopeeClone.shopeeClone.service.order.OrderProductService;
import com.shopeeClone.shopeeClone.service.order.OrderService;

@RestController
@RequestMapping("api/v1/orders")
public class OrderV1Api {
	@Autowired
	private OrderService service;
	
	@PostMapping
	public OrderDTO create(@RequestBody CreateOrderForm form) {
		return service.create(form);
	}

	@GetMapping()
	public List<OrderDTO> getAll() {
		return service.getall();
	}

	@GetMapping("/findByUser")
	public List<OrderDTO> findByUser() {
		return service.findByUser();
	}
	@GetMapping("/findByUserName")
	public PageDTO<OrderDTO> findByUserName(@RequestParam Map<String, String> params) {
		return service.findByUserName(params);
	}
}
