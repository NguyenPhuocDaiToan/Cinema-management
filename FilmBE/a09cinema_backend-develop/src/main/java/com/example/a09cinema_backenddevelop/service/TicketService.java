package com.example.a09cinema_backenddevelop.service;

import com.example.a09cinema_backenddevelop.model.BookedTicket;
import com.example.a09cinema_backenddevelop.model.entity.SeatDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketService {
    Page<BookedTicket> getBookedTicket(Pageable pageable, Long accountId);

    SeatDetail findById(Long id);
    void deleteTicket(Long id);
}
