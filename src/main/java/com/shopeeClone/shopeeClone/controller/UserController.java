package com.shopeeClone.shopeeClone.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class UserController {
	
	@GetMapping
	public String home() {
		return "user/homePage";
	}

	@GetMapping("/signUp")
	public String signup() {
		return "signUp";
	}
}
