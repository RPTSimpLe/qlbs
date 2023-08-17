package com.shopeeClone.shopeeClone.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name = "orders")
public class OrderEntity extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	
	@ManyToOne
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name = "address_id")
	private AddressEntity address;
	
	private String description;
	
	@OneToMany(mappedBy = "order")
	private List<OrderProductEntity> orderProduct = new ArrayList<>();

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<OrderProductEntity> getOrderProduct() {
		return orderProduct;
	}

	public void setOrderProduct(List<OrderProductEntity> orderProduct) {
		this.orderProduct = orderProduct;
	}
	
}
