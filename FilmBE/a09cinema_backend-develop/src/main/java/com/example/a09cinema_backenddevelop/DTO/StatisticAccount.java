package com.example.a09cinema_backenddevelop.dto;

import org.springframework.beans.factory.annotation.Value;

public interface StatisticAccount {
    @Value("#{target.fullname}")
    String getFullName();

    @Value("#{target.account_code}")
    String getAccountCode();

    @Value("#{target.numberTicket}")
    int getNumberTicket();

    @Value("#{target.money}")
    double getMoney();

    @Value("#{target.point}")
    int getPoint();
}
