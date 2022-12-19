package com.example.a09cinema_backenddevelop.model.dto;

import java.util.Date;

public interface SelectBookingDetail {
    Long getId();
    String getName();
    Date getDate_Show();
    String getTime_Show();
    String getSeat_Name();
    Integer getVip();
    Double getPrice();
    Double getTotal_Price();
    String getUrl_Img();
    String getAccount_Code();
    String getFullname();
    String getId_Card();
    String getPhone();
    Integer getTotal_Point();
    Integer getPoint_Exchange();
}
