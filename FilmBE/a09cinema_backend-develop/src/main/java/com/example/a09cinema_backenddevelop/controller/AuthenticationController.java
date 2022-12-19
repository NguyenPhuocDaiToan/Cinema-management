package com.example.a09cinema_backenddevelop.controller;

import com.example.a09cinema_backenddevelop.model.entity.Account;
import com.example.a09cinema_backenddevelop.payload.request.ResetPassRequest;
import com.example.a09cinema_backenddevelop.payload.request.SignInForm;
import com.example.a09cinema_backenddevelop.payload.request.VerifyRequest;
import com.example.a09cinema_backenddevelop.payload.response.JwtResponse;
import com.example.a09cinema_backenddevelop.payload.response.ResponseMessage;
import com.example.a09cinema_backenddevelop.security.accountprincal.AccountPrinciple;
import com.example.a09cinema_backenddevelop.security.jwt.JwtProvider;
import com.example.a09cinema_backenddevelop.service.AccountService;
import com.example.a09cinema_backenddevelop.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    AccountService accountService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        AccountPrinciple accountPrinciple = (AccountPrinciple) authentication.getPrincipal();
        Account account = accountService.findByUsername(signInForm.getUsername());

        return ResponseEntity.ok(
                new JwtResponse(token, accountPrinciple.getUsername(), accountPrinciple.getId(), account, accountPrinciple.getAuthorities())
        );
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> reset(@RequestBody SignInForm signInForm) throws MessagingException, UnsupportedEncodingException {

        if (accountService.existsByUsername(signInForm.getUsername()) != null) {
            accountService.addVerificationCode(signInForm.getUsername());
            return ResponseEntity.ok(new ResponseMessage("Sent email "));
        }

        return ResponseEntity.badRequest().body(new ResponseMessage("Tài khoản không đúng"));
    }

    @PostMapping("/verify-password")
    public ResponseEntity<?> VerifyPassword(@RequestBody VerifyRequest code) {
        Boolean isVerified = accountService.findAccountByVerificationCodeToResetPassword(code.getCode());
        if (isVerified) {
            return ResponseEntity.ok(new ResponseMessage("accepted"));
        } else {
            return ResponseEntity.ok(new ResponseMessage("error"));
        }
    }

    @PostMapping("/do-reset-password")
    public ResponseEntity<?> doResetPassword(@RequestBody ResetPassRequest resetPassRequest) {
        accountService.saveNewPassword(
                passwordEncoder.encode(resetPassRequest.getPassword()),
                resetPassRequest.getCode()
        );
        return ResponseEntity.ok(new ResponseMessage("success"));
    }

    @PostMapping("/add")
    public ResponseEntity<Account> addAccount(@Valid @RequestBody Account account, BindingResult bindingResult) {
        System.out.println(account.getEmail());
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
        if (accountService.existsByEmail(account.getEmail())) {
            System.out.println("Email đã được đăng kí");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (accountService.existsByUsername(account.getUsername())){
            System.out.println("Username đã đăng kí");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String passWord = account.getPassword();
        account.setPassword(passwordEncoder.encode(passWord));
        Account newAccount = accountService.saveAccount(account);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }
}
