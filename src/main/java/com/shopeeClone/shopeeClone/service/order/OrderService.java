package com.shopeeClone.shopeeClone.service.order;

import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.dto.cart.CartDTO;
import com.shopeeClone.shopeeClone.dto.order.CreateOrderForm;
import com.shopeeClone.shopeeClone.dto.order.OrderDTO;

import java.util.List;
import java.util.Map;

public interface OrderService {

	OrderDTO create(CreateOrderForm form);

	List<OrderDTO> getall();

	List<OrderDTO> findByUser();

	PageDTO<OrderDTO> findByUserName(Map<String, String> params);
}
