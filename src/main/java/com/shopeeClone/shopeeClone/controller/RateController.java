package com.shopeeClone.shopeeClone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RateController {
    @GetMapping("admin/rate/list")
    public String getAll(){
        return "admin/rate/listRate";
    }
}
