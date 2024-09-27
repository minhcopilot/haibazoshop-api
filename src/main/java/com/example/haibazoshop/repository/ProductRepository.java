package com.example.haibazoshop.repository;

import com.example.haibazoshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //find product by name
    boolean existsByName(String name);
    Page<Product> findAllByIsDeletedFalse(Pageable pageable);
    Optional<Product> findByIdAndIsDeletedFalse(Long id);
    boolean existsByNameAndIsDeletedFalse(String name);

}
