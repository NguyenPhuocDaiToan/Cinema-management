package com.example.a09cinema_backenddevelop.repository;
import com.example.a09cinema_backenddevelop.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
