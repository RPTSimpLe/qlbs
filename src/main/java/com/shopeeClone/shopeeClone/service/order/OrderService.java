package com.shopeeClone.shopeeClone.service.order;

import com.shopeeClone.shopeeClone.dto.cart.CartDTO;
import com.shopeeClone.shopeeClone.dto.order.CreateOrderForm;
import com.shopeeClone.shopeeClone.dto.order.OrderDTO;

public interface OrderService {

	OrderDTO create(CreateOrderForm form);
	
}
