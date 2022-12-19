package com.example.a09cinema_backenddevelop.repository;
import com.example.a09cinema_backenddevelop.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.a09cinema_backenddevelop.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findCategoryById(Long id);

    @Query(value = "SELECT * FROM a0921i1_cinema.category", nativeQuery = true)
    List<Category> findAllCategory();
}
