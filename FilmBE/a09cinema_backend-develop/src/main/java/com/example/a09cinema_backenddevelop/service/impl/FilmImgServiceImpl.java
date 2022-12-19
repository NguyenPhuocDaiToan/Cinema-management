package com.example.a09cinema_backenddevelop.service.impl;

import com.example.a09cinema_backenddevelop.model.entity.Film;
import com.example.a09cinema_backenddevelop.model.entity.FilmImg;
import com.example.a09cinema_backenddevelop.repository.FilmImgRepository;
import com.example.a09cinema_backenddevelop.service.FilmImgService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmImgServiceImpl implements FilmImgService {
    @Autowired
    private FilmImgRepository filmRepository;
    @Override
    public List<FilmImg> findAll() {
        return filmRepository.findAll();
    }
}
