package com.rakib.paginationsorting.controller;

import com.rakib.paginationsorting.dto.APIResponse;
import com.rakib.paginationsorting.entity.Product;
import com.rakib.paginationsorting.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private APIResponse<List<Product>> getProducts() {
        List<Product> list = service.findAllProduct();
        return new APIResponse<>(list.size(), list);
    }

    @GetMapping("/pagination/{offSet}/{pageSize}")
    private APIResponse<Page<Product>> getProductWithPagination(@PathVariable int offSet,
                                                                @PathVariable int pageSize) {
        Page<Product> page = service.findProductWithPagination(offSet, pageSize);
        return new APIResponse<>(page.getSize(), page);
    }

}
