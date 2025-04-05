package com.shopeeClone.shopeeClone.service;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public interface IPaymentService {

     //momo
     String createPaymentRequest(String amount);
     String checkPaymentStatus(String orderId);

}