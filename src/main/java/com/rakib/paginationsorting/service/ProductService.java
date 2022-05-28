package com.rakib.paginationsorting.service;

import com.rakib.paginationsorting.entity.Product;
import com.rakib.paginationsorting.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static support.Constant.DEFAULT_SEARCH_ITEM;

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

    public Page<Product> findProductWithPagination(Optional<Integer> offSet, Optional<Integer> pageSize) {
        int currentPage = offSet.orElse(0);
        int currentPerPage = pageSize.orElse(DEFAULT_SEARCH_ITEM);
        Pageable pageable = PageRequest.of(currentPage, currentPerPage);
        Page<Product> products = repository.findAll(pageable);
        return products;
    }

    public List<Product> findProductWithSorting(Optional<String> field, Optional<String> sortDirection) {
        String sortColumn = field.orElse("id");
        String sortDir = sortDirection.orElse(Sort.Direction.DESC.name());
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortColumn).ascending()
                : Sort.by(sortColumn).descending();
        return repository.findAll(sort);
    }

    public List<Product> getPaginationAndSortedData(int pageNo, int pageSize, String sortedBy, String sortedDirection) {

        String sortDir = !sortedDirection.isBlank() ? Sort.Direction.ASC.name() : Sort.Direction.DESC.name();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortedBy).ascending()
                : Sort.by(sortedBy).descending();
        Pageable page = PageRequest.of(pageNo, pageSize, sort);
        Page<Product> pageValue = repository.findAll(page);
        if (pageValue.hasContent()) {
            return pageValue.getContent();
        }
        return null;
    }
}
