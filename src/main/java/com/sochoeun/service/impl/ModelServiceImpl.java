package com.sochoeun.service.impl;

import com.sochoeun.entity.Brand;
import com.sochoeun.entity.Model;
import com.sochoeun.exception.NotFoundException;
import com.sochoeun.repository.ModelRepository;
import com.sochoeun.service.BrandService;
import com.sochoeun.service.ModelService;
import com.sochoeun.service.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final BrandService brandService;
    @Override
    public Model save(Model model) {
        return modelRepository.save(model);
    }

    @Override
    public Page<Model> findAllByBrandIdPageable(Integer brandId, Integer pageNo, Integer pageSize) {
        Pageable pageable = PageUtil.getPageable(pageSize,pageNo);
        return modelRepository.findAllByBrandId(brandId,pageable);
    }

    @Override
    public List<Model> findAll() {
        return modelRepository.findAll();
    }

    @Override
    public Model findId(Long id) {
        return modelRepository.findById(id).orElseThrow(()->new NotFoundException("Model",id));
    }

    @Override
    public Model update(Long id, Model model) {
        Model update = findId(id);
        Long brandId = model.getBrand().getId();
        brandService.getById(brandId);
        update.setName(model.getName());
        update.getBrand().setId(brandId);
        return modelRepository.save(update);
    }

    @Override
    public void delete(Long id) {
        Model isDelete = findId(id);
        modelRepository.delete(isDelete);
    }


}
