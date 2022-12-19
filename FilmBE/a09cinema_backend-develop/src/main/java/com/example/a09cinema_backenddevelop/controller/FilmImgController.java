package com.example.a09cinema_backenddevelop.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.a09cinema_backenddevelop.model.entity.FilmImg;
import com.example.a09cinema_backenddevelop.service.FilmImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/filmImg")
@CrossOrigin(origins = "*")
public class FilmImgController {
    @Autowired
    private FilmImgService filmImgService;

    @GetMapping
    public ResponseEntity<Iterable<FilmImg>> find() {
        List<FilmImg> filmImgs = filmImgService.findAll();
        if(filmImgs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(filmImgs, HttpStatus.OK);
    }
}
