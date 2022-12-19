package com.example.a09cinema_backenddevelop.service;
import com.example.a09cinema_backenddevelop.model.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;




public interface AccountService {
    Page<Account> getAllAccount(String username, Pageable pageable);

    List<Account> findAll();

    Optional<Account> findByIdOption(long id);
    Account findById(long id);
    void updatePassword(Long id, String newPassword);
    void updateInfo(Account account);
    Account findByUsername(String  username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Account save(Account account);
    Account saveAccount(Account account);
    void addVerificationCode(String username) throws MessagingException, UnsupportedEncodingException;
    Boolean findAccountByVerificationCodeToResetPassword(String code);
    void saveNewPassword(String password,String code);


//  Hiển thị, tìm kiếm và xoá Nhân viên
    Page<Account> listEmployee(Pageable pageable);
    Page<Account> searchEmployee(Pageable pageable, String s);
    void deleteEmployeeAccountById(Long id);

//    Page<Account> getAllAccount(Pageable pageable);


}
