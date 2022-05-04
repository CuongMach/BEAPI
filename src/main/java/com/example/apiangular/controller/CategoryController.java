package com.example.apiangular.controller;

import com.example.apiangular.entity.Category;
import com.example.apiangular.entity.Product;
import com.example.apiangular.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<Category>>findAll(){
        Iterable<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<Category> findAllById(@PathVariable Long id){
        Optional<Category> categoryOptional = categoryService.findById(id);
        if(!categoryOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Category> save(@ModelAttribute Category category) {
        categoryService.save(category);
        return new ResponseEntity<>( category, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Category newCategory = categoryOptional.get();
        newCategory.setId(category.getId());
        newCategory.setName(category.getName());
        categoryService.save(newCategory);
        return new ResponseEntity<>(newCategory, HttpStatus.OK);
    }



}
