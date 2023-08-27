package com.shopeeClone.shopeeClone.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "districts")
public class DistrictEntity extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long districtId;
	
	private String name;
	
	@Column(unique = true)
	private String code;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "province_id")
	private ProvinceEntity province;
	
	public ProvinceEntity getProvince() {
		return province;
	}

	public void setProvince(ProvinceEntity province) {
		this.province = province;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long provinceId) {
		this.districtId = provinceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
