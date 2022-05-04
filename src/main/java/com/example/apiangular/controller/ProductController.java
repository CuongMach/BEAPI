package com.example.apiangular.controller;

import com.example.apiangular.dto.ProductForm;
import com.example.apiangular.entity.Product;
import com.example.apiangular.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Value("C:/Codegym/img-md5/")
    String uploadPath;

    @GetMapping()
    public ResponseEntity<Iterable<Product>> findAll() {
        Iterable<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> save(@ModelAttribute ProductForm productForm) {
        MultipartFile imageFile = productForm.getImage(); //Lấy file ảnh
        String fileName = imageFile.getOriginalFilename(); //Lấy tên ảnh
        long currentTime = System.currentTimeMillis(); //Lấy thời gian hiện tại
        fileName = currentTime + fileName;
        try {
            FileCopyUtils.copy(imageFile.getBytes(),new File(uploadPath+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Product product = new Product();

        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        product.setDescription(productForm.getDescription());
        product.setCategory(productForm.getCategory());
        product.setImage(fileName);
        productService.save(product);
        return new ResponseEntity<>( product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product newProduct = productOptional.get();
        newProduct.setId(product.getId());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setDescription(product.getDescription());
        productService.save(newProduct);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

}
