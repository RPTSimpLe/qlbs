package com.shopeeClone.shopeeClone.utils;

import com.shopeeClone.shopeeClone.exeption.ValidateException;

public class AppStringUtils {
	public static boolean hasText(String input) {
		return input == null || input.isEmpty();
	}
	
	public static boolean hasNumber(Long id) {
		return id == null;
	}
	
	public static boolean hasTextAnd(String input) {
		return input != null && !input.isEmpty();
	}
}
