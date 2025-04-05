package com.shopeeClone.shopeeClone.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MomoConfig {
    @Value("MOMO")
    private String PARTNER_CODE;
    @Value("F8BBA842ECF85")
    private String ACCESS_KEY;
    @Value("K951B6PE1waDMi640xX08PD3vg6EkVlz")
    private String SECRET_KEY;
    @Value("http://localhost:8080/order/view")
    private String REDIRECT_URL;
    @Value("https://callback.url/notify")
    private String IPN_URL;
    @Value("payWithMethod")
    private String REQUEST_TYPE;

    public String getPARTNER_CODE() {
        return PARTNER_CODE;
    }

    public void setPARTNER_CODE(String PARTNER_CODE) {
        this.PARTNER_CODE = PARTNER_CODE;
    }

    public String getACCESS_KEY() {
        return ACCESS_KEY;
    }

    public void setACCESS_KEY(String ACCESS_KEY) {
        this.ACCESS_KEY = ACCESS_KEY;
    }

    public String getSECRET_KEY() {
        return SECRET_KEY;
    }

    public void setSECRET_KEY(String SECRET_KEY) {
        this.SECRET_KEY = SECRET_KEY;
    }

    public String getREDIRECT_URL() {
        return REDIRECT_URL;
    }

    public void setREDIRECT_URL(String REDIRECT_URL) {
        this.REDIRECT_URL = REDIRECT_URL;
    }

    public String getIPN_URL() {
        return IPN_URL;
    }

    public void setIPN_URL(String IPN_URL) {
        this.IPN_URL = IPN_URL;
    }

    public String getREQUEST_TYPE() {
        return REQUEST_TYPE;
    }

    public void setREQUEST_TYPE(String REQUEST_TYPE) {
        this.REQUEST_TYPE = REQUEST_TYPE;
    }
}
