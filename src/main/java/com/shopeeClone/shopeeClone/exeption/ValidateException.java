package com.shopeeClone.shopeeClone.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// http statusCode
// OK -> 200
// 404 -> Not found
// 400 -> Validate
// 500 -> Logic

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ValidateException extends ApplicationException {

	public ValidateException(String message) {
		super(message);
	}

}
