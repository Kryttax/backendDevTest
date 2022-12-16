package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

import com.example.demo.data.Product;
import com.example.demo.service.ServiceProduct;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private ServiceProduct service;

    @Test
    public void getSimilarTest() throws Exception {
        Product p1 = new Product("1", "Test Product 1", 10.60, true);
        Product p2 = new Product("2", "Test Product 2", 100000000, false);

        List<String> productsIds = new ArrayList<>(Arrays.asList(p1.getId(), p2.getId()));

        when(service.getSimilars("1")).thenReturn(productsIds);
        when(service.getProductDetails("1")).thenReturn(p1);
        when(service.getProductDetails("2")).thenReturn(p2);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/product/1/similar")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value(p1.getName()))
                .andExpect(jsonPath("$[0].price").value(p1.getPrice()))
                .andExpect(jsonPath("$[1].price").value(p2.getPrice()))
                .andExpect(jsonPath("$[1].available").value(p2.isAvailable()));
    }

    @Test
    public void getNotFoundPath() throws Exception {
        // Invoke wrong url for controller path
        mockMvc.perform(MockMvcRequestBuilders
                .get("/prod")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}