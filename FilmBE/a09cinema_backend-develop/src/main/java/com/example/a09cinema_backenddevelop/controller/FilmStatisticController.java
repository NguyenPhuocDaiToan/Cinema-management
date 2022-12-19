package com.example.a09cinema_backenddevelop.controller;

import com.example.a09cinema_backenddevelop.dto.StatisticFilm;
import com.example.a09cinema_backenddevelop.repository.FilmStatsticRepository;
import com.example.a09cinema_backenddevelop.service.FilmStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/statistic/")
public class FilmStatisticController {

    @Autowired
    private FilmStatisticService filmStatisticService;

    @Autowired
    private FilmStatsticRepository filmStatsticRepository;

    @GetMapping("/currentDay")
    public ResponseEntity<List<StatisticFilm>> filmStatistic() {

        List<StatisticFilm> statisticFilms = filmStatisticService.statisticalByCurrentDay();

        if (statisticFilms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<StatisticFilm>>(statisticFilms, HttpStatus.OK);
    }

    @GetMapping("/filmStatisticPage")
    public ResponseEntity<Page<StatisticFilm>> filmStatisticPage(@PageableDefault(size = 5) Pageable pageable)  {

            Page<StatisticFilm> StatisticFilms = filmStatsticRepository.findAllAndPage(pageable);
            return new ResponseEntity<>(StatisticFilms, HttpStatus.OK);
    }


    @GetMapping("/totalPrice")
    public ResponseEntity<Integer> getTotalPrice() {
        List<StatisticFilm> list = filmStatsticRepository.findAllStatisticFilm();
        int sum = 0;
        for(StatisticFilm statisticFilm : list) {
            sum += statisticFilm.getMoney();
        }
        return new ResponseEntity<>(sum, HttpStatus.OK);
    }
}
