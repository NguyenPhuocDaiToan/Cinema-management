package com.example.a09cinema_backenddevelop.service.impl;

import com.example.a09cinema_backenddevelop.model.entity.CategoryFilm;
import com.example.a09cinema_backenddevelop.repository.CategoryFilmRepository;
import com.example.a09cinema_backenddevelop.service.CategoryFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class CategoryFilmServiceImpl implements CategoryFilmService {
    @Autowired
    private CategoryFilmRepository categoryFilmRepository;
    @Override
    public List<CategoryFilm> findAllCategoryFilm() {
        return categoryFilmRepository.findAllCategoryFilm();
    }

    @Override
    public Boolean saveCategoryFilm(Long id,Long categoryId, Long filmId) {
        try{
            categoryFilmRepository.saveCategoryFilm(id,categoryId,filmId);
        }catch (Exception e){
            System.out.printf(String.valueOf(e));
            return false;
        }
       return true;
    }

    @Override
    public Long findNumberOfRecord() {
        return categoryFilmRepository.findNumberOfRecord();
    }
}
