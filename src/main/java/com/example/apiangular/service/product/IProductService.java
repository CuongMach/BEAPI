package com.example.apiangular.service.product;

import java.util.Optional;

public interface IProductService <Product>{
    Iterable<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    void removeProductById(Long id);
}
