package com.shopeeClone.shopeeClone.service.order;

import java.util.List;

import com.shopeeClone.shopeeClone.dto.order.OrderProductDTO;

public interface OrderProductService {

	void delete(List<Long> ids);

	List<OrderProductDTO> getall();

}
