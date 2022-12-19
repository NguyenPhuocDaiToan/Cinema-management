package com.example.a09cinema_backenddevelop.repository;
import com.example.a09cinema_backenddevelop.model.dto.TimeDto;
import com.example.a09cinema_backenddevelop.model.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TimeRepository extends JpaRepository<Time,Long> {
    @Query(nativeQuery = true, value = " SELECT `time`.id as time_id,`time`.time_show FROM a0921i1_cinema.time \n " +
            "inner join seat_detail on `time`.id=seat_detail.time_id "+
            "where seat_detail.date_show =?1 and  seat_detail.film_id=?2 "+
            "group by `time`.time_show"
    )
    List<TimeDto> findAllTimeShowBySeatDetail(String date_show, String film_id);
}
