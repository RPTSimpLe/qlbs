package com.shopeeClone.shopeeClone.controller;

import com.shopeeClone.shopeeClone.api.user.CartV1Api;
import com.shopeeClone.shopeeClone.api.user.UserV1Api;
import com.shopeeClone.shopeeClone.dto.cart.CartDTO;
import com.shopeeClone.shopeeClone.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartV1Api cartV1Api;
    @Autowired
    private UserV1Api userV1Api;
    @GetMapping("/listCart")
    public String cart(Model model){
        UserDTO userDTO = userV1Api.getUser();
        List<CartDTO> cartDTOS =cartV1Api.getAllByUId(userDTO.getUserId());
        model.addAttribute("cartDTOS",cartDTOS);
        return "user/cart/listCart";
    }
}
