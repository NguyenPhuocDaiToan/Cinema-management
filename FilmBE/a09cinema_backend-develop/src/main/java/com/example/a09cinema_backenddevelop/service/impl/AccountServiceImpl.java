package com.example.a09cinema_backenddevelop.service.impl;

import com.example.a09cinema_backenddevelop.model.entity.Account;
import com.example.a09cinema_backenddevelop.repository.AccountRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import  com.example.a09cinema_backenddevelop.service.AccountService;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findByIdOption(long id) {
        return accountRepository.findAccountId(id);
    }

    @Override
    public Account findById(long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Page<Account> getAllAccount(String username,
                                       Pageable pageable) {
        return accountRepository.findAll(username, pageable);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }


    @Override
    public void updatePassword(Long id, String newPassword) {
        accountRepository.updatePassword(id, newPassword);
    }

    @Override
    public void addVerificationCode(String username) throws MessagingException, UnsupportedEncodingException {
        String code = RandomString.make(10);
        accountRepository.addVerificationCode(code, username);
        Account account = accountRepository.findAccountByVerificationCode(code);
        this.sendVerificationEmailForResetPassWord(account.getFullname(), code, account.getEmail());
    }

    @Override
    public Boolean findAccountByVerificationCodeToResetPassword(String code) {
        Account account = accountRepository.findAccountByVerificationCode(code);
        if (account == null || account.isEnable()) {
            return false;
        } else {
            account.setEnabled(true);
            account.setVerificationCode(null);
            accountRepository.save(account);
            return true;
        }
    }

    @Override
    public void saveNewPassword(String password, String code) {
        accountRepository.saveNewPassword(password,code);
    }

    public void sendVerificationEmailForResetPassWord(String userName, String randomCode, String email)
            throws MessagingException, UnsupportedEncodingException {
        String subject = "Hãy xác thực email của bạn";
        String mailContent = "";
        String confirmUrl = "http://localhost:4200/verify-reset-password?code=" + randomCode;

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setFrom("luongquanghuynh264@gmail.com","A0921I1");
        helper.setTo(email);
        helper.setSubject(subject);
        mailContent = "<p sytle='color:red;'>Xin chào " + userName + " ,<p>" + "<p> Nhấn vào link sau để xác thực email của bạn:</p>" +
                "<h3><a href='" + confirmUrl + "'>Link Xác thực( nhấn vào đây)!</a></h3>" +
                "<p>NHÓM DỰ ÁN</p>";
        helper.setText(mailContent, true);
        javaMailSender.send(message);
    }

    @Override
    public void updateInfo(Account ac) {
        accountRepository.updateInfo(ac.getId(), ac.getFullname(), ac.getBirthday(),
                ac.getGender(), ac.getEmail(), ac.getIdCard(), ac.getPhone(), ac.getAddress());
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return accountRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }


//    Hiển thị, tìm kiếm và xoá Nhân Viên
    @Override
    public Page<Account> searchEmployee(Pageable pageable, String s) {
        return accountRepository.getSearchAllEmployee(pageable,s);
    }
    @Override
    public Page<Account> listEmployee(Pageable pageable) {
        return accountRepository.getAllEmployee(pageable);
    }
    @Override
    public void deleteEmployeeAccountById(Long id) {
        accountRepository.deleteEmployeeAccountById(id);
    }
}
