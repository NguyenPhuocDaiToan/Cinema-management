package com.example.a09cinema_backenddevelop.service.impl;

import com.example.a09cinema_backenddevelop.model.entity.SeatDetail;
import com.example.a09cinema_backenddevelop.repository.TicketRepository;
import com.example.a09cinema_backenddevelop.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Override
    public Page getBookedTicket(Pageable pageable, Long accountId) {
        return ticketRepository.getBookedTicket(pageable,accountId);
    }

    @Override
    public SeatDetail findById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteTicket(id);
    }
}
