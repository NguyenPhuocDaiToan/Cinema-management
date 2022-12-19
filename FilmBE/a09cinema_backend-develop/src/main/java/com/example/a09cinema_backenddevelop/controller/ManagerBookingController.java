package com.example.a09cinema_backenddevelop.controller;
import com.example.a09cinema_backenddevelop.dto.SelectAllBookingDto;
import com.example.a09cinema_backenddevelop.dto.SelectBookingDetail;
import com.example.a09cinema_backenddevelop.model.entity.Booking;
import com.example.a09cinema_backenddevelop.service.ManagerBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/managerBooking")
public class ManagerBookingController {
    @Autowired
    private ManagerBookingService service;
    @GetMapping("/list")
    public ResponseEntity<Page<SelectAllBookingDto>> getAllBooking(@RequestParam(defaultValue = "") String key, @PageableDefault(size =5) Pageable pageable){
        Page<SelectAllBookingDto>bookings=service.findAll(key, pageable);
        if (bookings.isEmpty()){
            return new ResponseEntity<>(bookings,HttpStatus.OK);
        }
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<SelectBookingDetail> findBookingDetailById(@PathVariable Long id) {
        Optional<SelectBookingDetail> bookingDetail = service.selectBookingDetail(id);
        if (!bookingDetail.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookingDetail.get(), HttpStatus.OK);

    }
    @GetMapping("/takeTicket/{id}")
    public ResponseEntity<SelectBookingDetail> takeTicket(@PathVariable Long id) {
        Optional<Booking> booking = service.getById(id);
        if (booking.isPresent()){
            if (booking.get().getTotalPrice()<booking.get().getAccount().getTotalPoint()){
                booking.get().setReceived(true);
                service.save(booking.get());
                Optional<SelectBookingDetail> bookingDetail = service.selectBookingDetail(id);
                return new ResponseEntity<>(bookingDetail.get(),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
