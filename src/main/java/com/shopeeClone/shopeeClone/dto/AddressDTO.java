package com.shopeeClone.shopeeClone.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressDTO {
	
	private String provinceName;
	private String districtName;
	private String wardName;
	private String description;
}
