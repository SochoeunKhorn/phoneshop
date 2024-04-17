package com.sochoeun.service.impl;

import com.sochoeun.exception.ApiException;
import com.sochoeun.exception.GlobalException;
import com.sochoeun.exception.NotFoundException;
import com.sochoeun.model.Brand;
import com.sochoeun.repository.BrandRepository;
import com.sochoeun.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

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
    public Brand update(Integer brandId, Brand brand) {
        Brand updatedBrand = getById(brandId);
        updatedBrand.setName(brand.getName());
        return brandRepository.save(updatedBrand);
    }
}
