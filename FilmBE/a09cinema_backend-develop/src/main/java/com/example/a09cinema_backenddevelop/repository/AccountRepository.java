package com.example.a09cinema_backenddevelop.repository;

import com.example.a09cinema_backenddevelop.model.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "select * from account where id = :id", nativeQuery = true)
    Optional<Account> findAccountId(@Param("id") long id);

    @Query(value= "select * from a0921i1_cinema.account " +
            "left join a0921i1_cinema.account_role " +
            "on a0921i1_cinema.account.id = a0921i1_cinema.account_role.account_id " +
            "where account_role.role_id = 1 and account.fullid like concat('%', :fullname , '%')",
           countQuery = "select count(*) from a0921i1_cinema.account left join a0921i1_cinema.account_role on a0921i1_cinema.account.id = a0921i1_cinema.account_role.account_id where account_role.role_id = 1 and account.fullname like concat('%', :fullname , '%')",
            nativeQuery = true)
    Page<Account> findAll(@Param("fullname") String username, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Account SET fullname = :fullName, birthday = :date, gender = :gender, " +
            "email = :email, id_card = :idCard, phone = :phone, address = :address" +
            " WHERE id = :id", nativeQuery = true)
    void updateInfo(Long id, String fullName, LocalDate date, String gender, String email, String idCard, String phone, String address);

    Account findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query(value ="update account set verification_code = :code where username = :username",nativeQuery = true)
    void addVerificationCode(@Param("code") String code,@Param("username") String username);

    @Query(value = "select * from account where verification_code = :code",nativeQuery = true)
    Account findAccountByVerificationCode(@Param("code") String code);

    @Modifying
    @Transactional
    @Query(value = " update account set password = :password where verification_code= :code",nativeQuery = true)
    void saveNewPassword(@Param("password") String password,@Param("code") String code);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Account SET password = :newPassword WHERE id = :id", nativeQuery = true)
    void updatePassword(Long id, String newPassword);

    // (Hiển thị, tìm kiếm và xoá Nhân Viên
    @Query(
            value = "select * from account inner join account_role on account.id= account_role.account_id WHERE account.deleted=0 and account_role.role_id=2",
            countQuery = "select COUNT(*) from account inner join account_role on account.id= account_role.account_id where account.deleted=0 and account_role.role_id=2"
            ,nativeQuery = true)
    Page<Account> getAllEmployee(Pageable pageable);
    @Query(
            value = "select * from account inner join account_role on account.id= account_role.account_id " +
                    "where account.deleted=0 and account_role.role_id=2 and (account.id like concat(:search,'%') " +
                    "or account.fullname like concat('%',:search,'%') or account.id_card like concat(:search,'%') " +
                    "or account.email like concat(:search,'%') or account.phone like concat(:search,'%') " +
                    "or account.address like concat('%',:search,'%'))",
            countQuery = "select COUNT(*) from account inner join account_role on account.id= account_role.account_id " +
                    "where account.deleted=0 and account_role.role_id=2 and(account.id like concat(:search,'%') " +
                    "or account.fullname like concat('%',:search,'%') or account.id_card like concat(:search,'%')  " +
                    "or account.email like concat(:search,'%') or account.phone like concat(:search,'%') " +
                    "or account.address like concat('%',:search,'%'))"
            ,nativeQuery = true)
    Page<Account> getSearchAllEmployee(Pageable pageable, String search);

    @Transactional
    @Modifying
    @Query(value = "update `account` set account.deleted = true where account.id=?1", nativeQuery = true)
    void deleteEmployeeAccountById(Long id);
    // Hiển thị, tìm kiếm và xoá Nhân Viên)
}
