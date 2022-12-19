package com.example.a09cinema_backenddevelop.service;
import com.example.a09cinema_backenddevelop.model.entity.Category;
import java.util.List;

import com.example.a09cinema_backenddevelop.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    List<Category> findAllCategory();
    Category findById(Long id);
}
