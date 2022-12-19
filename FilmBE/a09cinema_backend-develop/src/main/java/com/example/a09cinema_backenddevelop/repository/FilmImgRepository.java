package com.example.a09cinema_backenddevelop.repository;
import org.springframework.stereotype.Repository;

import com.example.a09cinema_backenddevelop.model.entity.FilmImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmImgRepository extends JpaRepository<FilmImg,Long> {
}
