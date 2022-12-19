package com.example.a09cinema_backenddevelop.repository;
import com.example.a09cinema_backenddevelop.dto.SelectAllBookingDto;
import com.example.a09cinema_backenddevelop.dto.SelectBookingDetail;
import com.example.a09cinema_backenddevelop.model.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerBookingRepository extends JpaRepository<Booking,Long> {
    @Query(value =
            "Select b.id as booking_id,b.booking_code, a.account_code, a.fullname, a.id_card, a.phone, f.name, t.time_show from booking b inner join `account` a on b.account_id=a.id inner join seat_detail sd on sd.booking_id=b.id inner join `time` t on t.id=sd.time_id inner join film f on f.id=sd.film_id where (b.booking_code like concat('%',:key,'%') or a.account_code like concat('%',:key ,'%') or a.fullname like concat('%',:key ,'%') or a.id_card like concat('%',:key ,'%') or a.phone like concat('%',:key ,'%') or  f.name like concat('%',:key ,'%') or t.time_show like concat('%',:key ,'%'))and b.received=0 ",
            nativeQuery = true,
            countQuery = "select count(*) from (Select b.id as booking_id,b.booking_code, a.account_code, a.fullname, a.id_card, a.phone, f.name, t.time_show from booking b inner join `account` a on b.account_id=a.id inner join seat_detail sd on sd.booking_id=b.id inner join `time` t on t.id=sd.time_id inner join film f on f.id=sd.film_id where (b.booking_code like concat('%',:key,'%') or a.account_code like concat('%',:key ,'%') or a.fullname like concat('%',:key ,'%') or a.id_card like concat('%',:key ,'%') or a.phone like concat('%',:key ,'%') or  f.name like concat('%',:key ,'%') or t.time_show like concat('%',:key ,'%'))and b.received=0  ) abc " )
    Page<SelectAllBookingDto> findAll(@Param("key") String key, Pageable pageable);
//    Booking findBookingByBookingCode(String bookingCode);
    @Query(value = "select b.id, b.point_exchange, f.name,sd.date_show,t.time_show,s.name as seat_name,s.vip,s.price,b.total_price,fi.url_img, a.account_code,a.fullname, a.id_card,a.phone,a.total_point\n" +
            "from booking b inner join `account` a on b.account_id=a.id inner join seat_detail sd on sd.booking_id=b.id inner join `time` t on t.id=sd.time_id inner join film f on f.id=sd.film_id\n" +
            "inner join seat s on sd.seat_id=s.id inner join film_img fi on fi.film_id=f.id where b.id =:id", nativeQuery = true)
    Optional<SelectBookingDetail> selectBookingDetail(@Param("id") Long id);

}
