package com.postal.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    //这里注入的restTemplate就是在com.sam.ConsumerApp中通过@Bean配置的实例
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/upload")
    public Object helloConsumer(@RequestBody String data, HttpServletRequest request) {
        //调用hello-service服务，注意这里用的是服务名，而不是具体的ip+port
       //String resutl =  restTemplate.getForObject("http://position/position/hello", String.class);
        //拿到header信息
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
          String key = (String) headerNames.nextElement();
          String value = request.getHeader(key);
          requestHeaders.add(key, value);
        }
        HttpEntity<String> requestEntity = new HttpEntity<String>(data, requestHeaders);
    	Object  resutl =  restTemplate.postForObject("http://position/position/upload", requestEntity, Object.class);
        System.out.println(resutl);
        return resutl;
    }
}