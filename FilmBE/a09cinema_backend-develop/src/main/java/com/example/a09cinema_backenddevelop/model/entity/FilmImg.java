package com.example.a09cinema_backenddevelop.model.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@JsonIdentityInfo(generator= JSOGGenerator.class)
public class FilmImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String urlImg;

    @JsonBackReference("film_imgs")
    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Film film;

    public FilmImg() {
    }

    public FilmImg(long id, String urlImg, Film film) {
        this.id = id;
        this.urlImg = urlImg;
        this.film = film;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
