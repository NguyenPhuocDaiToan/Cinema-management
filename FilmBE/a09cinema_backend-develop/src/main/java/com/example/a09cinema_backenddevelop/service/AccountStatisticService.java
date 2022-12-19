package com.example.a09cinema_backenddevelop.service;

import com.example.a09cinema_backenddevelop.dto.StatisticAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountStatisticService {

    List<StatisticAccount> statisticalByAccountDay();

    Page<StatisticAccount> findAllAndPage(Pageable pageable);

    List<StatisticAccount> findAllStatisticAccount();

}
