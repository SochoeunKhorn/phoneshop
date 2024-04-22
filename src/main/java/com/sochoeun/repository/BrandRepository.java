package com.sochoeun.repository;

import com.sochoeun.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long>, JpaSpecificationExecutor<Brand> {
    List<Brand> findByName(String name);
    List<Brand> findByNameIgnoreCase(String name);
    List<Brand> findByNameLike(String name);

    List<Brand> findByNameContaining(String name);

}
