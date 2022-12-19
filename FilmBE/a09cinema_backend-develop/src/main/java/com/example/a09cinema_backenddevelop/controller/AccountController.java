package com.example.a09cinema_backenddevelop.controller;
import com.example.a09cinema_backenddevelop.model.entity.Account;
import com.example.a09cinema_backenddevelop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/account")
@CrossOrigin("*")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/list")
    public ResponseEntity<Page<Account>> getAllAccount(@RequestParam(defaultValue = "", name = "username") String key_username,
                                                       @PageableDefault(size = 10) Pageable pageable) {

        Page<Account> accounts = accountService.getAllAccount(key_username, pageable);
        if (accounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Account> findAccountById(@PathVariable Long id) {
        Optional<Account> accountOptional = accountService.findByIdOption(id);
        if (!accountOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accountOptional.get(), HttpStatus.OK);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<Account> editAccount(@PathVariable Long id,
                                               @Valid
                                               @RequestBody Account account) {
        Optional<Account> accountOptional = accountService.findByIdOption(id);
        if (!accountOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        account.setId(accountOptional.get().getId());
        return new ResponseEntity<>(accountService.save(account), HttpStatus.OK);
    }

    @GetMapping("/checkExistEmail")
    public boolean checkExistEmail(@RequestParam String email) {
        return accountService.existsByEmail(email);
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<Account> updatePassword(@RequestParam Long id,
                                                  @RequestParam String oldPass,
                                                  @RequestParam String newPass) {

        Account account = accountService.findById(id);
        if(account == null) {
            System.out.println("account không tồn tại");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        // If old password = password in database
        if(passwordEncoder.matches(oldPass, account.getPassword())) {
            System.out.println("password hợp lệ");
            account.setPassword(passwordEncoder.encode(newPass));
            accountService.updatePassword(id, account.getPassword());
        }
        else {
            System.out.println("password không hợp lệ");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Account> save(@RequestParam Long id, @Valid @RequestBody Account accountRequest) {
        System.out.println("Hello");
        if (accountService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        accountRequest.setId(id);
        accountService.updateInfo(accountRequest);
        return new ResponseEntity<>(accountRequest, HttpStatus.OK);
    }

    @GetMapping(value = "employee/list")
    public Page<Account> getAllEmployee(@PageableDefault(5)Pageable pageable, @RequestParam("search") Optional<String> search) {
        if (search.isPresent()) {
            return accountService.searchEmployee(pageable, search.get());
        }
        return accountService.listEmployee(pageable);
    }
    @DeleteMapping(value = "employee/delete/{id}")
    public ResponseEntity<Account> deleteByEmployeeId(@PathVariable Long id) {
        accountService.deleteEmployeeAccountById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
