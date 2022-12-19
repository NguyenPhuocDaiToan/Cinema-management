package com.example.a09cinema_backenddevelop.repository;

import com.example.a09cinema_backenddevelop.model.BookedTicket;
import com.example.a09cinema_backenddevelop.model.entity.SeatDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TicketRepository extends JpaRepository<SeatDetail, Long> {

    //    @Query(value = "select new com.example.a09cinema_backenddevelop.model.BookedTicket(sd.id, f.name, b.dayTimeBooking,b.totalPrice, b.received) from SeatDetail sd  " +
//            "join sd.booking b  join sd.film f where b.isDeleted <> true ")
    @Query(value = "select new com.example.a09cinema_backenddevelop.model.BookedTicket(sd.id, f.name, b.dayTimeBooking,b.totalPrice, b.received) from SeatDetail sd  " +
            "join sd.booking b  join sd.film f where b.isDeleted <> true and b.account.id= :accountId")
    Page<BookedTicket> getBookedTicket(Pageable pageable, Long accountId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Booking SET is_deleted = true where id=:id", nativeQuery = true)
    void deleteTicket(Long id);
}

