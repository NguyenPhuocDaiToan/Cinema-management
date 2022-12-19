package com.example.a09cinema_backenddevelop.dto;

import org.springframework.beans.factory.annotation.Value;

public interface StatisticFilm {
    @Value("#{target.name}")
    String getName();

    @Value("#{target.numbersTicket}")
    int getNumberTicket();

    @Value("#{target.money}")
    double getMoney();
}
