package com.example.a09cinema_backenddevelop.service.impl;

import com.example.a09cinema_backenddevelop.dto.StatisticAccount;
import com.example.a09cinema_backenddevelop.repository.AccountStatisticRepository;
import com.example.a09cinema_backenddevelop.service.AccountStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountStatisticServiceImpl implements AccountStatisticService {

    @Autowired
    private AccountStatisticRepository accountStatisticRepository;

    @Override
    public List<StatisticAccount> statisticalByAccountDay() {
        return accountStatisticRepository.statisticalByAccountDay();
    }

    @Override
    public Page<StatisticAccount> findAllAndPage(Pageable pageable) {
        return accountStatisticRepository.findAllAndPageAccount(pageable);
    }

    @Override
    public List<StatisticAccount> findAllStatisticAccount() {
        return accountStatisticRepository.findAllStatisticAccount();
    }

}
