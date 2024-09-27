package com.example.haibazoshop.repository;

import com.example.haibazoshop.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {
    boolean existsByName(String name);
}