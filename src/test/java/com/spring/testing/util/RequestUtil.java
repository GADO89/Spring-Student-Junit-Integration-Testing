package com.spring.testing.util;

import jakarta.annotation.PostConstruct;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestUtil {

    private RestTemplate restTemplate;

    @PostConstruct
    public void setup(){
        CloseableHttpClient httpClient= HttpClients.custom().disableCookieManagement()
                .setConnectionManager()
        HttpComponentsClientHttpRequestFactory requestFactory
                =new HttpComponentsClientHttpRequestFactory();
       restTemplate =new RestTemplate();
    }
}
