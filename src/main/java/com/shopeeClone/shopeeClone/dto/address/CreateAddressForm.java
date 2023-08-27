package com.shopeeClone.shopeeClone.dto.address;

public class CreateAddressForm {
	private Long provinceId;
	private Long districtId;
	private Long wardId;
	private String description;
	public Long getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
