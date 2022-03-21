package com.rakib.paginationsorting.service;

import com.rakib.paginationsorting.entity.Product;
import com.rakib.paginationsorting.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

   /* @PostConstruct
    public void intDB() {
        List<Product> productList = IntStream.rangeClosed(1, 200)
                .mapToObj(i -> new Product("Product" + i, new Random().nextInt(100),
                        new Random().nextInt(5000))).collect(Collectors.toList());
        repository.saveAll(productList);
    }*/

    public List<Product> findAllProduct() {
        return repository.findAll();
    }

    public Page<Product> findProductWithPagination(int offSet, int pageSize) {
        Pageable pageable = PageRequest.of(offSet, pageSize);
        Page<Product> products = repository.findAll(pageable);
        return products;
    }
}
