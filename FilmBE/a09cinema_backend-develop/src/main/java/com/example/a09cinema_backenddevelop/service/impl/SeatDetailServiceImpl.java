package com.example.a09cinema_backenddevelop.service.impl;
import com.example.a09cinema_backenddevelop.model.dto.SeatDetailDto;
import com.example.a09cinema_backenddevelop.model.entity.SeatDetail;
import com.example.a09cinema_backenddevelop.repository.SeatDetailRepository;
import com.example.a09cinema_backenddevelop.service.SeatDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

import java.util.List;

@Service
public class SeatDetailServiceImpl implements SeatDetailService {
    @Autowired
    private SeatDetailRepository seatDetailRepository;
    @Override
    public SeatDetail findById(long id) {
        return seatDetailRepository.findById(id).orElse(null);}
    public List<SeatDetail> getAllSeatDetailByIdFilm(Long id) {
        return seatDetailRepository.getAllSeatDetailByIdFilm(id);
    }

    @Override
    public SeatDetailDto getIdSeatDetailByBookingCondition(String name, String date_show, long time_show_id) {
        return seatDetailRepository.getIdSeatDetailByBookingCondition(name, date_show, time_show_id);
    }

    @Override
    public SeatDetail getInfoSeatDetailById(long id) {
        return seatDetailRepository.getInfoSeatDetailById(id);
    }

    @Override
    public List<SeatDetail> getAllSeatDetailByIdToChoose(Date date_show, long film_id, long room_id, long time_id) {
        return seatDetailRepository.getAllSeatDetailByIdToChoose(date_show, film_id, room_id, time_id);
    }
}
