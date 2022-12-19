package com.example.a09cinema_backenddevelop.model.entity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
//@JsonIdentityInfo(generator= JSOGGenerator.class)
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @JsonManagedReference("time_seatDetail")
    @OneToMany(mappedBy = "time", cascade = CascadeType.ALL)
    private List<SeatDetail> seatDetail;

    private String timeShow;

    public Time() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<SeatDetail> getSeatDetail() {
        return seatDetail;
    }

    public void setSeatDetail(List<SeatDetail> seatDetail) {
        this.seatDetail = seatDetail;
    }

    public String getTimeShow() {
        return timeShow;
    }

    public void setTimeShow(String timeShow) {
        this.timeShow = timeShow;
    }
}
