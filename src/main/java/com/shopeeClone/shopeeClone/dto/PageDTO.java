package com.shopeeClone.shopeeClone.dto;

import java.util.ArrayList;
import java.util.List;

public class PageDTO<T> {
	private Integer page;
	private Integer limit;
	private Long totalItems;
	private List<T> data = new ArrayList<>();
	
	public PageDTO(Integer page, Integer limit, Long totalItems, List<T> data) {
		this.page = page;
		this.limit = limit;
		this.totalItems = totalItems;
		this.data = data;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getLimit() {
		return limit;
	}

	public Long getTotalItems() {
		return totalItems;
	}

	public List<T> getData() {
		return data;
	}
	
	
}
