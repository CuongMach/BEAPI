package com.example.apiangular.dto;

import com.example.apiangular.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductForm {
    private Long id;

    private String name;

    private double price;

    private String description;

    private MultipartFile image;

    private Category category;
}
