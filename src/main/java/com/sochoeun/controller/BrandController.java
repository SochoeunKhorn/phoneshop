package com.sochoeun.controller;

import com.sochoeun.dto.BrandDTO;
import com.sochoeun.model.Brand;
import com.sochoeun.service.BrandService;
import com.sochoeun.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/brands")
public class BrandController {

    private final BrandService brandService;
    @PostMapping()
    public ResponseEntity<?> createBrand(@RequestBody BrandDTO brandDTO){
        Brand brand = Mapper.toBrand(brandDTO);
        brand = brandService.create(brand);
        return ResponseEntity.ok(Mapper.toBrandDTO(brand));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getBrandById(@PathVariable("id") Integer brandId){
        var brand = brandService.getById(brandId);
        return ResponseEntity.ok(Mapper.toBrandDTO(brand));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer brandId,@RequestBody BrandDTO brandDTO){
        Brand brand = Mapper.toBrand(brandDTO);
        brandService.update(brandId,brand);
        return ResponseEntity.ok(Mapper.toBrandDTO(brand));
    }
}
