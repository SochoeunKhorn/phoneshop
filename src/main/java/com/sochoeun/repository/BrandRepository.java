package com.sochoeun.repository;

import com.sochoeun.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {
    List<Brand> findByName(String name);
    List<Brand> findByNameIgnoreCase(String name);

    List<Brand> findByNameContaining(String name);

}
