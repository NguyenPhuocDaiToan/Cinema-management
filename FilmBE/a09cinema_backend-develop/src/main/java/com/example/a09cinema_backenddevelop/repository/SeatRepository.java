package com.example.a09cinema_backenddevelop.repository;


import com.example.a09cinema_backenddevelop.model.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
}
