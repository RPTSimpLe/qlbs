package com.shopeeClone.shopeeClone.dto.address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistrictDTO {
	private Long id;
	private String name;
	private String code;
	private Long provinceId;
}
