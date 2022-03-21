package com.rakib.paginationsorting.controller;

import com.rakib.paginationsorting.dto.APIResponse;
import com.rakib.paginationsorting.entity.Product;
import com.rakib.paginationsorting.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public APIResponse<List<Product>> getProducts() {
        List<Product> list = service.findAllProduct();
        return new APIResponse<>(list.size(), list);
    }
}
