package com.shopeeClone.shopeeClone.api.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	private OrderService orderService;
	@Autowired
	private OrderProductService service;
	
	@PostMapping
	public OrderDTO create(@RequestBody CreateOrderForm form) {
		return orderService.create(form);
	}
	
	
	//orderproduct
	@GetMapping()
	public List<OrderProductDTO> getAll() {
		return service.getall();
	}
	
	@DeleteMapping("{ids}")
	public void delete(@PathVariable List<Long> ids) {
		 service.delete(ids);
	}
}
