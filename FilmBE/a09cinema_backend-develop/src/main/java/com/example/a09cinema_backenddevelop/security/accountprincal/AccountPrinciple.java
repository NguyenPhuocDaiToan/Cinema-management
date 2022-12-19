package com.example.a09cinema_backenddevelop.security.accountprincal;

import com.example.a09cinema_backenddevelop.model.entity.Account;
import com.example.a09cinema_backenddevelop.model.entity.Booking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AccountPrinciple implements UserDetails {
    private static final long serialVersionUID = 1L;
    private long id;
    private Boolean isEnabled;
    private String username;
    private String accountCode;
    @JsonIgnore
    private String password;
    private String fullName;
    private LocalDate birthday;
    private String idCard;
    private String address;
    private String phone;
    private String verificationCode;
    private String email;
    private String gender;
    private int totalPoint;
    private String imageUrl;
    private boolean deleted;
    private boolean enable;
    private String provider;
    private List<Booking> bookings;
    private Collection<? extends GrantedAuthority> roles;

    public AccountPrinciple(long id, Boolean isEnabled, String username, String accountCode, String password, String fullName, LocalDate birthday, String idCard, String address, String phone, String verificationCode, String email, String gender, int totalPoint, String imageUrl, boolean deleted, boolean enable, String provider, List<Booking> bookings, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.isEnabled = isEnabled;
        this.username = username;
        this.accountCode = accountCode;
        this.password = password;
        this.fullName = fullName;
        this.birthday = birthday;
        this.idCard = idCard;
        this.address = address;
        this.phone = phone;
        this.verificationCode = verificationCode;
        this.email = email;
        this.gender = gender;
        this.totalPoint = totalPoint;
        this.imageUrl = imageUrl;
        this.deleted = deleted;
        this.enable = enable;
        this.provider = provider;
        this.bookings = bookings;
        this.roles = roles;
    }

    public static AccountPrinciple build(Account account){
        List<GrantedAuthority> authorities = account.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return new AccountPrinciple(
                account.getId(),
                account.getIsEnabled(),
                account.getUsername(),
                account.getAccountCode(),
                account.getPassword(),
                account.getFullname(),
                account.getBirthday(),
                account.getIdCard(),
                account.getAddress(),
                account.getPhone(),
                account.getVerificationCode(),
                account.getEmail(),
                account.getGender(),
                account.getTotalPoint(),
                account.getImageUrl(),
                account.isDeleted(),
                account.isEnable(),
                account.getProvider(),
                account.getBookings(),
                authorities
        );
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
