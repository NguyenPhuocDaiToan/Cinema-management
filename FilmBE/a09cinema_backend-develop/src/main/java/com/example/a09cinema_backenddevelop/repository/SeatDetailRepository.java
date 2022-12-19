package com.example.a09cinema_backenddevelop.repository;
import com.example.a09cinema_backenddevelop.model.dto.SeatDetailDto;
import com.example.a09cinema_backenddevelop.model.entity.SeatDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

@Repository
public interface SeatDetailRepository extends JpaRepository<SeatDetail,Long> {
    @Query(nativeQuery = true,value = "SELECT * FROM a0921i1_cinema.seat_detail where film_id=?1 and (seat_detail.date_show between date(now()) and date(now())+ interval 4 day) group  by date_show order  by date_show")
    List<SeatDetail> getAllSeatDetailByIdFilm(Long id);

    @Query(nativeQuery = true,value = "SELECT seat_detail.id FROM seat_detail " +
            "inner join film on seat_detail.film_id=film.id " +
            "where film.`name` = ?1 and seat_detail.date_show = ?2 and seat_detail.time_id=?3 and (seat_detail.date_show between date(now()) and date(now())+ interval 4 day) " +
            "group by seat_detail.date_show")
    SeatDetailDto getIdSeatDetailByBookingCondition(String name, String date_show, long time_id);


    // lấy ra thông tin ghế có id
    @Query(nativeQuery = true, value = "select * from seat_detail where seat_detail.id=?1")
    SeatDetail getInfoSeatDetailById(long id);

    // lấy ra danh sách ghế phù hợp với phim đã chọn để chọn ghế
    @Query(nativeQuery = true, value = "SELECT * FROM a0921i1_cinema.seat_detail where seat_detail.date_show=?1 and seat_detail.film_id=?2 and seat_detail.room_id=?3 and seat_detail.time_id=?4;")
    List<SeatDetail> getAllSeatDetailByIdToChoose(Date date_show, long film_id, long room_id,long time_id);

}
