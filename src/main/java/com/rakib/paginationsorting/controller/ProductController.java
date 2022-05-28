package com.rakib.paginationsorting.controller;

import com.rakib.paginationsorting.dto.APIResponse;
import com.rakib.paginationsorting.entity.Product;
import com.rakib.paginationsorting.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("pagination")
    private APIResponse<Page<Product>> getProductWithPagination(@RequestParam Optional<Integer> offSet,
                                                                @RequestParam Optional<Integer> pageSize) {
        Page<Product> page = service.findProductWithPagination(offSet, pageSize);
        return new APIResponse<>(page.getSize(), page);
    }

    @GetMapping("bySorting")
    public APIResponse<List<Product>> getProductWithSort(@RequestParam Optional<String> field,
                                                         @RequestParam Optional<String> sortDirection) {
        List<Product> products = service.findProductWithSorting(field, sortDirection);
        return new APIResponse<>(products.size(), products);
    }

    public ResponseEntity<List<Product>> getPaginationAndSortedData(@RequestParam(defaultValue = "0") int pageNo,
                                                                    @RequestParam(defaultValue = "10") int pageSize,
                                                                    @RequestParam(defaultValue = "id") String sortedBy,
                                                                    @RequestParam(defaultValue = "DESC") String sortedDirection) {
        List<Product> productList = service.getPaginationAndSortedData(pageNo, pageSize, sortedBy, sortedDirection);
        return new ResponseEntity<>(productList, HttpStatus.OK);

    }
}
