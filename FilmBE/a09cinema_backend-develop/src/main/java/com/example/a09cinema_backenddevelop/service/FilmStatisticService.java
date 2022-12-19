package com.example.a09cinema_backenddevelop.service;

import com.example.a09cinema_backenddevelop.dto.StatisticFilm;
import com.example.a09cinema_backenddevelop.dto.StatisticFilm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FilmStatisticService {

    List<StatisticFilm> statisticalByCurrentDay();

    Page<StatisticFilm> findAllAndPage(Pageable pageable);

    List<StatisticFilm> findAllStatisticFilm();



}
