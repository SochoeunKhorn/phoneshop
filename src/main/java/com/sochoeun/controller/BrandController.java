package com.sochoeun.controller;

import com.sochoeun.Mapper.BrandMapper;
import com.sochoeun.dto.BrandDTO;
import com.sochoeun.entity.Brand;
import com.sochoeun.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/brands")
public class BrandController {

    private final BrandService brandService;
    @PostMapping()
    public ResponseEntity<?> createBrand(@RequestBody BrandDTO brandDTO){
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        brand = brandService.create(brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getBrandById(@PathVariable("id") Integer brandId){
        var brand = brandService.getById(brandId);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
    }

    @GetMapping()
    public ResponseEntity<?> getBrands(){
        List<BrandDTO> list = brandService.getBrands()
                .stream()
                .map(BrandMapper.INSTANCE::toBrandDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("filter")
    public ResponseEntity<?> getBrandsByName(@RequestParam("name") String name){
        List<BrandDTO> list = brandService.getBrands(name)
                .stream()
                .map(BrandMapper.INSTANCE::toBrandDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer brandId,@RequestBody BrandDTO brandDTO){
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        brandService.updated(brandId,brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
    }
}
