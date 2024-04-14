package com.sochoeun.controller;

import com.sochoeun.dto.BrandDTO;
import com.sochoeun.model.Brand;
import com.sochoeun.service.BrandService;
import com.sochoeun.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/brands")
@Slf4j
public class BrandController {

    private final BrandService brandService;
    @PostMapping("/create")
    public ResponseEntity<?> createBrand(@RequestBody BrandDTO brandDTO){
        Brand brand = Mapper.toBrand(brandDTO);
        brand = brandService.create(brand);
        log.info("" + brand);
        return ResponseEntity.ok(Mapper.toBrandDTO(brand));
    }
}
