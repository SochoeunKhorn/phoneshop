package com.sochoeun.service.impl;

import com.sochoeun.Mapper.ProductMapper;
import com.sochoeun.dto.ImportProductDTO;
import com.sochoeun.entity.Product;
import com.sochoeun.entity.ProductImportHistory;
import com.sochoeun.exception.NotFoundException;
import com.sochoeun.repository.ImportProductHistoryRepository;
import com.sochoeun.repository.ProductRepository;
import com.sochoeun.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ImportProductHistoryRepository importProductHistoryRepository;
    private final ProductMapper productMapper;
    @Override
    public Product create(Product product) {
        String name = "%s %s".formatted(product.getModel().getName(),product.getColor().getName());
        product.setName(name);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductImportHistory> getProductImportHistory() {
        return importProductHistoryRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new NotFoundException("Product",id));
    }

    @Override
    public void importProduct(ImportProductDTO importDTO) {
        // update on product
        Product product = getProduct(importDTO.getProductId());
        int availableUnit = 0;
        if (product.getAvailableUnit() != null){
            availableUnit = product.getAvailableUnit();
        }
        product.setAvailableUnit(availableUnit + importDTO.getImportUnit());
        productRepository.save(product);

        // create product history
        ProductImportHistory productImportHistory = productMapper.toProductImportHistory(importDTO, product);
        importProductHistoryRepository.save(productImportHistory);
    }
}
