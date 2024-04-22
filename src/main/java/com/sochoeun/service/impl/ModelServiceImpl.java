package com.sochoeun.service.impl;

import com.sochoeun.dto.ModelDTO;
import com.sochoeun.dto.PageDTO;
import com.sochoeun.entity.Brand;
import com.sochoeun.entity.Model;
import com.sochoeun.repository.ModelRepository;
import com.sochoeun.service.ModelService;
import com.sochoeun.service.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    @Override
    public Model save(Model model) {
        return modelRepository.save(model);
    }

    @Override
    public List<Model> findByBrandId(Integer brandId) {
        List<Model> byBrandId = modelRepository.findByBrandId(brandId);
        return byBrandId;
    }

    @Override
    public Page<Model> findAllByBrandIdPageable(Integer brandId, Integer pageNo,Integer pageSize) {
        Pageable pageable = PageUtil.getPageable(pageSize,pageNo);
        Page<Model> page = modelRepository.findAllByBrandId(brandId,pageable);
        return page;
    }


}
