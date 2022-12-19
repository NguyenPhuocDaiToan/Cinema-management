package com.example.a09cinema_backenddevelop.repository;

import com.example.a09cinema_backenddevelop.dto.StatisticFilm;
import com.example.a09cinema_backenddevelop.model.entity.SeatDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmStatsticRepository extends JpaRepository<SeatDetail, Long> {

    @Query(value = "select film.name , count(seat_detail.film_id) as numbersTicket , sum(booking.total_price) as money " +
            "from film " +
            "inner join seat_detail " +
            "on film.id = seat_detail.film_id " +
            "inner join booking  " +
            "on seat_detail.booking_id = booking.id " +
            "where booking.day_time_booking = CURDATE() " +
            "group by film.name order by money DESC;", nativeQuery = true)
    List<StatisticFilm> statisticalByCurrentDay();

    @Query(value = "select film.name , count(seat_detail.film_id) as numbersTicket , sum(booking.total_price) money " +
            "from film " +
            "inner join seat_detail " +
            "on film.id = seat_detail.film_id " +
            "inner join booking  " +
            "on seat_detail.booking_id = booking.id " +
            "where booking.day_time_booking = CURDATE() " +
            "group by film.name order by money DESC", nativeQuery = true,
            countQuery = "select count(*) from ( select film.name , count(seat_detail.film_id) as numbersTicket , sum(booking.total_price) money " +
                    "            from film " +
                    "            inner join seat_detail" +
                    "            on film.id = seat_detail.film_id" +
                    "            inner join booking " +
                    "            on seat_detail.booking_id = booking.id " +
                    "            where booking.day_time_booking = CURDATE() " +
                    "            group by film.name order by money DESC) abc ")
    Page<StatisticFilm> findAllAndPage(Pageable pageable);

    @Query(value = "select film.name , count(seat_detail.film_id) as numbersTicket , sum(booking.total_price) money " +
            "from film " +
            "inner join seat_detail " +
            "on film.id = seat_detail.film_id " +
            "inner join booking  " +
            "on seat_detail.booking_id = booking.id " +
            "where booking.day_time_booking = CURDATE() ", nativeQuery = true)
    List<StatisticFilm> findAllStatisticFilm();

}
