package com.shopeeClone.shopeeClone.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shopeeClone.shopeeClone.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
	
private Long userId;
	
	private String username;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date createdDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date modifierDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private String createdBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private String modifierBy;
	
	private List<UserEntity> role;
	private List<String> roleName = new ArrayList<String>();
	
	
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", username=" + username + ", firstName=" + firstName + ", lastName="
				+ lastName + ", phoneNumber=" + phoneNumber + ", address=" + address + ", createdDate=" + createdDate
				+ ", modifierDate=" + modifierDate + "]";
	}
	
}	
