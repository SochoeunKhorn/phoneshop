package com.sochoeun.service;

import com.sochoeun.entity.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    List<Product> getProducts();
}
