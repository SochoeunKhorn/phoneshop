package com.sochoeun.controller;

import com.sochoeun.Mapper.ProductMapper;
import com.sochoeun.dto.ImportProductDTO;
import com.sochoeun.dto.PriceDTO;
import com.sochoeun.dto.ProductDTO;
import com.sochoeun.entity.Product;
import com.sochoeun.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Validated
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDTO dto){
        Product product = productMapper.toProduct(dto);
        productService.create(product);
        return ResponseEntity.ok(product);
    }
    @PostMapping("/upload/excel")
    public ResponseEntity<?> uploadProduct(@RequestParam("file")MultipartFile file){
        Map<Integer, String> integerStringMap = productService.uploadProduct(file);
        return ResponseEntity.ok(integerStringMap);
    }
    @GetMapping()
    public ResponseEntity<?> getProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }
    @PostMapping("/importProduct")
    public ResponseEntity<?> importProduct(@RequestBody ImportProductDTO dto){
        productService.importProduct(dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/importProduct")
    public ResponseEntity<?> getProductImportHistory(){
        return ResponseEntity.ok(productService.getProductImportHistory());
    }
    @PostMapping("/{id}/setSalePrice")
    public ResponseEntity<?> setSalePrice( @PathVariable("id") Long productId,@Valid @RequestBody PriceDTO priceDTO){
        productService.setSalePrice(productId,priceDTO.getPrice());
        return ResponseEntity.ok(productService.getProduct(productId));
    }
}
