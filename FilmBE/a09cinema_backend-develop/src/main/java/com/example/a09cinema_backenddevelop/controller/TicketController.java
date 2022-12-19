package com.example.a09cinema_backenddevelop.controller;

import com.example.a09cinema_backenddevelop.model.BookedTicket;
import com.example.a09cinema_backenddevelop.model.entity.SeatDetail;
//import com.example.a09cinema_backenddevelop.model.entity.Ticket;
import com.example.a09cinema_backenddevelop.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @GetMapping("/getbookedticket")
    public ResponseEntity<Page<BookedTicket>> getBookedTicket(@PageableDefault(5) Pageable pageable,  @RequestParam Long accountId) {
        Page<BookedTicket> list = ticketService.getBookedTicket(pageable, accountId);
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PatchMapping("/deleteTicket/{id}")
    public ResponseEntity<SeatDetail> deleteTicket(@PathVariable Long id ){
        ticketService.deleteTicket(id);
        return new ResponseEntity<SeatDetail>(HttpStatus.OK);
    }
}
