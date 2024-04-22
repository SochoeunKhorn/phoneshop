package com.sochoeun.controller;

import com.sochoeun.Mapper.BrandMapper;
import com.sochoeun.dto.BrandDTO;
import com.sochoeun.dto.PageDTO;
import com.sochoeun.entity.Brand;
import com.sochoeun.entity.Model;
import com.sochoeun.service.BrandService;
import com.sochoeun.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/brands")
public class BrandController {

    private final BrandService brandService;
    private final ModelService modelService;
    @PostMapping()
    public ResponseEntity<?> createBrand(@RequestBody BrandDTO brandDTO){
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        brand = brandService.create(brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long brandId,@RequestBody BrandDTO brandDTO){
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        brandService.updated(brandId,brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getBrandById(@PathVariable("id") Long brandId){
        var brand = brandService.getById(brandId);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
    }
    @GetMapping("/{id}/models")
    public ResponseEntity<?> getAllBrandById(@PathVariable("id") Integer brandId,
                                             @RequestParam(value = "_pageNo",required = false,defaultValue = "0") Integer pageNo,
                                             @RequestParam(value = "_pageSize",required = false,defaultValue = "5") Integer pageSize
    ){
        Page<Model> pages = modelService.findAllByBrandIdPageable(brandId, pageNo, pageSize);
        PageDTO pageDTO = new PageDTO(pages);
        return ResponseEntity.ok(pageDTO);
    }

    /*@GetMapping()
    public ResponseEntity<?> getBrands(){
        List<BrandDTO> list = brandService.getBrands()
                .stream()
                .map(BrandMapper.INSTANCE::toBrandDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }*/

    @GetMapping("filter")
    public ResponseEntity<?> getBrandsByName(@RequestParam("name") String name){
        List<BrandDTO> list = brandService.getBrands(name)
                .stream()
                .map(BrandMapper.INSTANCE::toBrandDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    /*@GetMapping()
    public ResponseEntity<?> getBrandsSpecification(@RequestParam Map<String,String> params){
        List<BrandDTO> list = brandService.getBrands(params)
                .stream()
                .map(BrandMapper.INSTANCE::toBrandDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }*/

    @GetMapping()
    public ResponseEntity<?> getBrands(@RequestParam Map<String,String> params){
        Page<Brand> page = brandService.getBrands(params);
        PageDTO pageDTO = new PageDTO(page);
        return ResponseEntity.ok(pageDTO);
    }

}
