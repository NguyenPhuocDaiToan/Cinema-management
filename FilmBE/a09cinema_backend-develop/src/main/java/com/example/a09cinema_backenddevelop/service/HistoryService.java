package com.example.a09cinema_backenddevelop.service;

import com.example.a09cinema_backenddevelop.model.History;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoryService {
    /*
        Nguyen Phuoc Dai Toan: find total elements of list point reward
    */
    int findTotalPointReward(LocalDateTime start, LocalDateTime end, Long accountId);
    /*
        Nguyen Phuoc Dai Toan: find total elements of list point exchange
    */
    int findTotalPointExchange(LocalDateTime start, LocalDateTime end, Long accountId);
    /*
        Nguyen Phuoc Dai Toan: find list point reward by pagination
    */
    List<History> findHistoriesPointReward(LocalDateTime start, LocalDateTime end, int offset, int numberRecord, Long accountId);
    /*
        Nguyen Phuoc Dai Toan: find list point exchange by pagination
    */
    List<History> findHistoriesPointExchange(LocalDateTime start, LocalDateTime end, int offset, int numberRecord, Long accountId);
}
