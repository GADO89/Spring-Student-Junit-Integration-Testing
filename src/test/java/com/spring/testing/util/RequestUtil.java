package com.spring.testing.util;


import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class RequestUtil {

    private RestTemplate restTemplate;

    private  CloseableHttpClient httpClients;
    @PostConstruct
    public void setup() {
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory>create().register("http", new PlainConnectionSocketFactory()).build();
        BasicHttpClientConnectionManager connectionManager
                = new BasicHttpClientConnectionManager(socketFactoryRegistry);
        CloseableHttpClient HttpClient=HttpClients.custom().disableCookieManagement()
                .setConnectionManager(connectionManager).build();
        HttpComponentsClientHttpRequestFactory requestFactory=
                new HttpComponentsClientHttpRequestFactory(HttpClient);
        restTemplate = new RestTemplate(requestFactory);
    }
}
