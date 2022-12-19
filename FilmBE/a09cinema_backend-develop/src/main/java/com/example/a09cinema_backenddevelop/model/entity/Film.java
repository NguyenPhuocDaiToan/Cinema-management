
package com.example.a09cinema_backenddevelop.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
//@JsonIdentityInfo(generator= JSOGGenerator.class)
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private Date startDate;
    private Date endDate;
    private String actor;
    private String director;
    private String duration;
    private String trailer;
    private String studioName;
    private String content;
    private String version;
    private String img;
    private boolean isDeletedFlag = false;

    @JsonManagedReference("film_imgs")
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<FilmImg> filmImgs;
    @JsonManagedReference("film_seatDetail")
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<SeatDetail> seatDetails;
    @JsonManagedReference("film_category")
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<CategoryFilm> categoryFilms;


    public Film() {
    }


    public Film(long id, String name, Date startDate, Date endDate, String actor, String director, String duration, String trailer, String studioName, List<FilmImg> filmImgs, List<SeatDetail> seatDetails, List<CategoryFilm> categoryFilms) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.actor = actor;
        this.director = director;
        this.duration = duration;
        this.trailer = trailer;
        this.studioName = studioName;
        this.filmImgs = filmImgs;
        this.seatDetails = seatDetails;
        this.categoryFilms = categoryFilms;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public List<FilmImg> getFilmImgs() {
        return filmImgs;
    }

    public void setFilmImgs(List<FilmImg> filmImgs) {
        this.filmImgs = filmImgs;
    }
    public List<SeatDetail> getSeatDetails() {
        return seatDetails;
    }

    public void setSeatDetails(List<SeatDetail> seatDetails) {
        this.seatDetails = seatDetails;
    }

    public List<CategoryFilm> getCategoryFilms() {
        return categoryFilms;
    }
    public void setCategoryFilms(List<CategoryFilm> categoryFilms) {
        this.categoryFilms = categoryFilms;
    }
}
