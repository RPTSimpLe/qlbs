package com.shopeeClone.shopeeClone.utils;

import com.shopeeClone.shopeeClone.exeption.ValidateException;

public class validate {
	public static Long validateId(String id) {
		if(AppStringUtils.hasNumber(Long.valueOf(id))) {
			throw new ValidateException("id la 1 so");
		}
		return Long.valueOf(id);
	}
}
