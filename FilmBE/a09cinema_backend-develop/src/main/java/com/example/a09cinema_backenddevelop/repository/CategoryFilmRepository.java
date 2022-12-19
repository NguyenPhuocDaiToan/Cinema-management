package com.example.a09cinema_backenddevelop.repository;

import com.example.a09cinema_backenddevelop.model.entity.CategoryFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Repository
public interface CategoryFilmRepository extends JpaRepository<CategoryFilm, Long> {
    @Query(value = "select * from category_film", nativeQuery = true)
    List<CategoryFilm> findAllCategoryFilm();

    @Query(value="select count(*) from a0921i1_cinema.category_film",nativeQuery=true)
    Long findNumberOfRecord();

//    @Query(value = "SELECT * FROM Blog where author like concat('%',:author,'%')  and category_id like concat('%',:category,'%')",
//            nativeQuery = true,
//            countQuery = "select count(*) from ( SELECT * FROM blog where author like concat('%',:author,'%')  and category_id like concat('%',:category,'%') ) abc ")
//    Page<Blog> findByAuthorAndCategory(@Param("author") String author, @Param("category") String category, Pageable pageable);

    @Modifying
    @Query(value="INSERT INTO `a0921i1_cinema`.`category_film` (`id`, `category_id`,`film_id`)VALUES (:id,:categoryId,:filmId)", nativeQuery= true)
    @Transactional
    void saveCategoryFilm(@Param("id") Long id,@Param("categoryId") Long categoryId,@Param("filmId") Long filmId);

}
