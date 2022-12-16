package com.example.demo.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.example.demo.data.Product;

@Service
public class ServiceProduct {
    private final RestTemplate rest = new RestTemplate();
    private final String path = "http://localhost:3001/product/";

    public Product getProductDetails(@PathVariable("productId") String id) {
        return rest.getForObject(path + id, Product.class);
    }

    public List<String> getSimilars(@PathVariable("productId") String id) {
        return rest.exchange(
                path + id + "/similarids",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {
                }).getBody();
    }
}
