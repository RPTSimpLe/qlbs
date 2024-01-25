package com.shopeeClone.shopeeClone.controller;

import com.shopeeClone.shopeeClone.entity.UserEntity;
import com.shopeeClone.shopeeClone.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SignupController {
    private UserService userService;
    @GetMapping("signUp")
    public String signUp(){
        return "signUp";
    }
}
