package com.shopeeClone.shopeeClone.entity;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String phoneNumber;
	
	@OneToMany(mappedBy = "user")
	private List<AddressEntity> address;
	
	@ManyToMany(mappedBy = "users")
	private List<RoleEntity> roles = new ArrayList<>();

	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", address=" + address
				+ ", roles=" + roles + "]";
	}
	
	
	
}
