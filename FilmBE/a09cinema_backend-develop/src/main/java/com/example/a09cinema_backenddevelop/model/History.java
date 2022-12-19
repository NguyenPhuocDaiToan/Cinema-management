package com.example.a09cinema_backenddevelop.model;

import org.springframework.beans.factory.annotation.Value;

public interface History {
    Long getId();
    @Value("#{target.name}")
    String getFilmName();
    @Value("#{target.day_time_booking}")
    String getDate();
    @Value("#{target.point_reward}")
    String getPointReward();
    @Value("#{target.point_exchange}")
    String getPointExchange();
}
