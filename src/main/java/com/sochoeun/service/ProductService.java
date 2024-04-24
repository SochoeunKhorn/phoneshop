package com.sochoeun.service;

import com.sochoeun.dto.ImportProductDTO;
import com.sochoeun.entity.Product;
import com.sochoeun.entity.ProductImportHistory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Product create(Product product);
    List<Product> getProducts();
    List<ProductImportHistory> getProductImportHistory();
    Product getProduct(Long id);
    Product getByModelIdAndColorId(Long modelId,Long colorId);
    void importProduct(ImportProductDTO importDTO);
    Map<Integer, String> uploadProduct(MultipartFile file);
}
