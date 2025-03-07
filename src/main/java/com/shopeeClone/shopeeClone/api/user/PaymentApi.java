package com.shopeeClone.shopeeClone.api.user;

import com.shopeeClone.shopeeClone.dto.PaymentDTO;
import com.shopeeClone.shopeeClone.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentApi {
    private final PaymentService paymentService;
    @GetMapping("/vn-pay")
    public PaymentDTO.VNPayResponse pay(HttpServletRequest request) {
        return paymentService.createVnPayPayment(request);
    }
}