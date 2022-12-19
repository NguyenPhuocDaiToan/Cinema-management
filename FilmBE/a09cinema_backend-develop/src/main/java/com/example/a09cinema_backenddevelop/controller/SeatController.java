package com.example.a09cinema_backenddevelop.controller;

import com.example.a09cinema_backenddevelop.model.entity.SeatDetail;
import com.example.a09cinema_backenddevelop.service.SeatDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/seat")
public class SeatController {
    @Autowired
    private SeatDetailService seatDetailService;

    @GetMapping("/info")
    public SeatDetail getSeatDetailById(@RequestParam("id")long id){
        SeatDetail seatDetail = seatDetailService.findById(id);
        return seatDetail;
    }
}
