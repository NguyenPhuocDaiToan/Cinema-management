package com.example.a09cinema_backenddevelop.service;

import com.example.a09cinema_backenddevelop.model.entity.CategoryFilm;

import java.util.List;

public interface CategoryFilmService {
    List<CategoryFilm> findAllCategoryFilm();
    Boolean saveCategoryFilm(Long id, Long categoryId, Long filmId);
    Long findNumberOfRecord();
}
