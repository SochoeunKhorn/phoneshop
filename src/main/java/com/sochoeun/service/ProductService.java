package com.sochoeun.service;

import com.sochoeun.dto.ImportProductDTO;
import com.sochoeun.entity.Product;
import com.sochoeun.entity.ProductImportHistory;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    List<Product> getProducts();
    List<ProductImportHistory> getProductImportHistory();
    Product getProduct(Long id);
    void importProduct(ImportProductDTO importDTO);
}
