package com.example.apiangular.service.category;

import com.example.apiangular.entity.Category;

import java.util.Optional;

public interface ICategoryService<Category> {
    Iterable<Category> findAll();
    Optional<Category> findById(Long id);
    Category save(Category category);
    void removeProductById(Long id);
}
