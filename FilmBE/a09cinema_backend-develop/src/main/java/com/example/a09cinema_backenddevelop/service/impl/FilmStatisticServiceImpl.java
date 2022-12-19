package com.example.a09cinema_backenddevelop.service.impl;

import com.example.a09cinema_backenddevelop.dto.StatisticFilm;
import com.example.a09cinema_backenddevelop.repository.FilmStatsticRepository;
import com.example.a09cinema_backenddevelop.service.FilmStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmStatisticServiceImpl implements FilmStatisticService {

    @Autowired
    private FilmStatsticRepository filmStatsticRepository;


    @Override
    public List<StatisticFilm> statisticalByCurrentDay() {
        return filmStatsticRepository.statisticalByCurrentDay();
    }

    @Override
    public Page<StatisticFilm> findAllAndPage(Pageable pageable) {
        return filmStatsticRepository.findAllAndPage(pageable);
    }

    @Override
    public List<StatisticFilm> findAllStatisticFilm() {
        return filmStatsticRepository.findAllStatisticFilm();
    }


}
