package com.example.a09cinema_backenddevelop.service.impl;

import com.example.a09cinema_backenddevelop.model.History;
import com.example.a09cinema_backenddevelop.repository.BookingRepository;
import com.example.a09cinema_backenddevelop.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private BookingRepository bookingRepository;

    /*
         Nguyen Phuoc Dai Toan: find total elements of list point reward
     */
    @Override
    public int findTotalPointReward(LocalDateTime start, LocalDateTime end, Long accountId) {
        return bookingRepository.findTotalPointReward(start, end, accountId);
    }

    /*
        Nguyen Phuoc Dai Toan: find total elements of list point exchange
    */
    @Override
    public int findTotalPointExchange(LocalDateTime start, LocalDateTime end, Long accountId) {
        return bookingRepository.findTotalPointExchange(start, end, accountId);
    }

    /*
        Nguyen Phuoc Dai Toan: find list point reward by pagination
    */
    @Override
    public List<History> findHistoriesPointReward(LocalDateTime start, LocalDateTime end, int offset, int numberRecord, Long accountId) {
        return bookingRepository.findHistoriesPointReward(start, end, offset, numberRecord, accountId);
    }

    /*
        Nguyen Phuoc Dai Toan: find list point exchange by pagination
    */
    @Override
    public List<History> findHistoriesPointExchange(LocalDateTime start, LocalDateTime end, int offset, int numberRecord, Long accountId) {
        return bookingRepository.findHistoriesPointExchange(start, end, offset, numberRecord, accountId);
    }
}
