package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.Product;
import com.example.demo.service.ServiceProduct;

@RestController
@RequestMapping("/product")
public class ProductController {
  private final ServiceProduct instance;

  public ProductController(ServiceProduct instance) {
      this.instance = instance;
  }

  @GetMapping("/{productId}/similar")
  public ResponseEntity<List<Product>> getSimilarProducts(@PathVariable String productId) {
    return new ResponseEntity<List<Product>>(instance.getSimilars(productId).stream()
        .map(instance::getProductDetails)
        .collect(Collectors.toList()),
        HttpStatus.OK);
  }

}
