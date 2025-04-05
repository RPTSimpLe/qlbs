package com.shopeeClone.shopeeClone.api.user;


import com.shopeeClone.shopeeClone.dto.MomoRequest;
import com.shopeeClone.shopeeClone.service.IPaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class ApiPayment {
    @Autowired
    private IPaymentService paymentService;
    //MOMO
    @PostMapping("/momo/{amount}")
    public ResponseEntity<String> momoPayment(@PathVariable String amount) {
        String response = paymentService.createPaymentRequest(amount);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/order-status/{orderId}")
    public ResponseEntity<String> checkPaymentStatus(@PathVariable String orderId) {
        String response = paymentService.checkPaymentStatus(orderId);
        return ResponseEntity.ok(response);
    }

}
