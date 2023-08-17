package com.shopeeClone.shopeeClone.controller;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopeeClone.shopeeClone.utils.AppStringUtils;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserController {
	
	@GetMapping
	public String home() {
		return "user/homePage";
	}

}
