package com.example.a09cinema_backenddevelop.controller;
import com.example.a09cinema_backenddevelop.model.entity.Booking;

import com.example.a09cinema_backenddevelop.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin("*")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/info")
    public Booking getBookingById(@RequestParam("id")long id){
        Booking booking = bookingService.findById(id);
        return booking;
    }

    @PostMapping(value = "/add-booking")
    public ResponseEntity<Booking> addBooking(@RequestBody Booking[] bookings) {
        for (Booking booking : bookings) {
            bookingService.save(booking);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
