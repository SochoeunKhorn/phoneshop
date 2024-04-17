package com.sochoeun.service.impl;

import com.sochoeun.exception.NotFoundException;
import com.sochoeun.entity.Brand;
import com.sochoeun.repository.BrandRepository;
import com.sochoeun.service.BrandService;
import com.sochoeun.service.utils.PageUtil;
import com.sochoeun.specification.BrandFilter;
import com.sochoeun.specification.BrandSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    @Override
    public Brand create(Brand brand) {

        return brandRepository.save(brand);
    }

    @Override
    public Brand getById(Integer brandId) {
        /*Optional<Brand> brandOptional = brandRepository.findById(brandId);
        if(brandOptional.isPresent()){
            return brandOptional.get();
        }
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Brand ID : %d NotFound".formatted(brandId));*/
        return brandRepository.findById(brandId)
                /*.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,"Brand ID : %d NotFound".formatted(brandId)));*/
                .orElseThrow(() -> new NotFoundException("Brand",brandId));
    }

    @Override
    public Brand updated(Integer brandId, Brand brand) {
        Brand updatedBrand = getById(brandId);
        updatedBrand.setName(brand.getName());
        return brandRepository.save(updatedBrand);
    }

    @Override
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    @Override
    public List<Brand> getBrands(String name) {
        return brandRepository.findByNameContaining(name);
    }

    /*@Override
    public List<Brand> getBrands(Map<String, String> params) {
        BrandFilter brandFilter = new BrandFilter();

        if(params.containsKey("name")){
            String name = params.get("name");
            brandFilter.setName(name);
        }
        if(params.containsKey("id")){
            String id = params.get("id");
            brandFilter.setId(Integer.parseInt(id));
        }
        BrandSpecification specification =new  BrandSpecification(brandFilter);
        return brandRepository.findAll(specification);
    }*/

    @Override
    public Page<Brand> getBrands(Map<String, String> params) {
        BrandFilter brandFilter = new BrandFilter();

        if(params.containsKey("name")){
            String name = params.get("name");
            brandFilter.setName(name);
        }
        if(params.containsKey("id")){
            String id = params.get("id");
            brandFilter.setId(Integer.parseInt(id));
        }

        int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
        if(params.containsKey(PageUtil.PAGE_LIMIT)){
            pageLimit =Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }

        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        if(params.containsKey(PageUtil.PAGE_NUMBER)){
            pageNumber =Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }
        Pageable pageable = PageUtil.getPageable(pageLimit,pageNumber);
        BrandSpecification specification =new  BrandSpecification(brandFilter);
        Page<Brand> page = brandRepository.findAll(specification, pageable);
        return page;
    }
}
