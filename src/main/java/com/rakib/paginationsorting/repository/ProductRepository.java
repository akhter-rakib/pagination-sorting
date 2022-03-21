package com.rakib.paginationsorting.repository;

import com.rakib.paginationsorting.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
