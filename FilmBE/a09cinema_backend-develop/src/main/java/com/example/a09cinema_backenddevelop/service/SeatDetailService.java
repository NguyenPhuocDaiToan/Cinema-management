package com.example.a09cinema_backenddevelop.service;

import com.example.a09cinema_backenddevelop.model.entity.SeatDetail;
import java.util.Date;
import com.example.a09cinema_backenddevelop.model.dto.SeatDetailDto;
import java.util.List;

public interface SeatDetailService {
    SeatDetail findById(long id);
    List<SeatDetail> getAllSeatDetailByIdFilm(Long id);

    SeatDetailDto getIdSeatDetailByBookingCondition(String name, String date_show, long time_show_id);

    SeatDetail getInfoSeatDetailById(long id);

    List<SeatDetail> getAllSeatDetailByIdToChoose(Date date_show, long film_id, long room_id, long time_id);
}
