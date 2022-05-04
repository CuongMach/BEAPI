package com.example.apiangular.service.category;

import com.example.apiangular.entity.Category;
import com.example.apiangular.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public Iterable findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Object save(Object o) {
        return null;
    }


    @Override
    public void removeProductById(Long id) {

    }
}
