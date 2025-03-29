package com.shopeeClone.shopeeClone.service.impl.order;

import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.dto.rate.RateDTO;
import com.shopeeClone.shopeeClone.dto.user.UserDTO;
import com.shopeeClone.shopeeClone.entity.CartEntity;
import com.shopeeClone.shopeeClone.entity.OrderEntity;
import com.shopeeClone.shopeeClone.repository.*;
import com.shopeeClone.shopeeClone.service.UserService;
import com.shopeeClone.shopeeClone.utils.AppStringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
import com.shopeeClone.shopeeClone.service.CartService;
import com.shopeeClone.shopeeClone.service.order.OrderService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private EntityManager entityManager;
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
	@Autowired
	private UserService userService;
    @Autowired
    private OrderConvervter orderConvervter;

	@Override
	public OrderDTO create(CreateOrderForm form) {

		UserDTO userDTO = userService.getUser();
		List<CartEntity> cartEntity = cartRepository.findAllByUId(userDTO.getUserId());
		form.setCartIds(cartEntity.stream().map(e -> e.getCartId()).collect(Collectors.toList()));
		form.setProductIds(cartEntity.stream().map(e->e.getProduct().getProductId()).collect(Collectors.toList()));
		form.setUserId(userDTO.getUserId());
		form.setQuantities(cartEntity.stream().map(e->e.getQuantity()).collect(Collectors.toList()));
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

	@Override
	public List<OrderDTO> getall() {
		List<OrderEntity> orderEntityList =orderRepository.findAll();
		List<OrderDTO> orderDTOList = orderEntityList.stream().map(e -> convervter.toDTO(e)).collect(Collectors.toList());
		return orderDTOList;
	}

	@Override
	public List<OrderDTO> findByUser() {
		UserDTO userDTO = userService.getUser();
		List<OrderEntity> orderEntityList =orderRepository.findByUserId(userDTO.getUserId());
		List<OrderDTO> orderDTOList = orderEntityList.stream().map(e -> convervter.toDTO(e)).collect(Collectors.toList());
		return orderDTOList;	}

	@Override
	public PageDTO<OrderDTO> findByUserName(Map<String, String> params) {
		String pageStr = params.get("page");
		String limitStr = params.get("limit");

		Integer page =1;
		Integer limit = 10;
		if(AppStringUtils.hasTextAnd(pageStr)){
			page = Integer.valueOf(pageStr);
		}
		if(AppStringUtils.hasTextAnd(limitStr)){
			limit = Integer.valueOf(limitStr);
		}

		StringBuilder selectQueryBuilder = new StringBuilder("select r from OrderEntity r ");
		StringBuilder countQueryBuilder = new StringBuilder("select count(r.id) from OrderEntity r ");

		String username = params.get("username");
		if(AppStringUtils.hasTextAnd(username)){
			selectQueryBuilder.append(" where r.user.username like :username");
			countQueryBuilder.append(" where r.user.username like :username");
		}

		TypedQuery<OrderEntity> selectQuery = entityManager.createQuery(selectQueryBuilder.toString(), OrderEntity.class);
		TypedQuery<Long> countQuery = entityManager.createQuery(countQueryBuilder.toString(), Long.class);

		if(AppStringUtils.hasTextAnd(username)){
			selectQuery.setParameter("username", "%"+username+"%");
			countQuery.setParameter("username", "%"+username+"%");
		}

		Integer firstItem = (page-1) * limit;

		selectQueryBuilder.append(" order by r.createDate desc ");
		selectQuery.setFirstResult(firstItem);
		selectQuery.setMaxResults(limit);

		List<OrderEntity> orderEntityList = selectQuery.getResultList();
		List<OrderDTO> dtos = orderConvervter.toDTO(orderEntityList);

		Long count = countQuery.getSingleResult();
		return new PageDTO<>(page,limit,count,dtos);
	}

}
