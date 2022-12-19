package com.example.a09cinema_backenddevelop.service;


import com.example.a09cinema_backenddevelop.model.entity.Booking;
public interface BookingService {
    Booking findById(long id);
    void save(Booking booking);
}
