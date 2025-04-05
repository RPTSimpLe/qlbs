package com.shopeeClone.shopeeClone.service.impl;

import com.shopeeClone.shopeeClone.config.MomoConfig;
import com.shopeeClone.shopeeClone.service.IPaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class IPaymentServiceImpl implements IPaymentService {
    @Autowired
    private MomoConfig momoConfig;


    //momo
    public String createPaymentRequest(String amount) {
        try {
            // Generate requestId and orderId
            String requestId = momoConfig.getPARTNER_CODE() + new Date().getTime();
            String orderId = requestId;
            String orderInfo = "SN Mobile";
            String extraData = "";

            // Generate raw signature
            String rawSignature = String.format(
                    "accessKey=%s&amount=%s&extraData=%s&ipnUrl=%s&orderId=%s&orderInfo=%s&partnerCode=%s&redirectUrl=%s&requestId=%s&requestType=%s",
                    momoConfig.getACCESS_KEY(), amount, extraData, momoConfig.getIPN_URL(), orderId, orderInfo, momoConfig.getPARTNER_CODE(), momoConfig.getREDIRECT_URL(),
                    requestId, momoConfig.getREQUEST_TYPE());

            // Sign with HMAC SHA256
            String signature = signHmacSHA256(rawSignature, momoConfig.getSECRET_KEY());

            JSONObject requestBody = new JSONObject();
            requestBody.put("partnerCode", momoConfig.getPARTNER_CODE());
            requestBody.put("accessKey", momoConfig.getACCESS_KEY());
            requestBody.put("requestId", requestId);
            requestBody.put("amount", amount);
            requestBody.put("orderId", orderId);
            requestBody.put("orderInfo", orderInfo);
            requestBody.put("redirectUrl", momoConfig.getREDIRECT_URL());
            requestBody.put("ipnUrl", momoConfig.getIPN_URL());
            requestBody.put("extraData", extraData);
            requestBody.put("requestType", momoConfig.getREQUEST_TYPE());
            requestBody.put("signature", signature);
            requestBody.put("lang", "en");

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://test-payment.momo.vn/v2/gateway/api/create");
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(requestBody.toString(), StandardCharsets.UTF_8));

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return "{\"error\": \"Failed to create payment request: " + e.getMessage() + "\"}";
        }
    }

    // HMAC SHA256 signing method
    private static String signHmacSHA256(String data, String key) throws Exception {
        Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        hmacSHA256.init(secretKey);
        byte[] hash = hmacSHA256.doFinal(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @Override
    public String checkPaymentStatus(String orderId) {
        try {
            String requestId = momoConfig.getPARTNER_CODE() + new Date().getTime();
            String rawSignature = String.format(
                    "accessKey=%s&orderId=%s&partnerCode=%s&requestId=%s",
                    momoConfig.getACCESS_KEY(), orderId, momoConfig.getPARTNER_CODE(), requestId);
            String signature = signHmacSHA256(rawSignature, momoConfig.getSECRET_KEY());

            JSONObject requestBody = new JSONObject();
            requestBody.put("partnerCode", momoConfig.getPARTNER_CODE());
            requestBody.put("accessKey", momoConfig.getACCESS_KEY());
            requestBody.put("requestId", requestId);
            requestBody.put("orderId", orderId);
            requestBody.put("signature", signature);
            requestBody.put("lang", "en");

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://test-payment.momo.vn/v2/gateway/api/query");
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(requestBody.toString(), StandardCharsets.UTF_8));

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"Failed to check payment status: " + e.getMessage() + "\"}";
        }
    }
}
