package com.cwsoft.multi_tenant.controller;

import com.cwsoft.multi_tenant.model.Product;
import com.cwsoft.multi_tenant.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getCompanyProducts() {
        return productRepository.findAll(); // Automatically fetches products for the tenant's schema
    }
}
