package com.shopeeClone.shopeeClone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderControiller {
    @GetMapping("/order/view")
    public String index(){
        return "user/order/order";
    }

    @GetMapping("/admin/order")
    public String index1(){
        return "admin/order/order";
    }
}
