package com.sochoeun.repository;

import com.sochoeun.entity.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportProductHistoryRepository extends JpaRepository<ProductImportHistory,Long> {

}
