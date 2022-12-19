package com.example.a09cinema_backenddevelop.service;

import com.example.a09cinema_backenddevelop.model.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import com.example.a09cinema_backenddevelop.model.entity.CategoryFilm;
import java.util.Optional;

public interface FilmService {
    List<Film> findAll();

    Page<Film> findAll(Pageable pageable);

    Film findFilmByID(Long id);

    Page<Film> findSort(Pageable pageable);

    //    Film save(Film film);
    Optional<Film> findById(Long id);

    void deleteById(Long id);

    Page<Film> search(String name, Pageable pageable);

    Film save(Film film);
    List<CategoryFilm> findAllCategoryFilm();
    void saveCategoryFilm(Long categoryId, Long filmId);
    Long findFilmIdByName(String name);

    Film findFilmById(long id);
    List<Film> findAllListFilm();
}
