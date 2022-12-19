package com.example.a09cinema_backenddevelop.repository;
import com.example.a09cinema_backenddevelop.dto.StatisticAccount;
import com.example.a09cinema_backenddevelop.model.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountStatisticRepository extends JpaRepository<Account, Long> {

    @Query(value = "select a.fullname, a.account_code, count(b.booking_code) numberTicket, sum(b.total_price) money, sum(a.total_point) point\n" +
            "            from account as a join booking as b on a.id = b.account_id" +
            "            where b.day_time_booking = curdate() group by b.booking_code " +
            "            order by money DESC;", nativeQuery = true)
    List<StatisticAccount> statisticalByAccountDay();

    @Query(value = "select a.fullname, a.account_code, count(b.booking_code) as numberTicket, sum(b.total_price) as money, sum(a.total_point) as point" +
            "            from account as a join booking as b on a.id = b.account_id" +
            "            where b.day_time_booking = curdate() group by b.booking_code order by money DESC", nativeQuery = true,
            countQuery = "select count(*) from ( select a.fullname, a.account_code, count(b.booking_code) numberTicket, sum(b.total_price) money, sum(a.total_point) point" +
                    "           from account as a join booking as b on a.id = b.account_id " +
                    "           where b.day_time_booking = curdate() group by b.booking_code order by money DESC) abc ")
    Page<StatisticAccount> findAllAndPageAccount(Pageable pageable);

    @Query(value = "select sum(b.total_price) money" +
            "            from booking as b" +
            "            where b.day_time_booking = curdate();", nativeQuery = true)
    List<StatisticAccount> findAllStatisticAccount();

}
